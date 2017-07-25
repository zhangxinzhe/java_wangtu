(function($, window, document){
	var pub = {};
	window.systemRoleInfo = pub;
    pub.init = function() {
    	  $('#assignBtn').bind("click",assign);
          $('#addAllBtn').bind("click",addAll);
          $('#removeAllBtn').bind("click",removeAll);
          $('#updateBtn').bind("click",update);
          $('#deleteBtn').bind("click",deleteSystemRole);
          $('#backBtn').bind("click",back);
    }
    
    //分配权限
    function assign(){
    	//左侧数据
    	$.ajax({
    		 async:true,
             type:"post",
             url:"system/systemUsers.htm",
             data:{"roleIds":$('#roleId').val()},
             dataType:"json",
             success:function(result){
            	 $('#leftUl').children("li").remove();
            	 for(var i=0;i<result.length;i++){
            		 var li=$("<li>"+result[i].userName+"<a href='#'></a><input type='hidden' name='userIds' value="+result[i].id+"></li>");
            		 $('#leftUl').append(li);
            	 }
             	$('.org-list li').hover(function(){
        	        $(this).addClass('hover');
            	 },function(){
        	        $(this).removeClass('hover');
            	 });
             	$('#leftUl').on('click','li a',function(){
            		$(this).parent('li').appendTo( $('#rightUl'));
            		$('#rightUl').find('li').removeClass('hover');
            	});
             }
    	});
    	//右侧数据
    	$.ajax({
    		 async:true,
             type:"post",
             url:"system/userList.htm",
             data:{"roleIds":$('#roleId').val()},
             dataType:"json",
             success:function(result){
            	 $('#rightUl').children("li").remove();
            	 for(var i=0;i<result.length;i++){
            		 var li=$("<li>"+result[i].userName+"<a href='#'></a><input type='hidden' name='userIds' value="+result[i].id+"></li>");
            		 $('#rightUl').append(li);
            	 }
            	 $('.org-list li').hover(function(){
         	        $(this).addClass('hover');
             	 },function(){
         	        $(this).removeClass('hover');
             	 });
            	 $('#rightUl').on('click','li a',function(){
             		$(this).parent('li').appendTo( $('#leftUl'));
             		$('#leftUl').find('li').removeClass('hover');
             	});
             }
    	});
    	
    	
    	var param={div:"#selectLayer",
				closeObject:"#selectLayer .close,#selectLayer .submit,#selectLayer .reset",
				scroll:false,
				className:null,
				closeHandler:assignForSave 
				}
    	Box.showDiv(param);
    }
    
    function addAll(){
    	$('#rightUl').append($('#leftUl').children("li"));
    	$('#leftUl').children("li").remove();
    }
    function removeAll(){
    	$('#leftUl').append($('#rightUl').children("li"));
    	$('#rightUl').children("li").remove();
    }
    function assignForSave(){
   	 $('#assignUserForm').submit();
   }
    
    
    
//修改数据
    function update(){
    	location.href =Domain.cms_path+"/system/editSystemRole.htm?roleId="+$('#roleId').val();
    }
   
    //删除用户
    function deleteSystemRole(){
    	var param={info:"确定要删除该角色吗？",
    			overlayer:true,
    			callFn:function(){
    				 var params ={
    			         		"url":Domain.cms_path+"/system/delSystemRole.htm",
    			         		"data":{"roleIds":$('#roleId').val()},
    			         		"dataType":"json",
    			         		"fn":function(reply) {
    			         			FieldMsg.drawMessages(reply, back, null);
    			 		        }
    			         };
    			         Sender.ajax(params);
    			}
    	    	};
    	Tips.showConfirmWin(param);
    }
  function back(){
	  location.href =Domain.cms_path+"/system/systemRoleManage.htm";
  }
    
})(jQuery, window, document);