<#--
    顶部子系统header，
    curAppId：当前子系统id，数值型，默认为1
-->
<#macro header curAppId=1 >
    <div id="header">
        <div class="header">
            <div class="logo">后台管理</div>
           <#-- <#if apps?exists && (apps?size>1) > -->
            <#if apps?exists >
                <div class="menu">
                <#list apps as app>
                    <a href="${app.indexUrl!}" <#if app.id==curAppId>class="current"</#if> >${app.appName!}</a>
                </#list>
                </div>
            </#if>
            <div class="login-info">
                <a href="/logout.htm" class="logout" title="登出"></a>
                <span class="user"><span>${user.realName!}</span><a href="/resetPword.htm" class="find-pwd">修改密码</a></span>
            </div>
        </div>
    </div>
</#macro>


<#--
    左侧菜单sidebar
    curTopModId：当前一级菜单模块id，数值型
    curSubModId：当前二级菜单模块id，数值型
-->
<#macro sidebar curTopModId=0 curSubModId=0 >
    <#if topModules?exists>
    <div id="sidebar">
        <#list topModules as topModule>
            <#assign curIndex = topModule_index +1>
            <div class="side-item  <#if topModule.id == 1100000>side-item-home</#if> <#if topModule.id==curTopModId>side-item-open</#if>" >
                <p class="side-item-tt"><a href="<#if topModule.url?exists>${topModule.url!}<#else>javascript:;</#if>" class="a${curIndex}" >${topModule.name!}</a></p>
                <ul>
                    <#if subModuleMap.get(topModule.id)?exists>
                        <#list subModuleMap.get(topModule.id) as subModule>
                        <li><a href="${subModule.url!}" <#if subModule.id==curSubModId>class="current"</#if> >${subModule.name!}</a></li>
                        </#list>
                    </#if>
                </ul>
            </div>
        </#list>
    </div>
    </#if>
</#macro>

<#macro footer >
    <div id="footer">
        <p>Copyright 2012-2020 北京国臻教育科技有限公司 版权所有</p>
        <p>备案号 浙B2-20100206-5</p>
    </div>
</#macro>


<#-- 截取指定长度的字符串 -->
<#macro cutOff cutStr="" cutLen="10">${appsetting.subStr(cutStr, cutLen?number)}</#macro>

<#-- 限宽限高css样式 -->
<#macro ImgStyle name='' width='100' height='100'>
<style>
    #${name}{ width:expression((this.width > ${width}) ? "${width}px" : true);height:expression((this.height > ${height}) ? "${height}px" : true); max-height:${height}px; max-width:${width}px; border:0;}
</style>
</#macro>

<#--计数器，调用参数i-->
<#macro counter>
    <#if i?exists >
        <#assign i=i+1/>
    <#else>
        <#assign i=0/>
    </#if>
</#macro>


<#-- 功能：上传图片，临时路径，不改变静态页面样式。（定位，上传按钮宽高，文件最大限制，处理上传的url，支持文件类型，支持文件类型的描述）
        注:用此方法，需将其父类标签添加相对定位样式 style="position:relative;"
        
        参数如下：
     position      自定义上传按钮的相对位置，top right bottom left
     width         自定义上传按钮的长度，默认80
     height        自定义上传按钮的宽度，默认20
     index         同一页面中有多个该上传控件时每个控件的索引，默认不指定
     total         同一页面中有多个该上传控件的总数，默认不指定
     filesize      上传文件大小的上限，默认10 MB
     action        上传文件的访问URL，可修改。
                                                         默认：${DOMAIN_CMS}/common/upload/uploadTemp.htm
                                                         上传图片（临时）:${DOMAIN_CMS}/common/upload/uploadPicTemp.htm
     fileTypes     上传文件类型，多个时用分号隔开，默认*
     description   文件选择对话框文件类型的描述，默认所有文件
     display       是否显示上传进度条，默认false不显示，显示true
     callBack      文件上传成功后回调方法，默认为空
-->
<#macro uploadPic_temp_nocss position='' width=80 height=20 imgId='' index='' total='' filesize='10 MB' action='${DOMAIN_CMS}/common/upload/uploadTemp.htm' jsessionid='0' fileTypes='*.*' description='所有文件' display=false callBack=''>
<#if index==total>
    <link href="${DOMAIN_CMS}/js/component/swfupload/common/default.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${DOMAIN_CMS}/js/component/swfupload/single/js/handlers.js"></script>
</#if>
<script type="text/javascript">
<#if index==total>
    <#--根据不同的浏览器加载不同的js文件 -->
    if(!+[1,]){
        setTimeout(function(){<#--等待jQuery、handlers.js加载完成后-->
            jQuery.getScript("${DOMAIN_CMS}/js/component/swfupload/common/swfupload22.js", function(){initSingleUpload();});
        }, 500);
    }else{
        setTimeout(function(){<#--等待jQuery、handlers.js加载完成后-->
            jQuery.getScript("${DOMAIN_CMS}/js/component/swfupload/common/swfupload20.js", function(){initSingleUpload();});
        }, 500);
    }
    
    var initSingleUpload = function(){
        <#if index == ''>
            <#-- 单个文件上传 -->
            onloadUrl();
        <#elseif index==total>
            <#-- 有多个上传框，需要依次调用初始化语法 -->
            var total = '${total}';
            for(var i=1; i<=parseInt(total); i++){
                eval("onloadUrl"+i+"()");
            }
        </#if>
    }
