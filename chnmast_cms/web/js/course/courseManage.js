(function($, window, document){
	var pub = {};
	window.courseManage = pub;
    pub.init = function() {
        bindEvent();
    }
    
    function bindEvent() {
    	$("#search").bind("click", searchCourse);
    	$(".course-audit").bind("click", courseAudit);
    	$(".course-edit").bind("click", courseEdit);
    	$(".course-invalid").bind("click", courseInvalid);
    	$(".course-del").bind("click", courseDel);
    	
    	$("#course_courseSource").bind("change", changeSourse);
    }
	
	function searchCourse(){
		$("#coruseSearchForm").attr("action", Domain.cms_path + "/course/courseManage.htm");
		$("#coruseSearchForm").submit();
	}
	
	function courseEdit(){
		var id = $(this).attr("dataValue");
		window.location.href = Domain.cms_path + "/course/addCourse.htm?id="+id;
	}
	function courseAudit(){
		var id = $(this).attr("dataValue");
		window.location.href = Domain.cms_path + "/course/addCourse.htm?id="+id;
	}
	function courseInvalid(){
		var id = $(this).attr("dataValue");
		var flag = $(this).attr("flag");
		var info = "";
		if(flag == 0){
            info = "此课程确定需要发布吗？";
        }else if(flag == 1){
            info = "此课程确定需要下架吗？";
        }else {
            info = "此课程确定需要恢复吗？";
        }
		var params = {
            "info" : info,
            "callFn" : function(){
                var data = {
                    "url" : Domain.cms_path + "/course/courseInvalid.htm",
                    "data" : {"id":id, flag:flag},
                    "dataType" : 'json',
                    "fn" : function(result){
                        FieldMsg.drawMessages(result, function(){
                            $("#search").click();
                        });
                    }
                };
                Sender.ajax(data);
            }
        };
        Tips.showConfirmWin(params);
	}
	
	function courseDel(){
		var id = $(this).attr("dataValue");
		var params = {
            "info" : "您确定删除此课程所有信息吗？",
            "callFn" : function(){
                var data = {
                    "url" : Domain.cms_path + "/course/courseDel.htm",
                    "data" : {"id":id},
                    "dataType" : 'json',
                    "fn" : function(result){
                        FieldMsg.drawMessages(result, function(){
                            $("#search").click();
                        });
                    }
                };
                Sender.ajax(data);
            }
        };
        Tips.showConfirmWin(params);
	}
	
	function changeSourse(){
		var value = $(this).val();
		$("#course_auditStatus option:eq(0)").attr("selected", true);
		if(value=="BACK_ADD"){
			$("#course_auditStatus_tr").hide();
		}else{
			$("#course_auditStatus_tr").show();
		}
	}
	
})(jQuery, window, document);