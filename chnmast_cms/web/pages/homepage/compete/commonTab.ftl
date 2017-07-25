<#macro commonTab curLi=1 competeName='' competeId='' competeType="SZCX">
<ul class="public-tab fn-clear mt-10">
    <span class="tt" title="${competeName!}">${competeName!}</span>
    <input type="hidden" id="objectId" value="${competeId}" />
    <li <#if curLi==1>class="current"</#if> flag="1">基本信息</li>
    <li <#if curLi==4>class="current"</#if> flag="4">组委会</li>
    <li <#if curLi==5>class="current"</#if> flag="5">报名信息</li>
    <li <#if curLi==2>class="current"</#if> flag="2">热点追踪</li>
    <li <#if curLi==3>class="current"</#if> flag="3">活动图集</li>
    <li <#if curLi==6>class="current"</#if> flag="6">轮播图片</li>
    <li <#if curLi==7>class="current"</#if> flag="7">精彩视频</li>
    <#if competeType == competeTypeSZCX><#-- 神州唱响 -->
    </#if>
    <#if competeType == competeTypeHLJHC || competeType == competeTypeHLJYD><#-- 黄龙奖（合唱、乐队） -->
    </#if>
</ul>

<script>
    $(function(){
        $(".public-tab li").click(function(){
            var id= $("#objectId").val();
            var url = "/homepage/compete/editCompete.htm";
            var flag = $(this).attr("flag");
            if(flag == "1"){
                var url = "/homepage/compete/editCompete.htm";
            }else if(flag == "2"){
                url = "/homepage/compete/tabHotspot.htm";
            }else if(flag == "3"){
                url = "/homepage/compete/tabPicture.htm";
            }else if(flag == "4"){
                url = "/homepage/compete/tabCommittee.htm";
            }else if(flag == "5"){
                url = "/homepage/compete/tabAttend.htm";
            }else if(flag == "6"){
                url = "/homepage/compete/tabPlayPicture.htm";
            }else if(flag == "7"){
                url = "/homepage/compete/tabVideo.htm";
            }
            url = Domain.cms_path + url + "?id=" + id;
            window.location.href = url;      
        });
    });
</script>
</#macro>