</#if>
    var swfu${index};
    var application_url = '${DOMAIN_CMS}/js/component/swfupload/';

    //上传完毕后回调js函数，不论成功还是失败都调用此方法
    var _uploadCallBack${index } = function(file, tempFile){
        // 临时路径
        document.getElementById("uploadTempFile${index }").value=tempFile;
        // 图片预览
        if("${imgId!}" != "" && $("#${imgId!}")){
            var src= "${DOMAIN_UPLOAD_FILE!}/" + tempFile + "?" + new Date().getTime();
            $("#${imgId!}").attr("src", src);
        }else{
           Tips.loaded();
        }
        ${callBack?default('')}
    };
    
    <#-- 文件上传中重载方法 -->
    function uploadProgress_new${index }(file, bytesLoaded){
        // 图片加载效果
        if("${imgId!}" != "" && $("#${imgId!}")){
            var $avatar = $("#${imgId!}");
            $avatar.attr("src","/images/loading.gif");
        }else{
            var str = "";
            var percent = Math.floor(bytesLoaded/file.size *100);
            if(bytesLoaded == file.size){
                Tips.loading({info:'上传完成，正在保存中...'});
            }else{
                Tips.loading({info:'文件上传中，请稍候('+percent+'%)'});
            }
            
        }
    }
    
    function fileQueueError_new${index }(file, errorCode, message){
        var errorName = "";
        if (errorCode === SWFUpload.errorCode_QUEUE_LIMIT_EXCEEDED) {
            errorName = "You have attempted to queue too many files.";
            return;
        }

        if (errorName !== "") {
            alert(errorName);
            return;
        }

        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                Tips.showErrorMsg({info:"上传的文件内容为空！"});
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                Tips.showErrorMsg({info:"上传的文件超过限定大小！"});
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                Tips.showErrorMsg({info:"不是允许的文件类型！"});
                break;
            default:
                Tips.showErrorMsg({info:message});
                break;
        }
    }
    
    var onloadUrl${index} = function(){
        swfu${index} = new SWFUpload({
            // Backend Settings
            upload_url: "${action};jsessionid=${jsessionid}",
            post_params: {},

            file_size_limit : "${filesize}",
            file_types : "${fileTypes}",
            file_types_description : "${description}",
            file_upload_limit : 0,

            file_queue_error_handler : fileQueueError_new${index },
            file_dialog_complete_handler : fileDialogComplete,
            upload_progress_handler : uploadProgress_new${index },
            upload_error_handler : uploadError,
            upload_success_handler : uploadSuccess,
            upload_complete_handler : uploadComplete,

            // Button Settings
            button_image_url : application_url+"single/images/SmallSpyGlassWithTransperancy_17x18.png",
            button_placeholder_id : "spanButtonPlaceholder${index}",
            button_width: ${width},
            button_height: ${height},
            button_text : '',
            button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 12pt; }',
            button_text_top_padding: 0,
            button_text_left_padding: 18,
            button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
            button_cursor: SWFUpload.CURSOR.HAND,
            button_action : SWFUpload.BUTTON_ACTION.SELECT_FILE,
            
            // Flash Settings
            flash_url : "${DOMAIN_CMS}/js/component/swfupload/common/swfupload.swf",

            custom_settings : {
                upload_target : "divFileProgressContainer${index}",
                call_black : _uploadCallBack${index },<#-- 回调函数 -->
                plugins_index:"${index }"<#--同一个页面多个上传插件时插件的索引 -->
            },
            
            // Debug Settings
            debug: false
        });
    };

</script>
<span style="position:absolute;filter:alpha(opacity:0);opacity:0.01;cursor:pointer;${position}">
    <span id="spanButtonPlaceholder${index}"></span>
</span>
<div id="divFileProgressContainer${index}" <#if !display>style="display: none"</#if> ></div>
<div id="thumbnails${index }"></div>
<#-- 上传成功后会在此input中填写临时文件路径 -->
<input type="hidden" name="uploadTempFile" id="uploadTempFile${index }" value="${uploadTempFile!}"/>
</#macro>


<#--  功能：直接上传文件（图片），不改变静态页面样式。（定位，上传按钮宽高，文件最大限制，处理上传的url，支持文件类型，支持文件类型的描述）
           注:用此方法，需将其父类标签添加相对定位样式 style="position:relative;"
                  默认上传到临临时文件目录，可修改action地址直接上传到文件目录（action不连接参数，使用params）
        
        参数如下：
     position      自定义上传按钮的相对位置，top right bottom left
     width         自定义上传按钮的长度，默认80
     height        自定义上传按钮的宽度，默认20
     index         同一页面中有多个该上传控件时每个控件的索引，默认不指定
     total         同一页面中有多个该上传控件的总数，默认不指定
     filesize      上传文件大小的上限，默认10 MB
     action        上传文件的访问URL，可修改。无需加jsessionid后缀
                                                          默认：${DOMAIN_CMS}/common/upload/uploadTemp.htm
                                                         上传图片（临时）:${DOMAIN_CMS}/common/upload/uploadPicTemp.htm
     params        Url参数，json形式
     fileTypes     上传文件类型，多个时用分号隔开，默认*
     description   文件选择对话框文件类型的描述，默认所有文件
     callBack      文件上传成功后回调方法，默认为空
-->
<#macro upload_nocss position='' width=80 height=20 index='' total='' filesize='10 MB' action='${DOMAIN_CMS}/common/upload/uploadTemp.htm' params='{}' fileTypes='*.*' description='所有文件' jsessionid='0' callBack=''>
<#if index==total>
    <link href="${DOMAIN_CMS}/js/component/swfupload/common/default.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${DOMAIN_CMS}/js/component/swfupload/single/js/handlers.js"></script>
</#if>
<script type="text/javascript">
<#if index==total>
    <#--根据不同的浏览器加载不同的js文件 -->
    if(!+[1,]){
        setTimeout(function(){<#--等待jQuery、handlers.js加载完成后-->
            jQuery.getScript("${DOMAIN_CMS}/js/component/swfupload/common/swfupload22.js", function(){initSingleUpload();});
        }, 500);
    }else{
        setTimeout(function(){<#--等待jQuery、handlers.js加载完成后-->
            jQuery.getScript("${DOMAIN_CMS}/js/component/swfupload/common/swfupload20.js", function(){initSingleUpload();});
        }, 500);
    }
    
    var initSingleUpload = function(){
        <#if index == ''>
            <#-- 单个文件上传 -->
            onloadUrl();
        <#elseif index==total>
            <#-- 有多个上传框，需要依次调用初始化语法 -->
            var total = '${total}';
            for(var i=1; i<=parseInt(total); i++){
                eval("onloadUrl"+i+"()");
            }
        </#if>
    }
    
    
    <#-- 文件上传成功后重载方法 -->
    function uploadSuccess_new(file, serverData) {
        Tips.loaded();
        
        ${callBack?default('')}
    }

    <#-- 文件上传中重载方法 -->
    function uploadProgress_new(file, bytesLoaded){
        Tips.loading({info:'文件上传中，请稍候...'});
    }
    
    function fileQueueError_new(file, errorCode, message){
        var errorName = "";
        if (errorCode === SWFUpload.errorCode_QUEUE_LIMIT_EXCEEDED) {
            errorName = "You have attempted to queue too many files.";
            return;
        }

        if (errorName !== "") {
            alert(errorName);
            return;
        }

        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                Tips.showErrorMsg({info:"上传的文件内容为空！"});
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                Tips.showErrorMsg({info:"上传的文件超过限定大小！"});
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                Tips.showErrorMsg({info:"不是允许的文件类型！"});
                break;
            default:
                Tips.showErrorMsg({info:message});
                break;
        }
    }
</#if>

    var swfu${index};
    var application_url = '${DOMAIN_CMS}/js/component/swfupload/';
    var onloadUrl${index} = function(){
        swfu${index} = new SWFUpload({
            // Backend Settings
            upload_url: "${action};jsessionid=${jsessionid}",
            post_params: ${params},

            file_size_limit : "${filesize}",
            file_types : "${fileTypes}",
            file_types_description : "${description}",
            file_upload_limit : 0,

            file_queue_error_handler : fileQueueError_new,
            file_dialog_complete_handler : fileDialogComplete,
            upload_progress_handler : uploadProgress_new,
            upload_error_handler : uploadError,
            upload_success_handler : uploadSuccess_new,
            upload_complete_handler : uploadComplete,

            // Button Settings
            button_image_url : application_url+"single/images/SmallSpyGlassWithTransperancy_17x18.png",
            button_placeholder_id : "spanButtonPlaceholder${index}",
            button_width: ${width},
            button_height: ${height},
            button_text : '',
            button_text_style : '.button { font-family: Helvetica, Arial, sans-serif; font-size: 12pt; } .buttonSmall { font-size: 12pt; }',
            button_text_top_padding: 0,
            button_text_left_padding: 18,
            button_window_mode: SWFUpload.WINDOW_MODE.TRANSPARENT,
            button_cursor: SWFUpload.CURSOR.HAND,
            button_action : SWFUpload.BUTTON_ACTION.SELECT_FILE,
            
            // Flash Settings
            flash_url : "${DOMAIN_CMS}/js/component/swfupload/common/swfupload.swf",

            custom_settings : {
                plugins_index:"${index }"<#--同一个页面多个上传插件时插件的索引 -->
            },
            
            // Debug Settings
            debug: false
        });
    };
