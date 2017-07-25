<#--前台名称-->
<#assign SITE_SITE_NAME = stack.findValue('@net.zdsoft.common.constant.BaseConstants@SITE_HOME_SITE_NAME')>
<#assign SITE_SITE_NAME = appsetting.getParam(SITE_SITE_NAME)?default('')>
<#--后台名称-->
<#assign SITE_CMS_SITE_NAME = stack.findValue('@net.zdsoft.common.constant.BaseConstants@SITE_CMS_SITE_NAME')>
<#assign SITE_CMS_SITE_NAME = appsetting.getParam(SITE_CMS_SITE_NAME)?default('')>
<#--主域名-->
<#assign DOMAIN_HOME = stack.findValue('@net.zdsoft.common.constant.BaseConstants@DOMAIN_HOME')>
<#assign DOMAIN_HOME = appsetting.getParam(DOMAIN_HOME)?default('')>
<#--后台 主域名-->
<#assign DOMAIN_CMS = stack.findValue('@net.zdsoft.common.constant.BaseConstants@DOMAIN_CMS')>
<#assign DOMAIN_CMS = appsetting.getParam(DOMAIN_CMS)?default('')>
<#--手机主域名-->
<#assign DOMAIN_MOBILE = stack.findValue('@net.zdsoft.common.constant.BaseConstants@DOMAIN_MOBILE')>
<#assign DOMAIN_MOBILE = appsetting.getParam(DOMAIN_MOBILE)?default('')>
<#--file域名-->
<#assign DOMAIN_FILE = stack.findValue('@net.zdsoft.common.constant.BaseConstants@DOMAIN_FILE')>
<#assign DOMAIN_FILE = appsetting.getParam(DOMAIN_FILE)?default('')>
<#--uploadfile域名-->
<#assign DOMAIN_UPLOAD_FILE = stack.findValue('@net.zdsoft.common.constant.BaseConstants@DOMAIN_UPLOAD_FILE')>
<#assign DOMAIN_UPLOAD_FILE = appsetting.getParam(DOMAIN_UPLOAD_FILE)?default('')>