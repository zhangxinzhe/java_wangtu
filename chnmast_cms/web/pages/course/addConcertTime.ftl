<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-音乐会</title>

</head>
<body>
<@header curAppId=appId/>
<div id="container" class="fn-clear">
    <@sidebar curTopModId=1300000 curSubModId=1300200 />
    
    <div id="content">
        <p class="crumbs">当前位置：课程管理&nbsp;&gt;&nbsp;<span>音乐会</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li onclick="window.location.href='${DOMAIN_CMS}/concert/addConcert.htm?id=${id!}'">音乐会信息</li>
            <li class="current" onclick="window.location.href='${DOMAIN_CMS}/concert/concertTimeManage.htm?id=${id!}'">场次和票价</li>
        </ul>
        
        <div class="record-wrap mt-20">
            <input type="hidden" id="courseId" value="${id!}" />
            <table class="public-table public-table-row" index="99">
                <thead>
                    <tr class="first">
                        <th width="60" class="t-center">序号</th>
                        <th width="130">日期</th>
                        <th width="120">开始时间</th>
                        <th width="120">结束时间</th>
                        <th>场次主题</th>
                        <th width="85" class="t-center">操作</th>
                    </tr>
                </thead>
                <tbody id="time_tbody">
                    <#if timeList?? && timeList?has_content>
                    <#list timeList?if_exists as time>
                        <tr class="tt time-tr" timeValue="${time.id!}">
                            <td class="t-center b"><span class="sort-num">${time_index+1}</span></td>
                            <td>${time.beginTime?string('yyyy-MM-dd')}</td>
                            <td>${time.beginTime?string('HH:mm')}</td>
                            <td>${time.endTime?string('HH:mm')}</td>
                            <td>${time.title!}</td>
                            <td class="t-center">
                            <#if !time.isHistory>
                                <a href="javascript:;" class="i-edit edit-time" title="编辑场次" dataValue="${time.id!}"></a><a href="javascript:;" class="i-del ml-10 del-time" title="删除场次" dataValue="${time.id!}"></a>
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
                                            <#assign temp="直播票">
                                            <#if price.studyType.value == 1 && price.priceType.value == 0>
                                            <#assign temp="现场正式票">
                                            <#elseif price.studyType.value == 1 && price.priceType.value == 1>
                                            <#assign temp="现场预售票">
                                            </#if>
                                            <td width="110"><span studyType="${price.studyType.value}" priceType="${price.priceType.value}">${temp}</span></td>
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
                            <a href="javascript:;" class="abtn abtn-green fn-btn" id="time_add"><img src="${DOMAIN_CMS}/images/icon/add2.png">添加场次</a>
                        </td>
                    </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
<@footer />

<#include "/pages/jsinclude/course/addConcertjs.ftl"/>
<script>
    $(function(){
        addConcertTime.init();
        
        <#-- 初始化场次列表 
        <#if timeList?? && timeList?has_content>
        <#list timeList?if_exists as time>
            var bhHtml = concertTimeManage.buildHourSelectHtml("beginHours", ${time.beginTime?string('HH')}, ${time.isHistory?string});
            var bmHtml = concertTimeManage.buildMinuteSelectHtml("beginMinutes", ${time.beginTime?string('mm')}, ${time.isHistory?string});
            var ehHtml = concertTimeManage.buildHourSelectHtml("endHours", ${time.endTime?string('HH')}, ${time.isHistory?string});
            var emHtml = concertTimeManage.buildMinuteSelectHtml("endMinutes", ${time.endTime?string('mm')}, ${time.isHistory?string});
            var data = "${time.beginTime?string('yyyy-MM-dd')}";
            var str = "<tr>" +
                "<td class='t-center'>${time_index+1}</td>" +
                "<td><input type='text' <#if time.isHistory>class='input-txt input-txt-disabled'<#else>class='input-date' onclick='WdatePicker({isShowClear:false,readOnly:true,isShowWeek:true,minDate:\"%y-%M-%d\"})'</#if> style='width:90px;' name='days' readonly value='"+data+"' isHistoryData='${time.isHistory?string}'/></td>" + 
                "<td>" + bhHtml + bmHtml + "</td>" +
                "<td>" + ehHtml + emHtml + "</td>" +
                "<td><input type='text' <#if time.isHistory>class='input-txt input-txt-disabled' readonly<#else>class='input-txt'</#if> style='width:250px;' name='timeTitles' value='${time.title!}' maxlength='75' /></td>" +
                "<td><#if !time.isHistory><a href='javascript:;' class='i-del' title='删除' dataValue='${time.id!}'></a></#if></td>" +
                "</tr>";
            $("#time_tbody").append(str);
        </#list>
        </#if>-->
    });
</script>
</body>
</html>