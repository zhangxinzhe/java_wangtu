package net.zdsoft.chnmaster.action.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.common.action.BaseErrorAction;

/**
 * 错误处理Action
 * 
 * @author fangb
 */
@SuppressWarnings("serial")
@Scope("prototype")
@Controller
public class ErrorAction extends CmsBaseAction implements BaseErrorAction {

    @Override
    public String error() {
        return SUCCESS;
    }

    @Override
    public String exception() {
        return SUCCESS;
    }

    @Override
    public String noprivacy() {
        return SUCCESS;
    }

    @Override
    public String notexist() {
        return SUCCESS;
    }
}
