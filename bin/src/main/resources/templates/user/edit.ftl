<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑用户</title>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	
	<center>
		<form action="/user/edit" method="post">
			<input type="hidden" name="id" value="${user.id}" /><br><br>
			<br> 密码：<br>
			<br> <input type="text" name="pwd" size="100" /><br><br>
			<br> 用户账号：<br>
			<br> ${user.id}<br>
			<br> 剩余条数： <br>
			<br> <input type="text" name="number" size="100" value="${user.number?c}" /><br>
			<br> 回传URL： <br>
			<br> <input type="text" name="url" size="100" value="${(user.url)!}" /><br>
			<input type="submit" value="提交" />
		</form>
	</center>
</body>
</html>