<#assign base=request.contextPath />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base id="base" href="${base}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>等待页面</title>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<center>
		<#if batchId =='-99'>
			未授权
		<#elseif batchId =='-98'>
			文件格式不正确
		<#else>
			您有以下行数的数据格式不正确： <br>
			<#list is as i>
				${i},
			</#list>
			<br>
			<br>
			<a href="${base}/auth/batchResult/${batchId}">查询结果</a>
		</#if>
		<br>
		<a href="${base}/batch" target="_self">返回</a>
	</center>
</body>
</html>