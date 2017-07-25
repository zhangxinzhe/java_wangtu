(function($,window,document,undefined){
	var b = {};
	var serverId = $("#serverId").val();
	window.serverInfo = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		//修改
		$("#updateBtn").bind("click",function(){
			window.location.href = Domain.cms_path+"/wxb/editServer.htm?serverId="+serverId;
		});
		//返回
		$("#backBtn").bind("click",function(){
			window.location.href = Domain.cms_path+"/wxb/serverManage.htm";
		});
		//停用或启用
		$("#startOrStopServer").bind("click",startOrStop);
	}
	//停用或启用服务器
	function startOrStop(){
		var _type = $(this).attr("state"); 
		//弹出提示
		var mes = "确定要停用服务器？";
		if(_type=="start"){
			mes = "请事先保证服务器的正常运行。确定启用？";
		}
		var params = {
			info:mes,
			callFn:function(){doStartOrStop(_type);}
		}
		Tips.showConfirmWin(params);
	}
	//异步处理启用或者关闭服务器
	function doStartOrStop(_type){
		var data = {"type":_type,"serverId":serverId};
		var url = Domain.cms_path+"/wxb/doStartOrStop.htm";
		$.post(url,data,function(result){
			FieldMsg.drawMessages(result,refreshInfo,null);
//			if(result.flag=="true"){
//				Tips.showSuccessMsg({info:"操作成功！",callFn:refreshInfo});
//			}else{
//				Tips.showErrorMsg({info:result.ErrorMes});
//			}
		},"json");
	}
	//启用或者关闭后的处理
	function refreshInfo(){
		window.location.href = Domain.cms_path+"/wxb/serverInfo.htm?serverId="+serverId;
	}
})(jQuery,window,document);