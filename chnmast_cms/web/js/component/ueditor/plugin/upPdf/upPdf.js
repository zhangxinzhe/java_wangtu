;(function($,window,document,undefined){
	var b = {};
	var uEditor ;//百度编辑器对象
	var $div;//当前按钮所在div
	var uId;//编辑器id
	window.UpPdf = b;
	b.init = function(editor,div_id){
		uEditor = editor;
		$div = $(div_id);
		initIframe();
		bindEvent();
	}
	//绑定事件
	function bindEvent(){
		$("#"+uId+"_upPdf_file").on("change",upPdf);
		$("#"+uId+"_upPdf_iframe").on("load",function(){
			//回调处理
			var uploadResultJson = $(this).contents().find("*").first().text();
		    var uploadResult = $.parseJSON(uploadResultJson);
		    var $editor = $(uEditor.iframe.parentNode);
		    
		    if (uploadResult && uploadResult.state == "SUCCESS") {
		    	var innerHtml = '<iframe scrolling="yes" src="'+uploadResult.url+'" width="'+($editor.width()-50)+'" align="" frameborder="1" height="'+$editor.height()+'"></iframe>';
		    	uEditor.execCommand('inserthtml', innerHtml);
		    }
		});
	}
	function initIframe(){
		var url =uEditor.getActionUrl(uEditor.getOpt('fileActionName'));
		var $iconDiv = $div.find("div").first();
		uId = $(uEditor.iframe.parentNode).attr("id");
		$iconDiv.append("<form id='"+uId+"_upPdf_form' target='"+uId+"_upPdf_iframe' method='POST' enctype='multipart/form-data' action='"+url+"' style='display:block;width:20px;height:20px;overflow:hidden;border:0;margin:0;padding:0;position:absolute;top:0;left:0;filter:alpha(opacity=0);-moz-opacity:0;-khtml-opacity: 0;opacity: 0;cursor:pointer;'><input id='"+uId+"_upPdf_file' name='upfile' style='display:block;width:20px;height:20px;overflow:hidden;border:0;margin:0;padding:0;position:absolute;top:0;left:0;filter:alpha(opacity=0);-moz-opacity:0;-khtml-opacity: 0;opacity: 0;cursor:pointer;' accept='*.pdf' type='file'/></form>");
		$iconDiv.append("<iframe id='"+uId+"_upPdf_iframe' name='"+uId+"_upPdf_iframe' style='display:none;width:0;height:0;border:0;margin:0;padding:0;position:absolute;z-index:1'></iframe>");
	}
	//上传文件
	function upPdf(){
		var name = $(this).val();
		if(name.indexOf("pdf")==-1){
			alert("请选择pdf文件！");
			return ;
		}
		$("#"+uId+"_upPdf_form").submit();
	}
})(jQuery,window,document)