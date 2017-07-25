(function($, window, document){
	var pub = {};
	window.systemUserInfo = pub;
    pub.init = function() {
          $('#updateBtn').bind("click",update);
          $('#addAllBtn').bind("click",addAll);
          $('#removeAllBtn').bind("click",removeAll);
          $('#deleteBtn').bind("click",deleteSystemUser);
          $('#freezeBtn').bind("click",freeze);
          $('#unfreezeBtn').bind("click",unfreeze);
          $('#backBtn').bind("click",back);
          //权限分配部分
          $('#assignBtn').bind("click",assign);
          //数据修改隐藏部分
          $('#saveBtn').bind("click",saveData);
          $('#resetBtn').bind("click",resetPassword);
          $('#cancelBtn').bind("click",cancel);
          
    }
    
    //分配权限
    function assign(){
    	//左侧数据
    	$.ajax({
    		 async:true,
             type:"post",
             url:"system/listSystemRole.htm",
             data:{"userId":$('#userid').val()},
             dataType:"json",
             success:function(result){
            	 $('#leftUl').children("li").remove();
            	 for(var i=0;i<result.length;i++){
            		 var li=$("<li>"+result[i].roleName+"<a href='#'></a><input type='hidden' name='roleIds' value="+result[i].roleId+"></li>");
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
             url:"system/listSystemUserRole.htm",
             data:{"userId":$('#userid').val()},
             dataType:"json",
             success:function(result){
            	 $('#rightUl').children("li").remove();
            	 for(var i=0;i<result.length;i++){
            		 var li=$("<li>"+result[i].roleName+"<a href='#'></a><input type='hidden' name='roleIds' value="+result[i].roleId+"></li>");
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
   	 $('#assignRoleForm').submit();
   }
    
    
    
    //修改数据
    function update(){
    	$('#showDv').css('display','none');
    	$('#updateDv').css('display','');
    }

    //确认修改数据
    function saveData(){
    	FieldMsg.clearFieldError("#updateForm"); //清除各字段的报错提示
    	//用于正确信息判断
    	if(Verify.checkNotEmpty("#userName", "用户名") && Verify.checkUserName("#userName", "用户名")){ 
    		FieldMsg.addFieldSuccess("#userName"); 		
    	}
    	if(Verify.checkNotEmpty("#realName", "姓名")){ 
    	   	FieldMsg.addFieldSuccess("#realName"); 		
    	}
    	
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#updateForm")) return;
       	var data=$('#updateForm').serialize();
       	 var params ={
        		"url":Domain.cms_path+"/system/editSystemUser.htm",
        		"data":data,
        		"dataType":"json",
        		"fn":function(reply) {
        			FieldMsg.drawMessages(reply, errorGo, null);
		        }
        };
        Sender.ajax(params);
    }
    //重置密码
    function resetPassword() {
    	$.ajax({
    		async:true,
            type:"post",
            url:"system/resetSystemUserPassword.htm",
            data:{"userId":$('#userid').val()},
            dataType:"text",
            success:function(data){
            		var param={info:data,
                			overlayer:true,
                			callFn:null
                	    	};
            	Tips.showSuccessMsg(param);
            }
    	});
	}
    //取消修改数据
    function cancel(){
    	$('#showDv').css('display','');
    	$('#updateDv').css('display','none');
    }
   
    //删除用户
    function deleteSystemUser(){
    	var param={info:"确定要删除该用户吗？",
    			overlayer:true,
    			callFn:function(){
    				 var params ={
 			         		"url":Domain.cms_path+"/system/delSystemUser.htm",
 			         		"data":{"userId":$('#userid').val()},
 			         		"dataType":"json",
 			         		"fn":function(reply) {
 			         			FieldMsg.drawMessages(reply, back, errorGo);
 			 		        }
 			         };
 			         Sender.ajax(params);
    			}
    	    	};
    	Tips.showConfirmWin(param);
    }
    //返回按钮
    function back(){
    	location.href =Domain.cms_path+"/system/systemUserManage.htm";
    }
    //失败调用
    function errorGo(){
    	location.href =Domain.cms_path+"/system/viewSystemUser.htm?userId="+$('#userid').val();
    }
    //冻结用户
    function freeze(){
    	$.ajax({
    		async:true,
            type:"post",
            url:"system/cancelSystemUser.htm",
            data:{"userId":$('#userid').val()},
            dataType:"text",
            success:function(data){
        		var param={info:data,
            			overlayer:true,
            			callFn:function(){
            				location.href =Domain.cms_path+"/system/viewSystemUser.htm?userId="+$('#userid').val();
            			}
            	    	};
        	Tips.showSuccessMsg(param);
        }
    	});
    }
    //解冻用户
    function unfreeze(){
    	$.ajax({
    		async:true,
            type:"post",
            url:"system/backSystemUser.htm",
            data:{"userId":$('#userid').val()},
            dataType:"text",
            success:function(data){
        		var param={info:data,
            			overlayer:true,
            			callFn:function(){
            				location.href =Domain.cms_path+"/system/viewSystemUser.htm?userId="+$('#userid').val();
            			}
            	    	};
        	Tips.showSuccessMsg(param);
        }
    	});
    }
})(jQuery, window, document);