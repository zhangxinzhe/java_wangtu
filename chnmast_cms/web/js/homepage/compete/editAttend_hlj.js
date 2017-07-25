(function($, window, document, undefined) {
	var pub = {};
	window.editAttend_hlj = pub;
	pub.init = function() {
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		$('#saveBtn').bind('click',saveAttendHlj);
	}
	
	//保存
	function saveAttendHlj(){
		if(!Verify.checkAllVerify("#editAttendHljForm")){
			return ;
		}
		// js验证完毕,提交后台
		var serializedata = $('#editAttendHljForm').serialize();
		var params = {
			"url" : "/homepage/compete/saveAttendHlj.htm",
			"data" : serializedata,
			"dataType" : "json",
			"fn" : function(result) {
				if (result.flag) {
					// 提示成功
					Tips.showSuccessMsg({
						"info" : "保存成功",
						"callFn" : function() {
							window.location.href = Domain.cms_path+"/homepage/compete/tabAttend.htm?id="+$('input[name="id"]').val();
						}
					});
				} else {
					// 提示出错
					Tips.showErrorMsg({
						"info" : result.errorMsg,
						"callFn" : function() {
						}
					});
					return;
				}
			}
		};
		Sender.ajax(params);
	}

})(jQuery, window, document);