<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-联合会会员</title>
</head>

<body>
<@header curAppId=appId/>
<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    
    <div id="content">
        <p class="crumbs">当前位置：基础数据&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/unionMemberManage.htm?isTeam=true">联合会会员</a>&nbsp;&gt;&nbsp;<span>团体会员信息修改</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt">${(unionMember.agencyName)!''}</span>
            <li class="current">团体会员信息</li>
        </ul>
        <form id="unionMemberForm" method="post" action="${DOMAIN_CMS}/basic/saveUnionMember.htm">
        <input type="hidden" id="unionMemberId" name="unionMember.id" value="${unionMember.id!}"/>
        <input type="hidden" name="unionMember.unionMemberType" value="${unionMember.unionMemberType!}"/>
        <input type="hidden" name="unionMember.photoFile" value="${unionMember.photoFile!}"/>
        <input type="hidden" name="unionMember.paperFile" value="${unionMember.paperFile!}"/>
        <table class="form-table validateForm mt-10">
            <tr>
                <th>会员类型：</th>
                <td>${(unionMember.unionMemberType.nameValue)!''}</td>
            </tr>
            <tr>
                <th>会员编号：</th>
                <td>${(unionMember.unionCode)!''}</td>
            </tr>
            <tr>
                <th><span class="must">*</span>机构名称：</th>
                <td>
                <input type="text" class="input-txt" name="unionMember.agencyName" notNull="true" maxlength="50" placeholder="输入机构名称" value="${(unionMember.agencyName)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>注册地址：</th>
                <td>
                <input type="text" class="input-txt" name="unionMember.agencyAddress" notNull="true" maxlength="50" placeholder="输入注册地址" value="${(unionMember.agencyAddress)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>经营年期：</th>
                <td>
                <input type="text" class="input-txt t-80" name="unionMember.agencyYear" notNull="true" dataType="float" decimalLength='1' maxlength="5" placeholder="输入经营年期" value="${(unionMember.agencyYear)!''}"/>&nbsp;年
                <span class="onTips">从事本行业的时间，非营业执照上的经营年限</span>
                </td>
            </tr>
            <#if unionMember.unionMemberType == 'TEAM_EDUCATION'>
            <tr>
                <th><span class="must">*</span>分店数量：</th>
                <td>
                <input type="text" class="input-txt" name="unionMember.agencyBranchNum" notNull="true" dataType="integer" maxlength="6" placeholder="输入分店数量" value="${(unionMember.agencyBranchNum)!''}"/>
                </td>
            </tr>
            </#if>
            
            <#if unionMember.unionMemberType == 'TEAM_EQUIPMENT'>
            <tr>
                <th><span class="must">*</span>员工数量：</th>
                <td>
                <input type="text" class="input-txt" name="unionMember.agencyEmployeeNum" notNull="true" dataType="integer" maxlength="6" placeholder="输入员工数量" value="${(unionMember.agencyEmployeeNum)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>品牌名称：</th>
                <td>
                    <input type="text" class="input-txt" name="unionMember.agencyBrand" notNull="true" maxlength="50" placeholder="输入品牌名称" value="${(unionMember.agencyBrand)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>机构类别：</th>
                <td>
                    <#list agencyMode?if_exists as type>
                        <span <#if type_index != 0>class="ml-20"</#if>><input type="checkbox" class="chk" name="unionMember.agencyMode" value="${type.value}" <#if unionMember.agencyMode?? && unionMember.agencyMode?index_of('${type.value}')!=-1 > checked="checked"</#if> />${type.nameValue}</span>
                    </#list>
                </td>
            </tr>
            </#if>
            <tr>
                <th><span class="must">*</span>注册资金：</th>
                <td>
                <input type="text" class="input-txt" name="unionMember.agencyFund" notNull="true" maxlength="15" placeholder="输入注册资金" value="${(unionMember.agencyFund)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>优势特色：</th>
                <td>
                    <input type="text" class="input-txt" name="unionMember.agencyFeature" notNull="true" maxlength="50" placeholder="输入优势特色" value="${(unionMember.agencyFeature)!''}"/>
                </td>
            </tr>
            <#if unionMember.unionMemberType=='TEAM_EDUCATION'>
            <tr>
                <th><span class="must">*</span>教师人数：</th>
                <td>
                    <input type="text" class="input-txt" name="unionMember.agencyEmployeeNum" notNull="true" dataType="integer" maxlength="6" placeholder="输入教师人数" value="${(unionMember.agencyEmployeeNum)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>学员人数：</th>
                <td>
                    <input type="text" class="input-txt" name="unionMember.agencyTraineeNum" notNull="true" dataType="integer" maxlength="6" placeholder="输入学员人数" value="${(unionMember.agencyTraineeNum)!''}"/>
                </td>
            </tr>
            </#if>
            <tr>
                <th><span class="must">*</span>联系人姓名：</th>
                <td>
                <input type="text" class="input-txt" name="unionMember.agencyPrincipal" notNull="true" maxlength="20" placeholder="输入联系人姓名" value="${(unionMember.agencyPrincipal)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>联系人职务：</th>
                <td>
                <input type="text" class="input-txt" name="unionMember.workDuty" notNull="true" maxlength="30" placeholder="输入联系人职务" value="${(unionMember.workDuty)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>邮箱地址：</th>
                <td>
                    <input type="text" class="input-txt" id="email"  name="unionMember.email" notNull="true" maxlength="30" placeholder="输入邮箱地址" value="${(unionMember.email)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>联系电话：</th>
                <td>
                    <input type="text" class="input-txt" id="telephone" name="unionMember.telephone" notNull="true" maxlength="15" placeholder="输入联系电话" value="${(unionMember.telephone)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>所在地：</th>
                <td>
                    <@regionSelect id="regionCode" name="unionMember.regionCode" value="${unionMember.regionCode!}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>联络地址：</th>
                <td>
                    <input type="text" class="input-txt" name="unionMember.address" notNull="true" maxlength="50" placeholder="输入联络地址" value="${(unionMember.address)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>推荐人/推荐单位：</th>
                <td>
                    <input type="text" class="input-txt" name="unionMember.referrer" notNull="true" maxlength="50" placeholder="输入推荐人/推荐单位" value="${(unionMember.referrer)!''}"/>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>机构简介：</th>
                <td>
                    <textarea class="text-area fn-left t-500" name="unionMember.intro" maxLength="1500">${(unionMember.intro)!''}</textarea>
                </td>
            </tr>
            <tr>
                <th><span class="must">*</span>营业执照：</th>
                <td>
                    <#if unionMember.photoFile?default('')!=''>
                        <a href="/basic/downloadFile.htm?unionMemberId=${unionMember.id!}&flag=2">${unionMember.photoFileName!}</a>
                    <#else>未上传或文件丢失
                    </#if>
                </td>
            </tr>  
            <tr>
                <th><span class="must">*</span>会员推荐表：</th>
                <td>
                    <#if unionMember.paperFile?default('')!=''>
                        <a href="/basic/downloadFile.htm?unionMemberId=${unionMember.id!}&flag=1">${unionMember.paperFileName!}</a>
                    <#else>未上传或文件丢失
                    </#if>
                </td>
            </tr> 
            <tr>
                <th>审核状态：</th>
                <td><#if unionMember.unionAuditStatus == 'AUDITING'><span class="c-red">${(unionMember.unionAuditStatus.nameValue)!''}</span><#else>${(unionMember.unionAuditStatus.nameValue)!''}</#if></td>
            </tr>
            <#if unionMember.unionAuditStatus != 'AUDITING'>
            <tr>
                <th>审核日期：</th>
                <td><#if (unionMember.auditDate)??>${(unionMember.auditDate)?string('yyyy-MM-dd')}</#if></td>
            </tr>
            <tr>
                <th>审核人姓名：</th>
                <td>${(unionMember.auditRealName)!''}</td>
            </tr>
                <#if unionMember.unionAuditStatus == 'AUDITNOPASS'>
                <tr>
                    <th>审核信息：</th>
                    <td>${(unionMember.auditMsg)!''}</td>
                </tr>
                </#if>
            </#if>
            <tr>
                <th>&nbsp;</th>
                <td>
                    <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                    <a href="${DOMAIN_CMS}/basic/unionMemberManage.htm?isTeam=true" class="abtn abtn-green">返回</a>
                </td>
            </tr>
        </table>
        </form>
    </div>
</div>
<@footer />
<#include "/pages/jsinclude/basic/unionMember/editUnionMemberJs.ftl" />
<script>
    $(function(){
        editUnion_team.init();
    });
</script>
</body>
</html>