<%@ page language="java" contentType="text/html; UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Insert title here</title>
</head>
<body>
	OH!MYGOD!你请求的网页不存在！
<p>
<button id="gologin" type="button" onclick="gologin()">跳转到登录页面1
</button>
</br>
</br>
<button id="gologin" type="button" onclick="gologin()">跳转到登录页面2
</button>
</p>	
 <script type="text/javascript">
 function gologin(){
		window.location.href='/SpringMVC/mainlogin/test/login';
	};
 </script>
  <script type="text/css">

 </script>
</body>

</html>