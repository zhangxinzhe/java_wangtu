(function($, window, document){
	var pub = {};
	window.concertManage = pub;
    pub.init = function() {
        bindEvent();
    }
    
    function bindEvent() {
    	$("#search").bind("click", searchConcert);
    	$(".course-audit").bind("click", courseAudit);
    	$(".course-edit").bind("click", courseEdit);
    	$(".course-invalid").bind("click", courseInvalid);
    	$(".course-del").bind("click", courseDel);
    	$(".course-related").bind("click", courseRelated);
    	
    	$("#course_courseSource").bind("change", changeSourse);
    }
	
	function searchConcert(){
		$("#coruseSearchForm").attr("action", Domain.cms_path + "/concert/concertManage.htm");
		$("#coruseSearchForm").submit();
	}
	
	function courseEdit(){
		var id = $(this).attr("dataValue");
		window.location.href = Domain.cms_path + "/concert/addConcert.htm?id="+id;
	}
	function courseAudit(){
		var id = $(this).attr("dataValue");
		window.location.href = Domain.cms_path + "/concert/addConcert.htm?id="+id;
	}
	function courseInvalid(){
		var id = $(this).attr("dataValue");
		var flag = $(this).attr("flag");
		var info = "";
		if(flag == 0){
            info = "此音乐会确定需要发布吗？";
        }else if(flag == 1){
            info = "此音乐会确定需要下架吗？";
        }else {
        	info = "此音乐会确定需要恢复吗？";
        }
		var params = {
            "info" : info,
            "callFn" : function(){
                var data = {
                    "url" : Domain.cms_path + "/concert/concertInvalid.htm",
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
            "info" : "您确定删除此音乐会所有信息吗？",
            "callFn" : function(){
                var data = {
                    "url" : Domain.cms_path + "/concert/concertDel.htm",
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
	
	function courseRelated(){
		var id = $(this).attr("dataValue");
		var stuId = $(this).attr("stuId");
		Sender.openDiv({
    		"div" : "#relatedStuDiv",
    		"closeObject" : "#relatedStuDiv .close",
    		"url" : Domain.cms_path + "/concert/relatedStu.htm?id="+id+"&stuId="+stuId
    	});
		
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