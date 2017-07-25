(function($, window, document) {
	var pub = {};
	window.footer = pub;
	pub.init = function() {
		bindEvent();
	}
	// 绑定事件
	function bindEvent() {
		isShow();
		//搜索
		$("#searchButton").on("click", search);
		//新增
		$("#newButton").on("click",addFooter);
		//删除分类内容
		$(".info_delete_a").on("click",deleteFooter);
		//保存
		$("#saveBtn").on("click",saveData);
		//显示
		$(".contentType").bind('change',isShow);
		//修改是否显示
		$("a[name='isShow']").bind('click',updateIsShow);
	}
	
	function updateIsShow(){
		var isShow = $(this).attr("data-value");
		var footerId = $(this).attr("dataValue");
		isShow = isShow == 0 ? 1:0;
		var params ={
        		"url":Domain.cms_path+"/homepage/index/updateSortOrIsShow.htm",
        		"data":{'footerId':footerId,
        			'isShow':isShow},
        		"dataType":"json",
        		"fn":function(reply) {
        			if(reply.isSuccess == true){
        				$("#isShow"+footerId).text("").append(isShow == 1 ?"是":"否");
        				$("#modifyIsShow"+footerId).attr("data-value",isShow == 1?1:0);
        				$("#modifyIsShow"+footerId).text("").append(isShow==0?"&nbsp;显示&nbsp;":"不显示&nbsp;");
        			}
		        }
        };
        Sender.ajax(params);
	}
	
	footer.updateSort =function(footerId){
		var td = $("#displayOrder"+footerId);
		var displayOrder = td.text();
		td.text("").append("<input type='text' id='sortValue"+footerId+"' name='sort' value='"+displayOrder+"' style='width:35px;height:20px'>");
		$("#updateSort"+footerId).attr('href',"javascript:footer.saveSort("+footerId+");");
		$("#updateSort"+footerId).text("").append("<span style='color:red;'>保存排序号</span>");
		$("#sortValue"+footerId).change(function(){
			if(Validator.isNotBlank($(this).val()) && !Validator.isNumber($(this).val())){
				Tips.showErrorMsg({"info":"请输入大于0的排序号"});
				$(this).val("").focus();
				return;
			}
		});
	}
	
	footer.saveSort = function(footerId){
		var td = $("#displayOrder"+footerId);
		var sort = $("#sortValue"+footerId).val();
		if(sort < 0 || sort == ''){
			Tips.showErrorMsg({"info":"请输入大于0的排序号"});
			return;
		}
		var isShow = $("#modifyIsShow"+footerId).attr("data-value");
		var params ={
        		"url":Domain.cms_path+"/homepage/index/updateSortOrIsShow.htm",
        		"data":{'footerId':footerId,
        			'sort':sort,
        			'isShow':isShow},
        		"dataType":"json",
        		"fn":function(reply) {
        			if(reply.isSuccess == true){
        				td.text("").append(sort);
        				$("#updateSort"+footerId).attr('href',"javascript:footer.updateSort("+footerId+");");
        				$("#updateSort"+footerId).text("").append("修改排序号");
        			}
		        }
        };
        Sender.ajax(params);
	}
	
	function isShow(){
		var show = $(".contentType:checked").val();
		if(show == "LINK"){
			$("#link_url_tr").show();
			$(".content_tr").hide();
		}else{
			$("#link_url_tr").hide();
			$(".content_tr").show();
		}
	}
	
	function search(){
		$("#footerManage").attr("action", Domain.cms_path + '/homepage/index/footerManage.htm');
		$("#footerManage").submit();
	}
	
	function addFooter(){
		window.location.href = Domain.cms_path+"/homepage/index/editFooter.htm";
	}
	
	function deleteFooter(){
		var id = $(this).attr('dataValue');
		Tips.showConfirmWin({
			'info':"确认删除",
			'callFn':function(){
				var params ={
		        		"url":Domain.cms_path+"/homepage/index/removeFooter.htm",
		        		"data":{'footerId':id},
		        		"dataType":"json",
		        		"fn":function(reply) {
		        			if(reply.isSuccess == true){
		        				FieldMsg.drawMessages(reply, back, null);
		        			}else{
		        				Tips.showErrorMsg({"info":reply.errorMsg});
		        			}
				        }
		        };
		        Sender.ajax(params);
			}
		});
	}
	function saveData(){
		var isSubmit = true;
		if(!Verify.checkNotEmpty("#footerName","标题")){
			isSubmit = false;
		}
		if($(".contentType:checked").val() == "LINK" && !Verify.checkNotEmpty("#linkUrl","链接地址")){
			isSubmit = false;
		}
		if(!Verify.checkNotEmpty("#displayOrder","显示次序")){
			isSubmit = false;
		}
		
		if(!Verify.checkHttp("#linkUrl","链接地址")){
			isSubmit = false;
		}
		
		if(!Verify.checkNotMinusInt("#displayOrder","显示次序")){
			isSubmit = false;
		}
		if(isSubmit == false){
			return;
		}
		if(!Verify.checkAllVerify("#indexFooterForm")){
       		return ;
       	} 
		 //提交数据
		var data = $('#footerForm').serialize();
        Sender.ajax({
    		"url":Domain.cms_path+"/homepage/index/editFooter.htm?oper=o",
    		"data":data,
    		"dataType":"json",
    		"fn":function(reply) {
    			if(reply.isSuccess == true || reply.isSuccess == 'true'){
    				Tips.showSuccessMsg({"info":reply.promptMsg,"overlayer":true,"callFn":back});
    			}else{
    				Tips.showErrorMsg({"info":reply.errorMsg});
    			}
	        }
        });
	}
	
	function back(){
		window.location.href = Domain.cms_path+"/homepage/index/footerManage.htm";
	}
})(jQuery, window, document);