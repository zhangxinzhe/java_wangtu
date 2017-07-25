/*
 * @(#)upload.java    Created on 2015-3-21
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.filesystem.upload;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.filesystem.util.BlockNoteUtil;
import net.zdsoft.chnmaster.filesystem.util.UploadFileUtil;

/**
 * 分块上传
 *
 * @author zhangxz
 * @version $Revision: 1.0 $, $Date: 2015年11月17日 下午4:55:09 $
 */
public class BlockUpload {
    Logger log = LoggerFactory.getLogger(BlockUpload.class);

    private JSONObject clientJson;// 客户端的json
    private JSONObject noteJson;// 日志的json
    private String filePath;// 文件上传路径
    private String saveToken;// 文件的md5值
    private String noteKey;// 对应文件日志的key
    private Integer[] status;// 分块上传状态数组
    private InputStream is;// 上传分块流

    /**
     * 上传分块
     *
     * @param clientJson
     * @param is
     * @return
     */
    public JSONObject upload(JSONObject clientJson, InputStream is) {
        this.clientJson = clientJson;
        this.is = is;

        // 上传步骤
        int step = clientJson.getIntValue("step");

        // 文件md5值
        saveToken = clientJson.getString(step == 1 ? "file_hash" : "save_token");
        // 日志的key
        String uniqControl = clientJson.getString("uniqControl");
        noteKey = BlockNoteUtil.createNoteKey(clientJson.getString("path"), saveToken, uniqControl);
        // 日志
        noteJson = BlockNoteUtil.getNote(noteKey);
        // 正式的路径
        filePath = clientJson.getString("path");
        if (noteJson != null) {
            filePath = noteJson.getString("path");
        }

        try {
            // 开始上传
            if (step == 1) {
                return step1();
            }

            // 分块上传
            if (step == 2) {
                return step2();
            }

            // 结束上传
            return step3();
        }
        catch (Exception e) {
            log.error("上传失败！", e);
            e.printStackTrace();

            // 返回错误信息
            JSONObject returnJson = new JSONObject();
            returnJson.put("code", 400);
            returnJson.put("message", "上传失败，请重新上传！");
            return returnJson;
        }
        finally {
            // 保存notejson
            if (noteJson != null && step != 3) {// 最后一步完成上传，不用再保存记录
                if (status != null) {
                    noteJson.put("status", status);
                }
                BlockNoteUtil.putNote(noteKey, noteJson);
            }
        }
    }

    /**
     * 开始上传
     *
     * @throws Exception
     */
    private JSONObject step1() throws Exception {
        // 验证文件信息有效性，等到日志json
        noteJson = validateFileInfoForFirstStep(noteKey, clientJson);
        noteJson.put("save_token", saveToken);

        // 状态设置为0
        int file_blocks = noteJson.getInteger("file_blocks");
        if (noteJson.get("status") == null) {
            status = new Integer[file_blocks];
            for (int i = 0; i < status.length; i++) {
                status[i] = 0;
            }
            noteJson.put("status", status);
        }

        // 设置返回结果
        JSONObject returnJson = new JSONObject();
        returnJson.put("code", 200);
        returnJson.put("save_token", saveToken);
        returnJson.put("blocks", file_blocks);
        returnJson.put("status", noteJson.get("status"));
        returnJson.put("expired_at", noteJson.getLongValue("expiration"));
        return returnJson;
    }

    /**
     * 上传分块
     *
     * @throws Exception
     */
    private JSONObject step2() throws Exception {
        status = noteJson.getJSONArray("status").toArray(new Integer[] {});
        // 开始写入的位置
        int block_index = clientJson.getInteger("block_index");
        long block_size = noteJson.getInteger("block_size");
        long begin = block_index * block_size;
        try {
            // 保存文件
            UploadFileUtil.randomWrite(is, begin, filePath);

            // 将该模块的状态置为成功
            long file_size = UploadFileUtil.getFileLength(filePath);
            if (file_size % block_size == 0 || file_size == noteJson.getLong("file_size")) {
                status[block_index] = 1;
            }
            // 文件当前大小大于应该大小
            if (file_size > noteJson.getLong("file_size")) {
                throw new RuntimeException("文件当前大小大于应该大小");
            }

            // 创建定时器
            UploadTimer.createClearTimer();
        }
        catch (Exception e) {
            // 删除日志
            BlockNoteUtil.removeNote(noteKey);
            // 删除文件
            if (UploadFileUtil.fileExists(filePath)) {
                UploadFileUtil.deleteFile(filePath);
            }
            noteJson = null;
            throw e;
        }

        // 设置返回结果
        JSONObject returnJson = new JSONObject();
        returnJson.put("code", 200);
        returnJson.put("save_token", saveToken);
        returnJson.put("blocks", noteJson.get("file_blocks"));
        returnJson.put("status", status);
        returnJson.put("expired_at", noteJson.get("expiration"));
        return returnJson;
    }

