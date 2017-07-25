;
(function($,window,document,undefined){
	var b = {};
	var uEditor ;//百度编辑器对象
	var $div;//当前按钮所在div
	var uId;//编辑器id
	window.UpMusic = b;
	b.init = function(editor,div_id){
		uEditor = editor;
		$div = $(div_id);
		initIframe();
		bindEvent();
		
	}
	//绑定事件
	function bindEvent(){
		$("#"+uId+"_upMusic_file").on("change",upAudio);
		$("#"+uId+"_upMusic_iframe").on("load",function(){
			var uploadResultJson = $(this).contents().find("*").first().text();
		    var uploadResult = $.parseJSON(uploadResultJson);
		    if (uploadResult && uploadResult.state == "SUCCESS") {
		    	var innerHtml = '<embed type="application/x-shockwave-flash" class="edui-faked-music" pluginspage="http://www.macromedia.com/go/getflashplayer" src="http://box.baidu.com/widget/flash/bdspacesong.swf?from=tiebasongwidget&url='+uploadResult.url+'&name='+uploadResult.original+'&artist=&extra=&autoPlay=true&loop=true" width="400" height="95" align="none" wmode="transparent" play="true" loop="false" menu="false" allowscriptaccess="never" allowfullscreen="true"/>';
		    	uEditor.execCommand('inserthtml', innerHtml);
		    }
		});
	}
	function initIframe(){
		var url =uEditor.getActionUrl(uEditor.getOpt('videoActionName'));
		var $iconDiv = $div.find("div").first();
		uId = $(uEditor.iframe.parentNode).attr("id");
		$iconDiv.append("<form id='"+uId+"_upMusic_form' target='"+uId+"_upMusic_iframe' method='POST' enctype='multipart/form-data' action='"+url+"' style='display:block;width:20px;height:20px;overflow:hidden;border:0;margin:0;padding:0;position:absolute;top:0;left:0;filter:alpha(opacity=0);-moz-opacity:0;-khtml-opacity: 0;opacity: 0;cursor:pointer;'><input id='"+uId+"_upMusic_file' name='upfile' style='display:block;width:20px;height:20px;overflow:hidden;border:0;margin:0;padding:0;position:absolute;top:0;left:0;filter:alpha(opacity=0);-moz-opacity:0;-khtml-opacity: 0;opacity: 0;cursor:pointer;' accept='audio/MP3' type='file'/></form>");
		$iconDiv.append("<iframe id='"+uId+"_upMusic_iframe' name='"+uId+"_upMusic_iframe' style='display:none;width:0;height:0;border:0;margin:0;padding:0;position:absolute;'></iframe>");
	}
	//上传音乐文件
	function upAudio(){
		var fileName = $(this).val();
		if(fileName.indexOf("mp3")==-1){
			alert("请选择MP3文件！");
			return;
		}
		$("#"+uId+"_upMusic_form").submit();
	}
})(jQuery,window,document)