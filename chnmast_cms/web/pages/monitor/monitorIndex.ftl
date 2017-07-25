<!doctype html>
<head>
    <meta charset="UTF-8">
	<title>web管理</title>
    <#include "/pages/jsinclude/commonjs.ftl" />
    <style type="text/css">
        ul li{
           margin:20px;
        }
        ul li a{
           font-size:18px;
        }
    </style>
    
    <script>
        function cleanCache() {
            var params = {
                "url": "/cleanAllCache.htm",
                "data": {},
                "fn": function(result){
                    if(result==1) {
                        alert("操作成功！");
                    }
                }
            };
            Sender.ajax(params);
        }
    </script>
</head>
<body>
    <div>
        <ul>
            <li><a href="javascript:;" onclick="cleanCache();" >清除所有缓存 </a></li>
            <li><a href="/systemControlView.htm" target="_blank">系统并发控制 </a></li>
        </ul>
    </div>
</body>
</html>