</script>
<span style="position:absolute;filter:alpha(opacity:0);opacity:0.01;cursor:pointer;${position}">
    <span id="spanButtonPlaceholder${index}"></span>
</span>
</#macro>


<#--
    百度编辑器 
    id:id, name:名称,content:内容,width:宽,height:高,params:自定义编辑属性,UdCapture:显示截图,KfContent:显示公式编辑器
-->
<#macro ueditor id name='' content='' width='' height='' params="" UdCapture=true KfContent=true UpPdf=true UpMusic=true>
    <!--百度编辑器-->
    <script type="text/javascript" charset="utf-8" src="/js/component/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/component/ueditor/ueditor.all.min.js"> </script>
    <#if UpPdf>
    <!--pdf上传-->
    <script type="text/javascript" src="/js/component/ueditor/plugin/upPdf/addUpPdfBtn.js"></script>
    <script type="text/javascript" src="/js/component/ueditor/plugin/upPdf/upPdf.js"></script>
    </#if>
    
    <#if UpMusic>
    <!--音乐上传-->
    <script type="text/javascript" src="/js/component/ueditor/plugin/upMusic/addUpMusicBtn.js"></script>
    <script type="text/javascript" src="/js/component/ueditor/plugin/upMusic/upMusic.js"></script>
    </#if>
    
    <#if UdCapture>
    <!--截图-->
    <script type="text/javascript" src="/js/component/ueditor/plugin/udCapture/UdCapture.js"></script>
    <script type="text/javascript" src="/js/component/ueditor/plugin/udCapture/addUdCaptureButton.js"></script>
    </#if>
    
    <#if KfContent>
    <!-- 公式编辑器Js -->
    <script type="text/javascript" src="/js/component/ueditor/plugin/kityformula-plugin/addKityFormulaDialog.js"></script>
    <script type="text/javascript" src="/js/component/ueditor/plugin/kityformula-plugin/getKfContent.js"></script>
    <script type="text/javascript" src="/js/component/ueditor/plugin/kityformula-plugin/defaultFilterFix.js"></script>
    </#if>
    
    <script id="${id!}" name="${name!}" type="text/plain" style="${(width?has_content)?string('width:' + width + 'px;','')}${(height?has_content)?string('height:' + height + 'px;','')}">${content!}</script>
    
    
    <!-- 编辑器事件_自定义显示 -->
    <script type="text/javascript">
        //ckplay.swf所在的目录作为参数传入ueidtor
        var param = {ck_base_path:'/js/component/ckplayer'};
        //与自定义参数合并
        param = $.extend(param,${params?has_content?string(params,'{}')});
        var ue = UE.getEditor(
            '${id}',
            param
        );
    </script>
</#macro>


<#-- 公共分页1 -->
<#macro commonPage1 contentDiv="">
    <#list pageItems?if_exists as pageItem>
        <#if pageItem.pages>
            <#if pageItem.hasHref>
                <a href="javascript:void(0)" class='ml-10' onclick="${pageItem.href!};return false;">${pageItem.text!}</a>
            <#else>
                <span class="no">${pageItem.text!}</span>
            </#if>
        <#else>
            ${pageItem.text!}
        </#if>
    </#list>
    <div class="clear"></div>
    
    <script type="text/javascript">
        function gotoPage(url){
            <#if contentDiv != "">
                Sender.load({
                    "url":url,
                    "div":"#${contentDiv}"
                });
            <#else>
                window.location.href=url;
            </#if>
        }
    </script>
</#macro>

<#-- 公共分页2：可控制页数和每页显示条数 -->
<#macro commonPage2>
    <#list pageItems2 as pageItem>
        <#if pageItem.pages>
            <#if pageItem.hasHref>
            <a href="javascript:void(0)" class='ml-10' onclick="${pageItem.href!};return false;">${pageItem.text!}</a>
            <#else>
            <span class="no">${pageItem.text!}</span>
            </#if>
        <#else>
            ${pageItem.text!}
        </#if>
    </#list>
    <div class="clear"></div>
    <script type="text/javascript">
        function gotoPage(url,rowNum, currentPage,totalNum){
            var sizePerPage = $('#sizePerPage').val();
            if(!sizePerPage || isNaN(sizePerPage) || sizePerPage.indexOf(".") > -1 || sizePerPage < 1 || sizePerPage >100){
              alert('每页条数为正整数，最大不超过100');
              return;
            }
            if(currentPage == undefined && sizePerPage == rowNum){
              return;
            }
            //重新计算总页数
            var totalPage=Math.ceil(totalNum/sizePerPage);
            if(url.indexOf('?') != -1){
              url=url + "&page.rowNum="+sizePerPage;
            }else{
              url=url + "?page.rowNum="+sizePerPage;
            }
            //判断是否是跳转页面
            if (currentPage != undefined){
                var current = $("#currentPage").val();
        
                if(!current || isNaN(current) || current.indexOf(".") > -1 || current < 1 || current > totalPage){
                    alert('跳转页数为正整数，最大不超过' + totalPage);
                    return;
                }
                
                url=url.replace("page.currentPage="+currentPage,"page.currentPage="+current);
            }else{
                if(rowNum && sizePerPage != rowNum){
                    var reg = new  RegExp("page.currentPage=(\\d+)","g");
                    url=url.replace(reg,"page.currentPage=1");
                }
            }
            window.location.href=url;
        }
    </script>
</#macro>

<#-- 双分页（列表上下都含有分页功能）
            并且为同一page对象
