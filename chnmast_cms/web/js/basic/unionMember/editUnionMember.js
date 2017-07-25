/** 个人会员-编辑 **/
(function($, window, document){
	var pub = {};
	window.editUnion_pers = pub;
    pub.init = function() {
    	$("#saveBtn").bind("click", savePers);
    }
    
    var isUp = true;
    function savePers(){
    	if(!valAllInfoPers()){
    		return;
    	}
    	if(!isUp){
    		Tips.showWarnWin({"info" : "不要重复提交！"});
			return;
		}
    	var id = $("#unionMemberId").val();
    	isUp = false;
    	Tips.loading({info:"正在保存..."});
    	Sender.submitAjax({
			"frm" : "#unionMemberForm",
			"dataType" : "json",
			"fn" : function(result){
				if(result.isSuccess){
					Tips.showSuccessMsg({
						"info" : "会员保存成功！",
						"callFn" : function(){
							window.location.href = "/basic/viewUnionMember.htm?isTeam=false&unionMemberId="+id;
						}
					});
				}else{
					Tips.showErrorMsg({"info":result.errorMsg});
				}
			}
		});
    }
    
    function valAllInfoPers() {
    	FieldMsg.clearFieldError("#unionMemberForm"); //清除各字段的报错提示
    	if(!Verify.checkAllVerify("#unionMemberForm")){ //验证所有字段
    		var position = $(".form-table").offset();
	        $(document).scrollTop(position.top);
    		return false; 
    	}
    	
    	if(!Verify.checkEmail('#email')){
    		return false;
    	}
//    	if(!Validator.isMobile($('#telephone').val()) && !Validator.isPhone($('#telephone').val())){
//    		 FieldMsg.addFieldError($('#telephone'),"请输入合法的联系电话！",true);
//    		return false;
//    	}
    	
    	return true;
    }
    
})(jQuery, window, document);



/** 团体会员-编辑 **/
(function($, window, document){
	var pub = {};
	window.editUnion_team = pub;
    pub.init = function() {
    	$("#saveBtn").bind("click", saveTeam);
    }
    
    var isUp = true;
    function saveTeam() {
    	if(!valAllInfoTeam()){
    		return;
    	}
    	if(!isUp){
    		Tips.showWarnWin({"info" : "不要重复提交！"});
			return;
		}
		var id = $("#unionMemberId").val();
    	isUp = false;
    	Tips.loading({info:"正在保存..."});
    	Sender.submitAjax({
			"frm" : "#unionMemberForm",
			"dataType" : "json",
			"fn" : function(result){
				if(result.isSuccess){
					Tips.showSuccessMsg({
						"info" : "会员保存成功！",
						"callFn" : function(){
							window.location.href = "/basic/viewUnionMember.htm?isTeam=true&unionMemberId="+id;
						}
					});
				}else{
					Tips.showErrorMsg({"info":result.errorMsg});
				}
			}
		});
    }
    
    function valAllInfoTeam(){
    	FieldMsg.clearFieldError("#unionMemberForm"); //清除各字段的报错提示
    	if(!Verify.checkAllVerify("#unionMemberForm")){ //验证所有字段
    		var position = $(".form-table").offset();
	        $(document).scrollTop(position.top);
    		return false; 
    	}
    	if(!Verify.checkEmail('#email')){
    		return false;
    	}
//    	if(!Validator.isMobile($('#telephone').val()) && !Validator.isPhone($('#telephone').val())){
//    		FieldMsg.addFieldError($('#telephone'),"请输入合法的联系电话！",true);
//    		return false;
//    	}
    	
    	return true;
    }
})(jQuery, window, document);