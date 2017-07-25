(function($, window, document){
	var pub = {};
	window.vodManage = pub;
    pub.init = function() {
        bindEvent();
    }
    
    function bindEvent() {
    	$("#search").bind("click", searchVod);
    	
    	$(".course-edit").bind("click", vodEdit);
    	$(".course-invalid").bind("click", vodInvalid);
    	$(".course-del").bind("click", vodDel);
    }
	
	function searchVod(){
		$("#vodSearchForm").attr("action", Domain.cms_path + "/vod/vodManage.htm");
		$("#vodSearchForm").submit();
	}
	
	function vodEdit(){
		var id = $(this).attr("dataValue");
		window.location.href = Domain.cms_path + "/vod/addVod.htm?id="+id;
	}
	
	function vodInvalid(){
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
                    "url" : Domain.cms_path + "/vod/vodInvalid.htm",
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
	
	function vodDel(){
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
	
})(jQuery, window, document);