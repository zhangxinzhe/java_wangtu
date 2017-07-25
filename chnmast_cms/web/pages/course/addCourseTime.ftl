<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-基地课程</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1300000 curSubModId=1300100 />
    <div id="content">
        <p class="crumbs">当前位置：课程管理&nbsp;&gt;&nbsp;<span>基地课程</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li onclick="window.location.href='${DOMAIN_CMS}/course/addCourse.htm?id=${id!}'">课程信息</li>
            <li class="current" onclick="window.location.href='${DOMAIN_CMS}/course/courseTimeManage.htm?id=${id!}'">课次和价格</li>
        </ul>
        
        <div class="record-wrap mt-20">
            <table class="public-table price-table" index="99">
                <tbody id='cost_tbody'>
                    <tr class="first">
                        <td width='80' class="t-center">课程价格</td>
                        <td width='130'>课程原价:<span oldPrice='${course.courseOldcost!}'>￥${course.courseOldcost!}</span></td>
                        <td width='130'>课程现价:<span nowPrice='${course.courseNowcost!}'>￥${course.courseNowcost!}</span></td>
                        <td width='130'>会员现价:<span nowPriceVip='${course.courseNowcostVip!}'>￥${course.courseNowcostVip!}</span></td>
                        <td width='100' class='t-center'><a href='javascript:;' class='i-edit edit-price' id='editCostPrice' title='编辑套价'></a></td>
                    </tr>
                    <tr class="last">
                        <td width='80' class="t-center" style='color:red;'>合计(直播票)</td>
                        <td width='130' style='color:red;'>课程价格合计:<span id='courseTotal' courseTotal=''></span></td>
                        <td width='130' style='color:red;'>会员价格合计:<span id='courseTotalVip' courseTotalVip=''></span></td>
                        <td style='color:red;' colspan='2'>注：建议【课程原价】金额维护为【课程价格合计】金额</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="record-wrap mt-20">
            <input type="hidden" id="courseId" value="${id!}" />
            <table class="public-table public-table-row" index="99">
                <thead>
                    <tr class="first">
                        <th width="60" class="t-center">序号</th>
                        <th width="130">日期</th>
                        <th width="120">开始时间</th>
                        <th width="120">结束时间</th>
                        <th>课次主题</th>
                        <th width="85" class="t-center">操作</th>
                    </tr>
                </thead>
                <tbody id="time_tbody">
                    <#assign courseTotal = 0>
                    <#assign courseTotalVip = 0>
                    <#if timeList?? && timeList?has_content>
                    <#list timeList?if_exists as time>
                        <tr class="tt time-tr" timeValue="${time.id!}">
                            <td class="t-center b"><span class="sort-num">${time_index+1}</span></td>
                            <td>${(time.beginTime?string('yyyy-MM-dd'))!}</td>
                            <td>${(time.beginTime?string('HH:mm'))!}</td>
                            <td>${(time.endTime?string('HH:mm'))!}</td>
                            <td>${time.title!}</td>
                            <td class="t-center">
                            <#if !time.isHistory>
                                <a href="javascript:;" class="i-edit edit-time" title="编辑课次" dataValue="${time.id!}"></a><a href="javascript:;" class="i-del ml-10 del-time" title="删除课次" dataValue="${time.id!}"></a>
                            </#if>
                            </td>
                        </tr>
                        <tr>
                            <td class="t-center b row-td">票类</td>
                            <td colspan="5" class="public-table-inner">
                                <table class="public-table price-table" index="${time_index+1}">
                                    <tbody>
                                        <#if time.timePriceList?? && time.timePriceList?has_content>
                                        <#list time.timePriceList?if_exists as price>
                                        <tr <#if price_index==0>class="first"</#if>>
                                            <#if price.studyType.value == 0>
                                                <#assign temp="直播票">
                                            <#elseif price.studyType.value == 1>
                                                <#assign temp="现场票">
                                            </#if>
                                            <#if price.studyType.value == 0>
                                            <#assign courseTotal = courseTotal + price.nowPrice!>
                                            <#assign courseTotalVip = courseTotalVip + price.nowPriceVip!>
                                            </#if>
                                            <td width="110"><span studyType="${price.studyType.value}">${temp}</span></td>
                                            <td width="120"><span nowPrice="${price.nowPrice!}">票价：￥${price.nowPrice!}</span></td>
                                            <td width="120"><span nowPriceVip="${price.nowPriceVip!}">会员价：￥${price.nowPriceVip!}</span></td>
                                            <td width="100"><span num="${price.num!}"><#if (price.num)! gt 0>${price.num!}张<#else>不限制</#if></span></td>
                                            <td><span>${price.remark}</span></td>
                                            <td width="85" class="t-center">
                                                <a href="javascript:;" class="i-edit edit-price" title="编辑票类" dataValue="${price.id!}"></a><a href="javascript:;" class="i-del ml-10 del-price" title="删除票类" dataValue="${price.id!}"></a>
                                            </td>
                                        </tr>
                                        </#list>
                                        </#if>
                                    </tbody>
                                    <tfoot>
                                        <tr class="<#if !(time.timePriceList?? && time.timePriceList?has_content)>first</#if> last">
                                            <td colspan="5">
                                                <a href="javascript:;" class="abtn abtn-green fn-btn add-price"><img src="${DOMAIN_CMS}/images/icon/add2.png">添加价格</a>
                                            </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </td>
                        </tr>
                    </#list>
                    </#if>
                </tbody>
                <tfoot>
                    <tr class="last">
                        <td colspan="6">
                            <a href="javascript:;" class="abtn abtn-green fn-btn" id="time_add"><img src="${DOMAIN_CMS}/images/icon/add2.png">添加课次</a>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
<@footer />

<#include "/pages/jsinclude/course/addCoursejs.ftl"/>
<script>
    $(function(){
        addCourseTime.init();
        $('#courseTotal').attr('courseTotal','${courseTotal!}');
        $('#courseTotalVip').attr('courseTotalVip','${courseTotalVip!}');
        $('#courseTotal').html('￥'+${courseTotal!});
        $('#courseTotalVip').html('￥'+${courseTotalVip!});
    });
</script>
</body>
</html>