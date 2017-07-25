(function($, window, document){
	var pub = {};
	window.orderAnnul = pub;
	pub.init = function(){
		bindEvent();
	}
	
    function bindEvent() {
    	$("#searchButton").bind("click", search);
    	$("a.viewAnnul").bind("click", viewAnnul);
    	$("a.addAnnul").bind("click", addAnnul);
    	$("a.cancelAnnul").bind("click", cancelAnnul);
    }
	
    /**
     * 搜索
     */
	function search(){
		$("#orderSearchForm").attr("action", "/account/orderAnnul.htm");
		$("#orderSearchForm").submit();
	}
	
	/**
	 * 查看优惠
	 */
	function viewAnnul(){
		$("#viewAnnulDiv").html("");
		var _this = this;
        Sender.openDiv({
            "div" : "#viewAnnulDiv",
            "closeObject" : "#viewAnnulDiv a.close",
            "url" : Domain.cms_path+"/account/viewAnnul.htm?orderId="+$(_this).attr("orderId")
        });
	}
	
	/**
	 * 添加优惠
	 */
	function addAnnul(){
		var orderId = $(this).attr("orderId");
		Sender.ajax({
			"url" : Domain.cms_path+"/account/isCanOperateAnnul.htm",
			"data" : {orderId:orderId, operate:"ADD"},
			"fn" : function(result) {
				if(!dealResult(result)){
					return;
				}
				Sender.openDiv({
		            "div" : "#viewAnnulDiv",
		            "closeObject" : "#viewAnnulDiv a.close, #viewAnnulDiv a.reset",
		            "url" : Domain.cms_path+"/account/addAnnul.htm?orderId="+orderId
		        });
			}
		});
	}
	
	/**
	 * 取消优惠
	 */
	function cancelAnnul(){
		var orderId = $(this).attr("orderId");
		var tradeNo = $(this).attr("tradeNo");
		Sender.ajax({
			"url" : Domain.cms_path+"/account/isCanOperateAnnul.htm",
			"data" : {orderId:orderId, operate:"CANCEL"},
			"fn" : function(result) {
				if(!dealResult(result)){
					return;
				}
				Tips.showConfirmWin({
					info : "此订单确定需要取消优惠？",
					callFn : function(){
						Sender.ajax({
							"url" : Domain.cms_path+"/account/cancelAnnul.htm",
							"data" : {orderId:orderId,  "order.tradeNo":tradeNo},
							"fn" : function(result) {
								if(result=="success"){
									Tips.showSuccessMsg({
										"info" : "订单取消优惠成功！",
										"callFn" : function(){
											$("#searchButton").click();
										}
									});
								}else if(result=="ERROR"){
									Tips.showErrorMsg({info:"订单信息有误，请刷新页面重试！"});
								}else if(result=="ORDER_ANNUL_LOST"){
									Tips.showErrorMsg({info:"订单优惠记录异常，请刷新页面重试！"});
								}else if(result=="ORDER_REFLUSG"){
									Tips.showErrorMsg({info:"订单已更新，请刷新页面重试！"});
								}
							}
						});
					}
				});
			}
		});
	}
	
	function dealResult(result){
		if(result == "ERROR"){
			Tips.showAlertWin({"info" : "订单号异常，请刷新页面重试！"});
			return false;
		}
		else if(result == "ANNUL_ISHAVE"){
			Tips.showAlertWin({"info" : "此订单已经添加过后台优惠，请刷新页面！"});
			return false;
		}
		else if(result == "ANNUL_ISLOST"){
			Tips.showAlertWin({"info" : "此订单后台优惠丢失异常，请刷新页面！"});
			return false;
		}
		else if(result == "ORDER_LOST"){
			Tips.showAlertWin({"info" : "订单已丢失，请刷新页面重试！"});
			return false;
		}
		else if(result == "ORDER_SUCCESS"){
			Tips.showAlertWin({"info" : "订单已支付成功，无法继续操作！"});
			return false;
		}
		else if(result == "ORDER_CANCEL"){
			Tips.showAlertWin({"info" : "订单已关闭，无法继续操作！"});
			return false;
		}
		else if(result == "ORDER_TIMEOUT"){
			Tips.showAlertWin({"info" : "订单已超时，无法继续操作！"});
			return false;
		}
		else if(result == "ORDER_WILLPAY"){
			Tips.showAlertWin({"info" : "该订单已选择支付方式，无法继续操作！<br/><br/>注：只能对未选择支付方式的订单<br/>添加或取消优惠。"});
			return false;
		}
		return true;
	}
	
	
	pub.addAnnulOperate = function(){
		$("#annulAmount").unbind().bind("blur", function(){
			$("#afterAmount").html("");
			FieldMsg.clearFieldError($("#annulAmount"));
			var amount = StringUtil.trim($("#annulAmount").val());
			if(amount == ""){
				FieldMsg.addFieldError("#annulAmount","不能为空");
				return;
			}
			var beforeAmount = parseFloat($("#beforeAmount").attr("dataValue"));
			amount = parseFloat(amount);
			if(!Validator.isNotMinusFloat(amount,2)){
				FieldMsg.addFieldError("#annulAmount","最多保留2小数的正数");
				return;
			}else if(amount>beforeAmount){
				FieldMsg.addFieldError("#annulAmount","优惠金额不能大于订单金额");
				return;
			}else{
				$("#afterAmount").html((beforeAmount-amount).toFixed(2)+" 元");
			}
		});
		
		var submit = false;
		$("#addAnnulSubmit").bind("click", function(){
			if(submit){
				return;
			}
			submit = true;
			if(!validateAnnul()){
				submit = false;
				return;
			}
			$("#orderAnnulForm").attr("action", Domain.cms_path+"/account/addAnnul.htm");
			Sender.submitAjax({
				"frm" : "#orderAnnulForm",
				"fn" : function(result){
					if(result=="success"){
						Tips.showSuccessMsg({
							"info" : "订单添加优惠成功！",
							"callFn" : function(){
								$("#viewAnnulDiv a.reset").click;
								$("#searchButton").click();
							}
						});
					}else if(result=="ERROR"){
						Tips.showErrorMsg({info:"订单信息有误，请刷新页面重试！"});
					}else{
						Tips.showErrorMsg({info:result});
					}
				}
			});
		});
	}
	
	function validateAnnul(){
		if($("#annulType").val() == ""){
			FieldMsg.addFieldError("#annulType","不能为空");
			return false;
		}
		var amount = $("#annulAmount").val();
		if(StringUtil.trim(amount) == ""){
			FieldMsg.addFieldError("#annulAmount","不能为空");
			return false;
		}
		var beforeAmount = parseFloat($("#beforeAmount").attr("dataValue"));
		amount = parseFloat(amount);
		if(!Validator.isNotMinusFloat(amount,2)){
			FieldMsg.addFieldError("#annulAmount","最多保留2小数的正数");
			return false;
		}
		if(amount>beforeAmount){
			FieldMsg.addFieldError("#annulAmount","优惠金额不能大于订单金额");
			return false;
		}
		if($("#remark").val() == ""){
			FieldMsg.addFieldError("#remark","不能为空");
			return false;
		}
		return true;
	}
})(jQuery, window, document);
