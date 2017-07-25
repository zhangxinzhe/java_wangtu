(function($,window,document,undefined){
	var b = {};
	window.AssistantManage = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		//查询
		$("#searchButton").on("click",queryAssistant);
		//新增
		$("#newButton").on("click",function(){
			window.location.href = Domain.cms_path+"/basic/assistantEdit.htm";
		});
		//注销
		$(".assistant_cancel").on("click",function(){
			var id = $(this).attr("val");
			cancelRecover("cancel",id);
		});
		//恢复
		$(".assistant_recover").on("click",function(){
			var id = $(this).attr("val");
			cancelRecover("recover",id);
		});
		//全选或者反选
		$("#check_all_assist").on("click",checkAllOrNot);
		//删除
		$("#delete_assistant_btn").on("click",deleteAssistant);
	}

	function deleteAssistant(){
		//判断有没有选中
		var $ids = $(".check_item:checked");
		if($ids.length==0){
			Tips.showErrorMsg({info:"请至少选择一条数据！"});
			return;
		}
		$("#delete_form").html("");
		$ids.each(function(){
			$("#delete_form").append("<input type='hidden' name='ids' value='"+$(this).val()+"'/>");
		});
		
		var param = {
			info:"确定要删除选中的助教吗？",
			callFn:function(){
				var catalogLevel = $("#catalogLevel").val();
				var url = Domain.cms_path+"/basic/assistantDelete.htm";
				Tips.loading({info:"正在删除数据..."});
				$.post(url,$("#delete_form").serialize(),function(data){
					Tips.loaded();
					if(data){
						if(data.isSuccess){
							$("#searchButton").click();
						}else{
							Tips.showErrorMsg({info:data.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
	}
	
	//全选或者反选
	function checkAllOrNot(){
		var flag = $(this).prop("checked");
		if(flag){
			$(".check_item").prop("checked",true);
		}else{
			$(".check_item").prop("checked",false);
		}
	}
	//查询
	function queryAssistant(){
		$(".assistantManage").submit();
	}
	//注销、恢复
	function cancelRecover(operType,id){
		var info = "确定要";
		if("cancel"==operType){
			info += "注销这个助教吗？";
		}else{
			info += "恢复这个助教吗？";
		}
		var param = {
			info:info,
			callFn:function(){
				var url = Domain.cms_path+"/basic/assistantRecover.htm?assistantId="+id+"&operType="+operType;
				Tips.loading({info:"正在处理..."});
				$.post(url,null,function(dto){
					Tips.loaded();
					if(dto){
						if(dto.isSuccess){
							$("#searchButton").click();
						}else{
							Tips.showErrorWin({info:dto.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
	}
})(jQuery,window,document);