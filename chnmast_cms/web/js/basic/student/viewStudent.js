(function($, window, document){
	var pub = {};
	window.studentManage = pub;
    pub.init = function() {
        bindEvent();
    }
    
    //绑定页面事件方法
    function bindEvent() {
        $("#member-change").bind("click", memberChange);
    }
    
    //升级/取消会员
    function memberChange(){
		var id = $(this).attr("dataValue");
		var flag = $(this).attr("flag");
		var info = "";
		if(flag == 0){
            info = "确定取消会员吗？";
        }else if(flag == 1){
            info = "确定升级会员吗？";
        }
		var params = {
            "info" : info,
            "callFn" : function(){
                var data = {
                    "url" : Domain.cms_path + "/basic/isMember.htm",
                    "data" : {"id":id, flag:flag},
                    "dataType" : 'json',
                    "fn" : function(result){
                        FieldMsg.drawMessages(result, function(){
                        	document.location.href=Domain.cms_path +"/basic/viewStudent.htm?id="+id;
                        });
                    }
                };
                Sender.ajax(data);
            }
        };
        Tips.showConfirmWin(params);
	}
	
})(jQuery, window, document);