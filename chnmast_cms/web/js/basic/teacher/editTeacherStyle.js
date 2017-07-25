(function($, window, document){
	var pub = {};
	window.EditTeacherStyle = pub;
    pub.init = function(subjectCode) {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
    	$("#saveBtn").bind("click", submitPicture);
    }
    //保存
    function submitPicture(){
    	FieldMsg.clearFieldError("#addPictureForm"); //清除各字段的报错提示
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#addPictureForm")) return;
       	if($("#uploadTempPicFile") && $("#uploadTempPicFile").val() == '' && $("#pictureFileInput").val() == ''){
       		Tips.showErrorMsg({"info":"视频图片必须上传!"});
       		return ;
       	}
       	if($("#uploadTempVideoFile") && $("#uploadTempVideoFile").val() == '' && $("#videoFileInput").val() == ''){
       		Tips.showErrorMsg({"info":"教师风采视频必须上传!"});
       		return ;
       	}
		$('#addPictureForm').submit();
    }
    
})(jQuery, window, document);