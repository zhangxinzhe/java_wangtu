<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-系统参数</title>

</head>


<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
	
    <div id="content">
    	<p class="crumbs">当前位置：系统管理&nbsp;&gt;&nbsp;<a href="${DOMAIN_CMS}/system/systemConfigManage.htm">系统参数</a>&nbsp;&gt;&nbsp;<span>修改参数</span></p>
    	
    	<!--填写内容start-->
        <form method="post" id="creatForm" action="${DOMAIN_CMS}/system/editSystemConfig.htm" >
            <table class="form-table validateForm mt-10">
                 <tr>
                    <th><span class="must">*</span>参数名称：</th>
                    <td>
                        <input type="text" style="overflow:auto;" name="systemConfig.name" value="${systemConfig.name! }" class="input-txt input-txt-disabled" disabled>
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10"><span class="must">*</span>参数值：</th>
                    <td valign="top" class="pt-10">
                        <textarea class="text-area fn-left" id="systemConfig" name="systemConfig.value" validateConfig="${systemConfig.validate! }" style="width:500px;">${systemConfig.value!}</textarea>
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10">备注：</th>
                    <td valign="top" class="pt-10">
                        <textarea class="text-area fn-left input-txt-disabled" disabled  style="width:500px;">${systemConfig.description! }</textarea>
                        <input name="systemConfig.description" style="overflow:auto;" type="hidden" value="${(systemConfig.description)?html }">
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td>
                        <input name="systemConfig.code" value="${systemConfig.code! }" type="hidden"/>
                        <input type="hidden" name="configName" value="${configName!}">
                        <a class="abtn abtn-blue submit" href="javascript:;" id="saveBtn"><span>保存</span></a>
                        <a class="abtn abtn-green reset ml-5" href="${DOMAIN_CMS}/system/systemConfigManage.htm?configName=${configName!}" ><span>返回</span></a>
                        <#if systemConfig.code?? && systemConfig.code == 'wxb.module_params'>
                        <a class="ml-15" href="javascript:;" id="a_show" onclick="$('#wxb_params').show();$(this).hide();$('#a_hide').show();"><span>参数说明-展开</span></a>
                        <a class="ml-15" href="javascript:;" id="a_hide" onclick="$('#wxb_params').hide();$(this).hide();$('#a_show').show();" style="display:none;"><span>参数说明-关闭</span></a>
                        </#if>
                    </td>
                </tr>
                <tr id="wxb_params" style="display:none;">
                    <th>&nbsp;</th>
                    <td>
                        <div class="record-wrap mt-10" style="width:600px;">
                            <table class="public-table">
                                <tr>
                                    <th style="text-align:left; width:30px;"></th>
                                    <th style="text-align:left;">模块</th>
                                    <th style="text-align:left;">参数</th>
                                    <th style="text-align:left; width:50px;">值</th>
                                    <th style="text-align:left;">说明</th>
                                </tr>
                                <tr>
                                    <td class="t-center">1</td>
                                    <td>文档、白板</td>
                                    <td>NoDocShare</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">2</td>
                                    <td>网页共享</td>
                                    <td>NoWebShare</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">3</td>
                                    <td>电脑体检</td>
                                    <td>NoSysCheck</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">4</td>
                                    <td>桌面共享、应用共享</td>
                                    <td>NoAppShare</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">5</td>
                                    <td>多媒体播放</td>
                                    <td>NoPlayMedia</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">6</td>
                                    <td>课堂练习</td>
                                    <td>NoClassTest</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">7</td>
                                    <td>课堂管理</td>
                                    <td>NoClassMgr</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">8</td>
                                    <td>资料下载</td>
                                    <td>NoNetDisk</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">9</td>
                                    <td>统一录制</td>
                                    <td>NoRecord</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">10</td>
                                    <td>课堂点名</td>
                                    <td>NoRollCall</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">11</td>
                                    <td>课堂调查（投票）</td>
                                    <td>NoVote</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">12</td>
                                    <td>提问</td>
                                    <td>NoAskQ</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                                <tr>
                                    <td class="t-center">13</td>
                                    <td>学科符号</td>
                                    <td>NoSEG</td>
                                    <td>0或1</td>
                                    <td>1表示禁用<br/>0或者不传表示不禁用</td>
                                </tr>
                            </table>
                        </div>
                    </td>
                </tr>
            </table>
        </form>
        <!--填写内容end-->
    </div>
</div>
<@footer />
<script type="text/javascript">
$('#saveBtn').bind('click',function(){
    if(VerifyConfig.checkConfig('#systemConfig')){
        $('#creatForm').submit();
    }
});
</script>
</body>
</html>