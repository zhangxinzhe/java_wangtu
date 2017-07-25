(function($, window, document){
	var pub = {};
	window.updateSystemRole = pub;
    pub.init = function() {
          $('#saveBtn').bind("click",saveData);
          $('#cancelBtn').bind("click",cancel);
    }
    

    //确认修改数据
    function saveData(){
    	FieldMsg.clearFieldError("#updateForm"); //清除各字段的报错提示
    	//用于正确信息判断
    	if(Verify.checkNotEmpty("#roleName", "角色名")){ 
    	   	FieldMsg.addFieldSuccess("#roleName"); 		
    	  }
    	if(Verify.checkNotEmpty("#description", "描述")){ 
    		FieldMsg.addFieldSuccess("#description"); 		
    	}
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#updateForm")) return; 
      //调用静态树里的getCheckedNodeIds方法获取所有选中的节点id,并赋值给input
       	var checkedNodeIds=getCheckedNodeIds();
       	if(null!=checkedNodeIds){
       		$('#mod').val(checkedNodeIds);
       		var data = $('#updateForm').serialize();
            var params ={
            		"url":Domain.cms_path+"/system/editSystemRole.htm",
            		"data":data,
            		"dataType":"json",
            		"fn":function(reply) {
            			FieldMsg.drawMessages(reply, cancel, null);
    		        }
            };
            Sender.ajax(params);
       	}
    }
    
    //取消修改数据
    function cancel(){
    	location.href =Domain.cms_path+"/system/showSystemRole.htm?roleId="+$('#roleId').val();
    }
   
})(jQuery, window, document);