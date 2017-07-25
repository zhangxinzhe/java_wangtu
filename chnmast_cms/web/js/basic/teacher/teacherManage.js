(function($, window, document){
	var pub = {};
	window.TeacherManage = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
        $("#searchButton").bind("click", searchTeacher);
        $("#newButton").bind("click", addTeacher);
        $("#allCheckbox1").bind("click", allCheckbox);
        $("#allCheckbox2").bind("click", allCheckbox);
        $("#cancelButton").bind("click", cancelTeacher);
        $("#recoverButton").bind("click", recoverTeacher);
        $("#removeButton").bind("click", delTeacher);
    }
    
    //搜索
    function searchTeacher(){
    	$("#teacherForm").attr("action", Domain.cms_path + "/basic/teacherManage.htm");
    	$("#teacherForm").attr("target", "");
    	$("#teacherForm").submit();
    }
    
    //新增
    function addTeacher(){
    	location.href = Domain.cms_path + "/basic/addTeacher.htm";
    }
    
    //全选
    function allCheckbox(){
    	if($(this).attr('checked') == 'checked' || $(this).attr('checked') == true){
    		$("input[name='teacherIdsCheckbox']").attr("checked", true); 
    		$("#allCheckbox1").attr("checked", true); 
    		$("#allCheckbox2").attr("checked", true); 
    	}else{
    		$("input[name='teacherIdsCheckbox']").attr("checked", false); 
    		$("#allCheckbox1").attr("checked", false); 
    		$("#allCheckbox2").attr("checked", false); 
    	}
    }
    
    //注销
    function cancelTeacher(){
    	var teacherIds = new Array();
		var teacherNames = new Array();
  		$("input[name='teacherIdsCheckbox']").each(function(){
    		if(this.checked) {
    			teacherIds.push(this.value);
    			teacherNames.push($("#teacherName_"+this.value).html());
    		}
    	});
  		if(teacherIds == '') {
          Tips.showErrorMsg({"info":"请先选择名师大家!"});
          return ;
        }
  		var params ={
    		"url":"/basic/cancelTeacher.htm",
    		"data":{teacherIdStr : teacherIds.toString(), teacherName : teacherNames.toString()},
    		"fn":function() {
    			Tips.showSuccessMsg({"info":"所选名师大家已注销!"});
    			setTimeout(searchTeacher, 2000);
	        }
        };
        /**ajax提交数据*/
        Sender.ajax(params);
    }
    
    //恢复
    function recoverTeacher(){
    	var teacherIds = new Array();
		var teacherNames = new Array();
  		$("input[name='teacherIdsCheckbox']").each(function(){
    		if(this.checked) {
    			teacherIds.push(this.value);
    			teacherNames.push($("#teacherName_"+this.value).html());
    		}
    	});
  		if(teacherIds == '') {
          Tips.showErrorMsg({"info":"请先选择名师大家!"});
          return ;
        }
  		var params ={
    		"url":"/basic/recoverTeacher.htm",
    		"data":{teacherIdStr : teacherIds.toString(), teacherName : teacherNames.toString()},
    		"fn":function() {
    			Tips.showSuccessMsg({"info":"所选名师大家已恢复!"});
    			setTimeout(searchTeacher, 2000);
	        }
        };
        /**ajax提交数据*/
        Sender.ajax(params);
    }
    
    //删除
    function delTeacher(){
    	var teacherIds = new Array();
		var teacherNames = new Array();
  		$("input[name='teacherIdsCheckbox']").each(function(){
    		if(this.checked) {
    			teacherIds.push(this.value);
    			teacherNames.push($("#teacherName_"+this.value).html());
    		}
    	});
  		if(teacherIds == '') {
          Tips.showErrorMsg({"info":"请先选择名师大家!"});
          return ;
        }
  		Tips.showConfirmWin({"info":"确定要删除所选名师大家吗？","callFn": function(){
  			var params ={
        		"url":"/basic/delTeacher.htm",
        		"data":{teacherIdStr : teacherIds.toString(), teacherName : teacherNames.toString()},
        		"fn":function(result) {
        			if(result != '') {
        				Tips.showErrorMsg({"info":result});
						return ;
					}else{
						Tips.showSuccessMsg({"info":"删除名师大家成功!"});
					}
        			setTimeout(searchTeacher, 2000);
		        }
            };
            /**ajax提交数据*/
            Sender.ajax(params);
  		}});
    }
    
})(jQuery, window, document);