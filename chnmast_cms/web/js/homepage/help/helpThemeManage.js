(function($,window,document,undefined){
	var b = {};
	window.HelpThemeManage = b;
	b.init = function(){
		bindEvent();
		//菜单第一条默认选中
		$(".menu_catalog").first().click();
	}
	function bindEvent(){
		//分类管理tab
		$("#catalog_tab").on("click",function(){
			helpManage("catalog");
		});
		$("#theme_tab").on("click",function(){
			helpManage("theme");
		});
		//点击菜单栏加载主题
		$(".menu_catalog").on("click",loadHelpTheme);
	}
	//加载分类主题
	function loadHelpTheme(){
		var catalogId = $(this).attr("val");
		var param={
			div:".help-content",
			url:Domain.cms_path+"/homepage/help/helpThemeManage.htm?catalogId="+catalogId
		}
		Sender.load(param);
	}
	function helpManage(operType){
		window.location.href = Domain.cms_path+"/homepage/help/helpManage.htm?operType="+operType
	}
})(jQuery,window,document);