-->
<#macro doublePage2 className_top="public-page" className_bottom="public-page">
    <div class="${className_top!} page_div">
    <#if pageItems2?has_content>
        <#list pageItems2 as pageItem>
            <#if pageItem.pages>
                <#if pageItem.hasHref>
                <a href="javascript:void(0)" class='ml-10' onclick="${pageItem.href!};return false;">${pageItem.text!}</a>
                <#else>
                <span class="no">${pageItem.text!}</span>
                </#if>
            <#else>
                ${pageItem.text!}
            </#if>
        </#list>
    </#if>
    </div>
    <div class="clear"></div>
    
    <#nested>
    
    <div class="${className_bottom!} page_div">
    <#if pageItems2?has_content>
        <#list pageItems2 as pageItem>
            <#if pageItem.pages>
                <#if pageItem.hasHref>
                <a href="javascript:void(0)" class='ml-10' onclick="${pageItem.href!};return false;">${pageItem.text!}</a>
                <#else>
                <span class="no">${pageItem.text!}</span>
                </#if>
             <#else>
                ${pageItem.text!}
            </#if>
        </#list>
    </#if>
    </div>
    <div class="clear"></div>
    
    <script type="text/javascript">
        function gotoPage(url,rowNum, currentPage,totalNum){
            var evt = window.event || arguments.callee.caller.arguments[0]; // 获取event对象(ie || Firefox)
            var obj = evt.srcElement ? evt.srcElement : evt.target;
            
            //var sizePerPage = $('#sizePerPage').val();
            var sizePerPage = $(obj).parents(".page_div").find("#sizePerPage").val();
            
            if(!sizePerPage || isNaN(sizePerPage) || sizePerPage.indexOf(".") > -1 || sizePerPage < 1 || sizePerPage >100){
              alert('每页条数为正整数，最大不超过100');
              return;
            }
            if(currentPage == undefined && sizePerPage == rowNum){
              return;
            }
            //重新计算总页数
            var totalPage=Math.ceil(totalNum/sizePerPage);
            if(url.indexOf('?') != -1){
              url=url + "&page.rowNum="+sizePerPage;
            }else{
              url=url + "?page.rowNum="+sizePerPage;
            }
            //判断是否是跳转页面
            if (currentPage != undefined){
                //var current = $("#currentPage").val();
                var current = $(obj).parents(".page_div").find("#currentPage").val();
        
                if(!current || isNaN(current) || current.indexOf(".") > -1 || current < 1 || current > totalPage){
                    alert('跳转页数为正整数，最大不超过' + totalPage);
                    return;
                }
                
                url=url.replace("page.currentPage="+currentPage,"page.currentPage="+current);
            }else{
                if(rowNum && sizePerPage != rowNum){
                    var reg = new  RegExp("page.currentPage=(\\d+)","g");
                    url=url.replace(reg,"page.currentPage=1");
                }
            }
            window.location.href=url;
        }
    </script>
</#macro>

<#--静态加载整棵树
    jquery ： 最好使用ztree-demo中jquery-1.4.4.min.js
    action : 不能为空
    openSelect ： 是否打开选择框
    isUseRadio ： 选择框是radio/checkbox；false:checkbox、true:radio;(openSelect=true才起作用)
    isShowLine : 是否显示连线 默认显示
    isShowIcon : 是否显示图表 默认显示
    index      : 序号数，页面中第几个ztree
    total      : 总个数，页面中ztree个数
    
            设置解释：
        Y:"ps"   选中时，设置父子关联关系；p:关联父、s:关联子
        N:"ps"   取消选中时，设置父子关联关系；p:关联父、s:关联子
        radioType  radio分组范围：整棵树内:all、同一级目录:level
    
            说明：若使用下面方法，则需注意宏index参数
        expandAll：树节点 全部展开/全部收缩:true/false
        checkAllNodes：树节点 全选/取消全选:true/false
        getCheckedNodeIds：获取选中的所有节点ids，并存储id="checkedNodes"的隐藏域,return id1,id2,id3,id4...
        
-->
<#macro staticZTree action openSelect=false isUseRadio=false isShowLine=true isShowIcon=true index="" total="">
<#if index == total>
<link type="text/css" rel="stylesheet" href="/js/component/zTree/css/zTreeStyle.css" />

<script type="text/javascript" src="/js/component/zTree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="/js/component/zTree/jquery.ztree.excheck-3.5.js"></script>
</#if>
<script>
    var setting${index} = {
    
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "parentId"
            }
        }
        
        ,view: {
            dblClickExpand: true,
            <#if !isShowLine>
            showLine: false,
            </#if>
            <#if !isShowIcon>
            showIcon: false
            </#if>
        }
        
        <#if openSelect>
        ,check: {
            enable: true,
            chkboxType:{
                Y:"ps",
                N:"ps"
            }
            <#if isUseRadio>
            ,chkStyle: "radio"
            ,radioType: "all"
            </#if>
        }
        </#if>
        
    };
    
    function creatTree${index}() {
        var zNodes = [];
         $.ajax({
            type: 'POST',
            dataType: "text",
            cache: false, 
            url: "${action}",
            data: {},
            success: function (data) {
                zNodes = data;
                //初始化ztree
                if(data==""){
                    $("#treeDemo${index}").html("<div style=\"float:right; color:red\">暂无权限数据请分配</div>");
                }else{
                $.fn.zTree.init($("#treeDemo${index}"), setting${index}, eval('(' + zNodes + ')'));
                }
            } 
        });
    }
      
    $(document).ready(function(){
        creatTree${index}();
    });
    
    <#-- 树节点 全部展开/全部收缩:true/false -->
    function expandAll${index}(expandFlag){
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo${index}");
        if(expandFlag){
            treeObj.expandAll(true);
        }
        else{
            treeObj.expandAll(false);
        }
    }
    
    <#-- 树节点 全选/取消全选:true/false -->
    function checkAllNodes${index}(checked){
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo${index}");
        if(checked){
            treeObj.checkAllNodes(true);
        }
        else{
            treeObj.checkAllNodes(false);
        }
    }
    
    <#-- 获取选中的所有节点ids，并存储id="checkedNodes"的隐藏域
        return id1,id2,id3...
    -->
    function getCheckedNodeIds${index}(){
        var tree_ids = "";
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo${index}");
        var checkedNodes;
        
        var disabledNodes = treeObj.getNodesByParam("chkDisabled", true);
        if(disabledNodes.length > 0){
            <#--1.遍历取消禁用状态-->
            for (var i=0; i<disabledNodes.length; i++) {
                treeObj.setChkDisabled(disabledNodes[i], false);
            }
            <#--2.获取选中的节点-->
            checkedNodes = treeObj.getCheckedNodes(true);
            <#--3.遍历恢复禁用状态-->
            for (var i=0; i<disabledNodes.length; i++) {
                treeObj.setChkDisabled(disabledNodes[i], true);
            }
        }else{
            checkedNodes = treeObj.getCheckedNodes(true);
        }
        
        if(checkedNodes.length==0){
            alert("请至少选择一个模块！");
            return;
        }
        for(var i=0;i<checkedNodes.length;i++){
            if(checkedNodes[i].object.id != null){
                tree_ids += (checkedNodes[i].object.id + ',');
            }
        }
        tree_ids = tree_ids.substring(0,tree_ids.length-1);
        $("#checkedNodes${index}").val(tree_ids);
        return tree_ids;
    }
    
</script>
<div>
    <input type="hidden" id="checkedNodes${index}" name="checkedNodes" value="">
    <div>
        <ul id="treeDemo${index}" class="ztree" style="height:auto; width:250px"></ul>
    </div>
</div>
</#macro>


