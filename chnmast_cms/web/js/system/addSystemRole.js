(function($, window, document){
	var pub = {};
	window.addSystemRole = pub;
    pub.init = function() {
        bindEvent();
    }
    //绑定页面事件方法
    function bindEvent() {
        $("#saveBtn").bind("click", saveData);
        $("#backBtn").bind("click", callBack);
    }
    
  //保存数据,数据校验
    function saveData(){
    	FieldMsg.clearFieldError("#creatForm"); //清除各字段的报错提示
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#creatForm")) return; 
        //调用静态树里的getCheckedNodeIds方法获取所有选中的节点id,并赋值给input
       	var checkedNodeIds=getCheckedNodeIds();
       	if(null!=checkedNodeIds){
       		$('#mod').val(checkedNodeIds);
   			var data = $('#creatForm').serialize();
	        var params ={
         		"url":Domain.cms_path+"/system/addSystemRole.htm",
         		"data":data,
         		"dataType":"json",
         		"fn":function(reply) {
         			FieldMsg.drawMessages(reply, callBack, null);
 		        }
	        }
	        Sender.ajax(params);
       	}
    }
    
    
    //返回
    function callBack(){
    	 window.location.href=Domain.cms_path+"/system/systemRoleManage.htm";
    }
   
})(jQuery, window, document);