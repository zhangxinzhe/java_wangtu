(function($, window, document){
	var pub = {};
	window.unionMemberManage = pub;
    pub.init = function() {
        bindEvent();
    }
    
    //绑定页面事件方法
    function bindEvent() {
    	$('.public-tab li').bind('click', function() {
			var isTeam = $(this).attr('isTeam');
			window.location.href = Domain.cms_path + '/basic/unionMemberManage.htm?isTeam=' + isTeam;
		});
        $("#searchButton").bind("click", search);
    }
    
    //搜索
    function search(){
    	//$("#unionMemberForm").attr("action", Domain.cms_path + "/basic/unionMemberManage.htm");
    	//$("#unionMemberForm").attr("target", "");
    	$("#unionMemberForm").submit();
    }
    
})(jQuery, window, document);