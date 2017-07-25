(function($, window, document){
	var pub = {};
	window.agencyJoinManage = pub;
    pub.init = function() {
        bindEvent();
    }
    
    //绑定页面事件方法
    function bindEvent() {
        $("#searchButton").bind("click", searchAgencyJoin);
        $("#allCheckbox1").bind("click", allCheckbox);
        $("#allCheckbox2").bind("click", allCheckbox);
        
        $("#removeButton").bind("click", delAgency);
        $(".checkAgencyJoin").bind("click", checkAgencyJoin);
    }
    
    //搜索
    function searchAgencyJoin(){
    	$("#agencyJoinForm").attr("action", Domain.cms_path + "/basic/agencyJoinManage.htm");
    	$("#agencyJoinForm").submit();
    }
    
    //全选
    function allCheckbox(){
    	if($(this).attr('checked') == 'checked' || $(this).attr('checked') == true){
    		$("input[name='agencyIdsCheckbox']").attr("checked", true); 
    		$("#allCheckbox1").attr("checked", true); 
    		$("#allCheckbox2").attr("checked", true); 
    	}else{
    		$("input[name='agencyIdsCheckbox']").attr("checked", false); 
    		$("#allCheckbox1").attr("checked", false); 
    		$("#allCheckbox2").attr("checked", false); 
    	}
    }
    
    //删除
    function delAgency(){
    	var agencyIds = new Array();
		var agencyNames = new Array();
  		$("input[name='agencyIdsCheckbox']").each(function(){
    		if(this.checked) {
    			agencyIds.push(this.value);
    			agencyNames.push($("#agencyName_"+this.value).html());
    		}
    	});
  		if(agencyIds == '') {
          Tips.showErrorMsg({"info":"请先选择培训基地！"});
          return ;
        }
  		Tips.showConfirmWin({"info":"确定要删除所选申请加盟的培训基地吗？","callFn": function(){
  			var params ={
        		"url":"/basic/delAgencyJoin.htm",
        		"data":{idStr : agencyIds.toString(), agencyName : agencyNames.toString()},
        		"fn":function(result) {
        			if(result != '') {
        				Tips.showErrorMsg({"info":result});
						return ;
					}else{
						Tips.showSuccessMsg({
							"info":"删除申请加盟的培训基地成功!",
							"callFn":function(){
								$("#searchButton").click();
							}
						});
					}
		        }
            };
            Sender.ajax(params);
  		}});
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