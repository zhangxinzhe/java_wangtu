(function($, window, document){
	var pub = {};
	window.TeacherStyle = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
        $("#checkAll").bind("click", allCheckbox);
        $("#removeButton").bind("click", delTeacher);
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
    function delTeacher(){
    	var pictureIds = new Array();
  		$("input[name='pictureIdsCheckbox']").each(function(){
    		if(this.checked) {
    			pictureIds.push(this.value);
    		}
    	});
  		if(pictureIds == '') {
          Tips.showErrorMsg({"info":"请先选择要删除的教师风采!"});
          return ;
        }
  		Tips.showConfirmWin({"info":"确定要删除所选教师风采吗？","callFn": function(){
  			var params ={
        		"url":"/basic/delPicture.htm",
        		"data":{idStr : pictureIds.toString()},
        		"fn":function(result) {
        			if(result != '') {
        				Tips.showErrorMsg({"info":result});
						return ;
					}else{
						Tips.showSuccessMsg({"info":"删除教师风采成功!"});
					}
        			setTimeout(function(){
        				window.location.href = Domain.cms_path + '/basic/teacherStyle.htm?teacherId=' + $("#teacherId").val();
        			}, 2000);
		        }
            };
            /**ajax提交数据*/
            Sender.ajax(params);
  		}});
    }
    
    pub.openVideo = function(id){
        var url = Domain.cms_path + "/basic/openVideo.htm";
        var params = {
			url : url,
			data : {id : id},
			fn : function(content) {
				Tips.showDialog('', content, function() {
				}, true, "600", null, null);
				$(".close").bind("click", function() {
					$(".popUp-layer").remove();
				});
			}
		};
		Sender.ajax(params);
    }
    
})(jQuery, window, document);