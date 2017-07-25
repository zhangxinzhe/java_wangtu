package net.zdsoft.common.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

/**
 * http 文件下载
 * @作者 shenl
 * @创建日期 2011-7-16
 * @版本 V 1.0
 */
public class DownloadUtil {
    /**
     * 获取断点下载时的跳转长度
     * @return
     */
    public static long getRange(){
        // 断点续传
        String range = ServletActionContext.getRequest().getHeader("Range");
        long start;
        if (StringUtils.isEmpty(range)) {
            start = 0;
        } else {
            try {
                range = range.substring("bytes=".length(), range.indexOf("-"));
                start = Long.parseLong(range);
            } catch (NumberFormatException e) {
                start = 0;
            }
        }
        return start;
    }
    /**
     * http 文件下载
     * @param filePath
     * @param fileName
     * @param nPos
     * @param response
     * @throws Exception 
     */
    public static void download(String filePath, String fileName, long nPos) throws Exception{
        FileInputStream fis = new FileInputStream(filePath);
        download(fis, fileName, nPos);
    }
    /**
     * 下载
     * @param fis
     * @param fileName
     * @param nPos
     * @throws IOException
     */
    public static void download(InputStream fis, String fileName, long nPos) throws IOException{
        long l = fis.available();  
        HttpServletResponse response = ServletActionContext.getResponse();
        // 清空response
        response.reset();
        // 设置response的Header
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"),"iso8859-1"));
        
        String contentRange = null;
        if (nPos != 0) {
            fis.skip(nPos);
            contentRange = new StringBuffer("bytes ").append(nPos).append("-").append(l - 1).append("/").append(l).toString();
            response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);  
        }else {
            contentRange = l + "";
        }
        response.addHeader("Content-Length", contentRange);
        ServletOutputStream out = response.getOutputStream();
        try {
            IOUtils.copy(fis, out);
            out.flush();
        } finally{
            IOUtils.closeQuietly(fis);
        }
    }
}
