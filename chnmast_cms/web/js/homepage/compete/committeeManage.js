(function($, window, document, undefined) {
	var pub = {};
	window.committeeManage = pub;
	pub.init = function() {
		bindEvent();
	}

	function bindEvent() {
		$("#searchButton").bind("click", function() {
			$("#queryForm").submit();
		});
	}

	committeeManage.toDelCom = function(id, index) {
		Tips.showConfirmWin({
			'info' : "确定要删除这名组委会成员？",
			'callFn' : function() {
				var params = {
					url : Domain.cms_path + "/homepage/compete/deleteCommittee.htm",
					data : {
						"committeeId" : id,
						"competeId" : $("#competeId").val(),
						"committee.realName" : $("#realName" + index).html()
					},
					dataType : "json",
					async : true,
					fn : function(result) {
						FieldMsg.drawMessages(result, function() {
							location.href = Domain.cms_path + "/homepage/compete/tabCommittee.htm?id="
									+ $("#competeId").val();
						});
					},
					fne : function() {
						Tips.showAlertWin({
							info : "操作失败，请重试！"
						});
					}
				};
				Sender.ajax(params);
			}
		});
	}

	// 修改排序号
	committeeManage.modifyOneItemOrder = function(index) {
		var orderVal = $("#orderTd" + index).html();
		var TextStr = "<input type='text' maxLength='3' id='orderText_" + index + "' value='" + orderVal
				+ "' style='width:35px;height:20px'>";
		$("#orderTd" + index).html(TextStr);
		$("#modifyA" + index).html("保存排序号");
		$("#modifyA" + index).attr("href", "javascript:committeeManage.saveOneItemOrder(" + index + ")");
	}
	// 保存排序号
	committeeManage.saveOneItemOrder = function(index) {
		var orderVal = $("#orderText_" + index).val();
		var id = $("#modifyA" + index).attr("dataValue");
		var isVal = committeeManage.checkOrder(orderVal);
		if (isVal) {
			Sender.ajax({
				'url' : Domain.cms_path + '/homepage/compete/updateCommitteeOrderNo.htm',
				'data' : {
					'committeeId' : id,
					'orderNo' : orderVal,
					'competeId' : $("#competeId").val(),
					"committee.realName" : $("#realName" + index).html()
				},
				'dataType' : 'json',
				'fn' : function(result) {
					FieldMsg.drawMessages(result, function() {
						$("#orderTd" + index).html(orderVal);
						$("#modifyA" + index).html("修改排序号");
						$("#modifyA" + index).attr("href",
								"javascript:committeeManage.modifyOneItemOrder(" + index + ")");
					})
				}
			});
		}
	}
	// 检验排序号的有效性
	committeeManage.checkOrder = function(orderVal) {
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