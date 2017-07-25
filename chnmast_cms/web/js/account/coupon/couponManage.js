(function($, window, document){
	var pub = {};
	window.couponManage = pub;
	pub.init = function(){
		bindEvent();
	}
	
    function bindEvent() {
    	$("#searchButton").bind("click", search);
    	$("#exportCoupon").bind("click", exportCoupon);
    	$(".coupon-del").bind("click", deleteCoupon);
    	$(".coupon-cancel").bind("click", cancelCoupon);
    }
    
    //搜索
	function search(){
		$("#couponSearchForm").attr("action", Domain.cms_path + "/account/couponManage.htm");
		$("#couponSearchForm").submit();
	}
	
	//导出
	function exportCoupon(){
		$("#couponSearchForm").attr("action", Domain.cms_path + "/account/exportCoupon.htm");
		$("#couponSearchForm").attr("enctype", "application/x-www-form-urlencoded");
		$("#couponSearchForm").submit();
	}
	
	//删除
	function  deleteCoupon(){
		var couponCode = $(this).attr('dataValue');
		var param1 = {
			info : "您确定删除此优惠券信息？",
			callFn : function() {
				var params = {
					"url" : Domain.cms_path + "/account/deleteCoupon.htm",
					"data" : {"couponCode":couponCode, "delete_option":"delete"},
					"dataType" : "json",
					"fn" : function(result) {
						if(result.isSuccess) {
							Tips.showSuccessMsg({
								"info" : result.message,
								"callFn" : function() {
									$("#searchButton").click();
								}
							});
						}else {
							Tips.showErrorMsg({"info" : result.message});
							return;
						}
					}
				};
				Sender.ajax(params);
			}
		}
		//验证
		Sender.ajax({
			"url" : Domain.cms_path + "/account/deleteCoupon.htm",
			"data" : {"couponCode":couponCode, "delete_option":"valiadte"},
			"dataType" : "json",
			"fn" : function(result) {
				if(result.isOk) {
					Tips.showConfirmWin(param1);
				}else {
					Tips.showErrorMsg({"info" : result.message});
					return;
				}
			}
		});
	}
	
	//注销
	function cancelCoupon(){
		var couponCode = $(this).attr('dataValue');
		if(couponCode==""){
			return;
		}
		Tips.showConfirmWin({
			"info" : "优惠码注销后将无法再使用，<br/>您确定要注销此优惠码？",
			"callFn" : function(data){
				Sender.ajax({
					"url" : Domain.cms_path + "/account/cancelCoupon.htm",
					"data" : {"couponCode":couponCode},
					"dataType" : "json",
					"fn" : function(result){
						if(result.isSuccess){
							Tips.showSuccessMsg({
								"info" : result.promptMsg,
								"callFn" : function() {
									$("#searchButton").click();
								}
							});
						}else{
							Tips.showErrorMsg({"info" : result.errorMsg});
						}
					}
				});
			}
		});
	}
	
	
})(jQuery, window, document);
