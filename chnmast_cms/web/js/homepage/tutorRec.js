(function($, window, document) {
	var pub = {};
	window.tutorRec = pub;
	pub.init = function() {
		bindEvent();
	}

	function bindEvent() {
		$('.public-tab li').bind('click', function() {
			var isRec = $(this).attr('isRec');
			window.location.href = Domain.cms_path + '/homepage/tutor/tutorRec.htm?isTutorRec=' + isRec;
		});

		$(".tutorRec").bind("click", function() {
			var isTutorRec = $(this).attr("isReced_flag");
			var id = $(this).attr("dataValue");
			var info = "";
			if (isTutorRec == "true") {// 推荐
				info = "确定推荐此音乐导师吗！";
			} else {// 取消推荐
				info = "确定取消推荐此音乐导师！";
			}
			Tips.showConfirmWin({
				'info' : info,
				'callFn' : function() {
					Tips.loading({
						'info' : '正在操作，请稍候....'
					});
					Sender.ajax({
						'url' : Domain.cms_path + '/homepage/tutor/tutorRecUpdate.htm',
						'data' : {
							'id' : id,
							'isTutorRec' : isTutorRec
						},
						'dataType' : 'json',
						'fn' : function(retult) {
							Tips.loaded();
							FieldMsg.drawMessages(retult, function() {
								$('.public-tab').find("li.current").click();
							}, null);
						}
					});
				}
			});
		});

		if ($('#searchButton')) {
			$("#searchButton").bind("click", function() {
				$("#tutorForm").attr("action", Domain.cms_path + "/homepage/tutor/tutorRec.htm");
				$("#tutorForm").submit();
			});
		}

		if ($('#checkAll')) {
			$('#checkAll').bind('click', function() {
				var ch = document.getElementById('checkAll').checked;
				$(".public-table :checkbox").attr("checked", ch);
			});
		}

		if ($("#cancelRec")) {
			$("#cancelRec").bind("click", function() {
				var obj = $(".public-table").find('input:checkbox[name="ids"]:checked');
				if (obj.length == 0) {
					Tips.showAlertWin({
						"info" : "请选择需要操作的音乐导师！"
					});
				} else {
					$("#tableForm").attr("action", Domain.cms_path + "/homepage/tutor/tutorRecBatchUpdate.htm")
					Tips.showConfirmWin({
						'info' : "确定批量取消推荐音乐导师！",
						'callFn' : function() {
							Tips.loading({
								'info' : '正在操作，请稍候....'
							});
							Sender.submitAjax({
								"frm" : "#tableForm",
								"dataType" : "json",
								"fn" : function(result) {
									Tips.loaded();
									FieldMsg.drawMessages(result, function() {
										$('.public-tab').find("li.current").click();
									}, null);
								}
							});
						}
					});
				}
			});
		}
	}

	// 修改排序号
	tutorRec.modifyOneItemOrder = function(index) {
		var orderVal = $("#orderTd" + index).html();
		var TextStr = "<input type='text' maxLength='4' id='orderText_" + index + "' value='" + orderVal
				+ "' style='width:35px;height:20px'>";
		$("#orderTd" + index).html(TextStr);
		$("#modifyA" + index).html("保存排序号");
		$("#modifyA" + index).attr("href", "javascript:tutorRec.saveOneItemOrder(" + index + ")");
	}
	// 保存排序号
	tutorRec.saveOneItemOrder = function(index) {
		var orderVal = $("#orderText_" + index).val();
		var id = $("#modifyA" + index).attr("dataValue");
		var realName = $("#modifyA" + index).attr("dataRealName");
		var isVal = tutorRec.checkOrder(orderVal);
		if (isVal) {
			Sender.ajax({
				'url' : Domain.cms_path + '/homepage/tutor/tutorRecSeqUpdate.htm',
				'data' : {
					'id' : id,
					'tutor.recommendSeq' : orderVal,
					'tutor.realName' : realName
				},
				'dataType' : 'json',
				'fn' : function(result) {
					Tips.loaded();
					FieldMsg.drawMessages(result, function() {
						$("li[isRec='true']").click();
					}, null);
				}
			});
		}
	}
	// 检验排序号的有效性
	tutorRec.checkOrder = function(orderVal) {
		if (Validator.isEmpty(orderVal)) {
			Tips.showAlertWin({
				info : "排序号不能为空！"
			});
			return false;
		}
		if (!Validator.isPlusInt(orderVal)) {
			Tips.showAlertWin({
				info : "排序号请填入大于0的整数！"
			});
			return false;
		}
		return true;
	}
})(jQuery, window, document)