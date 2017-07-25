(function($, window, document){
	var pub = {};
	window.viewAgencyJoin = pub;
    pub.init = function() {
    	$("#checkbtn").bind("click", checkAgencyJoin);
//    	$("#checkbtn").click(function(){
//    		var id = $(this).attr("dataValue");
//    		if(id == undefined || id == ""){
//    			return;
//    		}
//    		Sender.ajax({
//    			"url" : Domain.cms_path + "/basic/checkAgencyJoin.htm",
//    			"data" : {"id" : id},
//    			"dataType" : "json",
//    			"fn" : function(result){
//    				FieldMsg.drawMessages(result);
//    			}
//    		});
//    	});
    }
    
    //同意加盟
    function checkAgencyJoin(){
    	var id = $(this).attr("dataValue");
		if(id == undefined || id == ""){
			return;
		}
		Sender.openDiv({
			"div":"#agenycJoinDiv", 
			"closeObject":"#agenycJoinDiv .submit, #agenycJoinDiv .close",
			"url" : Domain.cms_path + "/basic/getShortspell.htm?id="+id
		});
    }
})(jQuery, window, document);