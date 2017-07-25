<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-点播视频</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=1300000 curSubModId=1300300 />
    <div id="content">
        <p class="crumbs">当前位置：课程管理&nbsp;&gt;&nbsp;<span>点播视频</span></p>
        <ul class="public-tab fn-clear mt-10">
            <li onclick="window.location.href='${DOMAIN_CMS}/vod/addVod.htm?id=${id!}'">课程信息</li>
            <li class="current" onclick="window.location.href='${DOMAIN_CMS}/vod/vodTimeManage.htm?id=${id!}'">课次和价格</li>
        </ul>
        
        <div class="record-wrap mt-20">
            <table class="public-table price-table" index="99">
                <tbody id='cost_tbody'>
                    <tr class="first">
                        <td width='80' class="t-center">课程价格</td>
                        <td width='130'>课程原价:<span oldPrice='${vod.courseOldcost!}'>￥${vod.courseOldcost!}</span></td>
                        <td width='130'>课程现价:<span nowPrice='${vod.courseNowcost!}'>￥${vod.courseNowcost!}</span></td>
                        <td width='130'>会员现价:<span nowPriceVip='${vod.courseNowcostVip!}'>￥${vod.courseNowcostVip!}</span></td>
                        <td width='100' class='t-center'><a href='javascript:;' class='i-edit edit-price' id='editCostPrice' title='编辑套价'></a></td>
                    </tr>
                    <tr class="last">
                        <td width='80' class="t-center" style='color:red;'>合计(点播票)</td>
                        <td width='130' style='color:red;'>课程价格合计:<span id='courseTotal' courseTotal=''></span></td>
                        <td width='130' style='color:red;'>会员价格合计:<span id='courseTotalVip' courseTotalVip=''></span></td>
                        <td style='color:red;' colspan='2'>注：建议【课程原价】金额维护为【课程价格合计】金额</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="record-wrap mt-20">
            <input type="hidden" id="courseId" value="${id!}" />
            <table class="public-table public-table-row price-table" index="99">
                <thead>
                    <tr class="first">
                        <th width="50" class="t-center">序号</th>
                        <th width="300">课次主题</th>
                        <th colspan="3">票价信息</th>
                        <th width="130" class="t-center">操作</th>
                    </tr>
                </thead>
                <tbody id="time_tbody">
                    <#assign courseTotal = 0>
                    <#assign courseTotalVip = 0>
                    <#if timeList?? && timeList?has_content>
                    <#list timeList?if_exists as time>
                        <tr class="tt time-tr" timeValue="${time.id!}">
                            <td class="t-center b"><span class="sort-num">${time_index+1}</span></td>
                            <td>${time.title!}</td>
                             <#if time.timePriceList?? && time.timePriceList?has_content>
                                <#list time.timePriceList?if_exists as price>
                                    <#assign courseTotal = courseTotal + price.nowPrice!>
                                    <#assign courseTotalVip = courseTotalVip + price.nowPriceVip!>
                                    <td width="100"><span nowPrice="${price.nowPrice!}">票价：￥${price.nowPrice!}</span></td>
                                    <td width="100"><span nowPriceVip="${price.nowPriceVip!}">会员价：￥${price.nowPriceVip!}</span></td>
                                    <td><span>${price.remark}</span></td>
                                    <td style="display:none"><span>${price.id!}</span></td>
                                </#list>
                            </#if>
                            <td class="t-center">
                                <a href="javascript:;" class="fn-right i-del ml-20 mr-20 del-time" title="删除课次" dataValue="${time.id!}"></a>
                                <a href="javascript:;" class="fn-right i-edit edit-time" title="编辑课次" dataValue="${time.id!}"></a>
                            </td>
                        </tr>
                        <tr class="last">
                            <td class="t-center b">&nbsp;</td>
                            <#if time.video??>
                            <td width="120"><span class="c-blue"><#if time.video.uploadType ==1>【视频地址维护】<#else>【视频上传】</#if></span>&nbsp;${time.video.fileName!}</td>
                            <td colspan="3">
                                <#-- 2、swf断点续传
                                <div style="padding-top:5px;">
                                    <input type="hidden" value="${time.video.id}" name="video.id" />
                                    <input type="hidden" value="${id!}" name="video.courseId"/>
                                    <input type="hidden" value="${time.id!}" name="video.courseTimeId" />
                                    <input type="hidden" value="${user.id!}" name="userIdTemp" />
                                    <div id="add_video_${time.id!}">
                                        <@upload_block id="add_video_${time.id!}" index='${time_index+1}' total='${timeList?size}' path="/upload/video/${nowYear}/${nowMonth}/${nowDay}/${nowTime.time}.<extName>" fileSize="1024" uniqControl="0" width="200" height="30" afterUpload="after(id,data)"/>
                                    </div>
                                </div> -->
                                
                                <#-- 3、web上传-->
                                <div>
                                    <input type="hidden" value="${time.video.id}" name="video.id" />
                                    <input type="hidden" value="${id!}" name="video.courseId"/>
                                    <input type="hidden" value="${time.id!}" name="video.courseTimeId" />
                                    <input type="hidden" value="${user.id!}" name="userIdTemp" />
                                    
                                    <@upload_block_web id="add_video_${time.id!}" index='${time_index+1}' total='${timeList?size}' path="/upload/video/${nowYear}/${nowMonth}/${nowDay}/${nowTime.time}.<extName>" fileSize="2048" afterUpload="after_web(id,data)"/>
                                </div> 
                            </td>
                            <td class='t-center'> 
                                <a href='javascript:;' class='fn-right i-del ml-20 mr-20 del-video' title='删除视频' dataValue='${time.video.id}'></a>
                                <#-- 1、swf整体上传 
                                <span class="fn-right ml-20" style="position:relative;" title="更新视频">
                                    <a href='javascript:;' class="i-edit"></a>
                                    <#assign params = "{'video.id':'${time.video.id}', 'video.courseId':'${id!}', 'video.courseTimeId':'${time.id!}', 'userIdTemp':'${user.id!}', 'flag':2}">
                                    <@upload_nocss position="top:0px; left:0px;" index='${time_index+1}' total='${timeList?size}' width="16" height="16" fileTypes="*.mp4;*.flv" filesize='2048 MB' action='${DOMAIN_CMS}/vod/upload/addVideo.htm' params='${params}' callBack="callBack_swf(file, serverData, '${id!}')"/>
                                </span>-->
                                <a href='javascript:;' class='fn-right edit-ftp-video' videoId="${time.video.id}" courseId="${id!}" courseTimeId="${time.id!}" userIdTemp="${user.id!}" flag="1" <#if time.video.uploadType ==1>videoFile="${action.showVideoFilePath(time.video.videoFile!)}"</#if> style="display:block;width:16px;height:16px;background-image:url(../../images/icon/i_menu.png);background-repeat:no-repeat;background-position:0px -850px;" title='更新视频地址'></a>
                            </td>
                            <#else>
                            <td width="120">
                                <div>
                                    <a href='javascript:;' class='abtn abtn-blue fn-btn edit-ftp-video' courseId="${id!}" courseTimeId="${time.id!}" userIdTemp="${user.id!}" flag="1"><img src='${DOMAIN_CMS}/images/icon/add2.png'>维护视频地址</a>
                                    <#-- 1、swf整体上传 
                                    <span class="ml-10" style="position:relative;">
                                        <a href='javascript:;' class='abtn abtn-green fn-btn add-price'><img src='${DOMAIN_CMS}/images/icon/add2.png'>上传视频</a>
                                        <#assign params = "{'video.courseId':'${id!}', 'video.courseTimeId':'${time.id!}', 'userIdTemp':'${user.id!}', 'flag':2}">
                                        <@upload_nocss position="top:-5px; left:0px;" index='${time_index+1}' total='${timeList?size}' width="80" height="25" fileTypes="*.mp4;*.flv" filesize='2048 MB' action='${DOMAIN_CMS}/vod/upload/addVideo.htm' params='${params}' callBack="callBack_swf(file, serverData, '${id!}')"/>
                                    </span>-->
                                </div>
                            </td>
                            <td colspan="3">
                                <#-- 2、swf断点续传
                                <div style="padding-top:5px;">
                                    <input type="hidden" value="" name="video.id" />
                                    <input type="hidden" value="${id!}" name="video.courseId"/>
                                    <input type="hidden" value="${time.id!}" name="video.courseTimeId" />
                                    <input type="hidden" value="${user.id!}" name="userIdTemp" />
                                    <div id="add_video_${time.id!}">
                                        <@upload_block id="add_video_${time.id!}" index='${time_index+1}' total='${timeList?size}' path="/upload/video/${nowYear}/${nowMonth}/${nowDay}/${nowTime.time}.<extName>" fileSize="2048" uniqControl="0" width="200" height="30" afterUpload="after(id,data)"/>
                                    </div>
                                </div> -->
                                
                                <#-- 3、web上传 -->
                                <div>
                                    <input type="hidden" value="" name="video.id" />
                                    <input type="hidden" value="${id!}" name="video.courseId"/>
                                    <input type="hidden" value="${time.id!}" name="video.courseTimeId" />
                                    <input type="hidden" value="${user.id!}" name="userIdTemp" />
                                    
                                    <@upload_block_web id="add_video_${time.id!}" index='${time_index+1}' total='${timeList?size}' path="/upload/video/${nowYear}/${nowMonth}/${nowDay}/${nowTime.time}.<extName>" fileSize="2048" afterUpload="after_web(id,data)"/>
                                </div>
                            </td>
                            <td class="t-center">&nbsp;</td>
                            </#if>
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

