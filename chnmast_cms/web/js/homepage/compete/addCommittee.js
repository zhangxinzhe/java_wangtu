(function($, window, document, undefined) {
	var pub = {};
	window.addCommittee = pub;
	var _isAdd = true;
	var _competeType;
	pub.init = function(flag,competeType) {
		_isAdd = flag;
		_competeType = competeType;
		bindEvent();
	}

	function bindEvent() {
		$("#saveBtn").click(toSaveCommittee);
		$("#addJobTitle").click(addJobTitle);
	}

	function toSaveCommittee() {
		if(_competeType != 'SZCX'){//神州唱响不维护头像
			if (_isAdd && !Validator.isNotBlank($("input[name='uploadTempFile']").val())) {
				Tips.showAlertWin({
					info : "请上传头像文件！"
				});
				return false;
			}
		}

		if (!Verify.checkAllVerify("#committeeForm")) {
			return false;
		}
		$("#committeeForm").submit();
		Tips.loading({
			info : '提交中，请稍候...'
		});
	}
	
	function addJobTitle() {
		var tdObj = $(this).parents("td");
		tdObj.append('<br/><input name="jobTitleArray" value="" maxLength="30" type="text" class="input-txt mt-5"  placeholder="请输入个人职务"/>');
		var jobNum = tdObj.find("input[name='jobTitleArray']").length;
		if(jobNum>=3){
			$(this).hide();
		}
	}
})(jQuery, window, document);