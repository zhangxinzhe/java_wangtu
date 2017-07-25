(function($,window,document,undefined){
	var b = {};
	window.CollegeManage = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		//查询
		$("#searchButton").on("click",function(){
			queryCollege(false);
		});
		//新增
		$("#newButton").on("click",toAddCollege);
		//删除
		$(".delete_college").on("click",deleteCollege);
		//推荐
		$(".RECOMMEND_YES").on("click",function(){
			var id = $(this).attr("data-value");
			upateRecommend('YES',id);
		});
		//取消推荐
		$(".RECOMMEND_NO").on("click",function(){
			var id = $(this).attr("data-value");
			upateRecommend('NO',id);
		});
		//修改排序号
		$(document).on("click",".order_change",toOrderChange);
		//修改排序号保存
		$(document).on("click",".order_change_save",orderChange);
	}
	//打开修改排序号编辑
	function toOrderChange(){
		var id = $(this).attr("data-value");
		var orderNoId = "#order_"+id;
		var orderNo = $(orderNoId).html();
		$(orderNoId).html("<input type='text' style='width: 35px; height: 20px;' value='"+orderNo+"'/>");
		$(this).html("保存排序号");
		$(this).removeClass("order_change");
		$(this).addClass("order_change_save");
	}
	//打开修改排序号编辑
	function orderChange(){
		var id = $(this).attr("data-value");
		var orderNoId = "#order_"+id;
		var orderNo = $(orderNoId).find("input").val();
		if(isNaN(orderNo)){
			Tips.showAlertWin({info:"排序号必须为数字！"});
			return;
		}
		//开始保存
		var url = Domain.cms_path+"/homepage/college/updateDisplayNo.htm?id="+id+"&displayNo="+orderNo;
    	$.post(url,null,function(data){
    		if(data){
    			if(data.flag){
    				//刷新
    				queryCollege(true);
    			}else{
    				Tips.showAlertWin({info:data.msg});
    			}
    		}else{
    			Tips.showAlertWin({info:"修改失败请重试!"});
    		}
    	},"json");
	}
	//查询
	function queryCollege(isWithPage){
		var collegeName = $("#collegeName").val();
		var isRecommend = $("#collegeRecommend option:selected").val();
		if(isWithPage){
			window.location.href = Domain.cms_path+"/homepage/college/collegeManage.htm?collegeName="+encodeURI(collegeName)+"&isRecommend="+isRecommend+"&"+getPageDesc();
		}else{
			window.location.href = Domain.cms_path+"/homepage/college/collegeManage.htm?collegeName="+encodeURI(collegeName)+"&isRecommend="+isRecommend;
		}
		
	}
	//新增
	function toAddCollege(){
		window.location.href = Domain.cms_path+"/homepage/college/toAddCollege.htm";
	}
	//修改是否推荐
	function upateRecommend(isRecommend,id){
		var url = Domain.cms_path+"/homepage/college/updateRecommend.htm?id="+id+"&isRecommend="+isRecommend;
    	$.post(url,null,function(data){
    		if(data){
    			if(data.flag){
    				//刷新
    				queryCollege(true);
    			}else{
    				Tips.showAlertWin({info:data.msg});
    			}
    		}else{
    			Tips.showAlertWin({info:"修改失败请重试!"});
    		}
    	},"json");
	}
	//删除
	function deleteCollege(){
		var _this = this;
		var param = {
			info:"确定要删除这条数据吗？",
		    callFn:function(){
		    	var url = Domain.cms_path+"/homepage/college/delCollege.htm?id="+$(_this).attr("data-value");
		    	$.post(url,null,function(data){
		    		if(data){
		    			if(data.flag){
		    				//刷新
		    				queryCollege(true);
		    			}else{
		    				Tips.showAlertWin({info:data.msg});
		    			}
		    		}else{
		    			Tips.showAlertWin({info:"删除失败请重试!"});
		    		}
		    	},"json");
		    }
		}
		Tips.showConfirmWin(param);
	}
	
	//获取分页参数
	function getPageDesc(){
		var currentPage = $("#currentPage").val();
		var rowNum = $("#rowNum").val();
		if(currentPage=="" || rowNum==""){
			return "1=1";
		}
		return "page.currentPage="+currentPage+"&page.rowNum="+rowNum;
	}
})(jQuery,window,document);