(function($, window, document){
	var pub = {};
	window.courseReport = pub;
    pub.init = function() {
        bindEvent();
    }
    
    //绑定页面事件方法
    function bindEvent() {
    	$("#searchTime").bind("click", search);
        $("#allCheckbox1").bind("click", allCheckbox);
        $("#allCheckbox2").bind("click", allCheckbox);
        $("#chooseUser").bind("click", chooseUser);
        $("#userLi").bind("click",function(){
    			window.location.href = Domain.cms_path + "/account/stuReport.htm";
    	});
        $("#importButton").bind("click", importStuReport);
        $("#importFile").bind("change", function(){$("#importName").val($("#importFile").val());});
        $("#viewResultButton").bind("click", viewResultStuReport);
    }
    
    //搜索
    function search(){
    	var courseId = $("#courseId").val();
		if(courseId=="0" ){
			Tips.showAlertWin({info:"课程名称为必选条件，请选择！"});
			return;
		}
    	$("#courseReportForm").attr("action", Domain.cms_path + "/account/courseReport.htm");
    	$("#courseReportForm").attr("target", "");
    	$("#courseReportForm").submit();
    }
    
    //全选
    function allCheckbox(){
    	if($(this).attr('checked') == 'checked' || $(this).attr('checked') == true){
    		$("input[name='courseIdsCheckbox']").attr("checked", true); 
    		$("#allCheckbox1").attr("checked", true); 
    		$("#allCheckbox2").attr("checked", true); 
    	}else{
    		$("input[name='courseIdsCheckbox']").attr("checked", false); 
    		$("#allCheckbox1").attr("checked", false); 
    		$("#allCheckbox2").attr("checked", false); 
    	}
    }
    
    //选择学员
    function chooseUser(){
    	var  contentType=$('#contentType').val();
    	var obj = $('input:checkbox[name="courseIdsCheckbox"]:checked');
    	if(obj.length == 0){
    		if (contentType=="CONCERT") {
    			Tips.showWarnWin({"info":"请至少选择一个场次！"});
			}else {
				Tips.showWarnWin({"info":"请至少选择一个课次！"});
			}
    		return;
    	}
    	$("#userSelectDiv_search").click();
    	Box.showDiv({
    		"div" : "#userSelectDiv",
    		"closeObject" : "#userSelectDiv .close"
    	});
    }
    
    //确认选择课程
    pub.submitReport = function(){
    	var  contentType=$('#contentType').val();
    	$("#userSelectDiv_userName").val("");
    	$("#userSelectDiv_realName").val("");
    	var stuId = $("#userSelectDiv_userItemDiv").find('input:radio[name="userSelectDiv_radio"]:checked').val();
        var stuName = $("#userSelectDiv_userItemDiv").find('input:radio[name="userSelectDiv_radio"]:checked').attr("dataValue");
    	$("#chooseUserName").val(stuName);
    	var isTrue = true;
    	var student;
    	Sender.ajax({
    		"url" : Domain.cms_path + "/account/valStudent.htm?id="+stuId,
    		"dataType" : "json",
    		"async" : false,
    		"fn" : function(result){
    			if(result.ERROR_MSG != undefined){
    				Tips.showErrorMsg({"info":result.ERROR_MSG});
    				isTrue = false;
    			}else{
    				student = result;
    			}
    		}
    	});
    	if(!isTrue){
    		return;
    	}
    	var courseIds = new Array();
    	var htmlStr = "";
    	if(student){
    		var name = student.userName;
			htmlStr += "<tr><th style='width:100px'>报名学员：</th><td>"+name+"</td></tr>";
//			htmlStr += "<tr><th>"+temp+"价格：</th><td>"+course.courseNowcost+"元</td></tr>";
			
		var	temp = "";
			var courseSize = $('input:checkbox[name="courseIdsCheckbox"]:checked').length;
			$('input:checkbox[name="courseIdsCheckbox"]:checked').each(function(i){
				temp += $(this).attr("dataName")+"，";
				courseIds.push($(this).val());
			});
			temp = temp.substring(0,temp.length-1);
			if (contentType=="CONCERT") {
				htmlStr += "<tr><th>报名场次数：</th><td>"+courseSize+"</td></tr>";

			}else {
				htmlStr += "<tr><th>报名课次数：</th><td>"+courseSize+"</td></tr>";
			}
    	}
    	$("#reportInfoDiv_table").html("").append(htmlStr);
    	Box.showDiv({
    		"div" : "#reportInfoDiv",
    		"closeObject" : "#reportInfoDiv .close"
    	});
    	
    	// 提交事件
    	$("#reportInfoDiv .submit").unbind().bind("click", function(){
    		var idArrayStr = courseIds.toString();
    		var id = stuId;
    		var courseId=$("#courseId").val();
    		Tips.loading({"info":"正在处理中，请稍等..."});
    		Sender.ajax({
        		"url" : Domain.cms_path + "/account/courseReportSave.htm",
        		"data" : {"idArrayStr":idArrayStr, "id":id,"courseId":courseId},
        		"fn" : function(msg){
        			Tips.loaded();
        			if(msg == "ERROR_COUSE_EMPTY"){
        				Tips.showErrorMsg({"info":"请至少选择一个课次！"});
        			}else if(msg == "ERROR_STU_EMPTY"){
        				Tips.showErrorMsg({"info":"选择学员异常，请刷新页面重试！"});
        			}else if(msg == "ERROR_STU_NULL"){
        				Tips.showErrorMsg({"info":"选择学员已不存在，请刷新页面重试！"});
        			}else if(msg == "ERROR_STUE_NOT_NORMAL"){
        				Tips.showErrorMsg({"info":"选择学员已注销，不能报名该课程！"});
        			}else if(msg == "ERROR_STUE_NOT_PERSONAL"){
        				Tips.showErrorMsg({"info":"选择学员不是个人用户，不能报名该课程！"});
        			}else if(msg == "ERROR_COURSE_NULL"){
        				Tips.showErrorMsg({"info":"选择课次所属课程已不存在，请刷新页面重试！"});
        			}else if(msg == "ERROR_COURSE_NOT_PUBLISHED"){
        				Tips.showErrorMsg({"info":"选择课次所属课程未发布或已下架，您还可以报名其他课次！"});
        			}else if(msg == "ERROR_COURSE_END"){
        				Tips.showErrorMsg({"info":"选择课次所属课程已结束，您还可以报名其他课程！"});
        			}else if(msg == "ERROR_PRICES_EMPTY"){
        				Tips.showErrorMsg({"info":"选择课次还没有价格，暂时无法报名，您还可以报名其他课程！"});
        			}else if(msg == "success"){
        				Tips.showSuccessMsg({
        					"info" : "按学员批量报名成功！",
        					"callFn" : function(){
        						$("#reportInfoDiv .close").click();
        					}
    					});
        			}
        		}
        	});
    	});
    }
	//导入学员报名
    function importStuReport(){
    	var courseId = $("#courseId").val();
    	if(courseId=="0" ){
			Tips.showAlertWin({info:"请先选择课程"});
			return;
		}
    	$("#importForm").attr("action", Domain.cms_path + "/account/importStuReport.htm?courseId="+courseId);
        $("#importForm").attr("target", 'importStuReportFrame');
        $("#importForm").attr("enctype", 'multipart/form-data');
        $("#importForm").submit();
        Tips.loading({"info":"正在处理,请稍后！"});
    }
    //查看导入结果
    function viewResultStuReport(){
    	var params ={
    		"url":"/account/viewResultStuReport.htm",
    		"fn":function(result) {
    			if(result == "-1"){
                    window.location.href = Domain.cms_path + "/noprivacy.htm";
                }
                if(!result || result == "") {
                    Tips.showErrorMsg({"info":"当前没有任务文件!"});
                    return ;
                }
                if(result){
                    var info = result.split('#');
                    var path = info[0];
                    Tips.showWarnWin({"info":'用户导入报名结果文件下载：<p> <a href=\"'+Domain.uploadFile_path + "/" +path +'" title=\"点击下载\" >'+info[1]+'</a></p>'});
                }
	        }
        };
        /**ajax提交数据*/
        Sender.ajax(params);
    }
    pub.showResult = function(msg, isSuccess) {
    	Tips.loaded();
    	Tips.showAlertWin({"info" : msg, "callFn" : function(){}});
    }
})(jQuery, window, document);