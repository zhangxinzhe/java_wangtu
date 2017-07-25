package net.zdsoft.common.ueditor.upload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;

import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.ueditor.define.AppInfo;
import net.zdsoft.common.ueditor.define.BaseState;
import net.zdsoft.common.ueditor.define.State;

public class StorageManager {
    public static final int BUFFER_SIZE = 8192;

    public StorageManager() {
    }

    /**
     * 
     * @param data
     * @param relitavePath
     * @return
     */
    public static State saveBinaryFile(byte[] data, String relitavePath) {
        // 创建一个临时文件
        File tmpFile = createTmpFile();
        State state = valid(tmpFile);
        if (!state.isSuccess()) {
            return state;
        }

        try {
            // 将数据保存到临时文件
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile));
            bos.write(data);
            bos.flush();
            bos.close();

            // 保存到文件服务器
            state = saveToFileServer(tmpFile, relitavePath);
            return state;
        }
        catch (IOException ioe) {
            return new BaseState(false, AppInfo.IO_ERROR);
        }
        finally {
            // 删除临时文件
            if (tmpFile.exists()) {
                tmpFile.delete();
            }
        }
    }

    /**
     * 
     * @param is
     * @param relitavePath
     *            相对
     * @param maxSize
     * @return
     */
    public static State saveFileByInputStream(InputStream is, String relitavePath, long maxSize) {
        State state = null;
        File tmpFile = createTmpFile();
        byte[] dataBuf = new byte[2048];
        BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);
        try {
            // 将数据保存在临时文件中
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile),
                    StorageManager.BUFFER_SIZE);
            int count = 0;
            while ((count = bis.read(dataBuf)) != -1) {
                bos.write(dataBuf, 0, count);
            }
            bos.flush();
            bos.close();

            // 文件大小是否过大
            if (tmpFile.length() > maxSize) {
                tmpFile.delete();
                return new BaseState(false, AppInfo.MAX_SIZE);
            }
            // 保存到文件服务器
            state = saveToFileServer(tmpFile, relitavePath);
            return state;

        }
        catch (IOException e) {
        }
        finally {
            // 删除临时文件
            if (tmpFile.exists()) {
                tmpFile.delete();
            }
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    public static State saveFileByInputStream(InputStream is, String relitavePath) {
        State state = null;
        File tmpFile = createTmpFile();
        byte[] dataBuf = new byte[2048];
        BufferedInputStream bis = new BufferedInputStream(is, StorageManager.BUFFER_SIZE);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(tmpFile),
                    StorageManager.BUFFER_SIZE);
            int count = 0;
            while ((count = bis.read(dataBuf)) != -1) {
                bos.write(dataBuf, 0, count);
            }
            bos.flush();
            bos.close();
            state = saveToFileServer(tmpFile, relitavePath);
            return state;
        }
        catch (IOException e) {
        }
        finally {
            // 删除临时文件
            if (tmpFile.exists()) {
                tmpFile.delete();
            }
        }
        return new BaseState(false, AppInfo.IO_ERROR);
    }

    /**
     * 创建空的临时文件
     * 
     * @return
     */
    private static File createTmpFile() {
        File tmpDir = FileUtils.getTempDirectory();
        String tmpFileName = (Math.random() * 10000 + "").replace(".", "");
        return new File(tmpDir, tmpFileName);
    }

    /**
     * 保存到文件服务器
     * 
     * @param tmpFile
     * @param path
     * @return
     */
    private static State saveToFileServer(File tmpFile, String path) {
        State state = null;
        try {
            FileSystemUtil.saveFile(tmpFile, path);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseState(false, AppInfo.IO_ERROR);
        }

        state = new BaseState(true);
        state.putInfo("size", tmpFile.length());
        state.putInfo("title", tmpFile.getName());
        return state;
    }

    /**
     * 验证文件状态
     * 
     * @param file
     * @return
     */
    private static State valid(File file) {
        File parentPath = file.getParentFile();

        if ((!parentPath.exists()) && (!parentPath.mkdirs())) {
            return new BaseState(false, AppInfo.FAILED_CREATE_FILE);
        }

        if (!parentPath.canWrite()) {
            return new BaseState(false, AppInfo.PERMISSION_DENIED);
        }

        return new BaseState(true);
    }
}
