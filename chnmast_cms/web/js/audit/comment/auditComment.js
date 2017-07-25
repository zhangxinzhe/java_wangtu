(function($, window, document){
	var pub = {};
	window.auditComment = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
     	$('#audit1').bind('click',auditPassed);//审核通过
    	$('#audit2').bind('click',auditNotPassed);//审核不通过
    	//$('#audit3').bind('click',auditCancel);//取消审核
    }
    
    /**
     * 审核通过
     */
    function auditPassed(){
    	var idVal = $(this).attr('dataValue');
    	var type=$(this).attr('flag');
    	$.ajax({
    		"url" : "/audit/comment/auditCourseComment.htm",
			"data" : {
				"auditStu" : 2,
				"id" : idVal,
				"type":type
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
    	var idVal = $(this).attr('dataValue');
    	var type=$(this).attr('flag');
    	$.ajax({
    		"url" : "/audit/comment/auditCourseComment.htm",
			"data" : {
				"auditStu" : 3,
				"id" : idVal,
				"type":type
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

    
})(jQuery, window, document);