package net.zdsoft.common.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.entity.ReplyDto;
import net.zdsoft.common.exoperate.ExoperateProcessorDispatcher;
import net.zdsoft.common.resource.ResourcesLoad;
import net.zdsoft.common.utils.URLUtil;
import net.zdsoft.keel.util.DateUtils;

public abstract class BaseAction extends ActionSupport
        implements ServletRequestAware, ServletResponseAware, Preparable {
    private static final long serialVersionUID = 1L;
    protected Logger log = LoggerFactory.getLogger(getClass());

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected String requestUrl;// 域名后面的地址，即action
    protected String requestDomain;// 域名

    protected BaseUser user;
    protected ReplyDto replyDto = new ReplyDto(); // 向前端页面返回对象

    protected String uploadTempFile;// swf上传，文件临时路径

    @Resource
    protected ExoperateProcessorDispatcher exoperateProcessorDispatcher; // 附加业务转发服务接口

    @Override
    public void prepare() throws Exception {
        // 访问的URL
        requestUrl = getRequest().getServletPath();
        // 访问的域名
        requestDomain = URLUtil.getServerName(getRequest());
    }

    protected void printMsg(String msg) {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("msg", msg);
        printJson(json);
    }

    /**
     * 以html格式的header，向页面输出信息，包括：字符串，数字，json字符串，boolean类型等
     *
     * @param value
     *            输出的对象
     * @param isJsonFormat
     *            是否转换为json格式输出
     */
    protected String print(Object value, boolean isJsonFormat) {
        PrintWriter writer = null;
        try {
            HttpServletResponse resp = getResponse();
            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("utf-8");
            writer = resp.getWriter();
            if (isJsonFormat) {
                writer.print(JSON.toJSONString(value));
            }
            else {
                writer.print(value);
            }
        }
        catch (Exception e) {
            log.error("输出信息时出错，原因：", e);
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }

        return NONE;
    }

    /**
     * 以html格式输出字符串
     *
     * @param obj
     */
    protected String print(Object obj) {
        return print(obj, false);
    }

    /**
     * 以html格式输出JSON对象
     *
     * @param obj
     */
    protected String printJson(Object obj) {
        return print(obj, true);
    }

    /**
     * 以html格式输出JSON对象
     *
     * @param obj
     */
    protected String printJsonMap(Map<String, Object> json) {
        json.put("isLogin", getUser() != null);
        return print(json, true);
    }

    /**
     * 以html格式的header向页面输出，用于ajax输出一句话的提示信息（如：保存成功）
     *
     * @param message
     */
    public String wirteTextMessage(String message) {
        return print(message, false);
    }

    /**
     * 以json格式的header向页面输出，用于ajax输出组装成对象的提示信息（如：ReplyDto对象等）
     *
     * @param obj
     * @return
     */
    public String wirteJsonMessage(Object obj) {
        PrintWriter writer = null;
        try {
            HttpServletResponse response = getResponse();
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            writer = response.getWriter();
            writer.write(JSON.toJSONString(obj));
        }
        catch (IOException e) {
            log.error("输出信息时出错，原因：", e);
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        return NONE;
    }

    /**
     * ajax跨域请求输出信息
     *
     * @param value
     */
    protected String printJsonp(Object value) {
        PrintWriter writer = null;
        try {
            HttpServletResponse resp = getResponse();
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("text/javascript");
            String callback = getRequest().getParameter("callback");
            if (callback != null) {
                value = callback + "(" + value + ")";
            }
            writer = resp.getWriter();
            writer.print(value);
        }
        catch (Exception e) {
            log.error("输出信息时出错，原因：", e);
        }
        finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        return NONE;
    }

    /**
     * 转发附加业务操作
     *
     * @param operateType
     * @param values
     */
    @SuppressWarnings("rawtypes")
    public void dispatchExoperate(String operateType, Map values) {
        exoperateProcessorDispatcher.dispatchProcessor(operateType, values);
    }

    public void setExoperateProcessorDispatcher(ExoperateProcessorDispatcher exoperateProcessorDispatcher) {
        this.exoperateProcessorDispatcher = exoperateProcessorDispatcher;
    }

    public ExoperateProcessorDispatcher getExoperateProcessorDispatcher() {
        return exoperateProcessorDispatcher;
    }

    /**
     * 得到当前登录的用户
     *
     * @return
     */
    public abstract BaseUser getUser();

    /**
     * 获取当前是否开发模式，true表示是开发模式
     *
     * @return
     */
    public boolean getDevMode() {
        return ResourcesLoad.getDevMode();
    }

    /**
     * 得到服务器当前时间
     *
     * @return
     */
    public Date getNowTime() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 得到服务器当前时间
     *
     * @return
     */
    public String getNowYear() {
        return DateUtils.currentDate2String().substring(0, 4);
    }

    /**
     * 得到服务器当前时间
     *
     * @return
     */
    public String getNowMonth() {
        return DateUtils.currentDate2String().substring(5, 7);
    }

    /**
     * 得到服务器当前时间
     *
     * @return
     */
    public String getNowDay() {
        return DateUtils.currentDate2String().substring(8, 10);
    }

    public long getNowTimeMillis() {
        return System.currentTimeMillis();
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public String getRequestDomain() {
        return requestDomain;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return this.request;
    }

    public HttpServletResponse getResponse() {
        return this.response;
    }

    public ReplyDto getReplyDto() {
        return replyDto;
    }

    public void setReplyDto(ReplyDto replyDto) {
        this.replyDto = replyDto;
    }

    public String getUploadTempFile() {
        return uploadTempFile;
    }

    public void setUploadTempFile(String uploadTempFile) {
        this.uploadTempFile = uploadTempFile;
    }
}
