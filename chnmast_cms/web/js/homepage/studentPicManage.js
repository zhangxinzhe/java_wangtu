(function($, window, document, undefined) {
	var pub = {};
	window.studentPicManage = pub;
	pub.init = function(stuId) {
		window.studentPicManage.stuId = stuId;
		bindEvent();
	}
	// 绑定事件
	function bindEvent() {
		$("#checkAll").on("click", toCheckItem);
	}

	// 全选或取消全选
	function toCheckItem() {
		if ($("#checkAll").attr("checked") == "checked") {
			$("input[name='ids']").attr("checked", true);
		} else {
			$("input[name='ids']").attr("checked", false);
		}
	}
	// 显示或不显示
	studentPicManage.recOneItem = function(flag, id, picName) {
		var info = null;
		if (flag == true) {
			info = "确定显示这条数据？";
		} else {
			info = "确定取消显示这条数据？";
		}
		Tips.showConfirmWin({
			'info' : info,
			'callFn' : function() {
				Tips.loading({
					'info' : '正在操作，请稍候....'
				});
				Sender.ajax({
					'url' : Domain.cms_path + '/homepage/stu/indexStuPicRecUpdate.htm',
					'data' : {
						'id' : id,
						'isRec' : flag,
						'picture.pictureName' : picName
					},
					'dataType' : 'json',
					'fn' : function(result) {
						Tips.loaded();
						FieldMsg.drawMessages(result, function() {
							location.href = Domain.cms_path + '/homepage/stu/studentPicManage.htm?id='
									+ studentPicManage.stuId;
						}, null);
					}
				});
			}
		});
	}

	// 修改排序号
	studentPicManage.modifyOneItemOrder = function(index) {
		var orderVal = $("#orderTd" + index).html();
		var TextStr = "<input type='text' name='picture.showOrder' maxLength='4' id='orderText_" + index + "' value='"
				+ orderVal + "' style='width:35px;height:20px'>";
		$("#orderTd" + index).html(TextStr);
		$("#modifyA" + index).html("保存排序号");
		$("#modifyA" + index).attr("href", "javascript:studentPicManage.saveOneItemOrder(" + index + ")");
	}

	studentPicManage.saveOneItemOrder = function(index) {
		var orderVal = $("#orderText_" + index).val();
		var id = $("#modifyA" + index).attr("dataValue");
		var picName = $("#modifyA" + index).attr("dataName");
		var isVal = studentPicManage.checkOrder(orderVal);
		if (isVal) {
			Sender.ajax({
				'url' : Domain.cms_path + '/homepage/stu/indexStuPicOrderUpdate.htm',
				'data' : {
					'id' : id,
					'picture.showOrder' : orderVal,
					'picture.pictureName' : picName
				},
				'dataType' : 'json',
				'fn' : function(result) {
					Tips.loaded();
					FieldMsg.drawMessages(result, function() {
						location.href = Domain.cms_path + '/homepage/stu/studentPicManage.htm?id='
								+ studentPicManage.stuId;
					}, null);
				}
			});
		}
	}

	// 检验排序号的有效性
	studentPicManage.checkOrder = function(orderVal) {
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

	studentPicManage.deleteItem = function() {
		var checkLength = $("input[name='ids']:checked").length;
		if (checkLength <= 0) {
			Tips.showAlertWin({
				info : "请选择要删除的图片或视频！"
			});
			return false;
		}

		Tips.showConfirmWin({
			'info' : "确定要删除这些图片或视频？",
			'callFn' : function() {
				$("#picForm").attr("action", Domain.cms_path + '/homepage/stu/indexStuPicDelete.htm');
				var params = {
					frm : "#picForm",
					fn : function(result) {
						if (result.isSuccess) {
							location.href = Domain.cms_path + '/homepage/stu/studentPicManage.htm?id='
									+ studentPicManage.stuId;
						} else {
							Tips.showWarnWin({
								info : "操作失败，请重试！"
							});
						}

					},
					dataType : "json"
				};
				Sender.submitAjax(params);
			}
		});
	}

	studentPicManage.openVideo = function(id) {
		var url = Domain.cms_path + "/basic/openVideo.htm";
		var params = {
			url : url,
			data : {
				id : id
			},
			fn : function(content) {
				Tips.showDialog('', content, function() {
				}, true, "600", null, null);
				$(".close").bind("click", function() {
					$(".popUp-layer").remove();
				});
			}
		};
		Sender.ajax(params);
	}
})(jQuery, window, document);