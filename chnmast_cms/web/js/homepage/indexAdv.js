(function($, window, document, undefined) {
	var pub = {};
	window.indexAdv = pub;
	pub.init = function(tabStr,picPlatform) {
		indexAdv.tabStr = tabStr; // 当前tab
		indexAdv.picPlatform = picPlatform;//手机端还是PC端
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		$("#picLi").on("click", function() {
			location.href = Domain.cms_path + '/homepage/adv/advManage.htm?tabStr=pic';
		});
		$("#audioLi").on("click", function() {
			location.href = Domain.cms_path + '/homepage/adv/advManage.htm?tabStr=audio';
		});
		$("#videoLi").on("click", function() {
			location.href = Domain.cms_path + '/homepage/adv/advManage.htm?tabStr=video';
		});

		$("#search").on("click", function() {
			$("#searchForm").submit();
		})
	}
	// 预览视频
	indexAdv.toOpenVideo = function(id) {
		var params = {
			url : Domain.cms_path + "/homepage/adv/openVideo.htm",
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
	// 显示或者取消显示
	indexAdv.recOneItem = function(flag, id, title) {
		var info = null;
		if (flag == true) {
			info = "确定首页显示这条数据？";
		} else {
			info = "确定取消首页显示这条数据？";
		}
		Tips.showConfirmWin({
			'info' : info,
			'callFn' : function() {
				Tips.loading({
					'info' : '正在操作，请稍候....'
				});
				Sender.ajax({
					'url' : Domain.cms_path + '/homepage/adv/indexAdvRecUpdate.htm',
					'data' : {
						'tabStr' : indexAdv.tabStr,
						'id' : id,
						'indexAdv.title' : title,
						'isRec' : flag
					},
					'dataType' : 'json',
					'fn' : function(result) {
						Tips.loaded();
						FieldMsg.drawMessages(result, function() {
							location.href = Domain.cms_path + "/homepage/adv/advManage.htm?tabStr=pic&picPlatform="+indexAdv.picPlatform;	
						}, null);
					}
				});
			}
		});
	}

	// 修改排序号
	indexAdv.modifyOneItemOrder = function(index) {
		var orderVal = $("#orderTd" + index).html();
		var TextStr = "<input type='text' name='indexAdv.displayOrder' maxLength='4' id='orderText_" + index
				+ "' value='" + orderVal + "' style='width:35px;height:20px'>";
		$("#orderTd" + index).html(TextStr);
		$("#modifyA" + index).html("保存排序号");
		$("#modifyA" + index).attr("href", "javascript:indexAdv.saveOneItemOrder(" + index + ")");
	}
	// 保存排序号
	indexAdv.saveOneItemOrder = function(index) {
		var orderVal = $("#orderText_" + index).val();
		var id = $("#modifyA" + index).attr("dataValue");
		var title = $("#modifyA" + index).attr("dataTitle");
		var isVal = indexAdv.checkOrder(orderVal);
		if (isVal) {
			Sender.ajax({
				'url' : Domain.cms_path + '/homepage/adv/indexAdvOrderUpdate.htm',
				'data' : {
					'tabStr' : indexAdv.tabStr,
					'id' : id,
					'indexAdv.displayOrder' : orderVal,
					'indexAdv.title' : title
				},
				'dataType' : 'json',
				'fn' : function(result) {
					Tips.loaded();
					FieldMsg.drawMessages(result, function() {
						$("#orderTd" + index).html(orderVal);
						$("#modifyA" + index).html("修改排序号");
						$("#modifyA" + index).attr("href", "javascript:indexAdv.modifyOneItemOrder(" + index + ")");
					}, null);
				}
			});
		}
	}
	// 检验排序号的有效性
	indexAdv.checkOrder = function(orderVal) {
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
	indexAdv.deleteOneItem = function(id) {
		Tips.showConfirmWin({
			'info' : "确定要删除这条数据？",
			'callFn' : function() {
				location.href = Domain.cms_path + '/homepage/adv/indexAdvDelete.htm?tabStr=' + indexAdv.tabStr + '&id='
						+ id+"&picPlatform="+indexAdv.picPlatform;
			}
		});
	}
})(jQuery, window, document);