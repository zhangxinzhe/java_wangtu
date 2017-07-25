<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-加入联盟</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/agencyJoinManage.htm">加入联盟</a>&nbsp;&gt;&nbsp;<span>修改</span></p>
        
        <form id="agencyJoin_form" action="${DOMAIN_CMS}/basic/editAgencyJoin.htm" method="post">
            <input type="hidden" id="agencyJoinId" name="agencyJoin.id" value="${agencyJoin.id!}"/>
            <table class="form-table validateForm mt-10">
                <tr>
                    <th><span class="must">*</span>基地名称：</th>
                    <td>
                        <input type="text" class="input-txt" id="agencyName" name="agencyJoin.agencyName" notNull="true" placeholder="输入培训基地名称" maxlength="75" value="${agencyJoin.agencyName!}"/>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>基地类型：</th>
                    <td>
                        <select class="input-sel w-200" name="agencyJoin.agencyType" notNull="true">
                            <option value="">--请选择--</option>
                            <#list agencyTypes as type>
                                <option value="${type}" <#if agencyJoin.agencyType! == type>selected</#if>>${type.nameValue!}</option>
                            </#list>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>联系人：</th>
                    <td><input type="text" class="input-txt" id="contactMan" name="agencyJoin.contactMan" notNull="true" value="${agencyJoin.contactMan!}"></td>
                </tr>
                <tr>
                    <th><span class="must">*</span>联系电话：</th>
                    <td><input type="text" class="input-txt" id="contactPhone" name="agencyJoin.contactPhone" notNull="true" regex="/^([0-9])\d{6,11}$/" regexMsg="请输入正确的联系电话" maxlength="20" value="${agencyJoin.contactPhone!}" /></td>
                </tr>
                <tr>
                    <th>Email：</th>
                    <td><input type="text" class="input-txt" id="email" name="agencyJoin.email" regex="/^\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/" regexMsg="请输入有效的邮件地址" value="${agencyJoin.email!}" /></td>
                </tr>
                <tr>
                    <th><span class="must">*</span>所在地区：</th>
                    <td>
                        <input type="hidden" id="regionCode" name="agencyJoin.regionCode" value="${agencyJoin.regionCode?default("")}"/>
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
                    <td><input type="text" class="input-txt" id="address" name="agencyJoin.address" maxlength="100" value="${agencyJoin.address!}" style="width:350px;"></td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10">简介：</th>
                    <td valign="top" class="pt-10"><textarea class="text-area fn-left" id="introduction" name="agencyJoin.introduction" style="width:350px;">${agencyJoin.introduction!}</textarea></td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10">备注：</th>
                    <td valign="top" class="pt-10"><textarea class="text-area fn-left" id="remark" name="agencyJoin.remark" style="width:350px;">${agencyJoin.remark!}</textarea></td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                        <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                        <a href="${DOMAIN_CMS}/basic/agencyJoinManage.htm" class="abtn abtn-green ml-10">返回</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/agencyJoin/editAgencyJoinJs.ftl" />
<script>
    $(function(){
        editAgencyJoin.init();
    });
</script>
</body>
</html>