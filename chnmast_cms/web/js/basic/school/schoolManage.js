(function($, window, document){
	var pub = {};
	window.SchoolManage = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
        $("#searchButton").bind("click", searchSchool);
        $("#newButton").bind("click", addSchool);
        $("#importButton").bind("click", importSchool);
        $("#allCheckbox1").bind("click", allCheckbox);
        $("#allCheckbox2").bind("click", allCheckbox);
        $("#removeButton").bind("click", delSchool);
        $("#importFile").bind("change", function(){$("#importName").val($("#importFile").val());});
        $("#viewResultButton").bind("click", viewResultSchool);
    }
    
    //搜索
    function searchSchool(){
    	$("#schoolForm").attr("action", Domain.cms_path + "/basic/schoolManage.htm");
    	$("#schoolForm").attr("target", "");
    	$("#schoolForm").submit();
    }
    
    //新增
    function addSchool(){
    	location.href = Domain.cms_path + "/basic/schoolAdd.htm";
    }
    
    //全选
    function allCheckbox(){
    	if($(this).attr('checked') == 'checked' || $(this).attr('checked') == true){
    		$("input[name='schoolIdsCheckbox']").attr("checked", true); 
    		$("#allCheckbox1").attr("checked", true); 
    		$("#allCheckbox2").attr("checked", true); 
    	}else{
    		$("input[name='schoolIdsCheckbox']").attr("checked", false); 
    		$("#allCheckbox1").attr("checked", false); 
    		$("#allCheckbox2").attr("checked", false); 
    	}
    }
    
    //删除
    function delSchool(){
    	var schoolIds = new Array();
		var schoolNames = new Array();
  		$("input[name='schoolIdsCheckbox']").each(function(){
    		if(this.checked) {
    			schoolIds.push(this.value);
    			schoolNames.push($("#schoolName_"+this.value).html());
    		}
    	});
  		if(schoolIds == '') {
          Tips.showErrorMsg({"info":"请先选择学校!"});
          return ;
        }
  		Tips.showConfirmWin({"info":"确定要删除所选学校吗？","callFn": function(){
  			var params ={
        		"url":"/basic/schoolDelete.htm",
        		"data":{idStr : schoolIds.toString(), schoolName : schoolNames.toString()},
        		"fn":function(result) {
        			if(result != '') {
        				Tips.showErrorMsg({"info":result});
						return ;
					}else{
						Tips.showSuccessMsg({"info":"删除学校成功!"});
					}
        			setTimeout(searchSchool, 2000);
		        }
            };
            /**ajax提交数据*/
            Sender.ajax(params);
  		}});
    }
    //导入
    function importSchool(){
    	$("#importForm").attr("action", Domain.cms_path + "/basic/schoolImport.htm");
        $("#importForm").attr("target", 'importSchoolFrame');
        $("#importForm").attr("enctype", 'multipart/form-data');
        $("#importForm").submit();
        Tips.loading({"info":"正在处理,请稍后！"});
    }
    //查看导入结果
    function viewResultSchool(){
    	var params ={
    		"url":"/basic/viewSchoolResultExcel.htm",
    		"fn":function(result) {
    			if(result == "-1"){
                    window.location.href = Domain.cms_path + "/noprivacy.htm";
                }
                if(!result || result == "") {
                    Tips.showErrorMsg({"info":"当前没有任务文件!"});
                    return ;
                }
                if(result){
                    var info = result.split('#');
                    var path = info[0];
                    Tips.showWarnWin({"info":'学校导入结果文件下载：<p> <a href=\"'+Domain.uploadFile_path + "/" +path +'" title=\"点击下载\" >'+info[1]+'</a></p>'});
                }
	        }
        };
        /**ajax提交数据*/
        Sender.ajax(params);
    }
    pub.showResult = function(msg, isSuccess) {
    	Tips.loaded();
    	Tips.showAlertWin({"info" : msg, "callFn" : function(){
    		if(isSuccess){
    			searchSchool();
    		}
    	}});
    }
})(jQuery, window, document);