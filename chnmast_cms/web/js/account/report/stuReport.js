(function($, window, document){
	var pub = {};
	window.stuReport = pub;
    pub.init = function() {
        bindEvent();
    }
    
    //绑定页面事件方法
    function bindEvent() {
    	$("#searchButton").bind("click", search);
        $("#allCheckbox1").bind("click", allCheckbox);
        $("#allCheckbox2").bind("click", allCheckbox);
        $("#chooseCourse").bind("click", chooseCourse);
        $(".selectType").bind("click",selectType);
        $("#courseLi").bind("click",function(){
			window.location.href = Domain.cms_path + "/account/courseReport.htm";
        });
    }
    
    //搜索
    function search(){
    	$("#stuReportForm").attr("action", Domain.cms_path + "/account/stuReport.htm");
    	$("#stuReportForm").attr("target", "");
    	$("#stuReportForm").submit();
    }
    
    //全选
    function allCheckbox(){
    	if($(this).attr('checked') == 'checked' || $(this).attr('checked') == true){
    		$("input[name='userIdsCheckbox']").attr("checked", true); 
    		$("#allCheckbox1").attr("checked", true); 
    		$("#allCheckbox2").attr("checked", true); 
    	}else{
    		$("input[name='userIdsCheckbox']").attr("checked", false); 
    		$("#allCheckbox1").attr("checked", false); 
    		$("#allCheckbox2").attr("checked", false); 
    	}
    }
    
    //选择课程
    function chooseCourse(){
    	var obj = $('input:checkbox[name="userIdsCheckbox"]:checked');
    	if(obj.length == 0){
    		Tips.showWarnWin({"info":"请至少选择一个学员用户！"});
    		return;
    	}
    	$("#courseSelectDiv_search").click();
    	Box.showDiv({
    		"div" : "#courseSelectDiv",
    		"closeObject" : "#courseSelectDiv .close"
    	});
    }
    
    //确认选择课程
    var isSub = true;
    pub.submitReport = function(){
    	$("#courseSelectDiv_courseName").val("");
    	$("#courseSelectDiv_contentType").find("option[value='COURSE']").attr("selected",true);
    	var courseId = $("#courseSelectDiv_courseItemDiv").find('input:radio[name="courseSelectDiv_radio"]:checked').val();
        var courseName = $("#courseSelectDiv_courseItemDiv").find('input:radio[name="courseSelectDiv_radio"]:checked').attr("dataValue");
    	$("#chooseCourseName").val(courseName);
    	
    	var isTrue = true;
    	var course;
    	Sender.ajax({
    		"url" : Domain.cms_path + "/account/valCourse.htm?id="+courseId,
    		"dataType" : "json",
    		"async" : false,
    		"fn" : function(result){
    			if(result.ERROR_MSG != undefined){
    				Tips.showErrorMsg({"info":result.ERROR_MSG});
    				isTrue = false;
    			}else{
    				course = result;
    			}
    		}
    	});
    	if(!isTrue){
    		return;
    	}
    	var userIds = new Array();
    	var htmlStr = "";
    	if(course){
    		var temp = "";
    		if(course.contentType == "COURSE"){
    			temp = "课程";
			}else if(course.contentType == "CONCERT"){
    			temp = "音乐会";
    		}else if(course.contentType == "VOD"){
    			temp = "点播视频";
    		}
			htmlStr += "<tr><th style='width:100px;'>报名"+temp+"：</th><td>"+course.courseName+"</td></tr>";
			htmlStr += "<tr><th>"+temp+"价格：</th><td>"+course.courseNowcost+"元</td></tr>";
			
			temp = "";
			var stusize = $('input:checkbox[name="userIdsCheckbox"]:checked').length;
			$('input:checkbox[name="userIdsCheckbox"]:checked').each(function(i){
				temp += $(this).attr("dataName")+"，";
				userIds.push($(this).val());
			});
			temp = temp.substring(0,temp.length-1);
			htmlStr += "<tr valign='top'><th>报名人数：</th><td>["+stusize+"人]"+temp+"</td></tr>";
    	}
    	$("#reportInfoDiv_table").html("").append(htmlStr);
    	$("#reportInfoDiv .submit").unbind("click");
    	Box.showDiv({
    		"div" : "#reportInfoDiv",
    		"closeObject" : "#reportInfoDiv .close"
    	});
    	
    	// 提交事件
    	$("#reportInfoDiv .submit").bind("click", function(){
    		if(!isSub){
        		alert("请不要重复提交！");
        		return;
        	}
        	isSub = false;
    		var idArrayStr = userIds.toString();
    		var id = courseId;
    		Tips.loading({"info":"学员批量报名，正在处理中，请稍等..."});
    		Sender.ajax({
        		"url" : Domain.cms_path + "/account/stuReportSave.htm",
        		"data" : {"idArrayStr":idArrayStr, "id":id},
        		"fn" : function(msg){
        			Tips.loaded();
        			if(msg == "ERROR_STU_EMPTY"){
        				Tips.showErrorMsg({"info":"请至少选择一个学员用户！"});
        			}else if(msg == "ERROR_COURSE_EMPTY"){
        				Tips.showErrorMsg({"info":"选择课程异常，请刷新页面重试！"});
        			}else if(msg == "ERROR_COURSE_NULL"){
        				Tips.showErrorMsg({"info":"选择课程已不存在，请刷新页面重试！"});
        			}else if(msg == "ERROR_COURSE_NOT_PUBLISHED"){
        				Tips.showErrorMsg({"info":"此课程未发布或已下架，您还可以报名其他课程！"});
        			}else if(msg == "ERROR_COURSE_END"){
        				Tips.showErrorMsg({"info":"此课程已结束，您还可以报名其他课程！"});
        			}else if(msg == "ERROR_TIMES_EMPTY"){
        				Tips.showErrorMsg({"info":"此课程还没有课次，暂时无法报名，您还可以报名其他课程！"});
        			}else if(msg == "ERROR_PRICES_EMPTY"){
        				Tips.showErrorMsg({"info":"此课程还没有课次价格，暂时无法报名，您还可以报名其他课程！"});
        			}else if(msg == "success"){
        				Tips.showSuccessMsg({
        					"info" : "按学员批量报名成功！",
        					"callFn" : function(){
        						$("#reportInfoDiv .close").click();
        					}
    					});
        			}
        			isSub = true;
        		}
        	});
    	});
    }
	
    
  //选择分类
    function selectType(){
    	//清空错误提示
		$("#selectTypeDiv_errorMsg").html("");
		$("#selectTypeDiv_title").removeClass("input-txt-error");
		//加载数据
		loadGroupTypes();
		Box.showWin({target:"#selectTypeDiv"});
		
		$("#selectTypeDiv_submit").unbind().bind("click", selectGroupType);
		$("#selectTypeDiv_add").unbind().bind("click", addGroupType);
		$("#selectTypeDiv_del").unbind().bind("click", delGroupType);
    }
    //加载分组类型数据
    function loadGroupTypes(){
		var param = {
			"div":"#selectTypeDiv_container",
			"url":Domain.cms_path+"/basic/loadGroupTypes.htm"
		}
		Sender.load(param);
	}
    //新增分组类型
    function addGroupType(){
    	$("#selectTypeDiv_errorMsg").html("");
    	$("#selectTypeDiv_title").removeClass("input-txt-error");
    	var title = $("#selectTypeDiv_title").val();
	    if(StringUtil.trim(title)==""){
	    	$("#selectTypeDiv_title").addClass("input-txt-error");
	    	$("#selectTypeDiv_errorMsg").html("类型名称不能为空！");
	    	return;
	    }
	    Sender.ajax({
	    	"url" : Domain.cms_path+"/basic/addGroupType.htm",
	    	"data" : {"idStr" : title},
	    	"dataType" : "json",
	    	"fn" : function(result){
	    		if(result.isSuccess){
	    			loadGroupTypes();
	    		}else{
	    			$("#selectTypeDiv_title").addClass("input-txt-error");
	    			$("#selectTypeDiv_errorMsg").html(result.errorMsg);
	    		}
	    	}
	    });
    }
    //删除分组类型
    function delGroupType(){
    	$("#selectTypeDiv_errorMsg").html("");
    	$("#selectTypeDiv_title").removeClass("input-txt-error");
    	var id = $("#selectTypeDiv .type_radio:checked").val();
		if(!id){
			$("#selectTypeDiv_errorMsg").html("请选择需要删除的分类！");
			return;
		}
		Sender.ajax({
	    	"url" : Domain.cms_path+"/basic/delGroupType.htm",
	    	"data" : {"id" : id},
	    	"dataType" : "json",
	    	"fn" : function(result){
	    		if(result.isSuccess){
	    			loadGroupTypes();
	    		}else{
	    			$("#selectTypeDiv_errorMsg").html(result.errorMsg);
	    		}
	    	}
	    });
    }
    //选择分组类型
    function selectGroupType(){
    	var id = $("#selectTypeDiv .type_radio:checked").val();
    	var title = $("#selectTypeDiv").find(".type_radio:checked").attr("dataValue");
    	if(!id || id==undefined){
    		id="";
    		title="";
		}
    	$("#groupTypeId").val(id);
    	$("#groupTypeTitle").val(title);
    	$("#groupTypeIdStr").val(id);
    	$("#selectTypeDiv .close").click();
    }
})(jQuery, window, document);