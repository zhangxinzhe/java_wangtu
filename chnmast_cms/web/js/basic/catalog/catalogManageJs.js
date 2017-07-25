(function($,window,document,undefined){
	var b = {};
	window.CatologManage = b;
	b.init = function(){
		bindEvent();
	}
	function bindEvent(){
		//新增
		$("#newButton").on("click",function(){
			var cname = $.trim($("#cname").val());
			if(cname == ""){
				Tips.showErrorMsg({"info":"请输入分类名称!"});
		        return ;
			}
			$("#catalogForm").submit();
		});
		//修改
		$(".catalogEdit").on("click",function(){
			var $this = $(this);
			var html = $this.html();
			var tdId = $this.attr("val");
			var item = $("#cname_"+tdId);
			if(html == "修改"){
				$this.html("取消");
				item.html("<input class='input-txt' id='cname_item_"+tdId+"' value='"+item.attr("val")+"'/>");
				$("#save_btn_"+tdId).show();
			}else{
				$this.html("修改");
				item.html(item.attr("val"));
				$("#save_btn_"+tdId).hide();
			}
		});
		//保存修改
		$(".saveCatalog").on("click",function(){
			var tdId = $(this).attr("val");
			var cname = $("#cname_item_"+tdId).val();
			if($.trim(cname)==""){
				Tips.showErrorMsg({"info":"请输入分类名称!"});
				return;
			}
			window.location.href = "/basic/editCatalog.htm?id="+tdId+"&cname="+encodeURI(cname);
		});
		//删除
		$(".catalogDelete").on("click",function(){
			var tdId = $(this).attr("val");
			
			window.location.href = "/basic/deleteCatalog.htm?id="+tdId;
		});
	}
})(jQuery,window,document);