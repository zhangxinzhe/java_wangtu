(function($, window, document, undefined) {
	var pub = {};
	window.competeManage = pub;
	pub.init = function() {
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		$('#search').bind('click',search);
		$('.deleteBtn').bind('click',deleteOneItem);
	}
	
	//搜索
	function search(){
		$("#coruseSearchForm").submit();
	}
	
	// 删除一条数据
	function deleteOneItem() {
		var id = $(this).attr("val");
		var param = {
			info:"确定要删除这条信息吗？",
			callFn:function(){
				var url = Domain.cms_path+"/homepage/compete/deleteCompete.htm"
				var params = {
					'id':id
				};
				$.post(url,params,function(dto){
					if(dto){
						if(dto.isSuccess){
							window.location.href = Domain.cms_path+"/homepage/compete/competeManage.htm";
						}else{
							Tips.showErrorMsg({info:dto.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
	}

})(jQuery, window, document);