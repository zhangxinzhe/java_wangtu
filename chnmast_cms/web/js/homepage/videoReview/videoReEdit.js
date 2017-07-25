(function($,window,document,undefined){
	var b = {};
	window.InfoEdit = b;
	b.init = function(){
		bindEvent();
		changeContentType();
	}
	function bindEvent(){
		//信息内容类型改变时触发
		$(".contentType").on("click",changeContentType);
		//提交保存
		$("#saveBtn").on("click",subSaveForm);
		//预览
		$("#preview").on("click",function(){
			$("#is_preview").val("true");
			subSaveForm()
		});
		
	}
	//预览
	function infoPreview(){
		var content = $("#ueditor_textarea_content").val();
		var title = $("#title").val();
		var comeFrom = $("#comeFrom").val();
		var comeUrl = $("#comeUrl").val();
		var infoDate = $("#infoDate").val();
		var editor = $("#infoEditor").val();
		$("#preview_content").val(content);
		$("#preview_title").val(title);
		$("#preview_comeFrom").val(comeFrom);
		$("#preview_comeUrl").val(comeUrl);
		$("#preview_infoDate").val(infoDate);
		$("#preview_editor").val(editor);
		$("#previewForm").submit();
	}
	//提交保存
	function subSaveForm(){
		var flag = Verify.checkAllVerify("#videoReForm");
		if(!flag){
			return;
		}
		if($('input:radio[name="indexInfo.contentType"]:checked').val() == "CONTENT"){
			if(!Validator.isValidUrl($("#comeUrl").val())){
				FieldMsg.addFieldError($("#comeUrl"),"请输入有效的链接地址！以http://开头", false);
				return;
			}else{
				FieldMsg.clearFieldError($("#comeUrl"));
			}
		}
		if($('input:radio[name="indexInfo.contentType"]:checked').val() == "LINK"){
			if(!Validator.isValidUrl($("#linkUrl").val())){
				FieldMsg.addFieldError($("#linkUrl"),"请输入有效的链接地址！以http://开头", false);
				return;
			}else{
				FieldMsg.clearFieldError($("#linkUrl"));
			}
		}
		//如果是输入内容，验证编辑内容不为空
		$("#videoReForm").submit();
	}
	//信息类型切换时动态显示编辑内容
	function changeContentType(){		
		var value = $(".contentType:checked").val();
		$("#link_url_tr").hide();
		$(".content_tr").hide();
		$("#linkUrl").removeAttr("notNull");
		$("#comeFrom").removeAttr("notNull");
		$("#comeUrl").removeAttr("notNull");
		$("#infoDate").removeAttr("notNull");
		$("#infoEditor").removeAttr("notNull");
		if(value=="LINK"){
			$("#link_url_tr").show();
			$("#linkUrl").attr("notNull","true");
		}
		if(value=="CONTENT"){
			$(".content_tr").show();
			$("#comeFrom").attr("notNull","true");
			$("#comeUrl").attr("notNull","true");
			$("#infoDate").attr("notNull","true");
			$("#infoEditor").attr("notNull","true");
		}
		
	}
	//根据信息类型决定是否显示是否紧急选项模块
	function showIsUrgent(){
		if($("#notice").attr("checked")){ 
			$("#isUrgent_tr").show();
		}else {
			$("#isUrgent_tr").hide();
		}
	}
})(jQuery,window,document);