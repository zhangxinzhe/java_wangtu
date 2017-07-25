(function($, window, document, undefined) {
	var pub = {};
	window.addPic = pub;
	pub.init = function(picPlatform) {
		addPic.picPlatform=picPlatform;
		bindEvent();
	}
	// 绑定事件
	function bindEvent() {
		$("input[id^='conType'").on("click", changeLinkTr);
		$("#saveBtn").on("click", doSavePic);
	}

	// 改变linkTd是否显示
	function changeLinkTr() {
		if ($(this).attr('value') == "LINK") {
			$("#linkTr").removeAttr("style");
		} else if ($(this).attr('value') == "NONE") {
			$("#linkTr").css("display", "none");
		}
	}

	// 保存图片
	function doSavePic() {
		// 新增广告时 uploadTempFile不能为空
		if (!Validator.isNotBlank($("#picId").val()) ) {
			if(!Validator.isNotBlank($("input[name='uploadTempFile']").val()) && !Validator.isNotBlank($("input[name='uploadTempPicFileForMobile']").val())){
				Tips.showAlertWin({
					info : "请上传图片！"
				});
				return;
			}
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
		$("#pic_form").attr("action", Domain.cms_path + '/homepage/adv/indexAdvSave.htm?tabStr=pic&picPlatform='+addPic.picPlatform);
		$("#pic_form").submit();
	}
})(jQuery, window, document);