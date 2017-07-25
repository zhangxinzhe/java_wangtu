(function($, window, document, undefined) {
	var pub = {};
	window.tabAttend_hlj = pub;
	pub.init = function() {
		bindEvent();
	}

	function bindEvent() {
		$('#searchButton').bind('click',search);
		$("#exportAttendHlj").bind("click", exportAttendHlj);
		$('.delAttend').bind('click',deleteAttendHlj);
		$('.addVoteOpt').bind('click', addVoteOpt);
	}
	
	//搜索
	function search(){
		$("#attendHljSearchForm").attr("action", Domain.cms_path + "/homepage/compete/tabAttend.htm");
		$('#attendHljSearchForm').submit();
	}
	
	//导出
	function exportAttendHlj(){
		$("#attendHljSearchForm").attr("action", Domain.cms_path + "/homepage/compete/exportAttendHlj.htm");
		$("#attendHljSearchForm").attr("enctype", "application/x-www-form-urlencoded");
		$("#attendHljSearchForm").submit();
	}
	
	function deleteAttendHlj(){
		var id = $(this).attr('dataValue');
		var param = {
			info:"确定要删除这条信息吗？",
			callFn:function(){
				var url = Domain.cms_path+"/homepage/compete/deleteAttendHlj.htm"
				var params = {
						attendId : id
				};
				$.post(url,params,function(dto){
					if(dto){
						if(dto.isSuccess){
							window.location.href = Domain.cms_path+"/homepage/compete/tabAttend.htm?id="+$('#objectId').val();
						}else{
							Tips.showErrorMsg({info:dto.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
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
			"url" : "/homepage/compete/addVoteNumHljSave.htm",
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
				"url" : "/homepage/compete/addVoteNumHljSave.htm",
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