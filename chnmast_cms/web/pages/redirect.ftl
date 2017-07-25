<!doctype html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>后台管理</title>
</head>
<body>
   <!--<center>请点击<a href="javascript:void(0);" onclick="redirect();">这里</a>进入平台</center>-->
	<script type="text/javascript">
	redirect();
	//防止页面无法调整，页面停留3秒，判定为无法跳转，进行自动跳转
	setTimeout("redirect()", 3000);
	function redirect(){
        window.location.href='/login.htm';
	}
	</script>
</body>
</html>