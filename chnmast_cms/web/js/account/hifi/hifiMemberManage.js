(function($, window, document){
	var pub = {};
	window.hifiManage = pub;
	pub.init = function(){
		bindEvent();
	}
	
    function bindEvent() {
    	$("#searchButton").bind("click", search);
    	$(".tradingInfo").bind("click", tradingInfo);
    }
    
    //搜索
	function search(){
		$("#hifiUserForm").attr("action", Domain.cms_path + "/account/hifiMemberManage.htm");
		$("#hifiUserForm").submit();
	}
	//交易记录
	function tradingInfo(){
		var userId = $(this).attr("dataValue");
		Sender.openDiv({
            "div" : "#tradingInfoDiv",
            "closeObject" : "#tradingInfoDiv .close",
            "url" : Domain.cms_path+"/account/hifiMemberOrder.htm?id="+userId
        });
	}
})(jQuery, window, document);
