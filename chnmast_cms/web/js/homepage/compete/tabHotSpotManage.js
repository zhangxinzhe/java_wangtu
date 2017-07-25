(function($, window, document, undefined) {
	var pub = {};
	window.tabHotSpotManage = pub;
	var tabHotSpotManage.isTeam = null;
	pub.init = function(isTeam) {
		tabHotSpotManage.isTeam= isTeam;
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		//查询按钮
		$("#searchButton").on("click",queryInfo);
		//删除
		$(".info_delete_a").on("click",deleteInfo);
	}
	
	//删除
	function deleteInfo(){
		var id = $(this).attr("val");
		var param = {
			info:"确定要删除这条信息吗？",
			callFn:function(){
				var url = Domain.cms_path+"/homepage/compete/deleteHotSpot.htm"
				var params = {
					infoId:id
				};
				$.post(url,params,function(dto){
					if(dto){
						if(dto.isSuccess){
							window.location.href = Domain.cms_path+"/homepage/compete/tabHotspot.htm?id="+$('#objectId').val();
						}else{
							Tips.showErrorMsg({info:dto.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
	}
	
	//查询
	function queryInfo(){
		$(".infoManage").submit();
	}
	

})(jQuery, window, document);