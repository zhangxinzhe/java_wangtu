package net.zdsoft.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import net.zdsoft.common.entity.BaseUser;

public abstract class BaseAbstractInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = 1L;

    /**
     * 得到当前登录的用户
     * 
     * @return
     */
    public abstract BaseUser getUser();

    public static final HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    public static final HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    public static Object getSessionAttr(String attrName) {
        HttpSession session = getRequest().getSession();
        return session.getAttribute(attrName);
    }

    public static void setAttribute(String attrName, Object attr) {
        HttpSession session = getRequest().getSession();
        session.setAttribute(attrName, attr);
    }
}
