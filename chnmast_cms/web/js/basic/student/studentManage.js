(function($, window, document){
	var pub = {};
	window.studentManage = pub;
    pub.init = function() {
        bindEvent();
    }
    
    //绑定页面事件方法
    function bindEvent() {
    	$("#searchButton").bind("click", searchStudent);
        $("#allCheckbox1").bind("click", allCheckbox);
        $("#allCheckbox2").bind("click", allCheckbox);
        $("#passwordBtn").bind("click", passwordBtn);
        $("#resetPwdBtn").bind("click", resetPwd);
        $(".member-change").bind("click", memberChange);
        $("#newButton").bind("click", addStudent);
        $("#importButton").bind("click", importStudent);
        $("#importFile").bind("change", function(){$("#importName").val($("#importFile").val());});
        $("#viewResultButton").bind("click", viewResultStudent);
        $(".selectType").bind("click",selectType);
    }
    
    //搜索
    function searchStudent(){
    	$("#studentForm").attr("action", Domain.cms_path + "/basic/studentManage.htm");
    	$("#studentForm").attr("target", "");
    	$("#studentForm").submit();
    }
    
    //全选
    function allCheckbox(){
    	if($(this).attr('checked') == 'checked' || $(this).attr('checked') == true){
    		$("input[name='userIdsCheckbox']").attr("checked", true); 
    		$("#allCheckbox1").attr("checked", true); 
    		$("#allCheckbox2").attr("checked", true); 
    	}else{
    		$("input[name='userIdsCheckbox']").attr("checked", false); 
    		$("#allCheckbox1").attr("checked", false); 
    		$("#allCheckbox2").attr("checked", false); 
    	}
    }
  //新增
    function addStudent(){
    	location.href = Domain.cms_path + "/basic/studentAdd.htm";
    }
    //显示密码、隐藏密码
    function passwordBtn(){
    	var stuIds = new Array();
    	$("input[name='userIdsCheckbox']").each(function(){
    		stuIds.push(this.value);
    	});
    	if(stuIds == ''){
    		return ;
    	}
    	var flag = $(this).attr("dataFlag");
    	if(flag==1){
    		$(this).attr("dataFlag", 2);
    		$(this).text("隐藏密码");
            Sender.ajax({
            	"url" : Domain.cms_path + "/basic/showPwd.htm",
            	"data" : {idStr : stuIds.toString()},
            	"dataType" : "json",
            	"fn": function(data){
            		for(var i=0; i<data.length; i++) {
            			$("#pwd_" + data[i].id).html(data[i].password);
            		}
            	}
            });
    	}else{
    		$(this).attr("dataFlag", 1);
    		$(this).text("显示密码");
    		for(var i=0; i<stuIds.length; i++) {
    			$("#pwd_"+stuIds[i]).text("******");
    		}
    	}
    }
    
    //重置密码
    function resetPwd(){
    	var stuIds = new Array();
    	var allIds = new Array();
    	$("input[name='userIdsCheckbox']").each(function(){
    		allIds.push(this.value);
    		if(this.checked) {
    			stuIds.push(this.value);
    		}
    	});
		if(stuIds == '') {
			Tips.showErrorMsg({"info":"请先选择用户!"});
			return ;
        }
        Sender.ajax({
    		"url" : Domain.cms_path + "/basic/resetPwd.htm",
    		"data" : {idStr : stuIds.toString()},
    		"fn":function() {
    			Tips.showSuccessMsg({"info":"操作成功!"});
    			
    			$("#passwordBtn").attr("dataFlag", 1);
    			$("#passwordBtn").text("显示密码");
    			for(var i=0; i<allIds.length; i++) {
        			$("#pwd_"+allIds[i]).text("******");
        		}
	        }
        });
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
                            $("#searchButton").click();
                        });
                    }
                };
                Sender.ajax(data);
            }
        };
        Tips.showConfirmWin(params);
	}
    //导入
    function importStudent(){
    	if($("#groupTypeIdStr").val() == ""){
    		Tips.showWarnWin({"info" : "导入学员前，请选择分组类型！"});
    		return;
    	}
    	if($("#importName").val() == ""){
    		Tips.showWarnWin({"info" : "请选择需要导入的文件！"});
    		return;
    	}
    	$("#importForm").attr("action", Domain.cms_path + "/basic/studentImport.htm");
        $("#importForm").attr("target", 'importStudentFrame');
        $("#importForm").attr("enctype", 'multipart/form-data');
        $("#importForm").submit();
        Tips.loading({"info":"正在处理,请稍后！"});
    }
    //查看导入结果
    function viewResultStudent(){
    	var params ={
    		"url":"/basic/viewStudentResultExcel.htm",
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
                    Tips.showWarnWin({"info":'个人用户导入结果文件下载：<p> <a href=\"'+Domain.uploadFile_path + "/" +path +'" title=\"点击下载\" >'+info[1]+'</a></p>'});
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
    			searchStudent();
    		}
    	}});
    }
    
    //选择分类
    function selectType(){
    	//清空错误提示
		$("#selectTypeDiv_errorMsg").html("");
		$("#selectTypeDiv_title").removeClass("input-txt-error");
		//加载数据
		loadGroupTypes();
		Box.showWin({target:"#selectTypeDiv"});
		
		$("#selectTypeDiv_submit").unbind().bind("click", selectGroupType);
		$("#selectTypeDiv_add").unbind().bind("click", addGroupType);
		$("#selectTypeDiv_del").unbind().bind("click", delGroupType);
    }
    //加载分组类型数据
    function loadGroupTypes(){
		var param = {
			"div":"#selectTypeDiv_container",
			"url":Domain.cms_path+"/basic/loadGroupTypes.htm"
		}
		Sender.load(param);
	}
    //新增分组类型
    function addGroupType(){
    	$("#selectTypeDiv_errorMsg").html("");
    	$("#selectTypeDiv_title").removeClass("input-txt-error");
    	var title = $("#selectTypeDiv_title").val();
	    if(StringUtil.trim(title)==""){
	    	$("#selectTypeDiv_title").addClass("input-txt-error");
	    	$("#selectTypeDiv_errorMsg").html("类型名称不能为空！");
	    	return;
	    }
	    Sender.ajax({
	    	"url" : Domain.cms_path+"/basic/addGroupType.htm",
	    	"data" : {"idStr" : title},
	    	"dataType" : "json",
	    	"fn" : function(result){
	    		if(result.isSuccess){
	    			loadGroupTypes();
	    		}else{
	    			$("#selectTypeDiv_title").addClass("input-txt-error");
	    			$("#selectTypeDiv_errorMsg").html(result.errorMsg);
	    		}
	    	}
	    });
    }
    //删除分组类型
    function delGroupType(){
    	$("#selectTypeDiv_errorMsg").html("");
    	$("#selectTypeDiv_title").removeClass("input-txt-error");
    	var id = $("#selectTypeDiv .type_radio:checked").val();
		if(!id){
			$("#selectTypeDiv_errorMsg").html("请选择需要删除的分类！");
			return;
		}
		Sender.ajax({
	    	"url" : Domain.cms_path+"/basic/delGroupType.htm",
	    	"data" : {"id" : id},
	    	"dataType" : "json",
	    	"fn" : function(result){
	    		if(result.isSuccess){
	    			loadGroupTypes();
	    		}else{
	    			$("#selectTypeDiv_errorMsg").html(result.errorMsg);
	    		}
	    	}
	    });
    }
    //选择分组类型
    function selectGroupType(){
    	var id = $("#selectTypeDiv .type_radio:checked").val();
    	var title = $("#selectTypeDiv").find(".type_radio:checked").attr("dataValue");
    	if(!id || id==undefined){
    		id="";
    		title="";
		}
    	$("#groupTypeId").val(id);
    	$("#groupTypeTitle").val(title);
    	$("#groupTypeIdStr").val(id);
    	$("#selectTypeDiv .close").click();
    }
})(jQuery, window, document);