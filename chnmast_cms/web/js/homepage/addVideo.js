(function($, window, document, undefined) {
	var pub = {};
	window.addVideo = pub;
	pub.init = function() {
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		$("#saveBtn").on("click", doSaveVideo);
	}
	// 保存视频
	function doSaveVideo() {
		// 新增视频时时 uploadTempPicFile不能为空
		if (!Validator.isNotBlank($("#videoId").val())
				&& !Validator.isNotBlank($("input[name='uploadTempPicFile']").val())) {
			Tips.showAlertWin({
				info : "请上传视频展示图片！"
			});
			return;
		}
		// 新增视频时时 uploadTempLocalFile不能为空
		if (!Validator.isNotBlank($("#videoId").val())
				&& !Validator.isNotBlank($("input[name='uploadTempLocalFile']").val())) {
			Tips.showAlertWin({
				info : "请上传视频！"
			});
			return;
		}
		if (!Verify.checkAllVerify("#video_form")) {
			return;
		}
		if (Validator.isNotBlank($("#videoOrder").val()) && !Validator.isNumber($("#videoOrder").val())) {
			Tips.showAlertWin({
				info : "显示次序请填写数字！"
			});
			return;
		}
		$("#video_form").attr("action", Domain.cms_path + '/homepage/adv/indexAdvSave.htm?tabStr=video');
		$("#video_form").submit();
		Tips.loading({
			info : '提交中，请稍候...'
		});
	}
})(jQuery, window, document);