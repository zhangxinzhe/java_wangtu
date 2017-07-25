//截图参数
//Domain.exer = 'http://exer.cs.netstudy5.tst';
window.udCapture = {
	UDCAPTURE_VERSION : "1.6.0", //当前最新的控件版本号
	UDCAPTURE_MIME_TYPE : "application/udcapture-plugin", //mimeType
	UDCAPTURE_SAVEFILE : window.UEDITOR_CONFIG.serverUrl + "?action=uploadimage&isUrl=true", //后台保存图片的文件路径,aspx可以换成php或jsp
	UDCAPTURE_LICENSE : "", //注册授权许可号
	//安装文件
	UDCAPTURE_SETUP : function(){
		var url = "/ueditor/downloadCaptrue.htm";
		
		var supportActiveX = (window.ActiveXObject !== undefined);
		//64位浏览器安装文件
		if (supportActiveX && (window.navigator.platform == "Win64" || window.navigator.cpuClass == "x64")){
			url += "?type=64";
		}
		return  url;
	}(),
	udCaptureCtlId:'udCaptureCtl',
	containerId:'udCaptrueContainer'
};

//是否是ie
window.udCapture.supportActiveX = (window.ActiveXObject !== undefined);
var div = document.getElementById('udCaptrueContainer');
if(!div){
	div = '<div id="udCaptrueContainer" style="display:block;width:0px;height:0px;overflow:hidden;"></div>';
	$(div).appendTo('body');
	//document.write(div);
}

