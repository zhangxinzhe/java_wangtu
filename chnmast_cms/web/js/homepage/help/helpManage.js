(function($,window,document,undefined){
	var b = {};
	window.HelpManage = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		//分类管理tab
		$("#catalog_tab").on("click",function(){
			helpManage("catalog");
		});
		$("#theme_tab").on("click",function(){
			helpManage("theme");
		});
		//分类新增
		$(".catalog_add_btn").on("click",function(){
			catalogEdit(0);
		});
		//分类修改
		$(".catalog_toedit").on("click",function(){
			var id= $(this).attr("val");
			catalogEdit(id);
		});
		//全选或者反选
		$("#check_all").on("click",checkAllOrNot);
		//删除
		$("#delete_catalog_btn").on("click",deleteCatalog);
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
	//删除帮助分类
	function deleteCatalog(){
		//判断有没有选中
		var $ids = $(".check_item:checked");
		if($ids.length==0){
			Tips.showErrorMsg({info:"请至少选择一条数据！"});
			return;
		}
		$("#delete_form").html("");
		$ids.each(function(){
			$("#delete_form").append("<input type='hidden' name='ids' value='"+$(this).val()+"'/>");
		});
		
		var param = {
			info:"确定要删除这些分类吗？",
			callFn:function(){
				var catalogLevel = $("#catalogLevel").val();
				var url = Domain.cms_path+"/homepage/help/helpCatalogDelete.htm?catalogLevel="+catalogLevel;
				Tips.loading({info:"正在删除数据..."});
				$.post(url,$("#delete_form").serialize(),function(data){
					Tips.loaded();
					if(data){
						if(data.isSuccess){
							var param = {
								info:"删除成功！",
								callFn : function(){
									window.location.href = Domain.cms_path+"/homepage/help/helpManage.htm?catalogLevel="+catalogLevel;
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
	//帮助分类编辑
	function catalogEdit(catalogId){
		var catalogLevel = $("#catalogLevel").val();
		var param = {
			div:"#editHelpCatalog",
			closeObject:".close,.reset",
			url:Domain.cms_path+"/homepage/help/helpCatalogToEdit.htm?catalogId="+catalogId+"&catalogLevel="+catalogLevel
		}
		Sender.openDiv(param);
	}
	function helpManage(operType){
		window.location.href = Domain.cms_path+"/homepage/help/helpManage.htm?operType="+operType
	}
})(jQuery,window,document);