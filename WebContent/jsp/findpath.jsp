<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script src="/SpringMVC/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<form id="Form1">
		<p>
			<button onclick="findFile()">执行</button>
		</p>
		<p>path1获取的数据为：</p>

		<p id="path1"></p>
	</form>
	<br />

	<script type="text/javascript">
//		$(document).ready(function() {
			function findFile() {
				//开始加载
				$.ajax({
					url : webpath + '/config/findFile',
					type : 'POST',
					datatype : 'JSON',
					data : {
						'username' : username,
						'password' : password,
						'age' : 20
					}
				}).done(function(data) {
					if (data.success) {
						var path1value = data.path1value;
						$("#path1").Text();

						var path2value = data.path2value;
						$("#path2").Text();

						var path3value = data.path3value;
						$("#path3").Text();

						var path4value = data.path4value;
						$("#path4").Text();

						var path5value = data.path5value;
						$("#path5").Text();
					}

				}).fail(function(err) {
					alert("服务器响应失败!");
				});
			}
//		});
	</script>
</body>

</html>