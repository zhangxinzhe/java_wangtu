<!doctype html>
<head>
    <meta charset="UTF-8">
	<title>web管理-并发控制</title>
	<style type="text/css">
         table,body,div,img,input,a{
            margin:0px;
            padding:0px;
         }
         .wrap{
            margin:0 auto;
            width:900px;
         }
         .table{
              border:1px solid;
              width:100%;
          }
          td{
              border:1px solid;
          }
          td.title{
              background-color:#cccccc;
              text-align:right;
              width:100px;
          }
          .gray{
               color:gray;
               font-size:13px;
          }
       </style>
</head>
<body>
    <div>
        <#if controlMap?exists>
        <table width="100%">
            <tr width="100%">
                <td width="30%">并发控制项</td>
                <td>并发控制内容（操作时间|操作用户名|操作类名|方法名|控制项名称|操作时长）</td>
                <td width="5%">操作</td>
            </tr>
            <#list controlMap?keys as key> 
               <tr>
                   <td>${key}</td>
                   <td>${controlMap.get(key)}</td>
                   <td><a href="/removeSystemControlItem.htm?controlKey=${key}" >清除 </a></td>
               </tr>
            </#list>
        </table>
        <br/>
        <br/>
        <a href="/removeSystemControlItem.htm?controlKey=all" >清除所有并发控制项 </a>
        </#if>
    </div>
</body>
</html>