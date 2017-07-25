package net.zdsoft.chnmaster.interceptor;

import com.opensymphony.xwork2.ActionInvocation;

import net.zdsoft.chnmaster.utils.LoginUtils;
import net.zdsoft.common.entity.BaseUser;
import net.zdsoft.common.interceptor.BaseAbstractInterceptor;

@SuppressWarnings("serial")
public class UserIsLoginInterceptor extends BaseAbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        // 必须是后台登录用户
        BaseUser baseUser = getUser();
        if (baseUser == null) {
            return "login";
        }

        return invocation.invoke();
    }

    @Override
    public BaseUser getUser() {
        return LoginUtils.getInstance().getUserFromRequest(getRequest());
    }

}
