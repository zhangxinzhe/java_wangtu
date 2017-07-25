(function($,window,document,undefined){
	var b = {};
	window.CollegeEdit = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		//修改
		$("#saveBtn").on("click",editCollege);
	}
	//修改
	function editCollege(){
		var collegeName = $("#collegeName").val();
		if(collegeName == ""){
			Tips.showAlertWin({info:"请输入高校名称！"});
			return;
		}
		var isRecommend = $("input[type='radio']:checked").val();
		if(isRecommend=="" || isRecommend == undefined){
			Tips.showAlertWin({info:"请输选择是否推荐！"});
			return;
		}
		var displayNo = $("#displayNo").val();
		if(isNaN(displayNo)){
			Tips.showAlertWin({info:"排序号必须为数字！"});
			return;
		}
		var url = Domain.cms_path +"/homepage/college/editCollege.htm";
		$.post(url,$("#collegeForm").serialize(),function(result){
			if(result){
				if(result.flag){
					window.location.href = "/homepage/college/collegeManage.htm";
				}else{
					Tips.showAlertWin({info:result.msg});
				}
			}else{
				Tips.showAlertWin({info:"保存失败，请重试！"});
			}
		},"json");
	}
})(jQuery,window,document);