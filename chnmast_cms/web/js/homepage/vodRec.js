(function($, window, document) {
	var pub = {};
	window.vodRec = pub;
	pub.init = function() {
		bindEvent();
	}

	function bindEvent() {
		$('.public-tab li').bind('click', function() {
			var operate = $(this).attr('operate');
			window.location.href = Domain.cms_path + '/homepage/vod/vodRec.htm?operate=' + operate;
		});

		$(".vodRec").bind("click", function() {
			var isVodRec = $(this).attr("isReced_flag");
			var courseName = $(this).attr("dataName");
			var id = $(this).attr("dataValue");
			var info = "";
			if (isVodRec == "true") {// 推荐
				info = "确定推荐此课程！";
			} else {// 取消推荐
				info = "确定取消推荐此课程！";
			}
			Tips.showConfirmWin({
				'info' : info,
				'callFn' : function() {
					Tips.loading({
						'info' : '正在操作，请稍候....'
					});
					Sender.ajax({
						'url' : Domain.cms_path + '/homepage/vod/vodRecUpdate.htm',
						'data' : {
							'id' : id,
							'isVodRec' : isVodRec,
							'courseName' : courseName
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
				$("#vodForm").attr("action", Domain.cms_path + "/homepage/vod/vodRec.htm");
				$("#vodForm").submit();
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
						"info" : "请选择需要操作的课程！"
					});
				} else {
					$("#tableForm").attr("action", Domain.cms_path + "/homepage/vod/vodRecBatchUpdate.htm")
					Tips.showConfirmWin({
						'info' : "确定批量取消推荐课程！",
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
	vodRec.modifyOneItemOrder = function(index) {
		var orderVal = $("#orderTd" + index).html();
		var TextStr = "<input type='text' maxLength='4' id='orderText_" + index + "' value='" + orderVal
				+ "' style='width:35px;height:20px'>";
		$("#orderTd" + index).html(TextStr);
		$("#modifyA" + index).html("保存排序号");
		$("#modifyA" + index).attr("href", "javascript:vodRec.saveOneItemOrder(" + index + ")");
	}
	// 保存排序号
	vodRec.saveOneItemOrder = function(index) {
		var orderVal = $("#orderText_" + index).val();
		var id = $("#modifyA" + index).attr("dataValue");
		var courseName = $("#modifyA" + index).attr("dataCourseName");
		var isVal = vodRec.checkOrder(orderVal);
		if (isVal) {
			Sender.ajax({
				'url' : Domain.cms_path + '/homepage/vod/vodRecSeqUpdate.htm',
				'data' : {
					'id' : id,
					'vod.recommendSeq' : orderVal,
					'vod.courseName' : courseName
				},
				'dataType' : 'json',
				'fn' : function(result) {
					Tips.loaded();
					FieldMsg.drawMessages(result, function() {
						$("li[operate='reced']").click();
					}, null);
				}
			});
		}
	}
	// 检验排序号的有效性
	vodRec.checkOrder = function(orderVal) {
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