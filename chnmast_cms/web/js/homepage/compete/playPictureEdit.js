(function($, window, document, undefined) {
	var pub = {};
	window.playPictureEdit = pub;
	pub.init = function() {
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		$("#saveBtn").on("click", doSavePic);
	}
	
	// 保存图片
	function doSavePic() {
		if ($("#picId").val()==0 && $("input[name='uploadTempFile']").val()=="") {
			Tips.showAlertWin({
				info : "请上传图片！"
			});
			return;
		}
		if (!Verify.checkAllVerify("#pic_form")) {
			return;
		}
		if ($("#conType1").attr("checked") == "checked") {
			if (!Validator.isNotBlank($("#picLinkUrl").val())) {
				Tips.showAlertWin({
					info : "请填写链接地址！（以http://开头）"
				});
				return;
			} else {
				if (!StringUtil.startWith($("#picLinkUrl").val(), "http://")) {
					Tips.showAlertWin({
						info : "请填写合法的链接地址！（以http://开头）"
					});
					return;
				}
			}
		}
		if (Validator.isNotBlank($("#picOrder").val()) && !Validator.isNumber($("#picOrder").val())) {
			Tips.showAlertWin({
				info : "显示次序请填写数字！"
			});
			return;
		}

		$("#pic_form").attr("action", Domain.cms_path + '/homepage/compete/savePlayPicture.htm');
		$("#pic_form").submit();
	}
	
	
	

})(jQuery, window, document);