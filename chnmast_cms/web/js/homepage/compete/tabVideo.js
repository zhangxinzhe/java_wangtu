/** 活动专题-精彩视频 **/
(function($, window, document, undefined) {
	var pub = {};
	window.tabVideo = pub;
	pub.init = function() {
		bindEvent();
	}

	function bindEvent(){
		$("#searchButton").bind("click", search);
		$(".delVideo").bind("click", delVideo);
		$(".updateOrderNo").bind("click", updateOrderNo);
	}
	
	// 搜索
	function search(){
		$("#videoForm").attr("action", Domain.cms_path + "/homepage/compete/tabVideo.htm");
		$("#videoForm").submit();
	}
	
    // 删除
    function delVideo(){
		var videoId = $(this).attr("dataValue");
		var param = {
			info: "确定要删除这条信息吗？",
			callFn:function(){
				var url = Domain.cms_path+"/homepage/compete/delVideo.htm"
				var params = {videoId : videoId};
				$.post(url,params,function(dto){
					if(dto){
						if(dto.isSuccess){
							window.location.href = Domain.cms_path+"/homepage/compete/tabVideo.htm?id="+$('#objectId').val();
						}else{
							Tips.showErrorMsg({info:dto.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
    }
    
	// 修改排序号
    function updateOrderNo(){
    	var obj = $(this).parents("td").siblings("td.orderNoTd");
		var orderVal = obj.html();
		if(StringUtil.trim(orderVal) == ''){
			orderVal = 0;
		}
		obj.html('<input class="input-txt orderNoInput" style="width:35px;height:20px;" type="text" maxlength="4" value="'+orderVal+'">');
		$(this).html("保存");
		$(this).removeClass("updateOrderNo").addClass("c-red").addClass("saveOrderNo");
		$(this).unbind().bind("click", saveOrderNo);
		$('.orderNoInput').unbind();
		$('.orderNoInput').bind("keydown", orderNoEnter);
		$('.orderNoInput').bind("keyup", function(){$(this).val($(this).val().replace(/[^0-9]/g,''))});
    }
    
    // 保存排序号
    function saveOrderNo(){
    	var _this = $(this);
    	var videoId = _this.attr("dataValue");
    	var dataName = _this.attr("dataName");
		var orderNoTdObj = _this.parents("td").siblings("td.orderNoTd");
		var orderNoValue = orderNoTdObj.find(".orderNoInput").val();
		orderNoValue = Number(orderNoValue)
		if(orderNoValue < 0){
			Tips.showErrorMsg({"info":"排序号不能小于0"});
			return;
		}
		Sender.ajax({
			"url" : "/homepage/compete/updateOrderNo.htm",
			"data" : {'videoId':videoId, 'competeVideo.orderNo':orderNoValue, 'competeVideo.name':dataName},
			"dataType" : "json",
			"fn" : function(result){
				if(result.isSuccess){
					orderNoTdObj.html(orderNoValue);
					_this.html("修改排序号");
					_this.removeClass("saveOrderNo").removeClass("c-red").addClass("updateOrderNo");
					_this.unbind().bind('click', updateOrderNo);
				}else{
					Tips.showErrorMsg({info:result.errorMsg});
				}
			}
		});
    }
    
    // 修改排序号回车事件
    function orderNoEnter(event){
    	event = event || window.event;
		if(event.keyCode == 13){
			var obj = $(this).parents("td").siblings("td.optTd").find("a.saveOrderNo");
			obj.click();
		}
    }
    
    // 显示或者取消显示
    tabVideo.updateShow = function(flag, id, name) {
		var info = null;
		if (flag == true) {
			info = "您确定前台页【显示】这条数据吗？";
		} else {
			info = "您确定前台页【不显示】这条数据吗？";
		}
		Tips.showConfirmWin({
			'info' : info,
			'callFn' : function() {
				Tips.loading({'info':'正在操作，请稍候....'});
				Sender.ajax({
					'url' : Domain.cms_path + '/homepage/compete/updateShow.htm',
					'data' : {
						'videoId' : id,
						'competeVideo.name' : name,
						'showFlag' : flag
					},
					'dataType' : 'json',
					'fn' : function(result) {
						Tips.loaded();
						FieldMsg.drawMessages(result, function() {
							window.location.href = Domain.cms_path+"/homepage/compete/tabVideo.htm?id="+$('#objectId').val();
						}, null);
					}
				});
			}
		});
	}
    
})(jQuery, window, document);


/** 活动专题-精彩视频-编辑页 **/
(function($, window, document, undefined) {
	var pub = {};
	window.editVideo = pub;
	pub.init = function() {
		$("#saveBtn").bind("click", saveVideo);
	}
	
	// 保存
	var isTrue = true;//防止重复提交
	function saveVideo(){
		if(!isTrue){
    		return;
    	}
		isTrue = false;
		if (!Verify.checkAllVerify("#video_form")) {
			isTrue = true;
			return;
		}
		if(Validator.isBlank($("#videoId").val()) || $("#videoId").val()==0){
			// 新增时必须上传图片
			if($("#tempPicFile").val() == ""){
				isTrue = true;
				Tips.showAlertWin({
					info : "请上传视频预览图！"
				});
				return;
			}
			// 新增时必须上传视频
			if($("#tempVideoFile").val() == ""){
				isTrue = true;
				Tips.showAlertWin({
					info : "请上传视频文件！"
				});
				return;
			}
		}
		Tips.loading({'info':'正在保存，请稍候....'});
		$("#video_form").attr("action", Domain.cms_path + "/homepage/compete/editVideo.htm");
		Sender.submitAjax({
			"frm" : "#video_form",
			"dataType" : "json",
			"fn" : function(result){
				isTrue = true;
				Tips.loaded();
				if(result.isSuccess){
					Tips.showSuccessMsg({
						"info" : "精彩视频保存成功！",
						"callFn" : function(){
							window.location.href = Domain.cms_path + "/homepage/compete/tabVideo.htm?id="+$("#objectId").val();
						}
					});
				}else{
					Tips.showErrorMsg({"info":result.errorMsg});
				}
			}
		});
	}
	
	// 上传回调
	pub.afterUpload = function(file, index) {
		switch(index){
	        case 1 :
	            //上传后的图片路径
	            var uploadTempFile = document.getElementById("uploadTempFile"+index).value;
	            $("#tempPicFile").val(uploadTempFile);
	            break;
	        case 2 :
	        	//上传后的视频路径
	            var uploadTempFile = document.getElementById("uploadTempFile"+index).value;
	            $("#tempVideoFile").val(uploadTempFile);
	            var fileName = file.name;
	            $("#fileNameSpan").html(fileName);
	            break;
	    }
	}
	
})(jQuery, window, document);