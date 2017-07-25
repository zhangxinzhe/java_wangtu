/*
 * @(#)ImageCompressProcessor.java    Created on 2016年5月20日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.exoperate.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.springframework.stereotype.Component;

import net.zdsoft.common.exoperate.ExoperateProcessor;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.utils.ImageUtils;
import net.zdsoft.common.utils.NetstudyFileUtils;
import net.zdsoft.common.utils.OSUtil;
import net.zdsoft.common.utils.Util;

/**
 * @author hanqr
 * @version $Revision: 1.0 $, $Date: 2016年5月20日 上午9:32:39 $
 */
@SuppressWarnings("rawtypes")
@Component("imageCompressProcessor")
public class ImageCompressProcessor implements ExoperateProcessor {

    @Override
    public void process(String operateType, Map values) {
        String srcPath = (String) values.get("srcPath");
        String desPath = (String) values.get("desPath");
        int width = (int) values.get("width");
        int height = (int) values.get("height");
        boolean proportion = (boolean) values.get("proportion");
        long userId = (long) values.get("userId");

        try {
            InputStream is = FileSystemUtil.getFileAsStream(srcPath);
            String tempPath = OSUtil.getTempDir() + "/" + System.currentTimeMillis() + "_temp."
                    + Util.getSuffix(srcPath);
            File tempFile = new File(tempPath);
            if (tempFile.exists()) {
                tempFile.delete();
            }

            tempFile.createNewFile();
            OutputStream os = new FileOutputStream(tempFile);
            byte[] buffer = new byte[8192];
            int bytesRead = 0;
            while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            is.close();
            ImageUtils.imageCompress(tempFile, tempPath, width, height, proportion);
            FileSystemUtil.saveFile(tempFile, NetstudyFileUtils.deleteMark(desPath));
            tempFile.delete();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
