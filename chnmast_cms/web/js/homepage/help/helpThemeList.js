(function($,window,document,undefined){
	var b = {};
	window.HelpThemeList = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		//新增按钮事件
		$(".theme_add_btn").on("click",helpThemeAdd);
		//修改主题
		$(".theme_toedit").on("click",helpThemeEidt);
		//全选反选
		$("#check_all_theme").on("click",checkAllOrNot);
		//删除
		$("#delete_theme_btn").on("click",deleteTheme);
	}
	//删除
	function deleteTheme(){
		var $ids = $(".check_item:checked");
		if($ids.length==0){
			Tips.showErrorMsg({info:"请至少选择一条数据！"});
			return;
		}
		var param = {
			info:"确定要删除这些主题吗？",
			callFn:function(){
				var url = Domain.cms_path+"/homepage/help/helpThemeDelete.htm";
				Tips.loading({info:"正在删除数据..."});
				$.post(url,$("#theme_list_form").serialize(),function(data){
					Tips.loaded();
					if(data){
						if(data.isSuccess){
							var param = {
								info:"删除成功！",
								callFn : function(){
									var catalogId = $("#catalogId").val();
									var param={
										div:".help-content",
										url:Domain.cms_path+"/homepage/help/helpThemeManage.htm?catalogId="+catalogId
									}
									Sender.load(param);
								}
							}
							Tips.showSuccessMsg(param);
						}else{
							Tips.showErrorMsg({info:data.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
		
	}
	//全选或者反选
	function checkAllOrNot(){
		var flag = $(this).prop("checked");
		if(flag){
			$(".check_item").prop("checked",true);
		}else{
			$(".check_item").prop("checked",false);
		}
	}
	//主题新增
	function helpThemeAdd(){
		var catalogId = $("#catalogId").val();
		var param={
			div:".help-content",
			url:Domain.cms_path+"/homepage/help/helpThemeToEdit.htm?catalogId="+catalogId
		}
		Sender.load(param);
	}
	//主题修改
	function helpThemeEidt(){
		var themeId = $(this).attr("val");
		var catalogId = $("#catalogId").val();
		var param={
			div:".help-content",
			url:Domain.cms_path+"/homepage/help/helpThemeToEdit.htm?catalogId="+catalogId+"&themeId="+themeId
		}
		Sender.load(param);
	}
})(jQuery,window,document);