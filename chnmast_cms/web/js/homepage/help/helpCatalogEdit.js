(function($,window,document,undefined){
	var b = {};
	window.HelpCatalogEdit = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		//提交保存 
		$("#catalog_edit_sub").on("click",catalogEdit);
	}
	//编辑帮助分类
	function catalogEdit(){
		if(!Verify.checkAllVerify("#catalogForm")){
			return;
		}
		var url = Domain.cms_path +"/homepage/help/helpCatalogSave.htm";
		$("#submit_tips").html("<img src='" + Domain.cms_path + "/images/loading_little.gif' />");
		$.post(url,$("#catalogForm").serialize(),function(dto){
			if(dto){
				if(dto.isSuccess){
					$("#submit_tips").html("<span class='c-green'>保存成功！</span>");
					var catalogLevel = $("#catalogLevel").val();
					window.location.href = Domain.cms_path+"/homepage/help/helpManage.htm?catalogLevel="+catalogLevel;
				}else{
					$("#submit_tips").html("<span class='c-orange'>"+dto.errorMsg+"</span>");
				}
			}else{
				$("#submit_tips").html("<span class='c-orange'>保存失败，请重试！</span>");
			}
		},"json");
		
	}
})(jQuery,window,document);