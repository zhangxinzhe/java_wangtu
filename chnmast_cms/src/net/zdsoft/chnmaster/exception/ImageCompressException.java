/* 
 * @(#)ImageCompressException.java    Created on 2013-8-21
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id: ImageCompressException.java 2450 2013-08-21 15:28:50Z zhuyunfeng $
 */
package net.zdsoft.chnmaster.exception;

import net.zdsoft.common.exception.NetstudyException;

/**
 * @author Administrator
 * @version $Revision: 2450 $, $Date: 2013-08-21 23:28:50 +0800 (Wed, 21 Aug 2013) $
 */
public class ImageCompressException extends NetstudyException {
    private String imageFileName;

    public ImageCompressException(String imageFileName, String message) {
        super(message);

        this.imageFileName = imageFileName;
    }

    /**
     * 
     * @param imageFileName
     *            压缩失败的图片文件名
     * @param cause
     *            失败原因
     */
    public ImageCompressException(String imageFileName, Throwable cause) {
        super(cause.getMessage(), cause);

        this.imageFileName = imageFileName;
    }

    /**
     * 得到压缩失败的图片的文件名
     * 
     * @return
     */
    public String getImageFileName() {
        return this.imageFileName;
    }

}
