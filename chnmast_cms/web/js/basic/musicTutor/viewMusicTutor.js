(function($, window, document){
	var pub = {};
	window.viewMusicTutor = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
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
    		"url" : "/basic/auditMusicTutor.htm",
			"data" : {
				"auditStu" : 2,
				"tutorId" : idVal
			},
			dataType: 'json',
			"success" : function(json) {
				if(json.isSuccess){
					Tips.showSuccessMsg({
						"info" : json.msg,
						"callFn" : function(){
							window.location.reload(); 
						}
					});
				}else{
					Tips.showErrorMsg({"info" : json.msg});
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
        		"url" : "/basic/auditMusicTutor.htm",
    			"data" : {
    				"auditStu" : 3,
    				"auditMsg" : auditMsg,
    				"tutorId" : idVal
    			},
    			dataType: 'json',
    			"success" : function(json) {
    				if(json.isSuccess){
    					Tips.showSuccessMsg({
    						"info" : json.msg,
    						"callFn" : function(){
    							window.location.reload(); 
    						}
    					});
    				}else{
    					Tips.showErrorMsg({"info" : json.msg});
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
		    		"url" : "/basic/auditMusicTutor.htm",
					"data" : {
						"auditStu" : 1,
						"tutorId" : idVal
					},
					"dataType": 'json',
					"success" : function(json) {
						if(json.isSuccess){
	    					Tips.showSuccessMsg({
	    						"info" : json.msg,
	    						"callFn" : function(){
	    							window.location.reload(); 
	    						}
	    					});
	    				}else{
	    					Tips.showErrorMsg({"info" : json.msg});
	    				}
					}
		    	});
			}
		};
		Tips.showConfirmWin(param);
    }
    
})(jQuery, window, document);