    /**
     * 结束上传
     *
     * @throws Exception
     */
    private JSONObject step3() throws Exception {
        status = noteJson.getJSONArray("status").toArray(new Integer[] {});
        // 复制到正式文件
        long shouldSize = noteJson.getLong("file_size");
        long realSize = UploadFileUtil.getFileLength(filePath);
        // 集群环境下，文件最后一块上传到别的tomcat，导致文件获取大小不对，通过写进行刷新文件信息
        if (shouldSize != realSize) {
            UploadFileUtil.randomWrite(new byte[] {}, realSize, filePath);
            realSize = UploadFileUtil.getFileLength(filePath);
        }
        if (shouldSize != realSize) {
            // 删除日志
            BlockNoteUtil.removeNote(noteKey);
            // 删除文件
            if (UploadFileUtil.fileExists(filePath)) {
                UploadFileUtil.deleteFile(filePath);
            }
            noteJson = null;
            throw new Exception("文件异常[文件应该大小：" + shouldSize + "，文件实际大小：" + realSize + "]");
        }

        // 移除记录
        BlockNoteUtil.removeNote(noteKey);

        // 设置返回结果
        JSONObject returnJson = new JSONObject();
        returnJson.put("code", 200);
        returnJson.put("save_token", saveToken);
        returnJson.put("blocks", noteJson.get("file_blocks"));
        returnJson.put("status", status);
        returnJson.put("expired_at", noteJson.get("expiration"));
        returnJson.put("path", noteJson.getString("path"));
        returnJson.put("fileSize", realSize);
        log.debug("上传成功：{}", returnJson.toJSONString());
        return returnJson;
    }

    /**
     * 上传第一步，验证文件信息的有效性
     *
     * @param noteKey
     * @param currentJson
     * @return
     * @throws Exception
     */
    private JSONObject validateFileInfoForFirstStep(String noteKey, JSONObject currentJson) throws Exception {
        JSONObject noteJson = BlockNoteUtil.getNote(noteKey);
        // 继续上传
        if (noteJson != null) {
            // 日志记录的分块状态
            Integer[] status = noteJson.getJSONArray("status").toArray(new Integer[] {});
            for (int i = 0; i < status.length; i++) {
                // 部分上传完成
                if (status[i] == 0) {
                    // 开始写入的位置
                    long blockSize = noteJson.getInteger("block_size");
                    long shouldSize = i * blockSize;
                    long realSize = UploadFileUtil.getFileLength(filePath);
                    // 比实际大小大，记录信息有误
                    if (shouldSize > realSize) {
                        // 删除文件，重新上传
                        UploadFileUtil.deleteFile(filePath);
                        log.error("shouldSize > realSize，请排查原因");
                        return currentJson;
                    }
                    // 实际大小比应该大小大一个分块，说明文件异常或记录异常
                    if ((shouldSize + blockSize) <= realSize) {
                        // 删除文件，重新上传
                        log.error("(shouldSize + blockSize) <= realSize，请排查原因");
                    }
                    long shouldFileSize = noteJson.getLong("file_size");
                    // 上传文件大于完整文件大小
                    if (realSize > shouldFileSize) {
                        // 删除文件，重新上传
                        UploadFileUtil.deleteFile(filePath);
                        log.error("文件当前大小比完整文件实际大小大，请排查原因");
                        return currentJson;
                    }
                    // 继续上传
                    // noteJson.put("path", currentJson.getString("path"));
                    noteJson.put("expiration", currentJson.getLongValue("expiration"));
                    return noteJson;
                }
                // 全部上传完成，step 3会验证正确性
                else if (i == (status.length - 1) && status[i] == 1) {
                    // noteJson.put("path", currentJson.getString("path"));
                    noteJson.put("expiration", currentJson.getLongValue("expiration"));
                    return noteJson;
                }
            }
        }

        // 新的上传
        String filePath = currentJson.getString("path");
        // 文件路径被占用，删除该文件
        if (UploadFileUtil.fileExists(filePath)) {
            UploadFileUtil.deleteFile(filePath);
        }
        return currentJson;
    }

}