<#-- 异步加载ztree（分节点异步加载） 
    action : 不能为空
    openSelect ： 是否打开选择框
    isUseRadio ： 选择框是radio/checkbox；false:checkbox、true:radio;(openSelect=true才起作用)
    isShowLine : 是否显示连线 默认显示
    isShowIcon : 是否显示图表 默认显示
    index      : 序号数，页面中第几个ztree
    total      : 总个数，页面中ztree个数
    
            说明：若重写回调方法或使用下面方法，则需注意宏index参数。
                        如：页面中第二个树节点点击事件 index=2 total=5，则：treeItemClick2(){}
        asyncAllNodes：异步加载，内部加载树所有数据
        getCheckedNodeIds：获取选中的所有节点ids，并存储id="checkedNodes"的隐藏域,return id1,id2,id3,id4...
-->
<#macro asyncZTree action="" openSelect=false isUseRadio=false isShowLine=true isShowIcon=true index="" total="">
<#if index == total>
<link type="text/css" rel="stylesheet" href="${staticPath}/js/component/zTree/css/zTreeStyle.css" />

<script type="text/javascript" src="${staticPath}/js/component/zTree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${staticPath}/js/component/zTree/jquery.ztree.excheck-3.5.js"></script>
</#if>

<script>

    var zTree${index};
    
    var setting${index} = {
        async: {
            enable: true,
            type: "post",
            url: "${action}",
            autoParam: ["id=parentId"],
            otherParam: {}
        }
        
        ,callback: {
            onClick: clickNode${index},
            onAsyncError: asyncError${index},
            onAsyncSuccess: asyncSuccess${index}
        }
        
        ,view: {
            dblClickExpand: true,
            <#if !isShowLine>
            showLine: false,
            </#if>
            <#if !isShowIcon>
            showIcon: false
            </#if>
        }
        
        <#if openSelect>
        ,check: {
            enable: true,
            chkboxType:{
                Y:"s",
                N:"ps"
            }
            <#if isUseRadio>
            ,chkStyle: "radio"
            ,radioType: "all"
            </#if>
        }
        </#if>
    };

    $(document).ready(function(){
        $.fn.zTree.init($("#treeDemo${index}"), setting${index});
        zTree${index} = $.fn.zTree.getZTreeObj("treeDemo${index}");
    });
    
    <#-- 节点被点击的事件 （不需要修改此方法，重写treeItemClick()方法）-->
    function clickNode${index}(event, treeId, treeNode) {
        treeItemClick${index}(event, treeId, treeNode);
    }
    function treeItemClick${index}(event, treeId, treeNode) {
    }
    
    
    <#-- 异步加载出现异常错误 （不需要修改此方法，重写zTreeOnAsyncError()方法）-->
    function asyncError${index}(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
        zTreeOnAsyncError${index}(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown);
    }
    function zTreeOnAsyncError${index}(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
    }
    
    
    <#-- 异步加载正常结束  （不需要修改此方法，重写zTreeOnAsyncSuccess()方法） -->
    function asyncSuccess${index}(event, treeId, treeNode, msg) {
        zTreeOnAsyncSuccess${index}(event, treeId, treeNode, msg);
    }
    function zTreeOnAsyncSuccess${index}(event, treeId, treeNode, msg) {
    }
    
    
    <#-- 异步悄悄加载所有节点 -->
    function asyncAllNodes${index}() {
        asyncNodes${index}(zTree${index}.getNodes());
    }
    <#-- 加载树节点 -->
    function asyncNodes${index}(nodes) {
        if (!nodes) return;
        for (var i=0, l=nodes.length; i<l; i++) {
            if (nodes[i].isParent && nodes[i].zAsync) {//是父节点并且已经加载过节点
                asyncNodes(nodes[i].children);
            } else {
                zTree.reAsyncChildNodes(nodes[i], "refresh", true);
            }
        }
    }
    
    
    <#-- 获取选中的所有节点ids，并存储id="checkedNodes"的隐藏域
        return id1,id2,id3...
    -->
    function getCheckedNodeIds${index}(){
        var tree_ids = "";
        var treeObj = $.fn.zTree.getZTreeObj("treeDemo${index}");
        var checkedNodes;
        
        var disabledNodes = treeObj.getNodesByParam("chkDisabled", true);
        if(disabledNodes.length > 0){
            <#--1.遍历取消禁用状态-->
            for (var i=0; i<disabledNodes.length; i++) {
                treeObj.setChkDisabled(disabledNodes[i], false);
            }
            <#--2.获取选中的节点-->
            checkedNodes = treeObj.getCheckedNodes(true);
            <#--3.遍历恢复禁用状态-->
            for (var i=0; i<disabledNodes.length; i++) {
                treeObj.setChkDisabled(disabledNodes[i], true);
            }
        }else{
            checkedNodes = treeObj.getCheckedNodes(true);
        }
        
        if(checkedNodes.length==0){
            alert("请至少选择一个模块！");
            return;
        }
        for(var i=0;i<checkedNodes.length;i++){
            if(checkedNodes[i].object.id != null){
                tree_ids += (checkedNodes[i].object.id + ',');
            }
        }
        tree_ids = tree_ids.substring(0,tree_ids.length-1);
        $("#checkedNodes${index}").val(tree_ids);
        return tree_ids;
    }
</script>
<div>
    <input type="hidden" id="checkedNodes${index}" name="checkedNodes" value="">
    <div>
        <ul id="treeDemo${index}" class="ztree" style="height:auto; width:250px"></ul>
    </div>
</div>
</#macro>

<#-- 课程选择器（隐藏域为课程、音乐会、点播的id值，文本内容为课程、音乐会、点播的名称）
    isShowOpen  是否显示-打开弹出层的选择框，默认打开true，【false时,隐藏域和文本域参数可不填，且建议引用宏在页面body最后面】
    action      查询课程action，默认查询所有课程${DOMAIN_CMS}/courseAllSelect.htm；查询可报名课程${DOMAIN_CMS}/courseReportSelect.htm
    cId         隐藏域ID框id值
    cIdName     隐藏域ID框name值
    cIdValue    隐藏域ID框value值
    cnId        文本域Name框id值
    cnName      文本域Name框name值
    cnValue     文本域Name框value值
    fn          回调函数
-->
<#macro courseSelect_new isShowOpen=true action="${DOMAIN_CMS}/courseAllSelect.htm" cId="courseSelect_courseId" cIdName="" cIdValue="" cnId="courseSelect_courseName" cnName="" cnValue="" fn="">
<#if isShowOpen>
<div class="select-analog fn-left">
    <input type="hidden" id="${cId}" name="${cIdName}" value="${cIdValue}" />
    <input type="text" class="input-txt" id="${cnId}" name="${cnName}" value="${cnValue}" placeholder="选择课程" readOnly/>
    <a href="javascript:;" id="openCourseSelectDiv" class="open"></a>
</div>
</#if>
<div class="popUp-layer" id="courseSelectDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>课程选择</span></p>
    <div class="wrap">
        <div class="org-search">
            <div class="org-search-tt">
                <span>课程类型：</span>
                <select class="input-sel w-100" id="courseSelectDiv_contentType">
                    <option value="COURSE">基地课程</option>
                    <option value="CONCERT">音乐会</option>
                    <option value="VOD">点播视频</option>
                </select>
                
                <span class="ml-10">课程名称：</span>
                <input type="text" class="input-txt t-200" id="courseSelectDiv_courseName">
                <a href="javascript:;" class="abtn abtn-blue ml-10" id="courseSelectDiv_search">查询</a>
            </div>
            <div class="org-search-inner" style="height:260px;" id="courseSelectDiv_courseItemDiv">
            </div>
        </div>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue" style="margin-right: 370px;" href="javascript:;" id="courseSelectDiv_clear"><span>清除</span></a>
        <a class="abtn abtn-blue" href="javascript:;" id="courseSelectDiv_submit"><span>确定</span></a>
        <a class="abtn abtn-green close ml-5" href="javascript:;"><span>取消</span></a>
    </p>
