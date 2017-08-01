(function($,window,document,undefined){
	var b = {};
	window.AccountManage = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		$("#searchButton").on("click",searchAccount);
		$(".accountUpdate").on("click",updateAccount);
		$(".updateCancel").on("click",cancelUpdate);
	}
	function searchAccount(){
		var userName = $("#userName").val();
		var realName = $("#realName").val();
		window.location.href = "/account/accountManage.htm?userName="+encodeURI(userName)+"&realName="+encodeURI(realName);
	}
	function updateAccount(){
		var $this = $(this);
		var id = $this.attr("val");
		var htmlStr = $this.html();
		if(htmlStr == "修改"){
			$this.next().show();
			$this.html("保存");
			var $funds = $("#funds_"+id);
			$funds.html("<input type='text' class='input-txt' style='width:80px' id='funds_txt_"+id+"' value='"+$funds.attr("val")+"'/>");
		}else{
			var fund = $("#funds_txt_"+id).val();
			if(isNaN(fund)){
				Tips.showErrorMsg({"info":"请输入正确的数字!"});
				return;
			}
			if(fund<0){
				Tips.showErrorMsg({"info":"余额必须大于0!"});
				return;
			}
			var url = "/account/updateAccount.htm?accountId="+id+"&founds="+fund;
			$.post(url,null,function(result){
				if(result.msg=="success"){
					window.$("#searchButton").click();
				}else{
					Tips.showErrorMsg({"info":result.msg});
				}
			},"json");
		}
	}
	function cancelUpdate(){
		var $this = $(this);
		var id = $this.attr("val");
		var $funds = $("#funds_"+id);
		$this.hide();
		$this.prev().html("修改");
		$funds.html("￥"+$funds.attr("val"));
	}
})(jQuery,window,document);