(function($, window, document, undefined) {
	var pub = {};
	window.editAttend = pub;
	pub.init = function() {
		// 初始化参赛曲目列表
		showSecondSong();
		
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		$("#saveBtn").bind("click",save);
		$("#groupType").on("change",showSecondSong);
	}
	
	//保存方法
	function save(){
		FieldMsg.clearFieldError("#editAttendForm"); //清除各字段的报错提示
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#editAttendForm")) return;
       	var obj=$(".form-table").find('input:radio[name="competeAttend.sex"]:checked');
       	if(obj.length == 0){
       		Tips.showErrorMsg({"info":"请选择性别！"});
       		return;
       	}
       	var img=$("#attendImg").attr("src");
       	if(img=="/images/default_teacher.png"||img==""){
       		Tips.showErrorMsg({"info":"请选择参赛照片！"});
       		return;
       	}
		$('#editAttendForm').submit();
	}
	
	//根据参赛组别决定是否显示复赛歌曲
	function showSecondSong(){
		if($("#groupType").val()=="TEA_FOLK_STYLE"||$("#groupType").val()=="TEA_BEL_CANTO"){ 
			$("#secondSong").hide();
			$("#secondSong").find('input[name="competeAttend.secondSong"]').removeAttr("notNull");
		}else {
			$("#secondSong").show();
			$("#secondSong").find('input[name="competeAttend.secondSong"]').attr("notNull",true);
		}
	}

})(jQuery, window, document);