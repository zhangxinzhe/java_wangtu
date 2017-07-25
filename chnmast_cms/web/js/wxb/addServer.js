(function($,window,document,undefined){
	var b = {};
	window.addServer = b;
	b.init = function(){
		bindEvent();
	}
	//绑定事件
	function bindEvent(){
		//返回
		$("#backBtn").bind("click",function(){
			Util.winBack();
		});
		//保存
		$("#saveButton").bind("click",saveInfo);
		//获取信息
		$("#getDogInfo").bind("click",getDogInfo);
	}
	
	//保存信息
	function saveInfo(){
		if(!Verify.checkAllVerify("#serverForm")) return; //验证所有字段
		//验证服务器名称
		var serverName = $("#serverName").val();
		if(!Validator.test(/^[\u4e00-\u9fa5_a-zA-Z0-9]+$/,serverName)){
			FieldMsg.addFieldError("#serverName", "服务器名称格式错误");
			return;
		}
		//提交表单
		$("#serverForm").submit();
	}
	
	//获取服务器加密狗信息
	function getDogInfo() {
		var id = $("#server_id").val();
		if(id == ""){
			Tips.showErrorMsg({"info" : "此服务器不存在，无法获取信息！"});
			return;
		}
		var params = {
			url : Domain.cms_path+"/wxb/getDogInfo.htm",
			data : {serverId : id},
			dataType : 'json',
			fn : function(result){
				var msg = result.ERROR;
				if(msg){
					Tips.showErrorMsg({"info" : msg});
					return;
				}
				
				$("#dogName").attr("value", result.name);
				$("#dogNo").attr("value", result.dogid);
				$("#maxCapacity").attr("value", result.maxuser);
				$("#proxyNum").attr("value", result.proxynum);
				var noexpire = result.noexpire;
				if(noexpire === "0"){//过期
		            //设置过期时间
		            $('#dogEndTime').attr("value", result.expiredate);
		            //设置过期标志上传字段值
		            $('#dogNoExpire').attr("value", "NO");
				}else{
					$('#dogEndTime').attr("value", "");
		            $('dogNoExpire').attr("value", "YES");
	            }
				var valueE = $('#dogEndTime').val();
				$("#noexpire").find('option[value="'+valueE+'"]').attr("selected", "selected").siblings("option").removeAttr("selected");;
				$("#lastSynDate").attr("value", result.date);
				
				saveInfo();//保存信息
			}
		}
		Sender.ajax(params);
	}
}(jQuery,window,document));