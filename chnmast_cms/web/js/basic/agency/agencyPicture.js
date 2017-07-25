(function($, window, document){
	var pub = {};
	window.AgencyPicture = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
        $("#checkAll").bind("click", allCheckbox);
        $("#removeButton").bind("click", delAgency);
    }
    
    //全选
    function allCheckbox(){
    	if($(this).attr('checked') == 'checked' || $(this).attr('checked') == true){
    		$("input[name='pictureIdsCheckbox']").attr("checked", true); 
    	}else{
    		$("input[name='pictureIdsCheckbox']").attr("checked", false); 
    	}
    }
    
    //删除
    function delAgency(){
    	var pictureIds = new Array();
  		$("input[name='pictureIdsCheckbox']").each(function(){
    		if(this.checked) {
    			pictureIds.push(this.value);
    		}
    	});
  		if(pictureIds == '') {
          Tips.showErrorMsg({"info":"请先选择展示图片!"});
          return ;
        }
  		Tips.showConfirmWin({"info":"确定要删除所选展示图片吗？","callFn": function(){
  			var params ={
        		"url":"/basic/delPicture.htm",
        		"data":{idStr : pictureIds.toString()},
        		"fn":function(result) {
        			if(result != '') {
        				Tips.showErrorMsg({"info":result});
						return ;
					}else{
						Tips.showSuccessMsg({"info":"删除展示图片成功!"});
					}
        			setTimeout(function(){
        				window.location.href = Domain.cms_path + '/basic/agencyPicture.htm?agencyId=' + $("#agencyId").val();
        			}, 2000);
		        }
            };
            /**ajax提交数据*/
            Sender.ajax(params);
  		}});
    }
    
})(jQuery, window, document);