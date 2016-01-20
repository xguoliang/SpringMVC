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
<script src="<%=basePath%>/js/send.js" type="text/javascript"></script>
<title>Insert title here</title>
</head>
<body>
	<form id="myForm4">
			URL: <input id="url" type="text" name="urlname"  size="100"/>
			</br>
		</br>
		 	ParamName1: <input id="paramname1" type="text" name="paramname1" />
		 	Paramvalue1: <input id="paramvalue1" type="text" name="paramvalue1" />
		 	
		</br>
		 	ParamName2: <input id="paramname2" type="text" name="paramname2" />
		 	Paramvalue2: <input id="paramvalue2" type="text" name="paramvalue2" />
		 	
		</br>
		 	ParamName3: <input id="paramname3" type="text" name="paramname3" />
		 	Paramvalue3: <input id="paramvalue3" type="text" name="paramvalue3" />
		 	
		</br>
		 	ParamName4: <input id="paramname4" type="text" name="paramname4" />
		 	Paramvalue4: <input id="paramvalue4" type="text" name="paramvalue4" />
		</br>
		 	ParamName5: <input id="paramname5" type="text" name="paramname5" />
		 	Paramvalue5: <input id="paramvalue5" type="text" name="paramvalue5" />
		 	
		</br>
		 	ParamName6: <input id="paramname6" type="text" name="paramname6" />
		 	Paramvalue6: <input id="paramvalue6" type="text" name="paramvalue6" />		 	
		 	
		 	
		</br>
		 
		<select id="type">
			<option selected value="XXX-FORM">XXX-FORM</option>
  			<option value="JSON">JSON</option>
		</select>
		
		</br> 
		</br> 
		<input type="button" id="sent" value="发送请求" onclick="sent()" />
	</form>
	</br>

</body>
</html>