</div>
<script>
$(function(){
<#if isShowOpen>
    // 打开弹出层
    $("#openCourseSelectDiv").bind("click", function(){
        $("#courseSelectDiv_courseName").val("");
        Box.showDiv({
            "div" : "#courseSelectDiv",
            "closeObject" : "#courseSelectDiv .close"
        });
        $("#courseSelectDiv_search").click();
    });
</#if>

    // 类型切换
    $("#courseSelectDiv_contentType").bind("change", function(){
        $("#courseSelectDiv_courseName").val("");
        var contentType = $("#courseSelectDiv_contentType").val();
        var selectCId = $("#${cId}").val();
        if(selectCId == undefined){
            selectCId = 0;
        }
        var param = {
            "div" : "#courseSelectDiv_courseItemDiv",
            "url" : "${action}?c.contentType="+contentType+"&selectCId="+selectCId
        };
        Sender.load(param);
    });
    
    // 查询
    $("#courseSelectDiv_search").bind("click", function(){
        var contentType = $("#courseSelectDiv_contentType").val();
        var courseName = $("#courseSelectDiv_courseName").val();
        var selectCId = $("#${cId}").val();
        if(selectCId == undefined){
            selectCId = 0;
        }
        var param = {
            "div" : "#courseSelectDiv_courseItemDiv",
            "url" : "${action}?c.contentType="+contentType+"&c.courseName="+encodeURI(courseName)+"&selectCId="+selectCId
        };
        Sender.load(param);
    });
    
    // 回车搜索
    $("#courseSelectDiv_courseName").keyup(function(){
        if(event.keyCode == 13){
            $("#courseSelectDiv_search").click();
        }
    });
    
    //清除条件
    $('#courseSelectDiv_clear').bind("click", function(){
        $('#${cId}').val('');
        $('#${cnId}').val('');
        $("#courseSelectDiv .close").click();
    });
    
    
    // 选择
    $("#courseSelectDiv_submit").bind("click", function(){
        var obj=$("#courseSelectDiv_courseItemDiv").find('input:radio[name="courseSelectDiv_radio"]:checked');
        if(obj.length == 0){
            var tipMsg = "请选择一个基地课程！";
            if($("#courseSelectDiv_contentType").val() == "CONCERT"){
                tipMsg = "请选择一场音乐会！";
            }else if($("#courseSelectDiv_contentType").val() == "VOD"){
                tipMsg = "请选择一个点播视频！";
            }
            Tips.showErrorMsg({
                "info":tipMsg, 
                "callFn":function(){
                    Box.showDiv({
                        "div" : "#courseSelectDiv",
                        "closeObject" : "#courseSelectDiv .close"
                    });
                }
            });
        }else{
            var courseId = $("#courseSelectDiv_courseItemDiv").find('input:radio[name="courseSelectDiv_radio"]:checked').val();
            var courseName = $("#courseSelectDiv_courseItemDiv").find('input:radio[name="courseSelectDiv_radio"]:checked').attr("dataValue");
            <#if isShowOpen>
                $("#${cId}").val(courseId);
                $("#${cnId}").val(courseName);
            </#if>
            $("#courseSelectDiv .close").click();
           // setTimeout(${fn!},100);
           ${fn!};
        }
    });
});
</script>
</#macro>

<#-- 音乐会/课程   选择器（隐藏域为课程、音乐会的id值，文本内容为课程、音乐会的名称）
    couTypeId   类型的框Id值 (音乐会，课程)
    couId       隐藏ID框id值
    couIdName   隐藏ID框name值
    couIdValue  隐藏ID框value值
    couNameId   隐藏Name框id值
    couName     隐藏Name框name值
    couNameValue隐藏Name框value值
    fn             回调函数
-->
<#macro courseSelect couTypeId="" couId="courseId" couIdName="" couIdValue="" couNameId="courseName" couName="" couNameValue="" fn="">
<div class="select-analog fn-left">
    <input type="hidden" id="${couId}" name="${couIdName}" value="${couIdValue}" />
    <input type="text" class="input-txt" id="${couNameId}" name="${couName}" value="${couNameValue}" placeholder="选择课程/音乐会" readOnly/>
    <a href="javascript:;" id="openCourseSelectDiv" class="open"></a>
</div>
<div class="popUp-layer" id="selectCouDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>选择课程/音乐会</span></p>
    <div class="wrap" style="height:225px;">
        <div class="org-search">
            <div class="org-search-tt">
                <span>名称：</span>
                <input type="text" class="input-txt" id="selectCouDiv_name">
                <a href="javascript:;" class="abtn abtn-blue ml-20" id="selectCouDiv_search">查询</a>
            </div>
            <div class="org-search-inner" id="selectCouDiv_listDiv">
            </div>
       </div>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue submit" href="javascript:;" id="selectCouDiv_select"><span>确定</span></a>
        <a class="abtn abtn-green ml-5 close" href="javascript:;" id="selectCouDiv_esc"><span>取消</span></a>
    </p>
</div>
<script>
$(function(){
    $("#openCourseSelectDiv").bind("click", function(){
        Box.showDiv({
            "div" : "#selectCouDiv",
            "closeObject" : "#selectCouDiv_esc, #selectCouDiv .close"
        });
        $("#selectCouDiv_name").val('');
        var param1 = {
            "div":"#selectCouDiv_listDiv",
            "url":Domain.cms_path+"/stat/courseList.htm?course.contentType="+$("#${couTypeId}").val()+"&course.courseName="+encodeURI($('#selectCouDiv_name').val())
        };
        Sender.load(param1);
    });
    
    //查询
    $("#selectCouDiv_search").bind("click", function(){
        var param = {
            "div":"#selectCouDiv_listDiv",
            "url":Domain.cms_path+"/stat/courseList.htm?course.contentType="+$("#${couTypeId}").val()+"&course.courseName="+encodeURI($('#selectCouDiv_name').val())
        };
        Sender.load(param);
    });
    //回车搜索
    $("#selectCouDiv_name").keyup(function(){
        if(event.keyCode == 13){
            $("#selectCouDiv_search").click();
        }
    });
    //选择
    $("#selectCouDiv_select").bind("click", function(){
        var courseId = $("input[name='cou_radio']:checked").val();
        var courseName = $("input[name='cou_radio']:checked").attr("dataValue");
        if(courseId == null || courseName == ''){
            alert("请选择课程/音乐会！");
            return;
        }
        $("#selectCouDiv_esc").click();
        $("#${couId}").val(courseId);
        $("#${couNameId}").val(courseName);
        ${fn!};
    });
});
</script>
</#macro>

