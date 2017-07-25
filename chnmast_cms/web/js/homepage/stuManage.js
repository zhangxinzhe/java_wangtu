(function($, window, document, undefined) {
	var pub = {};
	window.stuManage = pub;
	pub.init = function() {
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		$("#search").on("click", function() {
			$("#searchForm").submit();
		})
	}

	// 显示或取消显示
	stuManage.recOneItem = function(flag, id, stuName) {
		var info = null;
		if (flag == true) {
			info = "确定首页显示这名学员？";
		} else {
			info = "确定取消首页显示这名学员？";
		}
		Tips.showConfirmWin({
			'info' : info,
			'callFn' : function() {
				Tips.loading({
					'info' : '正在操作，请稍候....'
				});
				Sender.ajax({
					'url' : Domain.cms_path + '/homepage/stu/indexStuRecUpdate.htm',
					'data' : {
						'id' : id,
						'isRec' : flag,
						'student.stuName' : stuName
					},
					'dataType' : 'json',
					'fn' : function(result) {
						Tips.loaded();
						FieldMsg.drawMessages(result, function() {
							$("#search").click();
						}, null);
					}
				});
			}
		});
	}

	// 修改排序号
	stuManage.modifyOneItemOrder = function(index) {
		var orderVal = $("#orderTd" + index).html();
		var TextStr = "<input type='text' name='student.orderNo' maxLength='4' id='orderText_" + index + "' value='"
				+ orderVal + "' style='width:35px;height:20px'>";
		$("#orderTd" + index).html(TextStr);
		$("#modifyA" + index).html("保存排序号");
		$("#modifyA" + index).attr("href", "javascript:stuManage.saveOneItemOrder(" + index + ")");
	}
	// 保存排序号
	stuManage.saveOneItemOrder = function(index) {
		var orderVal = $("#orderText_" + index).val();
		var id = $("#modifyA" + index).attr("dataValue");
		var stuName = $("#modifyA" + index).attr("dataName");
		var isVal = stuManage.checkOrder(orderVal);
		if (isVal) {
			Sender.ajax({
				'url' : Domain.cms_path + '/homepage/stu/indexStuOrderUpdate.htm',
				'data' : {
					'id' : id,
					'student.orderNo' : orderVal,
					'student.stuName' : stuName
				},
				'dataType' : 'json',
				'fn' : function(result) {
					Tips.loaded();
					FieldMsg.drawMessages(result, function() {
						$("#search").click();
					}, null);
				}
			});
		}
	}

	// 检验排序号的有效性
	stuManage.checkOrder = function(orderVal) {
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

	// 删除一条数据
	stuManage.deleteOneItem = function(id) {
		Tips.showConfirmWin({
			'info' : "确定要删除这名学员？",
			'callFn' : function() {
				location.href = Domain.cms_path + '/homepage/stu/indexStuDelete.htm?id=' + id;
			}
		});
	}
})(jQuery, window, document);