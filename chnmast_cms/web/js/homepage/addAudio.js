(function($, window, document) {
	var pub = {};
	window.addAudio = pub;
	pub.init = function() {
		bindEvent();
	}

	// 绑定事件
	function bindEvent() {
		$("#saveBtn").on("click", doSaveAudio);
		//打开音乐类型选择页面
		$(".selectType").on("click",openSelectType);
		//新增内容分类
		$("#add_adv_type").on("click",addAdvType);
		//删除分类内容
		$("#delete_adv_type").on("click",deleteAdvType);
		//分类选择--确定
		$("#selectType_submit").on("click",setAdvTypeBack);
	}

	// 保存
	function doSaveAudio() {
		// 新增音频时时 uploadTempLocalFile不能为空
		if (!Validator.isNotBlank($("#audioId").val())
				&& !Validator.isNotBlank($("input[name='uploadTempLocalFile']").val())) {
			Tips.showAlertWin({
				info : "请上传音频！"
			});
			return;
		}
		if (!Verify.checkAllVerify("#audio_form")) {
			return;
		}
		$("#audio_form").attr("action", Domain.cms_path + '/homepage/adv/indexAdvSave.htm?tabStr=audio');
		$("#audio_form").submit();
		Tips.loading({
			info : '提交中，请稍候...'
		});
	}
	//打开音乐类型选择页面
	function openSelectType(){
		var param = {target:"#selectType_div"};
		Box.showWin(param);
		//清空错误提示
		$("#type_errorMsg").html("");
		$("#select_title").removeClass("input-txt-error");
		//加载数据
		loadAdvTypeList();
	}
	//视音频分类修改
	function addAdvType(){
	    var title = $("#select_title").val();
	    if(title==""){
	    	$("#select_title").addClass("input-txt-error");
	    	$("#type_errorMsg").html("分类内容不能为空！");
	    	return ;
	    }
	    $("#select_title").removeClass("input-txt-error");
	    $("#type_errorMsg").html("");
	    var param = {
	    	"advType.title":title,
	    	"tabStr":"audio"
	    }
		var url = Domain.cms_path +"/homepage/adv/addAdvType.htm";
	    $.post(url,param,function(data){
	    	if(data){
	    		if(data.flag){
	    			loadAdvTypeList();
	    		}else{
	    			$("#type_errorMsg").html(data.errorMsg);
	    		}
	    	}
	    },"json");
	}
	//加载音频分类列表
	function loadAdvTypeList(){
		var param = {
			"div":"#selectType_container",
			"url":Domain.cms_path+"/homepage/adv/advTypeList.htm?tabStr=audio"
		}
		Sender.load(param);
	}
	//新增分类内容
	function deleteAdvType(){
		var id = $(".type_radio:checked").val();
		if(!id){
			$("#type_errorMsg").html("请选择需要删除的分类！");
			return;
		}
		$("#type_errorMsg").html("");
		var param = {
	    	"advTypeId":id,
	    	"tabStr":"audio"
	    }
		var url = Domain.cms_path +"/homepage/adv/deleteAdvType.htm";
	    $.post(url,param,function(data){
	    	if(data){
	    		if(data.flag){
	    			loadAdvTypeList();
	    		}else{
	    			$("#type_errorMsg").html(data.errorMsg);
	    		}
	    	}
	    },"json");
	}
	//分类选择--确定
	function setAdvTypeBack(){
		var id = $(".type_radio:checked").val();
		var title = $(".type_radio:checked").attr("dataValue");
		if(!id||!title){
			$("#type_errorMsg").html("请选择分类内容！");
			return;
		}
		$("#type_errorMsg").html("");
		$("#typeId").val(id);
		$("#title").val(title);
		//关闭弹出窗
		Box.closeDiv("#selectType_div");
	}
})(jQuery, window, document);