<#-- 课次/场次   选择器（隐藏域为课次、场次的id值，文本内容为课程序号  课程时间）
    couId       课程、音乐会的id值框
    couTimeSeq   隐藏课次/场次seq框id值
    couTimeSeqName 隐藏课次/场次seq框name值
    couTimeSeqValue   隐藏课次/场次Seq框value值   
    couTimeNameId  课次/场次name框id值
    couTimeName    课次/场次name框name值    
    couTimeNameValue 课次/场次name框value值  
    fn             回调函数
-->
<#macro courseTimeSelect couId="" couTimeId="courseTimeId" couTimeSeq="courseId" couTimeSeqName="" couTimeSeqValue="" couTimeNameId="courseName" couTimeName="" couTimeNameValue="" fn="">
<div class="select-analog fn-left">
    <input type="hidden" id="${couTimeSeq}" name="${couTimeSeqName}" value="${couTimeSeqValue}" />
    <input type="text" class="input-txt" id="${couTimeNameId}" name="${couTimeName}" value="${couTimeNameValue}" placeholder="选择课次/场次" readOnly/>
    <a href="javascript:;" id="openTimeSelectDiv" class="open"></a>
</div>
<div class="popUp-layer" id="selectCouTimeDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>选择课次/场次</span></p>
    <div class="wrap" style="height:200px;">
        <div class="org-search">
            <div class="org-search-inner" id="selectCouTimeDiv_listDiv">
            </div>
        </div>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue submit" href="javascript:;" id="selectCouTimeDiv_select"><span>确定</span></a>
        <a class="abtn abtn-green ml-5 close" href="javascript:;" id="selectCouTimeDiv_esc"><span>取消</span></a>
    </p>
</div>
<script>
$(function(){
    $("#openTimeSelectDiv").bind("click", function(){
        var couId = $("#${couId}").val();
        if(couId==null||couId=="0"){
           Tips.showAlertWin({"info":"请先选择课程/音乐会！"});
           return false;
        }
        Box.showDiv({
            "div" : "#selectCouTimeDiv",
            "closeObject" : "#selectCouTimeDiv_esc, #selectCouTimeDiv .close"
        });
        var param1 = {
            "div":"#selectCouTimeDiv_listDiv",
            "url":Domain.cms_path+"/stat/courseTimeList.htm?courseId="+couId
        };
        Sender.load(param1);
    });
   
    //选择
    $("#selectCouTimeDiv_select").bind("click", function(){
        var courseTimeSeq = $("input[name='couTime_radio']:checked").val();
        var courseTimeName = $("input[name='couTime_radio']:checked").attr("dataValue");
        var courseTimeId = $("input[name='couTime_radio']:checked").attr("id");
        if(courseTimeSeq == null || courseTimeName == ''){
            alert("请选择课次/场次！");
            return;
        }
        $("#selectCouTimeDiv_esc").click();
        $("#${couTimeSeq}").val(courseTimeSeq);
        $("#${couTimeNameId}").val(courseTimeName);
        $("#${couTimeId}").val(courseTimeId);
        ${fn!};
    });
});
</script>
</#macro>


<#-- 地区选择框
    id:默认regionCode，
    name：默认regionCode 
-->
<#macro regionSelect id="regionCode" name="regionCode" value="" >
<input type="hidden" id="${id}" name="${name}" value="${value}"/>
<div class="apply-form-content">
    <select class="input-sel" id="province_sel">
        <option value="">- 省 -</option>
    </select>
    <select class="input-sel" id="city_sel">
        <option value="">- 市 -</option>
    </select>
    <select class="input-sel" id="county_sel">
        <option value="">- 区 -</option>
    </select>
</div>
<script>
    $(function(){
        //加载省
        loadRegion("province",null,init);
        //选择省加载市
        $(document).on("change","#province_sel",function(){
            var regionCode = $(this).val();
            $("#${id}").val(regionCode);
            $("#city_sel").html('<option value="">- 市 -</option>');
            $("#county_sel").html('<option value="">- 区 -</option>');
            if(regionCode==""){
                return;
            }
            loadRegion("city",regionCode);
            
        });
        //选择市加载区
        $(document).on("change","#city_sel",function(){
            var regionCode = $(this).val();
            $("#${id}").val(regionCode);
            $("#county_sel").html('<option value="">- 区 -</option>');
            if(regionCode==""){
                return;
            }
            loadRegion("county",regionCode);
        });
        //区县点击事件
        $(document).on("change","#county_sel",function(){
            var regionCode = $(this).val();
            $("#${id}").val(regionCode);
        });
        
        //加载地区
        function loadRegion(type,regionCode,callFn){
            var url = "/system-listRegions.htm?regionType=";
            if(type=="province"){
                url += type;
            }else{
                url += type+"&regionCode="+regionCode;
            }
            var obj = null;
            if(type=="city"){
                obj = $("#city_sel");
            }else if(type=="county"){
                obj = $("#county_sel");
            }else{
                obj = $("#province_sel");
            }
            $.post(url,null,function(data){
                $.each(data,function(){
                    obj.append('<option value="'+this.regionCode+'">'+this.regionName+'</option>');
                });
                if(callFn){
                    callFn();
                }
            },"json");
        }
        
        //初始化数据
        function init(){
            var regionCode = $("#${id}").val();
            if(regionCode==""){
                return;
            }
            var province_code = regionCode.substring(0,2);
            var city_code = regionCode.substring(0,4);
            $("#province_sel option").each(function(){
                var val = $(this).val();
                if(val==province_code){
                    $(this).prop("selected","true");
                    return false;
                }
            });
            loadRegion("city",province_code,function(){
                $("#city_sel option").each(function(){
                    var val = $(this).val();
                    if(val==city_code){
                        $(this).prop("selected","true");
                        return false;
                    }
                });
            });
            loadRegion("county",city_code,function(){
                $("#county_sel option").each(function(){
                    var val = $(this).val();
                    if(val==regionCode){
                        $(this).prop("selected","true");
                        return false;
                    }
                });
            });
        }
        
    });
</script>
</#macro>

<#-- 学员选择器（隐藏域为学员的id值，文本内容为学员的用户名）
    isShowOpen  是否显示-打开弹出层的选择框，默认打开true，【false时,隐藏域和文本域参数可不填，且建议引用宏在页面body最后面】
    cId         隐藏域ID框id值
    cIdName     隐藏域ID框name值
    cIdValue    隐藏域ID框value值
    cnId        文本域Name框id值
    cnName      文本域Name框name值
    cnValue     文本域Name框value值
    fn          回调函数
-->
<#macro userSelect isShowOpen=true cId="courseId" cIdName="" cIdValue="" cnId="courseName" cnName="" cnValue="" fn="">
<#if isShowOpen>
<div class="select-analog fn-left">
    <input type="hidden" id="${cId}" name="${cIdName}" value="${cIdValue}" />
    <input type="text" class="input-txt" id="${cnId}" name="${cnName}" value="${cnValue}" placeholder="选择学员" readOnly/>
    <a href="javascript:;" id="openUserSelectDiv" class="open"></a>