//截图函数
(function(_this){
	var controlLoaded = false;//是否已经加载
	var udCapCtl = null;
	
	/**
	 * 开始屏幕截图
	 * @returns
	 */
	_this.capture = function() {
	    if (f_installCheck()) {
	        if (_this.supportActiveX || controlLoaded) {
	                udCapCtl.StartCapture();
	        }
	        else {
	            //最小化后截图有些情况需要延迟启动才有效,主要是Google Chrome
	            setTimeout(function () {
                    udCapCtl.StartCapture();
	            }, 300);
	        }
	    }
	}
	
	//版本比较，检查是否安装了新版本
	function f_hasNewVer(instVer) {
	    if (instVer.substring(0, 1) == 'v')
	        instVer = instVer.substring(1, instVer.length);

	    var newVer = _this.UDCAPTURE_VERSION.split(".");
	    var curVer = instVer.split(".");
	    if (parseInt(newVer[0]) > parseInt(curVer[0]))
	        return true;
	    if (parseInt(newVer[0]) == parseInt(curVer[0]) && parseInt(newVer[1]) > parseInt(curVer[1]))
	        return true;
	    if (parseInt(newVer[0]) == parseInt(curVer[0]) && parseInt(newVer[1]) == parseInt(curVer[1])
	             && parseInt(newVer[2]) > parseInt(curVer[2]))
	        return true;
	    return false;
	}
	
	//IE事件注册
	function f_addEvent(element, type, handler) {
	    if (element.attachEvent) {
	        element.attachEvent(type, handler);
	    } else {
	        f_attachIE11Event(element, type, handler);
	    }
	}
	
	//单独处理IE11的事件
	function f_attachIE11Event(obj, eventId, _functionCallback) {
	    var nameFromToStringRegex = /^function\s?([^\s(]*)/;
	    var paramsFromToStringRegex = /\(\)|\(.+\)/;
	    var params = _functionCallback.toString().match(paramsFromToStringRegex)[0];
	    var functionName = _functionCallback.name || _functionCallback.toString().match(nameFromToStringRegex)[1];
	    var handler = document.createElement("script");
	    handler.setAttribute("for", obj.id);
	    handler.event = eventId + params;
	    handler.appendChild(document.createTextNode(functionName + params + ";"));
	    document.body.appendChild(handler);
	};
	
	//检查是否安装了插件
	function f_installCheck() {
	    if (udCapCtl)//已经启用
	    {
	        controlLoaded = true;
	        return true;
	    }

	    if (_this.supportActiveX) {//if IE
	        document.getElementById(_this.containerId).innerHTML = "<object id=\"" + _this.udCaptureCtlId + "\" width=\"0\" height=\"0\" classid=\"CLSID:0FAE7655-7C34-4DEE-9620-CD7ED969B3F2\"></object>";
	        var axObj = document.getElementById(_this.udCaptureCtlId);
	        if (axObj.PostUrl != undefined) {
                udCapCtl = document.getElementById(_this.udCaptureCtlId);
                udCapCtl.PostUrl = _this.UDCAPTURE_SAVEFILE;
                udCapCtl.License = _this.UDCAPTURE_LICENSE;
                //以下IE事件注册
                f_addEvent(udCapCtl, "OnBeforeCapture", f_onBeforeCapture);
                f_addEvent(udCapCtl, "OnCaptureCanceled", f_onCaptureCanceled);
                f_addEvent(udCapCtl, "OnCaptureCompleted", f_onCaptureCompleted);
                f_addEvent(udCapCtl, "OnBeforeUpload", f_onBeforeUpload);
                f_addEvent(udCapCtl, "OnUploadCompleted", f_onUploadCompleted);
                f_addEvent(udCapCtl, "OnUploadFailed", f_onUploadFailed);
                return true;
	        }
	        else {
	            if (confirm("您尚未安装在线屏幕截图控件，点确定进行安装")) {
	                document.getElementById(_this.containerId).innerHTML = "";
	                f_startSetup();
	            }
	            return false;
	        }
	    }
	    else if (navigator.plugins)//NP
	    {
	        var plugin = (navigator.mimeTypes && navigator.mimeTypes[_this.UDCAPTURE_MIME_TYPE]) ? navigator.mimeTypes[_this.UDCAPTURE_MIME_TYPE].enabledPlugin : 0;
	        if (plugin) {
	            var pluginVersion = "v1.0.0";
	            var words = plugin.description.split(" ");
	            if (words[words.length - 1].substring(0, 1) == "v"){
	            	pluginVersion = words[words.length - 1];
	            }

                document.getElementById(_this.containerId).innerHTML = "<embed id=\"" + _this.udCaptureCtlId + "\" width=\"0\" height=\"0\" type=\"" + _this.UDCAPTURE_MIME_TYPE + "\"></embed>";
                udCapCtl = document.getElementById(_this.udCaptureCtlId);
                udCapCtl.PostUrl = _this.UDCAPTURE_SAVEFILE;
                udCapCtl.License = _this.UDCAPTURE_LICENSE;
                //事件处理
                udCapCtl.OnBeforeCapture = "f_onBeforeCapture";
                udCapCtl.OnCaptureCanceled = "f_onCaptureCanceled";
                udCapCtl.OnCaptureCompleted = "f_onCaptureCompleted";
                udCapCtl.OnBeforeUpload = "f_onBeforeUpload";
                udCapCtl.OnUploadCompleted = "f_onUploadCompleted";
                udCapCtl.OnUploadFailed = "f_onUploadFailed";
                return true;
	        }
	        if (confirm("您尚未安装在线屏幕截图插件，点确定进行安装")) {
	            f_startSetup();
	        }
	    }
	    return false;
	}
	
	//开始安装插件
	function f_startSetup() {
		window.location.href = _this.UDCAPTURE_SETUP;
	}
})(window.udCapture);

//事件处理函数
function f_onBeforeCapture() {
}
function f_onCaptureCanceled() {
}
function f_onCaptureCompleted(file) {
}
function f_onBeforeUpload(file, size) {
}
function f_onUploadCompleted(url) {
	window.udCapture.editor.execCommand('insertHtml', "<img src=\"" + url + "\">");
}
function f_onUploadFailed(errorCode) {
}