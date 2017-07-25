/*
 * @(#)FileUploadAction.java    Created on 2015年11月5日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.upload.FilePathBuilder;
import net.zdsoft.common.upload.RequestFileHandler;
import net.zdsoft.common.upload.UploadFile;
import net.zdsoft.common.utils.SecurityUtils;
import net.zdsoft.common.utils.Util;

/**
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年11月5日 下午5:36:21 $
 */

@Scope("prototype")
@Controller
public class UploadAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    private static final String PIC_VIEW = "pic_view";

    /**
     * 上传文件到临时文件夹下
     *
     * @return
     */
    public void uploadTemp() {
        UploadFile file = null;
        try {
            file = RequestFileHandler.handleFile(getRequest(), null);
            if (file != null) {
                // 对文件的名称进行安全性拦截：防止临时路径中出现安全性字符
                String fileRealName = SecurityUtils.filterSpecialSymbols(file.getFileRealName());
                String desPath = FilePathBuilder.getStoreTempPath() + BaseConstants.SEPERATOR + fileRealName;
                FileSystemUtil.saveFile(file.getFile(), desPath);
                print("success" + desPath);
            }
            else {
                addActionError("请选择要上传的文件！");
            }
        }
        catch (Exception e) {
            log.error("文件上传失败，原因：", e);
        }
    }

    /**
     * 上传图片到临时文件夹下，同时提供图片的预览功能（临时文件名：pic_view_{timestamp}.后缀名）
     *
     * @return
     */
    public void uploadPicTemp() {
        UploadFile file = null;
        try {
            file = RequestFileHandler.handleFile(getRequest(), null);
            if (file != null) {
                String desPath = FilePathBuilder.getStoreTempPath() + BaseConstants.SEPERATOR + PIC_VIEW + "_"
                        + new Date().getTime() + "." + Util.getSuffix(file.getFileRealName());
                FileSystemUtil.saveFile(file.getFile(), desPath);
                print("success" + desPath);
            }
            else {
                addActionError("请选择要上传的图片！");
            }
        }
        catch (Exception e) {
            log.error("图片上传失败，原因：", e);
            addActionError("图片上传失败！");
        }
    }

    @Override
    public BaseUser getUser() {
        return null;
    }

}
