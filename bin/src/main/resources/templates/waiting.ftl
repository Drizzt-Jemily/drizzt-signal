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
		<#if id =='-99'>
			未授权
		<#elseif id =='-98'>
			号码格式不正确
		<#else>
			<a href="${base}/auth/oneResult/${id}">查询结果</a>
		</#if>
		<br>
		<a href="${base}/" target="_self">返回</a>
	</center>
</body>
</html>