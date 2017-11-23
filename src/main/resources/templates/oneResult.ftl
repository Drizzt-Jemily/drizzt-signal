<#assign base=request.contextPath />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base id="base" href="${base}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>单个号码测试结果</title>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	<br>
	<center>
		<table border="1">
			<tr>
				<td>手机号码</td>
				<td>状态</td>
			</tr>
				<tr>
					<td>${calling}</td>
					<td>${cr}</td>
				</tr>
		</table><br>
		<a href="${base}/" target="_self">返回</a>
	</center>
</body>
</html>