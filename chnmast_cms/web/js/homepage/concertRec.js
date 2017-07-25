(function($, window, document, undefined) {
	var pub = {};
	window.concertRec = pub;
	pub.init = function() {
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		$("#recedLi").on("click", toLoadRecedList);
		$("#recLi").on("click", toLoadRecList);
		$("#searchButton").on("click", toQueryRecList);
		$('#checkAll').bind('click', function() {
			var ch = document.getElementById('checkAll').checked;
			$(".public-table :checkbox").attr("checked", ch);
		});
	}

	// 加载已推荐列表
	function toLoadRecedList() {
		window.location.href = Domain.cms_path + '/homepage/concert/concertRec.htm?isReced=' + true;
	}

	// 加载可推荐列表
	function toLoadRecList() {
		window.location.href = Domain.cms_path + '/homepage/concert/concertRec.htm?isReced=' + false;
	}

	// 查询可推荐列表
	function toQueryRecList() {
		$("#concertForm").attr("action", Domain.cms_path + '/homepage/concert/concertRec.htm?isReced=' + false);
		$("#concertForm").submit();
	}

	// 单个推荐
	pub.recOneItem = function(id, concertName) {
		Tips.showConfirmWin({
			'info' : "确定推荐此音乐会？",
			'callFn' : function() {
				Tips.loading({
					'info' : '正在操作，请稍候....'
				});
				Sender.ajax({
					'url' : Domain.cms_path + '/homepage/concert/concertRecUpdate.htm',
					'data' : {
						'id' : id,
						'concertName' : concertName,
						'isReced' : false
					},
					'dataType' : 'json',
					'fn' : function(result) {
						Tips.loaded();
						FieldMsg.drawMessages(result, function() {
							toLoadRecList();
						}, null);
					}
				});
			}
		});
	}
	// 单个取消推荐
	pub.cancleRecOneItem = function(id, concertName) {
		Tips.showConfirmWin({
			'info' : "确定取消推荐此音乐会？",
			'callFn' : function() {
				Tips.loading({
					'info' : '正在操作，请稍候....'
				});
				Sender.ajax({
					'url' : Domain.cms_path + '/homepage/concert/concertRecUpdate.htm',
					'data' : {
						'id' : id,
						'concertName' : concertName,
						'isReced' : true
					},
					'dataType' : 'json',
					'fn' : function(result) {
						Tips.loaded();
						FieldMsg.drawMessages(result, function() {
							toLoadRecedList();
						}, null);
					}
				});
			}
		});
	}
	// 批量取消推荐
	pub.cancleRecBatch = function() {
		var checkedLength = $("input[id^='id']:checked").length;
		if (checkedLength == 0) {
			Tips.showAlertWin({
				"info" : "请选择需要操作的音乐会！"
			});
		} else {
			$("#batchForm").attr("action", Domain.cms_path + '/homepage/concert/concertRecBatchUpdate.htm')
			Tips.showConfirmWin({
				'info' : "确定批量取消推荐音乐会?",
				'callFn' : function() {
					Tips.loading({
						'info' : '正在操作，请稍候....'
					});
					$("#batchForm").append($("input[id^='id']:checked"));
					Sender.submitAjax({
						"frm" : "#batchForm",
						"dataType" : "json",
						"fn" : function(result) {
							Tips.loaded();
							FieldMsg.drawMessages(result, function() {
								toLoadRecedList();
							}, null);
						}
					});
				}
			});
		}

	}

	// 修改排序号
	concertRec.modifyOneItemOrder = function(index) {
		var orderVal = $("#orderTd" + index).html();
		var TextStr = "<input type='text' maxLength='4' id='orderText_" + index + "' value='" + orderVal
				+ "' style='width:35px;height:20px'>";
		$("#orderTd" + index).html(TextStr);
		$("#modifyA" + index).html("保存排序号");
		$("#modifyA" + index).attr("href", "javascript:concertRec.saveOneItemOrder(" + index + ")");
	}
	// 保存排序号
	concertRec.saveOneItemOrder = function(index) {
		var orderVal = $("#orderText_" + index).val();
		var id = $("#modifyA" + index).attr("dataValue");
		var courseName = $("#modifyA" + index).attr("dataCourseName");
		var isVal = concertRec.checkOrder(orderVal);
		if (isVal) {
			Sender.ajax({
				'url' : Domain.cms_path + '/homepage/concert/concertRecSeqUpdate.htm',
				'data' : {
					'id' : id,
					'concert.recommendSeq' : orderVal,
					'concert.courseName' : courseName
				},
				'dataType' : 'json',
				'fn' : function(result) {
					Tips.loaded();
					FieldMsg.drawMessages(result, function() {
						$("#recedLi").click();
					}, null);
				}
			});
		}
	}
	// 检验排序号的有效性
	concertRec.checkOrder = function(orderVal) {
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
})(jQuery, window, document);
