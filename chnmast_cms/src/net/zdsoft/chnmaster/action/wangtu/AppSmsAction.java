/*
 * @(#)AppUserAction.java    Created on 2017年7月14日
 * Copyright (c) 2017 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.chnmaster.action.wangtu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSONObject;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushDevice;
import net.zdsoft.chnmaster.entity.wangtu.SmsPushMsg;
import net.zdsoft.chnmaster.enums.wangtu.DeviceType;
import net.zdsoft.chnmaster.service.sms.SmsPushDeviceService;
import net.zdsoft.chnmaster.service.sms.SmsPushMsgService;

/**
 * @author pc
 * @version $Revision: 1.0 $, $Date: 2017年7月14日 上午10:33:56 $
 */
@Scope("prototype")
@Controller
public class AppSmsAction extends CmsPageAction {
	private static final long serialVersionUID = 6943539821957694807L;
	@Resource
    private SmsPushDeviceService smsPushDeviceService;
    @Resource
    private SmsPushMsgService smsPushMsgService;
    
    private String clientId;
    private int pushStatus;
    /**
     * 获取手机配置信息
     */
    public void mobileConfig(){
    	JSONObject dataJson = new JSONObject();
    	JSONObject androidJson = new JSONObject();
    	dataJson.put("android", androidJson);
    	androidJson.put("name", "/sysfile/mobile/1.apk");
    	androidJson.put("innerVersion", "2017080616");
    	androidJson.put("version", "1.0");
    	if(getUser() != null && StringUtils.isNotBlank(clientId)){
    		 smsPushDeviceService.updatePushDevice(getUser().getId(), 0, clientId, clientId, null, DeviceType.ANDROID);
    		 Object pushStatus = getRequest().getSession().getAttribute("pushStatus");
    		 if(pushStatus == null){
    			 SmsPushDevice pusdDevice = smsPushDeviceService.getPushDeviceByPushToken(clientId);
    			 if(pusdDevice != null){
    				 pushStatus = pusdDevice.getPushStatus();
    			 }
    		 }
    		// 返回消息推送状态
    		 if (pushStatus != null) {
    			 androidJson.put("pushStatus",pushStatus  + "");
             }
    	}else {
    		 dataJson.put("refreshAgain", true);
        }
    	dataJson.put("isLogin", getUser() != null);
    	
    	printJson(dataJson);
    }
    
    public void updatePushStatus(){
    	if (StringUtils.isBlank(clientId)) {
            return;
        }
        if (smsPushDeviceService.updatePushStatus(clientId, pushStatus) == 1) {
            getRequest().getSession().setAttribute("pushStatus", pushStatus);
        }
    }
    
    /**
     * 消息列表
     */
    public void pushMsgList(){
    	Map<String, Object> json = new HashMap<String, Object>();
    	getPage().setRowNum(8);
    	 List<SmsPushMsg> smsPushMsgs =smsPushMsgService.getPushMsgs(getUser().getId(), getPage());
    	 json.put("list", smsPushMsgs);
         json.put("page", getPage());
         printJsonMap(json);
    }

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setPushStatus(int pushStatus) {
		this.pushStatus = pushStatus;
	}
    
    
}