</div>
</#if>
<div class="popUp-layer" id="userSelectDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>学员选择</span></p>
    <div class="wrap">
        <div class="org-search">
            <div class="org-search-tt">
                <span>用户名：</span>
                <input type="text" class="input-txt t-120" id="userSelectDiv_userName"/>
                
                <span class="ml-10">姓名：</span>
                <input type="text" class="input-txt t-120" id="userSelectDiv_realName">
                <a href="javascript:;" class="abtn abtn-blue ml-10" id="userSelectDiv_search">查询</a>
            </div>
            <div class="org-search-inner" style="height:260px;" id="userSelectDiv_userItemDiv">
            </div>
        </div>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue" href="javascript:;" id="userSelectDiv_submit"><span>确定</span></a>
        <a class="abtn abtn-green close ml-5" href="javascript:;"><span>取消</span></a>
    </p>
</div>
<script>
$(function(){
<#if isShowOpen>
    // 打开弹出层
    $("#openUserSelectDiv").bind("click", function(){
        $("#userSelectDiv_userName").val("");
        Box.showDiv({
            "div" : "#userSelectDiv",
            "closeObject" : "#userSelectDiv .close"
        });
        $("#userSelectDiv_search").click();
    });
</#if>

    // 查询
    $("#userSelectDiv_search").bind("click", function(){
        var userName = $("#userSelectDiv_userName").val();
        var realName = $("#userSelectDiv_realName").val();
        var param = {
            "div" : "#userSelectDiv_userItemDiv",
            "url" : Domain.cms_path+"/userSelect.htm?u.userName="+encodeURI(userName)+"&u.realName="+encodeURI(realName)
        };
        Sender.load(param);
    });
    
    // 回车搜索
    $("#userSelectDiv_userName").keyup(function(){
        if(event.keyCode == 13){
            $("#userSelectDiv_search").click();
        }
    });
    
    // 选择
    $("#userSelectDiv_submit").bind("click", function(){
        var obj=$("#userSelectDiv_userItemDiv").find('input:radio[name="userSelectDiv_radio"]:checked');
        if(obj.length == 0){
            Tips.showErrorMsg({
                "info":"请选择学员！！", 
                "callFn":function(){
                    Box.showDiv({
                        "div" : "#userSelectDiv",
                        "closeObject" : "#userSelectDiv .close"
                    });
                }
            });
        }else{
            var userId = $("#userSelectDiv_userItemDiv").find('input:radio[name="userSelectDiv_radio"]:checked').val();
            var userName = $("#userSelectDiv_userItemDiv").find('input:radio[name="userSelectDiv_radio"]:checked').attr("dataValue");
            <#if isShowOpen>
                $("#${cId}").val(userId);
                $("#${cnId}").val(userName);
            </#if>
            $("#userSelectDiv .close").click();
         //   setTimeout(${fn!},100);
            ${fn!};
        }
    });
});
</script>
</#macro>


<#--  功能：分块上传文件（视频）
        参数如下：
    id              触发事件的元素id
    path            文件保存路径
    customId        自定义配置，conf.xml控制
    fileSize        上传文件大小
    uniqControl     通过序号，控制并发上传同一个文件
    layout          buttonOnly、landscape、portrait
    width height    按钮样式           
    index total     同一页面中有多个该上传控件时索引控制，默认不指定
    beforeUpload
    afterUpload
-->
<#macro upload_block id="" path="" fileSize="500" index='' total='' width=80 height=20 customId="chnmaster_video" layout="landscape" uniqControl="" beforeUpload="" afterUpload="">
<#if index==total>
    <script type="text/javascript" src="${DOMAIN_CMS}/js/component/selfupload/swfobject.js?${appsetting.getVersionId()}"></script>
    <script type="text/javascript" src="${DOMAIN_CMS}/js/component/selfupload/UploadUtil.js?${appsetting.getVersionId()}"></script>
</#if>
<script type="text/javascript">
    $(function(){
        //视频上传
        UploadUtil.create({
            'id':'${id}',
            'xmlUrl':'/js/component/upload/config.xml',
            'customId':'${customId}',
            'uploadType':'block',//分块上传
            'uniqControl':${uniqControl},
            'fileSize':${fileSize},
            'tip':'最大文件不能超过${fileSize}M',
            'layout':'${layout}',
            'width':${width},
            'height':${height},
            'path':'${path}',
            'beforeUpload':function(){

            }.bind(this),
            'afterUpload':function(data){
                var id='${id}';
                ${afterUpload?default('')};
            }.bind(this)
        });
    });
</script>
</#macro>


<#--  功能：分块上传文件（视频）
        参数如下：
    id              触发事件的元素id，不能为空
    path            文件保存路径
    fileSize        上传文件大小
    uploadType      上传类型，block分块上传；total整块上传
    exts            上传类型控制，例：jpg,wrf,zip,flv,txt,sql
    multiple        是否选择多个文件,默认false
-->
<#macro upload_block_web id="" path="" fileSize="500" exts="flv,mp4" uploadType="block" multiple='false' index='' total='' initUpload="" progress="" beforeUpload="" afterUpload="" error="">
<a href="javascript:;" id="${id}" class='abtn abtn-green fn-btn' style="padding:0px;"><img src='${DOMAIN_CMS}/images/icon/add2.png'>上传视频</a><span class="ml-10 f-14 c-999">单份资料小大不能超过${fileSize!}M</span>
<ul id="${id}-list" class="webuploader-list" style="display:none"></ul>
<#if index==total>
<link href="${DOMAIN_CMS}/js/component/webupload/webuploader.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${DOMAIN_CMS}/js/component/webupload/jquery.base64.js?${appsetting.getVersionId()}"></script>
<script type="text/javascript" src="${DOMAIN_CMS}/js/component/webupload/jquery.md5.js?${appsetting.getVersionId()}"></script>
<script type="text/javascript" src="${DOMAIN_CMS}/js/component/webupload/js-webuploader/webuploader.js?${appsetting.getVersionId()}"></script>
<script type="text/javascript" src="${DOMAIN_CMS}/js/component/webupload/UploadUtil.js?${appsetting.getVersionId()}"></script>
</#if>
<script type="text/javascript">
    var FileSystemConfig = {
        uploadUrl_total :  '${DOMAIN_CMS}/upload/upload.action',
        uploadUrl_block :  '${DOMAIN_CMS}/upload/upload.action'
    };
    
    $(function(){
        //视频上传
        UploadUIUtil.create({
            'id':'${id}',
            'uploadType':'${uploadType}',
            'exts':'${exts}',
            'fileSize':${fileSize},
            'multiple':false,
            'path':'${path}',
            'initUpload':function(files){
                //选择文件后触发该方法，给所有上传文件设定样式，包括错误文件
            },
            'beforeUpload':function(data){
            
            },
            'afterUpload':function(data){
                var id='${id}';
                ${afterUpload?default('')};
            },
            'progress':function(fileIndex,percentage,isMd5){
                //上传进度处理
            },
            'error':function(data){
                
            }
        });
    });
</script>
</#macro>