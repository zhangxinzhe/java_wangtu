(function($,window,document,undefined){
	var b = {};
	window.AssistantEdit = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		$("#saveBtn").on("click",saveAssistant);
		
	}
	
	//保存数据
	function saveAssistant(){
		if(!Verify.checkAllVerify("#assistantForm")){
			return;
		}
		var url = Domain.cms_path+"/basic/assistantSave.htm";
		Tips.loading({info:"正在保存数据..."});
		$.post(url,$("#assistantForm").serialize(),function(dto){
			Tips.loaded();
			if(dto){
				if(dto.isSuccess){
					var param ={
						info:"保存成功",
						callFn:function(){
							window.location.href = Domain.cms_path+"/basic/assistantManage.htm";
						}
					}
					Tips.showSuccessMsg(param);
				}else{
					Tips.showErrorMsg({info:dto.errorMsg});
				}
			}
		},"json");
	}
})(jQuery,window,document);