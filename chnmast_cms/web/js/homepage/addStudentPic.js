(function($, window, document, undefined) {
	var pub = {};
	window.addStudentPic = pub;
	pub.init = function(id) {
		window.addStudentPic.stuId = id;
		bindEvent();
	}
	// 绑定事件
	function bindEvent() {
		$("#saveBtn").on("click", doSavePic);
	}
	// 保存图片
	function doSavePic() {
		// 新增 uploadTempFile不能为空
		if (!Validator.isNotBlank($("#pictureIdHid").val())) {
			if (!Validator.isNotBlank($("#uploadTempFile1").val())) {
				Tips.showAlertWin({
					info : "请上传图片！"
				});
				return;
			}
			if ($("#uploadTempFile2").length > 0 && !Validator.isNotBlank($("#uploadTempFile2").val())) {
				Tips.showAlertWin({
					info : "请上传视频！"
				});
				return;
			}
		}
		if (!Verify.checkAllVerify("#addPictureForm")) {
			return;
		}
		if (Validator.isNotBlank($("#showOrder").val()) && !Validator.isNumber($("#showOrder").val())) {
			Tips.showAlertWin({
				info : "显示次序请填写数字！"
			});
			return;
		}
		$("#addPictureForm").attr("action", Domain.cms_path + '/homepage/stu/stuPictureSave.htm');
		var params = {
			frm : "#addPictureForm",
			fn : function(result) {
				if (result.isSuccess) {
					location.href = Domain.cms_path + '/homepage/stu/studentPicManage.htm?id=' + addStudentPic.stuId;
				} else {
					Tips.showWarnWin({
						info : "操作失败，请重试！"
					});
				}

			},
			dataType : "json"
		};
		Sender.submitAjax(params);
		Tips.loading({
			info : '提交中，请稍候...'
		});
	}
})(jQuery, window, document);