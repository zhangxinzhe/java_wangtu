(function($, window, document, undefined) {
	var pub = {};
	window.addStudent = pub;
	pub.init = function() {
		bindEvent();
	}
	// 绑定事件
	function bindEvent() {
		$("#saveBtn").on("click", doSaveStudent);
	}
	// 保存图片
	function doSaveStudent() {
		if (!Verify.checkAllVerify("#studentForm")) {
			return;
		}
		if (Validator.isNotBlank($("#orderNo").val()) && !Validator.isNumber($("#orderNo").val())) {
			Tips.showAlertWin({
				info : "显示次序请填写数字！"
			});
			return;
		}
		$("#studentForm").attr("action", Domain.cms_path + '/homepage/stu/studentSave.htm');
		$("#studentForm").submit();
	}
})(jQuery, window, document);