<!doctype html>
<#include "/pages/common/commonmacro.ftl" />
<html>
<head>
<meta charset="utf-8">

</head>

<body >

<div>
    <br>
    <div style="width: 1200px;margin: 0px auto;border:1px solid #ccc">
        <div style="padding: 20px 30px 0px;">
            <div style="padding: 0px 30px;">
                <h1 style="text-align: center;font-weight: 100;font-size: 24px;color: #100707;" title="${indexInfo.title!}"><@cutOff cutStr="${indexInfo.title!}" cutLen="50"/></h1>
                <p style="font-size:12px;padding-top:15px;text-align:center;color:#888;">来源：<a href="${indexInfo.comeUrl!}" target="_blank">${indexInfo.comeFrom!}</a><span style="margin-right: 50px;margin-left: 50px;">时间：${(indexInfo.infoDate?string('yyyy-MM-dd'))!}</span>编辑：${indexInfo.editor!}</p>
                <div >${content!}</div>
            </div>
        </div>
    </div>
    <br>
</div>

</body>
</html>