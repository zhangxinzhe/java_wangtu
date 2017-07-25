<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-新增学校</title>
</head>

<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/schoolManage.htm">学校维护</a>&nbsp;&gt;&nbsp;<span>新增学校</span></p>
        <form id="schoolForm" method="post" action="${DOMAIN_CMS}/basic/schoolAdd.htm" target="addSchoolFrame">
        <table class="form-table validateForm mt-10">
            <tr>
                <th><span class="must">*</span>学校名称：</th>
                <td>
                    <input type="text" class="input-txt" id="nameInput" name="school.schoolName" notNull="true" maxlength="25" placeholder="输入学校名称" value="${school.schoolName!}"/>
                </td>
            </tr>
            <tr>
                <th>联系人：</th>
                <td><input type="text" class="input-txt" id="contactManInput" name="school.contactMan" value="${school.contactMan!}"></td>
            </tr>
            <tr>
                <th>联系电话：</th>
                <td><input type="text" class="input-txt" id="contactPhoneInput" name="school.contactPhone" regex="/^([0-9])\d{6,11}$/" regexMsg="请输入正确的联系电话" maxlength="20" value="${school.contactPhone!}"></td>
            </tr>
            <tr>
                <th>Email：</th>
                <td><input type="text" class="input-txt" id="emailInput" name="school.email" regex="/^\w{1,}[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/" regexMsg="请输入有效的邮件地址" value="${school.email!}"></td>
            </tr>
            <tr>
                <th>所在地区：</th>
                <td>
                    <input type="hidden" id="regionCode" name="school.regionCode" value="${school.regionCode?default("")}"/>
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
                <td><input type="text" class="input-txt" id="addressInput" name="school.address" maxlength="100" value="${school.address!}"></td>
            </tr>
            <tr>
                <th>简介：</th>
                <td><textarea class="text-area fn-left t-350" id="introductionInput"  name="school.introduction" maxlength="4000">${school.introduction!}</textarea></td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td><a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a><a href="javascript:location.href='${DOMAIN_CMS}/basic/schoolManage.htm'" class="abtn abtn-green ml-10">返回</a></td>
            </tr>
        </table>
        </form>
        <iframe id="addSchoolFrame" name="addSchoolFrame" style="display: none;"></iframe>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/school/addSchoolJs.ftl" />
<script>
    $(function(){
        AddSchool.init();
    });
    function showResult(msg, isSuccess, id){
        AddSchool.showResult(msg, isSuccess, id);
    }
</script>
</body>
</html>