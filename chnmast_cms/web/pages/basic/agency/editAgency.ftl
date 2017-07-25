<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-修改培训基地</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/agencyManage.htm">培训基地</a>&nbsp;&gt;&nbsp;<span>修改培训基地</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt" title="${agency.agencyName!}"><@cutOff cutStr="${agency.agencyName!}" cutLen="20"/></span>
            <li class="current" onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/editAgency.htm?agencyId=${agencyId!}'">基本信息</li>
            <#if canAgencyPic>
            <li onclick="javascript:window.location.href='${DOMAIN_CMS}/basic/agencyPicture.htm?agencyId=${agencyId!}'">展示图片</li>
            </#if>
        </ul>
        <form id="agencyForm" method="post" action="${DOMAIN_CMS}/basic/editAgency.htm">
        <input type="hidden" name="agency.id" value="${agency.id!}"/>
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>基地名称：</th>
                <td>
                    <input type="text" class="input-txt" id="nameInput" name="agency.agencyName" notNull="true" maxlength="25" placeholder="输入培训基地名称" value="${agency.agencyName!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>基地类型：</th>
                <td>
                    <select class="input-sel w-200" name="agencyTypeValue" notNull="true">
                        <option value="">--请选择--</option>
                        <#list agencyTypes as type>
                            <option value="${type.value}" <#if agency.agencyType?exists && agency.agencyType.value == type.value>selected</#if>>${type.nameValue!}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>排行：</th>
                <td>
                    <input type="text" class="input-txt" id="rankingInput" name="agency.ranking" regex="/^([0-9])\d{0,2}$/" regexMsg="请输入正确的数字"  maxlength="2" value="${agency.ranking!}"/>
                </td>
            </tr>
            <tr>
                <th>联系人：</th>
                <td><input type="text" class="input-txt" id="contactManInput" name="agency.contactMan" value="${agency.contactMan!}"></td>
            </tr>
            <tr>
                <th>联系电话：</th>
                <td><input type="text" class="input-txt" id="contactPhoneInput" name="agency.contactPhone" regex="/^([0-9])\d{6,10}$/" regexMsg="请输入正确的联系电话" maxlength="20" value="${agency.contactPhone!}"></td>
            </tr>
            <tr>
                <th>Email：</th>
                <td><input type="text" class="input-txt" id="emailInput" name="agency.email" regex="/^\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/" regexMsg="请输入有效的邮件地址" value="${agency.email!}"></td>
            </tr>
            <tr>
                <th><span class="must">*</span>所在地区：</th>
                <td>
                    <input type="hidden" id="regionCode" name="agency.regionCode" value="${agency.regionCode?default("")}"/>
                    <div class="floatL" style="width:500px" >
                        <select id="provinceSel" class="input-sel">
                            <option value="" >请选择所在省</option>
                        </select>
                        <select id="citySel" class="input-sel">
                            <option value="" >请选择所在市</option>
                        </select>
                        <select id="countySel" class="input-sel">
                            <option value="" >请选择所在区/县</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <th>详细地址：</th>
                <td><input type="text" class="input-txt" id="addressInput" name="agency.address" maxlength="100" value="${agency.address!}"></td>
            </tr>
            <tr>
                <th><span class="must">*</span>简介：</th>
                <td><textarea class="text-area fn-left t-350" id="introductionInput" name="agency.introduction" notNull="true" maxlength="4000">${agency.introduction!}</textarea></td>
            </tr>
            <tr>
                <th><span class="must">*</span>基地管理员：</th>
                <td>
                    <input type="text" class="input-txt" readonly id="usernameInput" name="agency.username" regex="/^[a-zA-Z0-9_]+$/" regexMsg="请输入英文字母或是数字,其它字符是不允许的" notNull="true" maxlength="25" placeholder="输入管理员用户名" value="${agency.username!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>密码：</th>
                <td>
                    <input class="input-txt"  id="passwordInput" name="agency.password" type="password" value="${agency.password!}" notNull="true" maxlength="20" oncopy="return false;" oncut="return false;" onpaste="return false" onkeyup="return showPwdRank(this.value);return false;">
                </td>
            </tr>
            <tr id="levelTr">
                <th>&nbsp;</th>
                <td>
                    <div id="securityLevel" class="acc-safety acc-safety-low"><span>为保证您账号的安全性，请使用为数字+字母的组合，长度在6到20位哦！</span></div>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td><a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a><a href="javascript:location.href='${DOMAIN_CMS}/basic/agencyManage.htm'" class="abtn abtn-green ml-10">返回</a></td>
            </tr>
        </table>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/agency/editAgencyJs.ftl" />
<script>
$(function(){
    EditAgency.init();
});
</script>
</body>
</html>