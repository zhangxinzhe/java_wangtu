(function($,window,document,undefined){
	var b = {};
	window.videoReManage = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		//查询按钮
		$("#searchButton").on("click",queryVideoRe);
		//新增按钮
		$("#videoReAdd").on("click",addVideoRe);
		//删除视频
		$(".videoDelete").on("click",deleteVideoRe);
		
	}
	
	//删除回顾视频
	function deleteVideoRe(){
		var id = $(this).attr("val");
		var param = {
			info:"确定要删除这条信息吗？",
			callFn:function(){
				var url = Domain.cms_path+"/homepage/info/videoReDel.htm"
				var params = {
					infoId:id
				};
				$.post(url,params,function(dto){
					if(dto){
						if(dto.isSuccess){
							window.location.href = Domain.cms_path+"/homepage/info/videoReManage.htm";
						}else{
							Tips.showErrorMsg({info:dto.errorMsg});
						}
					}
				},"json");
			}
		}
		Tips.showConfirmWin(param);
	}
	//回顾视频
	function queryVideoRe(){
		$(".videoReManage").submit();
	}
	//回顾视频
	function addVideoRe(){
		window.location.href = Domain.cms_path+"/homepage/info/videoReAdd.htm";
	}
})(jQuery,window,document);