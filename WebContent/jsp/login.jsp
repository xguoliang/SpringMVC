<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script src="<%=basePath%>/js/login.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<form id="Form1" action="<%=basePath %>/Login/test/login1.do" method="post">
		First name1: <input type="text" name=username /> </br> Last name1: <input
			type="text" name="password" /> </br> <input type="submit" value="登录login1" />
	</form>
	
	</br>
	<form id="Form2">
		First name2: <input id="input21" type="text" name="username" />
		</br> Last name2: <input id="input22" type="text" name="password" />
		</br> <input type="button" id="button1" value="登录login2" onclick="login2()" />
	<a>以form</a>
	</form>
	</br>
	<form id="myForm3">
		First name3: <input id="input31" type="text" name="username" />
		</br> Last name3: <input id="input32" type="text" name="password" />
		</br> <input type="button" id="button2" value="登录login3" onclick="login3()" />
	</form>
	</br>
	
	<form id="myForm4">
			URL: <input id="url" type="text" name="urlname"  size="100"/>
		</br>
		 	PARAMS: <input id="params" type="text" name="paramsname" />
		</br> 
		 	TYPE: <input id="type" type="text" name="typename" />
		</br> 
		<input type="button" id="sent" value="发送请求" onclick="sent()" />
	</form>
	</br>
	
</body>
</html>