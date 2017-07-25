/*
 * @(#)DeteleAllFilesProcessor.java    Created on 2016年4月16日
 * Copyright (c) 2016 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.exoperate.common;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import net.zdsoft.common.exoperate.ExoperateProcessor;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.utils.NetstudyFileUtils;

/**
 * 多文件，异步删除
 *
 * @author hongxu
 * @version $Revision: 1.0 $, $Date: 2016年4月16日 下午4:32:58 $
 */
@SuppressWarnings("rawtypes")
@Controller
public class DeteleAllFilesProcessor implements ExoperateProcessor {
    Logger log = LoggerFactory.getLogger(getClass());

    @SuppressWarnings("unchecked")
    @Override
    public void process(String operateType, Map values) {
        List<String> fileList = (List<String>) values.get("FILE_LIST");
        for (String filePath : fileList) {
            try {
                FileSystemUtil.deleteFile(NetstudyFileUtils.deleteMark(filePath));
            }
            catch (Exception e) {
                log.error("文件[{}]删除出错！原因：{}", filePath, e.getMessage());
            }
        }
    }
}
