(function($,window,document,undefined){
	var b = {};
	window.serverManage = b;
	b.init = function(){
		bindEvent();
	}
	//绑定事件
	function bindEvent(){
		//点击搜索
		$("#search").bind("click",function(){
			$("#searchForm").submit();
		});
		//新增事件
		var _add = $("#addButton");
		if(_add){
			_add.bind("click",function(){
				window.location = Domain.cms_path+"/wxb/addServer.htm";
			});
		}
		//删除事件
		var _del = $(".del_server");
		if(_del){
			_del.each(function(){
				$(this).bind("click",deleteServer);
			});
		}
		//查看事件
		var _info =$(".server_info");
		if(_info){
			_info.each(function(){
				$(this).bind("click",function(){
					var id = $(this).attr("val");
					window.location.href = Domain.cms_path+"/wxb/serverInfo.htm?serverId="+id;
				});
			});
		}
	}
	
	function deleteServer(){
		var id = $(this).attr("val");
		var params = {
			info:"确定删除吗？",
			callFn:function(){
				var url = Domain.cms_path+"/wxb/deleteServer.htm";
				$.post(url,{serverId:id},function(result){
					FieldMsg.drawMessages(result,function(){$("#searchForm").submit();},null);
				},"json");
			}
		}
		Tips.showConfirmWin(params);	
	}
})(jQuery,window,document);