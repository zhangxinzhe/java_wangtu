(function($, window, document){
	var pub = {};
	window.editMusicTutor = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
    	$('#saveBtn').bind('click',saveMusicTutor);
    }
    
    //保存
    var isUp = true;
    function saveMusicTutor(){
    	if(!validate()){
    		return;
    	}
    	if(!isUp){
    		Tips.showWarnWin({"info" : "不要重复提交！"});
			return;
		}
    	var id = $("#tutorId").val();
    	isUp = false;
    	Tips.loading({info:"正在保存..."});
    	Sender.submitAjax({
			"frm" : "#musicTutorForm",
			"dataType" : "json",
			"fn" : function(result){
				if(result.isSuccess){
					Tips.showSuccessMsg({
						"info" : result.msg,
						"callFn" : function(){
							window.location.href = "/basic/viewMusicTutor.htm?tutorId="+id;
						}
					});
				}else{
					Tips.showErrorMsg({"info":result.msg});
				}
			}
		});
    }
    
    //验证表单字段
    function validate() {
    	FieldMsg.clearFieldError("#musicTutorForm"); //清除各字段的报错提示
    	
    	if($('#photoFile').val()==''){
    		if($('#uploadTempFile').val()==null || $('#uploadTempFile').val()=='' || $('#uploadTempFile').val()==undefined){
    			Tips.showErrorMsg({"info":"请选择个人照片！"});
    			return false;
    		}
    	}
    	
    	
    	if(!Verify.checkAllVerify("#musicTutorForm")){ //验证所有字段
    		var position = $(".form-table").offset();
	        $(document).scrollTop(position.top);
    		return false; 
    	}
    	
    	if(!Verify.checkEmail('#email')){
    		return false;
    	}
    /*	if(!Validator.isMobile($('#phone').val()) && !Validator.isPhone($('#phone').val())){
    		 FieldMsg.addFieldError($('#telephone'),"请输入合法的联系电话！",true);
    		return false;
    	}*/
    	
    	return true;
    }
    
    
})(jQuery, window, document);