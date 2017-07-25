(function($,window,document,undefined){
	var b = {};
	window.InfoManage = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		//查询按钮
		$("#searchButton").on("click",queryInfo);
		//新增按钮
		$("#newButton").on("click",addInfo);
		//删除新闻
		$(".info_delete_a").on("click",deleteInfo);
		
	}
	
	//删除公告新闻
	function deleteInfo(){
		var id = $(this).attr("val");
		var param = {
			info:"确定要删除这条信息吗？",
			callFn:function(){
				var url = Domain.cms_path+"/homepage/info/infoDelete.htm"
				var params = {
					infoId:id
				};
				$.post(url,params,function(dto){
					if(dto){
						if(dto.isSuccess){
							window.location.href = Domain.cms_path+"/homepage/info/infoManage.htm";
						}else{
							Tips.showErrorMsg({info:dto.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
	}
	//查询公告、新闻
	function queryInfo(){
		$(".infoManage").submit();
	}
	//新增新闻、公告
	function addInfo(){
		window.location.href = Domain.cms_path+"/homepage/info/infoAdd.htm";
	}
})(jQuery,window,document);