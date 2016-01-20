function getRootPath() {
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath);
	var prePath = strFullPath.substring(0, pos);
	var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	return (prePath + postPath);
}
var webpath = getRootPath(); // webpath就是目录路径变量
function send() {
	var param1 = $("#param1").val();
	var paramvalue1 = $("#paramvalue1").val();
	$.ajax({
		url : webpath+'/Login/test/login2.do',
		type : 'POST',
		datatype : 'JSON',
		data : {
			'username' : username,
			'password' : password,
			'age' : 20
		}
	}).done(function(data) {
		document.write(data);
	}).fail(function(err) {
		alert("服务器响应失败!");
	});
};
