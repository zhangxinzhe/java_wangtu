(function($,window,document,undefined){
	var b = {};
	window.HelpThemeEdit = b;
	b.init = function(){
		bindEvent();
		changeContentType();
	}
	function bindEvent(){
		//信息内容类型改变时触发
		$(".contentType").on("click",changeContentType);
		//提交保存
		$(".theme_save").on("click",subSaveForm);
		//返回
		$(".theme_edit_bak").on("click",themeEditBack);
	}
	//返回
	function themeEditBack(){
		var catalogId = $("#catalogId").val();
		var param={
			div:".help-content",
			url:Domain.cms_path+"/homepage/help/helpThemeManage.htm?catalogId="+catalogId
			
		}
		Sender.load(param);
	}
	//提交保存
	function subSaveForm(){
		if(!Verify.checkAllVerify("#themeForm")){
			return ;
		}
		var url = Domain.cms_path+"/homepage/help/helpThemeSave.htm";
		Tips.loading({info:"正在保存数据..."});
		$.post(url,$("#themeForm").serialize(),function(data){
			Tips.loaded();
			if(data){
				if(data.isSuccess){
					var param = {
						info:"保存成功！",
						callFn : function(){
							$(".theme_edit_bak").click();
						}
					}
					Tips.showSuccessMsg(param);
				}else{
					Tips.showErrorMsg({info:data.errorMsg});
				}
			}
		},"json");
	}
	//信息类型切换时动态显示编辑内容
	function changeContentType(){		
		var value = $(".contentType:checked").val();
		$("#link_url_tr").hide();
		$(".content_tr").hide();
		$("#linkUrl").removeAttr("notNull");
		if(value=="LINK"){
			$("#link_url_tr").show();
			$("#linkUrl").attr("notNull","true");
		}
		if(value=="CONTENT"){
			$(".content_tr").show();
		}
	}
})(jQuery,window,document);