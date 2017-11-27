<#assign base=request.contextPath />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base id="base" href="${base}">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量测试结果</title>
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
				<#list batchResponses as batchResponse>
				<tr>
					<td>${batchResponse.calling}</td>
					<td>${batchResponse.callResult}</td>
				</tr>
				</#list>
		</table><br>
		<a href="${base}/batch" target="_self">返回</a>
	</center>
</body>
</html>