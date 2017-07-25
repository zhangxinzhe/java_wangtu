(function($, window, document){
	var pub = {};
	window.AgencyManage = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
        $("#searchButton").bind("click", searchAgency);
        $("#newButton").bind("click", addAgency);
        $("#allCheckbox1").bind("click", allCheckbox);
        $("#allCheckbox2").bind("click", allCheckbox);
        $("#resetPasswordButton").bind("click", resetPassword);
        $("#cancelButton").bind("click", cancelAgency);
        $("#recoverButton").bind("click", recoverAgency);
        $("#removeButton").bind("click", delAgency);
    }
    
    //搜索
    function searchAgency(){
    	$("#agencyForm").attr("action", Domain.cms_path + "/basic/agencyManage.htm");
    	$("#agencyForm").attr("target", "");
    	$("#agencyForm").submit();
    }
    
    //新增
    function addAgency(){
    	location.href = Domain.cms_path + "/basic/addAgency.htm";
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
    
    //重置密码
    function resetPassword(){
    	var agencyIds = new Array();
    	$("input[name='agencyIdsCheckbox']").each(function(){
    		if(this.checked) {
    			agencyIds.push(this.value);
    		}
    	});
		if(agencyIds == '') {
			Tips.showErrorMsg({"info":"请先选择培训基地!"});
			return ;
        }
        var params ={
    		"url":"/basic/resetAgency.htm",
    		"data":{idStr : agencyIds.toString()},
    		"fn":function() {
    			Tips.showSuccessMsg({"info":"操作成功!"});
    			setTimeout(searchAgency, 2000);
	        }
        };
        /**ajax提交数据*/
        Sender.ajax(params);
    }
    
    //注销
    function cancelAgency(){
    	var agencyIds = new Array();
		var agencyNames = new Array();
  		$("input[name='agencyIdsCheckbox']").each(function(){
    		if(this.checked) {
    			agencyIds.push(this.value);
    			agencyNames.push($("#agencyName_"+this.value).html());
    		}
    	});
  		if(agencyIds == '') {
          Tips.showErrorMsg({"info":"请先选择培训基地!"});
          return ;
        }
  		var params ={
    		"url":"/basic/cancelAgency.htm",
    		"data":{idStr : agencyIds.toString(), agencyName : agencyNames.toString()},
    		"fn":function() {
    			Tips.showSuccessMsg({"info":"所选培训基地已注销!"});
    			setTimeout(searchAgency, 2000);
	        }
        };
        /**ajax提交数据*/
        Sender.ajax(params);
    }
    
    //恢复
    function recoverAgency(){
    	var agencyIds = new Array();
		var agencyNames = new Array();
  		$("input[name='agencyIdsCheckbox']").each(function(){
    		if(this.checked) {
    			agencyIds.push(this.value);
    			agencyNames.push($("#agencyName_"+this.value).html());
    		}
    	});
  		if(agencyIds == '') {
          Tips.showErrorMsg({"info":"请先选择培训基地!"});
          return ;
        }
  		var params ={
    		"url":"/basic/recoverAgency.htm",
    		"data":{idStr : agencyIds.toString(), agencyName : agencyNames.toString()},
    		"fn":function() {
    			Tips.showSuccessMsg({"info":"所选培训基地已恢复!"});
    			setTimeout(searchAgency, 2000);
	        }
        };
        /**ajax提交数据*/
        Sender.ajax(params);
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
          Tips.showErrorMsg({"info":"请先选择培训基地!"});
          return ;
        }
  		Tips.showConfirmWin({"info":"确定要删除所选培训基地吗？","callFn": function(){
  			var params ={
        		"url":"/basic/delAgency.htm",
        		"data":{idStr : agencyIds.toString(), agencyName : agencyNames.toString()},
        		"fn":function(result) {
        			if(result != '') {
        				Tips.showErrorMsg({"info":result});
						return ;
					}else{
						Tips.showSuccessMsg({"info":"删除培训基地成功!"});
					}
        			setTimeout(searchAgency, 2000);
		        }
            };
            /**ajax提交数据*/
            Sender.ajax(params);
  		}});
    }
    
})(jQuery, window, document);