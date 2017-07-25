<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>${appsetting.getParam("site.cms_site_name")?default('')}-登录</title>
<link rel="shortcut icon" type="image/x-icon" href="/images/chnmaster.ico" />
<link rel="stylesheet" type="text/css" href="/css/login_style.css?${appsetting.getVersionId()}"/>
</head>
<body>
<div class="login-page">
    <div class="p-1"></div>
    <div class="p-2">
    	<div class="grid"><a href="${appsetting.getParam("domain.home")?default('')}/index.htm" class="back">返回应用首页>></a></div>
    </div>
    <div class="p-3">
    	<div class="grid grid-wx">
            <form id="loginForm" method="post" action="/login.htm" >
            <table border="0" cellspacing="0" cellpadding="0" class="has-yzm wangxiao">
                <tr>
                    <td class="tit">用户名：</td>
                    <td class="txt"><input type="text" value="${loginName!}" class="input-txt" id="username" name="loginName" maxlength="50" /></td>
                    <td rowspan="3" class="btn">&nbsp;</td>
                    <td rowspan="3" class="name">${appsetting.getParam("site.cms_site_name")?default('')}</td>
                </tr>
                <tr>
                    <td class="tit">密&nbsp;码：</td>
                    <td class="txt"><input type="password" value="" class="input-txt" id="password" maxlength="20" /></td>
                </tr>
                <tr>
                    <td class="tit">&nbsp;&nbsp;</td>
                    <td class="txt"><font id="errorMsg" color="red"><#if action.hasActionErrors()>${action.actionErrors}</#if></font></td>
                </tr>
            </table>
            <input type="hidden" name="redirectUrl" value="${redirectUrl!}"/>
            <div class="grid-btn"><input type="button" value="" tabindex="-1" class="input-btn" id="showLayer"/></div>
            <input type="hidden" value="1" name="isLogin" />
            <input type="hidden" value="" id="loginPassword" name="loginPassword" />
            </form>
        </div>
    </div>
    <div class="p-4" align="center" style="color:white;font-size:15px;">为保证系统功能的正常使用，建议您使用IE8及以上、谷歌、火狐浏览器</div>
    <div class="p-5"></div>
</div>
<script type="text/javascript" src="/js/jquery-1.7.2.min.js?${appsetting.getVersionId()}"></script>
<script type="text/javascript" src="/js/base.js?${appsetting.getVersionId()}"></script>
<script type="text/javascript" src="/js/md5.js?0"></script>
<script type="text/javascript" src="/js/index/login.js?${appsetting.getVersionId()}"></script>  
</body>
</html>
