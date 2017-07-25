(function($, window, document){
	var pub = {};
	var pro = null;
	var city = null;
	var cou = null;
	window.editStudent = pub;
    pub.init = function(subjectCode) {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
    	$("#saveBtn").bind("click", editStudent);
    	$(".selectType").bind("click",selectType);
    }
	
    //保存
    function editStudent(){
    	FieldMsg.clearFieldError("#studentForm"); //清除各字段的报错提示
    	//验证手机号
    	/*var mobile=$("#phoneInput").val();
    	if (!Validator.isMobileAll(mobile)) {
    		FieldMsg.addFieldError("#phoneInput", "手机号码格式不正确", false);	
    		return;
		}*/
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#studentForm")) return;
       	if(Validator.test(/[&;%\'\"<>]/,$("#passwordInput").val())){
       		FieldMsg.addFieldError("#passwordInput", "密码不能包含特殊字符", false);
       		return;
       	}
       	if(!Verify.checkOverLen3("#passwordInput","密码", 6,20))return;
    	if($("#repassword").val() != $("#passwordInput").val()){	
        	FieldMsg.addFieldError("#repassword", "两次输入的密码不一致", false);
        	return;
    	}
		$('#studentForm').submit();
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