<#-- 维护视频地址 -->
<div class="popUp-layer layer-500" id="videoUrlDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>视频地址维护</span></p>
    <div class="wrap" style="height:110px;">
        <div class="org-search">
            <div class="org-search-tt">
                <table>
                    <tr>
                        <td><span>视频地址：</span></td>
                        <td>${appsetting.getParam('domain.upload_file')?default('')}/upload/video2/</td>
                    </tr>
                    <tr>
                        <td><span>&nbsp;</span></td>
                        <td>
                            <input type="text" class="input-txt" style="width:300px;" id="videoUrlDiv_videoFile">
                            <a href="javascript:;" class="abtn abtn-blue" id="videoUrl_submit">确定</a>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <p class="onTips f12">视频地址格式建议：20160701/abc.mp4</p>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                        <td>
                            <p id="videoUrlDiv_msg" class="pt-10 pl-10 c-red f12"></p>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>

<@footer />
<#include "/pages/jsinclude/course/addVodjs.ftl"/>
<script>
    $(function(){
        addVodTime.init();
        $('#courseTotal').attr('courseTotal','${courseTotal!}');
        $('#courseTotalVip').attr('courseTotalVip','${courseTotalVip!}');
        $('#courseTotal').html('￥'+${courseTotal!});
        $('#courseTotalVip').html('￥'+${courseTotalVip!});
    });
    
    <#--1、swf整体上传-回调方法-->
    function callBack_swf(file, serverData, courseId) {
        var result = eval('(' + serverData + ')'); 
        if(result.isSuccess == true){
            Tips.showSuccessMsg({
                "info" : "视频上传成功！",
                "callFn" : function(){
                    location.href= Domain.cms_path + "/vod/vodTimeManage.htm?id="+courseId;
                }
            });
        } else{
            Tips.showErrorMsg({
                "info" : result.errorMsg,
                "callFn" : function(){
                    location.href= Domain.cms_path + "/vod/vodTimeManage.htm?id="+courseId;
                }
            });
        }
    }
    
    <#--2、swf断点续传-回调方法；-->
    <#-- 此回调方法不适用多文件同时上传 -->
    function after(id,data) {
        var filePath=data.path;
        var fileName=data.name;
        var tdObj = $('#'+id).parents("td");
        var videoId = tdObj.find($("input[name='video.id']")).val();
        var courseId = tdObj.find($("input[name='video.courseId']")).val();
        var courseTimeId = tdObj.find($("input[name='video.courseTimeId']")).val();
        var userIdTemp = tdObj.find($("input[name='userIdTemp']")).val();
        Sender.ajax({
            "url" : Domain.cms_path + '/vod/upload/addVideo.htm',
            "data" : {
                "flag":3,
                "userIdTemp":userIdTemp,
                "video.courseId":courseId, 
                "video.id":videoId, 
                "video.courseTimeId":courseTimeId ,
                "uploadTempLocalFile":filePath,
                "fileName":fileName
                },
            "dataType" : "json",
            "fn" : function(result){
                if(result.isSuccess){
                    Tips.showSuccessMsg({
                        "info" : "视频上传成功！", 
                        "callFn" : function(){
                            window.location.href = Domain.cms_path + "/vod/vodTimeManage.htm?id=" + courseId;
                        }
                    });
                }else{
                    Tips.showErrorMsg({"info":result.errorMsg});
                }
            }
        });
    }
    
    <#--3、web上传-回调方法；-->
    function after_web(id,data){
        var filePath=data.path;
        var fileName=data.name;
        var tdObj = $('#'+id).parents("td");
        var videoId = tdObj.find($("input[name='video.id']")).val();
        var courseId = tdObj.find($("input[name='video.courseId']")).val();
        var courseTimeId = tdObj.find($("input[name='video.courseTimeId']")).val();
        var userIdTemp = tdObj.find($("input[name='userIdTemp']")).val();
        Sender.ajax({
            "url" : Domain.cms_path + '/vod/upload/addVideo.htm',
            "data" : {
                "flag":3,
                "userIdTemp":userIdTemp,
                "video.courseId":courseId, 
                "video.id":videoId, 
                "video.courseTimeId":courseTimeId ,
                "uploadTempLocalFile":filePath,
                "fileName":fileName
                },
            "dataType" : "json",
            "fn" : function(result){
                if(result.isSuccess){
                    var afterVid = videoId;
                    if(!videoId || videoId=='' || videoId==0) {
                        afterVid = result.promptMsg.substring(8);
                    }
                    var prevTdHtml = "<span class='c-blue'>【视频上传】</span>&nbsp;"+fileName;
                    $(tdObj.prev()).html(prevTdHtml);
                    var nextTdHtml = "<a href='javascript:;' class='fn-right i-del ml-20 mr-20 del-video' title='删除视频' dataValue='"+afterVid+"'></a>" +
                        "<a href='javascript:;' class='fn-right edit-ftp-video' videoId='"+afterVid+"' courseId='"+courseId+"' courseTimeId='"+courseTimeId+"' userIdTemp='"+userIdTemp+"' flag='1' style='display:block;width:16px;height:16px;background-image:url(../../images/icon/i_menu.png);background-repeat:no-repeat;background-position:0px -850px;' title='更新视频地址'></a>";
                    $(tdObj.next()).html(nextTdHtml);
                    tdObj.find($("input[name='video.id']")).val(afterVid);
                }else{
                    Tips.showErrorMsg({"info":result.errorMsg});
                }
            }
        });
    }
    
</script>
</body>
</html>