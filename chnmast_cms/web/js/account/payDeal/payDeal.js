(function($, window, document){
	var pub = {};
	window.PayDeal = pub;
	var submit = false;
	pub.init = function(){
		bindEvent();
	}
	
	pub.cashpay = function(){
		$("#cashpayBut").bind("click", function(){
			if (submit){
				return;
			}
			submit = true;
			if(!Verify.checkAllVerify("#cashpayForm")){
				submit = false;
				return;
			}
			var url="/account/doPayDeal.htm";
			$(this).onAjax({
				"url" : url, 
				"data" : $("#cashpayForm").serialize(), 
				"fn" : function(rs){
					if (rs == "success"){
						var msg = "完成订单成功！";
						var param = {
							info:msg,
							callFn : search
						}
						Tips.showSuccessMsg(param);
					}else if (rs == "ERROR"){
						Tips.showErrorMsg({info:"订单信息有误，请重新操作！"});
					}else{
						Tips.showErrorMsg({info:rs});
					}
					submit = false;
				}
			});
		});
	}
	
	//绑定页面事件方法
    function bindEvent() {
    	$("#searchButton").bind("click", search);
    	$(".cancel").bind("click", cancel);
    	$(".finish").bind("click", finish);
    }
	
    /**
     * 搜索
     */
	function search(){
		$("#payForm").attr("action", "/account/payDeal.htm");
		$("#payForm").submit();
	}
	
	/**
	 * 退回
	 */
	function cancel(){
		var orderId = $(this).attr("orderId");
		var param = {
			info : "确定退回此订单吗？",
			callFn : function(){
				dealOrder(orderId, "cancel");
			}
		};
		Tips.showConfirmWin(param);
	}
	
	/**
	 * 完成
	 */
	function finish(){
		var orderId = $(this).attr("orderId");
		var payType = $(this).attr("payType");
		var param = {
			info : "确定完成此订单吗？",
			callFn : function(){
				if (payType != "ALIPAY" && payType!='WECHATPAY' && payType!= 'APPLEPAY'){
					var param = {
						div:"#cashpayDiv",
						closeObject:".close,.reset",
						url:"/account/payDeal-cashpay.htm?order.id=" + orderId
					};
					Sender.openDiv(param);
				}else{

					dealOrder(orderId, "finish");
				}
			}
		};
		Tips.showConfirmWin(param);
	}
	
	function dealOrder(orderId, method){
		if (submit){
			return;
		}
		submit = true;
		var url="/account/doPayDeal.htm";
		$(this).onAjax({
			"url" : url, 
			"data" : {"order.id":orderId, "method":method}, 
			"fn" : function(rs){
				if (rs == "success"){
					var msg;
					if (method == "cancel"){
						msg = "退回订单成功！";
					}else if (method == "finish"){
						msg = "完成订单成功！";
					}
					var param = {
						info:msg,
						callFn : search
					}
					Tips.showSuccessMsg(param);
				}else if (rs == "ERROR"){
					Tips.showErrorMsg({info:"订单信息有误，请重新操作！"});
				}else{
					Tips.showErrorMsg({info:rs});
				}
				submit = false;
			}
		});
	}
})(jQuery, window, document);
