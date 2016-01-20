function getRootPath() {
	var strFullPath = window.document.location.href;
	var strPath = window.document.location.pathname;
	var pos = strFullPath.indexOf(strPath);
	var prePath = strFullPath.substring(0, pos);
	var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
	return (prePath + postPath);
}
var webpath = getRootPath(); // webpath就是目录路径变量
function login2() {
	var username = $("#input21").val();
	var password = $("#input22").val();
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

function login3() {
	var username = $("#input31").val();
	var password = $("#input32").val();
	$.ajax({
		url : webpath+'/Login/test/login3',
		type : 'POST',
		datatype : 'JSON',
		data : {
			'username' : username,
			'password' : password
		}
	}).done(function(data) {
		if (data['success']) {
			alert("增加组织成功!");
			slideDown(0);
			refreshOrgDetail();
		} else {
			alert("增加组织失败");
		}

	}).fail(function(err) {
		alert("服务器响应失败!");
	});
};
