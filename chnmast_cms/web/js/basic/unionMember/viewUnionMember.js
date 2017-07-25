(function($, window, document){
	var pub = {};
	window.viewUnionMember = pub;
	viewUnionMember.isTeam = false;
	viewUnionMember.idVal = 0;
	
    pub.init = function(isTeam,idVal) {
    	viewUnionMember.isTeam = isTeam;
    	viewUnionMember.idVal = idVal;
        bindEvent();
    }
    
    //绑定页面事件方法
    function bindEvent() {
    	//$('#unionCode').bind('blur',ajaxValidateUnionCode);
    	$('#audit1').bind('click',auditPassed);//审核通过
    	$('#audit2').bind('click',auditNotPassed);//审核不通过
    	$('#audit3').bind('click',auditCancel);//取消审核
    }
    
    /**
     * 审核通过
     */
    function auditPassed(){
    	var idVal = $(this).attr('dataValue');
    	$.ajax({
    		"url" : "/basic/auditUnionMember.htm",
			"data" : {
				"auditStu" : 2,
				"unionMemberId" : idVal
			},
			"success" : function(msg) {
				if(msg == 'FALSE_ID'){
					Tips.showErrorMsg({"info" : "审核未能通过，数据异常，请刷新页面重试！"});
					return;
				}else if(msg == 'FALSE_UNIONCODE_NULL'){
					Tips.showErrorMsg({"info" : "审核未能通过，生成会员编号失败，请刷新页面重试！"});
					return;
				}else if(msg == 'FALSE_UNIONCODE_REPEAT'){
					Tips.showErrorMsg({"info" : "审核未能通过，生成会员编号重复，请刷新页面重试！"});
					return;
				}else if(msg == 'FALSE_ILLEGAL'){
					Tips.showErrorMsg({"info" : "审核操作失败，请刷新页面重试！"});
					return ;
				}else{
					if(msg.substring(0,5) == "TRUE_") {
						Tips.showSuccessMsg({
							"info" : "审核通过成功，会员编号为：<span class='c-red'>"+msg.substring(5)+"</span>",
							"callFn" : function(){
								window.location.reload(); 
							}
						});
					}
		    	}
			}
    	});
    }

    /**
     * 审核不通过
     */
    function auditNotPassed(){
    	$("#popUp2_tips").html('');
    	var idVal = $(this).attr('dataValue');
    	Sender.openDiv({
            "div" : "#popUp2",
            "closeObject" : "#popUp2 a.close",
        });
    	
    	$("#popUp2_audit").bind('click',function(){
    		var auditMsg = $('#popUp2_auditMsg').val();
        	if(auditMsg == null || auditMsg == ""){
        		$("#popUp2_tips").html('请输入审核信息');
        		$('#popUp2_auditMsg').focus();
        		return;
        	}
        	
        	$.ajax({
        		"url" : "/basic/auditUnionMember.htm",
    			"data" : {
    				"auditStu" : 3,
    				"auditMsg" : auditMsg,
    				"unionMemberId" : idVal
    			},
    			"success" : function(msg) {
    				if(msg == 'FALSE_AUDITMSG_NULL'){
    					Tips.showErrorMsg({
							"info" : "请输入审核信息！",
						});
    					return ;
    				}else if(msg== 'FALSE_ILLEGAL'){
    					Tips.showErrorMsg({
							"info" : "审核不通过操作失败，请刷新页面重试！",
						});
    					return ;
    				}else if(msg=='true'){
    					Tips.showSuccessMsg({
							"info" : "审核不通过成功",
							"callFn" : function(){
								window.location.reload(); 
							}
						});
    				}
    			}
        	});
    	});
    }

    /**
     * 取消审核
     */
    function auditCancel(){
    	var idVal = $(this).attr('dataValue');
    	var param = {
			info : "您是否确定取消审核？",
			callFn : function(){
				$.ajax({
		    		"url" : "/basic/auditUnionMember.htm",
					"data" : {
						"auditStu" : 1,
						"unionMemberId" : idVal
					},
					"success" : function(msg) {
						if(msg =='FALSE_ILLEGAL'){
							Tips.showErrorMsg({info:"取消审核操作失败，请刷新页面重试！"});
						}else if(msg=='true'){
							Tips.showSuccessMsg({
								"info" : "取消审核操作成功",
								"callFn" : function(){
									window.location.reload(); 
								}
							});
						}
					}
		    	});
			}
		};
		Tips.showConfirmWin(param);
    }
   
    /**
     * 验证会员编号的重复性
     */
    function ajaxValidateUnionCode(){
    	var params = {
			"url" : "/basic/ajaxValidateUnionCode.htm",
			"data" : {
				"unionCode" : $(this).val()  
			},
			"dataType" : "text",
			"fn" : function(isOK) {
				$("#check_error").html("");
				if(isOK=='false'){
					$("#check_error").html("已经存在此会员编号请重新输入！");
					return;
				}
			}
		};
	  Sender.ajax(params);
    }
    
})(jQuery, window, document);