(function($, window, document){
	var pub = {};
	window.commentAuditManage = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
        $("#searchBtn").bind("click", search);
        $("#course_tab").bind("click", courseList);
        $("#video_tab").bind("click", videoList);
    }

    function courseList(){
    	var type = 1;
    	//提交查询
    	findForType(type);
    }
    
    function videoList(){
    	var type = 2;
    	//提交查询
    	findForType(type);
    }
    
    function findForType(type){
    	if(type == 1){
    		window.location.href=Domain.cms_path + "/audit/comment/commentAuditManage.htm";
    	}else{
    		window.location.href=Domain.cms_path + "/audit/comment/videoCommentAuditManage.htm?type="+type;
    	}
    }
    
    //清楚选择的课程
//    commentAuditManage.remove_select_course = function(){
//    	$('#courseId').val('');
//    	$('#courseName').val('');
//    }
    
    //搜索
    function search(){
    	var type = $("#type").val();
    	var action;
    	if(type==1){
    		action = Domain.cms_path + "/audit/comment/commentAuditManage.htm";
    	}else{
    		action = Domain.cms_path + "/audit/comment/videoCommentAuditManage.htm?type="+type;
    	}
    	$("#commentAuditForm").attr("action", action);
    	$("#commentAuditForm").submit();
    }
    
})(jQuery, window, document);