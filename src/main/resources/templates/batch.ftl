<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量测试</title>
</head>
<body>
	<br>
	<br>
	<br>
	<br>
	
	<center>
		<form action="auth/batch" method="post" enctype="multipart/form-data">
			<br> 密码：<br>
			<br> <input type="text" name="pwd" size="100" /><br><br>
			<br> 请上传TXT格式文件，文件中每一行只能放一个号码： <br>
			<br> <input type="file" name="file" size="100" /><br><br>
			<input type="submit" value="验证" />
		</form>
	</center>
</body>
</html>