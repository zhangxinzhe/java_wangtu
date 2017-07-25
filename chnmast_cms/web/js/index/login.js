jQuery(function(){
	var winWidth=jQuery(window).width();
	var winHeight=jQuery(window).height();
	var layWidth=jQuery('.layer-tips').width()+10;
	var layHeight=jQuery('.layer-tips').height()+40;
	var layLeft=(winWidth-layWidth)/2;
	var layTop=(winHeight-layHeight)/2;
	jQuery('.layer-tips').css({'top':layTop,'left':layLeft});
	
	jQuery('.layer-tips .btn').click(function(){
		jQuery('.mask-layer,.layer-tips').hide();
	});
	jQuery('.layer-tips .btn-has-yzm').click(function(){
		jQuery('.mask-layer,.layer-tips').hide();
		jQuery('.grid table').addClass('has-yzm');
		jQuery('.grid table tr.hide-tr').show();
	});
});

$('#username').bind("keyup",function(event){
	if(event.keyCode == 13){
		$("#password").focus();
	}
});

$('#password').bind("keyup",function(event){
	if(event.keyCode == 13){
		$("#showLayer").click();
	}
});
$('#showLayer').bind("click",function(){
	if(!validateLogin()){
		return;
	}
	$("#loginForm").submit();
});

function validateLogin(){
	if($("#username").val() == ""){
		$("#errorMsg").html("[用户名不能为空！]");
		return false;
	}
	
	if($("#password").val() == ""){
		$("#errorMsg").html("[密码不能为空！]");
		return false;
	}
	
	//对密码进行md5加密后提交
	$("#loginPassword").val(MD5.encode($("#password").val()));
	
	return true;
}