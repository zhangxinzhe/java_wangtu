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
        <p class="crumbs">当前位置：基础数据 &nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/basic/unionMemberManage.htm?isTeam=${isTeam?string('true','false')}">联合会会员</a><span>查看</span></p>
        <ul class="public-tab fn-clear mt-10">
            <span class="tt"><#if !isTeam>${(unionMember.realName)!''}<#else>${(unionMember.agencyName)!''}</#if></span>
            <li class="current">基本信息</li>
        </ul>
       
        <div class="fn-clear">
            <#if !isTeam>
            <div class="userImg-wrap fn-right mr-20">
                <@ImgStyle name='photoImg' width='130' height='180'/>
                <p style="width:130px;margin:0 auto">个人照片</p>
                <p class="img" style="width:130px;height:180px;margin:0 auto">
                    <img id="photoImg" src="<#if unionMember.photoFile?default('')!=''>${DOMAIN_UPLOAD_FILE}/${unionMember.photoFile!}<#else>/images/default.jpg</#if>" alt="照片">
                </p>
            </div>
            </#if>
            <div class="userInfo-wrap">
                <table class="form-table validateForm mt-10">
                <#if !isTeam>
                    <tr>
                        <th style="width:120px;">姓名：</th>
                        <td>${(unionMember.realName)!''}</td>
                    </tr>
                    <tr>
                        <th>会员类型：</th>
                        <td>${(unionMember.unionMemberType.nameValue)!''}</td>
                    </tr>
                    <tr>
                        <th>会员编号：</th>
                        <td>${(unionMember.unionCode)!''}</td>
                    </tr>
                    <tr>
                        <th>性别：</th>
                        <td>${(unionMember.sex.nameValue)!''}</td>
                    </tr>
                    <tr>
                        <th>民族：</th>
                        <td>${(unionMember.nation)!''}</td>
                    </tr>
                    <tr>
                        <th>出生日期：</th>
                        <td><#if (unionMember.birthday)??>${(unionMember.birthday)?string('yyyy-MM-dd')!''}</#if></td>
                    </tr>
                    <tr>
                        <th>学历：</th>
                        <td>${(unionMember.degree)!''}</td>
                    </tr>
                    <tr>
                        <th>毕业院校：</th>
                        <td>${(unionMember.graduateSchool)!''}</td>
                    </tr>
                    <tr>
                        <th>专业：</th>
                        <td>${(unionMember.major)!''}</td>
                    </tr>
                    <tr>
                        <th>教学年资：</th>
                        <td>${(unionMember.teacherYear)!''}</td>
                    </tr>
                    <tr>
                        <th>政治面貌：</th>
                        <td>${(unionMember.political)!''}</td>
                    </tr>
                    <tr>
                        <th>邮箱地址：</th>
                        <td>${(unionMember.email)!''}</td>
                    </tr>
                    <tr>
                        <th>工作单位：</th>
                        <td>${(unionMember.workUnit)!''}</td>
                    </tr>
                    <tr>
                        <th>工作部门：</th>
                        <td>${(unionMember.workDept)!''}</td>
                    </tr>
                    <tr>
                        <th>工作职务：</th>
                        <td>${(unionMember.workDuty)!''}</td>
                    </tr>
                    <tr>
                        <th>联系电话：</th>
                        <td>${(unionMember.telephone)!''}</td>
                    </tr>
                    <tr>
                        <th>行政区划：</th>
                        <td>${(unionMember.systemRegion.fullName)!''}</td>
                    </tr>
                    <tr>
                        <th>单位地址：</th>
                        <td>${(unionMember.address)!''}</td>
                    </tr>
                    <tr>
                        <th>推荐人/推荐单位：</th>
                        <td>${(unionMember.referrer)!''}</td>
                    </tr>
                    <tr>
                        <th>个人介绍/工作履历：</th>
                        <td>${(unionMember.intro)!''}</td>
                    </tr>
                    <tr>
                        <th>单位推荐意见：</th>
                        <td>${(unionMember.opinion)!''}</td>
                    </tr>
                <#else>
                    <#--S 团队 -->
                    <tr>
                        <th>机构名称：</th>
                        <td>${(unionMember.agencyName)!''}</td>
                    </tr>
                    <tr>
                        <th>会员类型：</th>
                        <td>${(unionMember.unionMemberType.nameValue)!''}</td>
                    </tr>
                    <tr>
                        <th>会员编号：</th>
                        <td>${(unionMember.unionCode)!''}</td>
                    </tr>
                    <tr>
                        <th>注册地址：</th>
                        <td>${(unionMember.agencyAddress)!''}</td>
                    </tr>
                    <tr>
                        <th>经营年期：</th>
                        <td>${(unionMember.agencyYear)!''}</td>
                    </tr>
                    <#if unionMember.unionMemberType=='TEAM_EDUCATION'><#-- 团队教育 -->
                        <tr>
                            <th>分店数量：</th>
                            <td>${(unionMember.agencyBranchNum)!''}</td>
                        </tr>
                    </#if>
                    <#if unionMember.unionMemberType == 'TEAM_EQUIPMENT'>
                        <tr>
                            <th>员工数量：</th>
                            <td>${(unionMember.agencyEmployeeNum)!''}</td>
                        </tr>
                        <tr>
                            <th>品牌名称：</th>
                            <td>${(unionMember.agencyBrand)!''}</td>
                        </tr>
                        <tr>
                            <th>机构类别：</th>
                            <td>
                            <#list agencyMode?if_exists as type>
                                <#if unionMember.agencyMode?? && unionMember.agencyMode?index_of('${type.value}')!=-1>${type.nameValue!}</#if>
                            </#list>
                            </td>
                        </tr>
                    </#if>
                    <tr>
                        <th>注册资金：</th>
                        <td>${(unionMember.agencyFund)!''}</td>
                    </tr>
                    <tr>
                        <th>优势特色：</th>
                        <td>${(unionMember.agencyFeature)!''}</td>
                    </tr>
                    <#if unionMember.unionMemberType=='TEAM_EDUCATION'>
                        <tr>
                            <th>教师人数：</th>
                            <td>${(unionMember.agencyEmployeeNum)!''}</td>
                        </tr>
                        <tr>
                            <th>学员人数：</th>
                            <td>${(unionMember.agencyTraineeNum)!''}</td>
                        </tr>
                    </#if>
                    <tr>
                        <th>联系人姓名：</th>
                        <td>${(unionMember.agencyPrincipal)!''}</td>
                    </tr>
                    <tr>
                        <th>联系人职务：</th>
                        <td>${(unionMember.workDuty)!''}</td>
                    </tr>
                    <tr>
                        <th>邮箱地址：</th>
                        <td>${(unionMember.email)!''}</td>
                    </tr>
                    <tr>
                        <th>联系电话：</th>
                        <td>${(unionMember.telephone)!''}</td>
                    </tr>
                    <tr>
                        <th>所在地：</th>
                        <td>${(unionMember.systemRegion.fullName)!''}</td>
                    </tr>
                    <tr>
                        <th>联络地址：</th>
                        <td>${(unionMember.address)!''}</td>
                    </tr>
                    <tr>
                        <th>推荐人/推荐单位：</th>
                        <td>${(unionMember.referrer)!''}</td>
                    </tr>
                    <tr>
                        <th>机构介绍：</th>
                        <td>${(unionMember.intro)!''}</td>
                    </tr>
                    <tr>
                        <th>营业执照：</th>
                        <td>
                            <#if unionMember.photoFile?default('')!=''>
                                <a href="/basic/downloadFile.htm?unionMemberId=${unionMember.id!}&flag=2">${unionMember.photoFileName!}</a>
                            <#else>未上传或文件丢失
                            </#if>
                        </td>
                    </tr>
                    <#--E 团队 -->
                </#if>
                    <tr>
                        <th>会员推荐表：</th>
                        <td>
                            <#if unionMember.paperFile?default('')!=''>
                                <a href="/basic/downloadFile.htm?unionMemberId=${unionMember.id!}&flag=1">${unionMember.paperFileName!}</a>
                            <#else>未上传或文件丢失
                            </#if>
                        </td>
                    </tr>
                    <tr>
                        <th>提交申请日期：</th>
                        <td><#if (unionMember.applyDate)??>${(unionMember.applyDate)?string('yyyy-MM-dd HH:mm:ss')!''}</#if></td>
                    </tr>
                    <tr>
                        <th>审核状态：</th>
                        <td><#if unionMember.unionAuditStatus == 'AUDITING'><span class="c-red">${(unionMember.unionAuditStatus.nameValue)!''}</span><#else>${(unionMember.unionAuditStatus.nameValue)!''}</#if></td>
                    </tr>
                <#if unionMember.unionAuditStatus != 'AUDITING'>
                    <tr>
                        <th>审核日期：</th>
                        <td><#if (unionMember.auditDate)??>${(unionMember.auditDate)?string('yyyy-MM-dd HH:mm:ss')}</#if></td>
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
                         <#if canAuditUnionMember>
                             <#if unionMember.unionAuditStatus == 'AUDITING'><#-- 审核中 -->
                                 <a href="javascript:;" id="audit1" class="abtn abtn-blue" dataValue="${unionMember.id!}" flag="2">审核通过</a>   
                                 <a href="javascript:;" id="audit2" class="abtn abtn-blue" dataValue="${unionMember.id!}" flag="3">审核不通过</a>
                             <#elseif unionMember.unionAuditStatus == 'AUDITPASS' || unionMember.unionAuditStatus == 'AUDITNOPASS'>
                                 <a href="javascript:;" id="audit3" class="abtn abtn-blue" dataValue="${unionMember.id!}" flag="1">取消审核</a>
                             </#if>
                         </#if>
                             <a href="${DOMAIN_CMS}/basic/unionMemberManage.htm?isTeam=${isTeam?string('true','false')}" id="back" class="abtn abtn-green">返回</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<#-- 审核不通过弹窗  -->
<div class="popUp-layer layer-600" style="display:none;" id="popUp2">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>审核不通过</span></p>
    <div class="wrap" style="height:145px;">
        <div class="org-search-tt mb-5">
            <div class="org-search-td">
                <span>审核信息：</span>
            </div>
            <textarea class="text-area t-500 mt-5" id="popUp2_auditMsg" maxLength="400"></textarea>
        </div>
        <span class="c-red ml-5" id="popUp2_tips"></span>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue" href="javascript:;" id="popUp2_audit"><span>确定</span></a>
        <a class="abtn abtn-blue close" href="javascript:;" ><span>关闭</span></a>
    </P>
</div>

<@footer />
<#include "/pages/jsinclude/basic/unionMember/viewUnionMemberJs.ftl" />
<script>
    $(function(){
        viewUnionMember.init('${isTeam?string('true','false')}','${(unionMember.id)!'0'}');
    });
</script>
</body>
</html>