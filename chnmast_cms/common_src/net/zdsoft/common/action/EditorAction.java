/* 
 * @(#)controller.java    Created on 2015-2-10
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.action;

import java.io.InputStream;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.filesystem.util.FileSystemUtil;
import net.zdsoft.common.ueditor.ActionEnter;
import net.zdsoft.keel.util.ServletUtils;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015-2-10 下午3:17:22 $
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class EditorAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private final Pattern p = Pattern.compile("http(.*)png");
    private boolean isUrl;

    /**
     * 编辑器上传总控制开关
     */
    public void controller() {
        PrintWriter out = null;
        try {
            getRequest().setCharacterEncoding("utf-8");
            HttpServletResponse resp = getResponse();
            resp.setCharacterEncoding("utf-8");
            String rootPath = getRequest().getSession().getServletContext().getRealPath("/");
            out = resp.getWriter();
            if (isUrl) {
                String json = new ActionEnter(getRequest(), rootPath).exec();
                Matcher m = p.matcher(json);
                while (m.find()) {
                    out.print(m.group() + "?s=1");
                    break;
                }
            }
            else {
                out.print(new ActionEnter(getRequest(), rootPath).exec().toString());
            }
        }
        catch (Exception e) {
            log.error("会话输出JSON信息时出错！", e);
        }
        finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public void downloadCaptrue() throws Exception {
        String path = "/sysfile/udCapture/UdCapture.msi";
        String type = request.getParameter("type");
        if ("64".equals(type)) {
            path = "/sysfile/udCapture/UdCapture64.msi";
        }
        InputStream is = FileSystemUtil.getFileAsStream(path);
        ServletUtils.download(is, request, response, "UdCapture.msi");
    }

    @Override
    public BaseUser getUser() {
        return null;
    }

    public void setIsUrl(boolean isUrl) {
        this.isUrl = isUrl;
    }
}
