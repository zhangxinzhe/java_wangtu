/* 
 * author dongzk
 */
package net.zdsoft.common.freemarker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.freemarker.FreemarkerManager;
import org.apache.struts2.views.freemarker.ScopesHashModel;

import com.opensymphony.xwork2.util.ValueStack;

public class AppSettingFreemarkerManager extends FreemarkerManager {
    public static final String KEY_WEBCONFIG = "appsetting";


    @Override
    protected void populateContext(ScopesHashModel model, ValueStack stack, Object action,
            HttpServletRequest request, HttpServletResponse response) {
        super.populateContext(model, stack, action, request, response);
        model.put(KEY_WEBCONFIG, AppSetting.getInstance());
    }
    
    
}
