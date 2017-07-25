/** 活动专题-活动图集 **/
(function($, window, document, undefined) {
	var pub = {};
	window.tabPicture = pub;
	pub.init = function() {
		bindEvent();
	}

	function bindEvent(){
		$("#searchButton").bind("click", search);
		
		$("#checkAll").bind("click", allCheckbox);
		
		$("#delButton").bind("click", delPictures);
		
		$(".delPicture").bind("click", delPic);
	}
	
	// 搜索
	function search(){
		$("#pictureForm").attr("action", "/homepage/compete/tabPicture.htm?id="+$("#objectId").val());
		$("#pictureForm").submit();
	}
	
	// 全选
    function allCheckbox(){
    	if($(this).attr('checked') == 'checked' || $(this).attr('checked') == true){
    		$("input[name='pictureIdsCheckbox']").attr("checked", true); 
    	}else{
    		$("input[name='pictureIdsCheckbox']").attr("checked", false); 
    	}
    }
    
    // 批量删除
    function delPictures(){
    	var pictureIds = new Array();
  		$("input[name='pictureIdsCheckbox']").each(function(){
    		if(this.checked) {
    			pictureIds.push(this.value);
    		}
    	});
  		if(pictureIds == '') {
        	Tips.showErrorMsg({"info":"请先选择要删除的图集!"});
        	return ;
        }
  		Tips.showConfirmWin({"info":"确定批量删除所选图集？","callFn": function(){
  			var params ={
        		"url":"/homepage/compete/delPicture.htm",
        		"data":{idStr : pictureIds.toString()},
        		"fn":function(result) {
        			if(result != '') {
        				Tips.showErrorMsg({"info":result});
						return ;
					}else{
						Tips.showSuccessMsg({
							"info":"删除活动图集成功!",
							"callFn" : function(){
								window.location.href = Domain.cms_path + '/homepage/compete/tabPicture.htm?id='+$("#objectId").val();
							}
						});
					}
		        }
            };
            Sender.ajax(params);
  		}});
    }
    
    // 删除
    function delPic(){
    	var id = $(this).attr("dataValue");
    	Tips.showConfirmWin({"info":"确定要删除此活动图集？","callFn": function(){
  			var params ={
        		"url":"/homepage/compete/delPicture.htm",
        		"data":{idStr : id},
        		"fn":function(result) {
        			if(result != '') {
        				Tips.showErrorMsg({"info":result});
						return ;
					}else{
						Tips.showSuccessMsg({
							"info" : "删除活动图集成功!",
							"callFn" : function(){
								window.location.href = Domain.cms_path + '/homepage/compete/tabPicture.htm?id='+$("#objectId").val();
							}
						});
					}
		        }
            };
            Sender.ajax(params);
  		}});
    }
    
    // 显示或者取消显示
    tabPicture.recOneItem = function(flag, id, title) {
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
					'url' : Domain.cms_path + '/homepage/compete/tabPictureRecUpdate.htm',
					'data' : {
						'id' : id,
						'picture.pictureName' : title,
						'isRec' : flag
					},
					'dataType' : 'json',
					'fn' : function(result) {
						Tips.loaded();
						FieldMsg.drawMessages(result, function() {
							window.location.href = Domain.cms_path+"/homepage/compete/tabPicture.htm?id="+$('#objectId').val();
						}, null);
					}
				});
			}
		});
	}
	
	// 修改排序号
    tabPicture.modifyOneItemOrder = function(index) {
		var orderVal = $("#showOrder" + index).html();
		var TextStr = "<input type='text' name='picture.displayOrder' maxLength='4' id='orderText_" + index
				+ "' value='" + orderVal + "' style='width:35px;height:20px'>";
		$("#showOrder" + index).html(TextStr);
		$("#modifyA" + index).html("保存排序号");
		$("#modifyA" + index).attr("href", "javascript:tabPicture.saveOneItemOrder(" + index + ")");
	}
	// 保存排序号
    tabPicture.saveOneItemOrder = function(index) {
		var orderVal = $("#orderText_" + index).val();
		var id = $("#modifyA" + index).attr("dataValue");
		var title = $("#modifyA" + index).attr("dataTitle");
		var isVal = tabPicture.checkOrder(orderVal);
		if (isVal) {
			Sender.ajax({
				'url' : Domain.cms_path + '/homepage/compete/pictureOrderUpdate.htm',
				'data' : {
					'id' : id,
					'picture.showOrder' : orderVal,
					'picture.pictureName' : title
				},
				'dataType' : 'json',
				'fn' : function(result) {
					Tips.loaded();
					FieldMsg.drawMessages(result, function() {
						$("#showOrder" + index).html(orderVal);
						$("#modifyA" + index).html("修改排序号");
						$("#modifyA" + index).attr("href", "javascript:tabPicture.modifyOneItemOrder(" + index + ")");
					}, null);
				}
			});
		}
	}
	
	// 检验排序号的有效性
    tabPicture.checkOrder = function(orderVal) {
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



(function($, window, document, undefined) {
	var pub = {};
	window.editPicture = pub;
	pub.init = function() {
		$("#saveBtn").bind("click", function(){
			if (!Verify.checkAllVerify("#pic_form")) {
				return;
			}
			var obj=$("#pic_form").find('input:radio[name="picture.isShow"]:checked');
	    	if(obj.length == 0){
		        Tips.showErrorMsg({"info":"请选择是否显示！"});
	    		return false;
	    	}
	    	if ($("#pictureId").val()==0 && $("input[name='uploadTempFile']").val() == "") {
	    		Tips.showErrorMsg({info : "请上传图片！"});
				return;
			}
	    	
	    	$("#pic_form").attr("action", Domain.cms_path + '/homepage/compete/savePicture.htm');
			$("#pic_form").submit();
		});
	}
})(jQuery, window, document);