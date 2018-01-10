<#assign base=request.contextPath />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base id="base" href="${base}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
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
				<td>用户账号</td>
				<td>剩余条数</td>
				<td>注册时间</td>
				<td>回传URL</td>
				<td width="50px">编辑</td>
			</tr>
				<#list all as user>
				<tr>
					<td>${user.id}</td>
					<td>${user.number?c}</td>
					<td>${(user.registerTime?string('yyyy.MM.dd HH:mm:ss'))!}</td>
					<td>${(user.url)!}</td>
					<td><a href="${base}/user/toEdit/${user.id}" target="_self">编辑</a></td>
				</tr>
				</#list>
		</table><br>
		<a href="${base}/user/toAdd" target="_self">添加用户</a>
	</center>
</body>
</html>