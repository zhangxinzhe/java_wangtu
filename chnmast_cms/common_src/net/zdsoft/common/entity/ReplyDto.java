/* 
 * @(#)ReplyDto.java    Created on 2015-5-11
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author dongzk
 * @version $Revision: 1.0 $, $Date: 2015-5-11 下午3:25:13 $
 */
public class ReplyDto implements Serializable {
    private static final long serialVersionUID = 1L;

    // 操作成功还是失败，true成功；false失败
    private boolean isSuccess = true;

    // 提示信息
    private String promptMsg;

    // 错误信息
    private String errorMsg;

    // 出错字段及提示信息
    private Map<String, String> fieldErrorMap;

    // 提示页面打开时,须执行的js脚本，如：alert(111);
    // private String script;

    public ReplyDto() {

    }

    public ReplyDto(boolean isSuccess, String message) {
        this.isSuccess = isSuccess;
        if (isSuccess) {
            this.promptMsg = message;
        }
        else {
            this.errorMsg = message;
        }
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getPromptMsg() {
        return promptMsg;
    }

    public void setPromptMsg(String promptMsg) {
        this.promptMsg = promptMsg;
    }

    public Map<String, String> getFieldErrorMap() {
        return fieldErrorMap;
    }

    public void addFieldError(String fieldName, String errorMsg) {
        isSuccess = false;

        if (fieldErrorMap == null) {
            fieldErrorMap = new LinkedHashMap<String, String>();
        }
        String thisFieldErrors = fieldErrorMap.get(fieldName);
        if (thisFieldErrors == null) {
            fieldErrorMap.put(fieldName, errorMsg);
        }
        else {
            thisFieldErrors = thisFieldErrors + "  " + errorMsg;
            fieldErrorMap.put(fieldName, thisFieldErrors);
        }
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        isSuccess = false;
        this.errorMsg = errorMsg;
    }

//    public String getScript() {
//        return script;
//    }
//
//    public void setScript(String script) {
//        this.script = script;
//    }

}
