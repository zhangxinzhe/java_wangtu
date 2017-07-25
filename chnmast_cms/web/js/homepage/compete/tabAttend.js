(function($, window, document, undefined) {
	var pub = {};
	window.tabAttendManage = pub;
	pub.init = function() {
		bindEvent();
	}

	function bindEvent() {
		$('#searchButton').bind('click',search);
		$("#exportAttend").bind("click", exportAttend);
		$('a[name="maintainVideoUrl"]').bind('click',maintainVideoUrl);
		$('#videoUrl_maintain').bind('click',saveVideoUrl);
		$('.addVoteOpt').bind('click', addVoteOpt);
	}
	
	//搜索
	function search(){
		$("#attendSearchForm").attr("action", Domain.cms_path + "/homepage/compete/tabAttend.htm");
		$("#attendSearchForm").submit();
	}
	
	//导出
	function exportAttend(){
		$("#attendSearchForm").attr("action", Domain.cms_path + "/homepage/compete/exportAttend.htm");
		$("#attendSearchForm").attr("enctype", "application/x-www-form-urlencoded");
		$("#attendSearchForm").submit();
	}
	
	//维护视频地址弹框
	function maintainVideoUrl(){
		var id=$(this).attr('dataValue');
		var url=$(this).attr('videoUrl');
		$("#competeAttendId").val(id);
		$("#videoUrl").val(url);
		$("#videoUrlDiv_msg").html("");
		Box.showWin({"target":"#videoUrlDiv","modal":true,"isCenter":true});
	}
	//保存视频地址
	function saveVideoUrl(){
		$("#videoUrlDiv_msg").html("");
		var id=$("#competeAttendId").val();
		var url=$("#videoUrl").val();
		if(url != "" && !Validator.isValidUrl(url)){
			$("#videoUrlDiv_msg").html("请输入有效的链接地址！以http://开头")
			return;
		}
		var params ={
    		"url":"/homepage/compete/maintainVideoUrl.htm",
    		"data":{'videoUrl':url,'competeAttendId':id},
    		"fn":function(result) {
    			if(result != '') {
    				$("#videoUrlDiv_msg").html(result)
					return;
				}else{
					Box.hideWin({"target":"#videoUrlDiv"});
					Tips.showSuccessMsg({"info":"维护视频地址成功!", "callFn":function(){
						search();
					}});
				}
	        }
        };
        /**ajax提交数据*/
        Sender.ajax(params);
	}

	//加票操作
	function addVoteOpt(){
		var obj = $(this).parents("td").siblings("td.addVoteNumTd");
		var numValue = obj.html();
		if(StringUtil.trim(numValue) == ''){
			numValue = 0;
		}
		obj.html('<input class="input-txt addVoteInput" style="width:35px;height:20px;" type="text" maxlength="6" value="'+numValue+'">');
		$(this).html("保存");
		$(this).removeClass("addVoteOpt").addClass("c-red").addClass("saveVoteOpt");
		$(this).unbind().bind("click", saveVoteOpt);
		$('.addVoteInput').unbind();
		$('.addVoteInput').bind("keydown", addVoteEnter);
		$('.addVoteInput').bind("keyup", function(){$(this).val($(this).val().replace(/[^0-9]/g,''))});
	}
	//保存加票操作
	function saveVoteOpt(){
		var _this = $(this);
		var addTdObj = _this.parents("td").siblings("td.addVoteNumTd");
		var totalTdObj = _this.parents("td").siblings("td.voteNumTd");
		var id = _this.attr("dataValue");
		var numValue = addTdObj.find(".addVoteInput").val();
		numValue = Number(numValue)
		if(numValue < 0){
			Tips.showErrorMsg({"info":"加票数不能小于0！"});
			return;
		}
		Sender.ajax({
			"url" : "/homepage/compete/addVoteNumSave.htm",
			"data" : {id:id,addVoteNum:numValue},
			"fn" : function(result){
				if(result == "ID_NULL"){
					Tips.showErrorMsg({"info":"数据异常，请刷新页面重试！"});
				}else if(result == "ATTEND_NULL"){
					Tips.showErrorMsg({"info":"报名信息已经不存在，请刷新页面！"});
				}else if(result == "ERROR"){
					Tips.showErrorMsg({"info":"加票失败，请刷新页面重试！"});
				}else{
					if(result.indexOf("SUCCESS_") == 0){
						var totalNum=result.substring(8);
						addTdObj.html(numValue);
						totalTdObj.html(totalNum);
						_this.html("加票");
						_this.removeClass("saveVoteOpt").removeClass("c-red").addClass("addVoteOpt");
						_this.unbind().bind('click', addVoteOpt);
					}
				}
			}
		});
	}
	//回车事件
	function addVoteEnter(event){
		event = event || window.event;
		if(event.keyCode == 13){
			var _this = $(this);
			var optObj = _this.parents("td").siblings("td.optTd").find(".saveVoteOpt");
			var totalTdObj = _this.parents("td").siblings("td.voteNumTd");
			var id = optObj.attr("dataValue");
			var numValue = _this.val();
			numValue = Number(numValue)
			if(numValue < 0){
				Tips.showErrorMsg({"info":"加票数不能小于0！"});
				return;
			}
			Sender.ajax({
				"url" : "/homepage/compete/addVoteNumSave.htm",
				"data" : {id:id,addVoteNum:numValue},
				"fn" : function(result){
					if(result == "ID_NULL"){
						Tips.showErrorMsg({"info":"数据异常，请刷新页面重试！"});
					}else if(result == "ATTEND_NULL"){
						Tips.showErrorMsg({"info":"报名信息已经不存在，请刷新页面！"});
					}else if(result == "ERROR"){
						Tips.showErrorMsg({"info":"加票失败，请刷新页面重试！"});
					}else{
						if(result.indexOf("SUCCESS_") == 0){
							var totalNum=result.substring(8);
							totalTdObj.html(totalNum);
							optObj.html("加票");
							optObj.removeClass("saveVoteOpt").removeClass("c-red").addClass("addVoteOpt");
							optObj.unbind().bind('click', addVoteOpt);
							_this.parents("td").html(numValue);
						}
					}
				}
			});
		}
	}
	
})(jQuery, window, document);