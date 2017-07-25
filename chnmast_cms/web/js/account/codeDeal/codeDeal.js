(function($,window,document,undefined){
	var b = {};
	window.CodeDeal = b;
	b.init = function(){
		bindEvent();
	}
	
	function bindEvent(){
		$("#search_code_del").on("click",queryCodeList);
		//使用验票码
		$(".code-use-btn").on("click",function(){
			codeOperate("use",$(this).attr("val"));
		});
		//取消验票码
		$(".code-cancel-btn").on("click",function(){
			codeOperate("unuse",$(this).attr("val"));
		});
		//快速验票
		$("#quick_check").on("click",openQuickCheck);
		//快速验票-验票号精确查找
		$("#quick_check_search").on("click",quickCheckSearch);
		//快速验票-验票号精确查找-回车
		$("#quick_verifycode").on("keydown",quickEnter);
		//快速验票-使用按钮
		
		$("#quick_checke_use").on("click",quickCheck);
	
	}
	//课程
	b.courseChangeBack = function(){
		$("#courseTimeId").val("");
		$("#courseTimeName").val("");
		$("#courseSeq").val("");
	}
	//快速匹配回车事件
	function quickEnter(event){
		if(event.keyCode==13){
			quickCheckSearch();
		}
	}
	//打开快速验票窗口
	function openQuickCheck(){
		var courseId = $("#courseId").val();
		var courseTimeId = $("#courseTimeId").val();
		if(courseId=="0" || courseTimeId=="0"){
			Tips.showAlertWin({info:"课程和课次信息为必选条件！"});
			return;
		}
		$("#quick_verifycode").val("");
		$("#quick_check_container").html("");
		$("#quick_check_error").html("");
		Box.showWin({target:"#quick_chick_layer"});
		$("#quick_verifycode").focus();
	}
	
	//查询
	function queryCodeList(){
		var courseId = $("#courseId").val();
		var courseTimeId = $("#courseTimeId").val();
		if(courseId=="0" || courseTimeId=="0"){
			Tips.showAlertWin({info:"课程和课次信息为必选条件！"});
			return;
		}
		$(".searchForm").submit();
	}
	//验票号精确查询
	function quickCheckSearch(){
		var verifyCode = $("#quick_verifycode").val();
		var courseId = $("#courseId").val();
		var courseTimeId = $("#courseTimeId").val();
		var url = "/account/verifyCodeQuickQuery.htm?verifyCode="+verifyCode+"&courseId="+courseId+"&courseTimeId="+courseTimeId;
		$("#quick_check_error").html("");
		Sender.load({div:"#quick_check_container",url:url});
		
	}
	//快速验证-验证码验证
	function quickCheck(){
		$("#quick_check_error").html("");
		var codeId =$("#codeId").val();
		if(!codeId){
			$("#quick_check_error").html("请输入正确的验票码！");
			$("#quick_verifycode").focus();
			return;
		}
		var state = $("#detailState").val();
		if(state=="USED"){
			$("#quick_check_error").html("该验证码已被使用！");
			$("#quick_verifycode").focus();
			return;
		}
		var url = "/account/updateVerifyCode.htm?codeId="+codeId+"&operType=use";
		Tips.loading({info:"正在处理请稍后..."})
		$.post(url,null,function(data){
			Tips.loaded();
			if(data){
				if(data.result){
					Tips.showAlertWin({info:"验证成功",callFn:function(){
						openQuickCheck();
					}});
					$(".submit").focus();
				}else{
					$("#quick_check_error").html(data.errorMsg);
					Box.showWin({target:"#quick_chick_layer"});
					$("#quick_verifycode").focus();
				}
			}
		},"json");
	}
	//验证或者取消验证码
	function codeOperate(operType,codeId){
		var content = "确定要";
		if(operType=="use"){
			content += "使用这条验证码吗？";
		}else{
			content += "取消这条验证码吗？";
		}
		var param ={
			info:content,
			callFn:function(){
				var url = "/account/updateVerifyCode.htm?codeId="+codeId+"&operType="+operType;
				Tips.loading({info:"正在处理请稍后..."})
				$.post(url,null,function(data){
					Tips.loaded();
					if(data){
						if(data.result){
							$(".searchForm").submit();
						}else{
							Tips.showErrorWin({info:data.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
	}
})(jQuery,window,document);