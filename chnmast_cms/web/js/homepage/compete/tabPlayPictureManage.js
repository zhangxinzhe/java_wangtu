(function($, window, document, undefined) {
	var pub = {};
	window.tabPlayPictureManage = pub;
	pub.init = function() {
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		//查询按钮
		$("#searchButton").on("click",queryPicture);
		//删除
		$(".indexAdv_delete_a").on("click",deletePicture);
	}
	
	//删除
	function deletePicture(){
		var id = $(this).attr("val");
		var param = {
			info:"确定要删除这条信息吗？",
			callFn:function(){
				var url = Domain.cms_path+"/homepage/compete/deletePlayPicture.htm"
				var params = {
						indexAdvId:id
				};
				$.post(url,params,function(dto){
					if(dto){
						if(dto.isSuccess){
							window.location.href = Domain.cms_path+"/homepage/compete/tabPlayPicture.htm?id="+$('#objectId').val();
						}else{
							Tips.showErrorMsg({info:dto.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
	}
	
	//查询
	function queryPicture(){
		$(".pictureManage").submit();
	}
	
	// 显示或者取消显示
	tabPlayPictureManage.recOneItem = function(flag, id, title) {
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
					'url' : Domain.cms_path + '/homepage/compete/indexAdvRecUpdate.htm',
					'data' : {
						'id' : id,
						'indexAdv.title' : title,
						'isRec' : flag
					},
					'dataType' : 'json',
					'fn' : function(result) {
						Tips.loaded();
						FieldMsg.drawMessages(result, function() {
							window.location.href = Domain.cms_path+"/homepage/compete/tabPlayPicture.htm?id="+$('#objectId').val();
						}, null);
					}
				});
			}
		});
	}
	
	
	// 修改排序号
	tabPlayPictureManage.modifyOneItemOrder = function(index) {
		var orderVal = $("#orderTd" + index).html();
		var TextStr = "<input type='text' name='indexAdv.displayOrder' maxLength='4' id='orderText_" + index
				+ "' value='" + orderVal + "' style='width:35px;height:20px'>";
		$("#orderTd" + index).html(TextStr);
		$("#modifyA" + index).html("保存排序号");
		$("#modifyA" + index).attr("href", "javascript:tabPlayPictureManage.saveOneItemOrder(" + index + ")");
	}
	// 保存排序号
	tabPlayPictureManage.saveOneItemOrder = function(index) {
		var orderVal = $("#orderText_" + index).val();
		var id = $("#modifyA" + index).attr("dataValue");
		var title = $("#modifyA" + index).attr("dataTitle");
		var isVal = tabPlayPictureManage.checkOrder(orderVal);
		if (isVal) {
			Sender.ajax({
				'url' : Domain.cms_path + '/homepage/compete/indexAdvOrderUpdate.htm',
				'data' : {
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
						$("#modifyA" + index).attr("href", "javascript:tabPlayPictureManage.modifyOneItemOrder(" + index + ")");
					}, null);
				}
			});
		}
	}
	
	// 检验排序号的有效性
	tabPlayPictureManage.checkOrder = function(orderVal) {
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