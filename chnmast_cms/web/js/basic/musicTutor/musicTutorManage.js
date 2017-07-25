(function($, window, document){
	var pub = {};
	window.musicTutorManage = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
        $("#searchButton").bind("click", search);
    }
    
    //搜索
    function search(){
    	$("#musicTutorForm").attr("action", Domain.cms_path + "/basic/musicTutorManage.htm");
    	//$("#musicTutorForm").attr("target", "");
    	$("#musicTutorForm").submit();
    }
    
})(jQuery, window, document);