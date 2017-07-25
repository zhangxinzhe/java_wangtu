(function($, window, document, undefined) {
	var pub = {};
	window.hotSpotEdit = pub;
	pub.init = function() {
		bindEvent();
		changeContentType();
		changeHignLight();
	}

	// 绑定事件
	function bindEvent() {
		//信息内容类型改变时触发
		$(".contentType").on("click",changeContentType);
		$(".chk2").on("click",changeHignLight);
		//提交保存
		$("#saveBtn").on("click",subSaveForm);
	}
	
	//提交保存
	function subSaveForm(){
		var flag = Verify.checkAllVerify("#infoForm");
		if(!flag){
			return;
		}
		if($('input:radio[name="indexInfo.contentType"]:checked').val() == "LINK"){
			if(!Validator.isValidUrl($("#linkUrl").val())){
				FieldMsg.addFieldError($("#linkUrl"),"请输入有效的链接地址！以'http://'开头，不以'/'结尾", false);
				return;
			}else{
				FieldMsg.clearFieldError($("#linkUrl"));
			}
		}
		//如果是输入内容，验证编辑内容不为空
		$("#infoForm").submit();
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
	
	//改变高亮
	function changeHignLight(){
		var v = $('.chk2:checked').val();
		if(v=="YES"){
			$('#newDays').parents('tr').show();
			$("#newDays").attr("nonnegative","true");
			$("#newDays").attr("dataType","integer");
		}else if(v=="NO"){
			$('#newDays').parents('tr').hide();
			$("#newDays").removeAttr("nonnegative");
			$("#newDays").removeAttr("dataType");
		}
	}

})(jQuery, window, document);