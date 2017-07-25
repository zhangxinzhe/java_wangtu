(function($, window, document){
	var pub = {};
	window.EditPicture = pub;
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
       	if($("#uploadTempFile").val() == '' && $("#pictureFileInput").val() == ''){
       		Tips.showErrorMsg({"info":"请先选择上传图片!"});
       		return ;
       	}
		$('#addPictureForm').submit();
    }
    
})(jQuery, window, document);