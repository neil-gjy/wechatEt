<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">

<%@ include file="/common/wx-header.jsp"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/js-lib.jsp"%>

</head>

<body class="hold-transition" >
	<div class="wrapper col-md-12 col-sm-12" >
		<div class="content-wrapper" >
		<section>
		<div class="row col-md-8 col-sm-8">	
			<!-- <nav class="navbar navbar-fixed-top transparent" style="background: transparent none repeat scroll 0% 0%;"> -->
			<div class="row">
				<div class="col-md-8 col-sm-8 " style="margin-top: 10px">
					<form  id="loginForm">
						<div class="row">
							<div class="form-group has-feedback col-md-6">
								<span class="glyphicon glyphicon-user form-control-feedback"></span>
						        <input type="text" id="username" name="username" class="form-control" placeholder="用户名">
						     </div>
					     </div>
					     <div class="row">
						     <div class="form-group has-feedback col-md-6">
						     	<span class="glyphicon glyphicon-lock form-control-feedback"></span>
						        <input type="password" id="password" name="password" class="form-control" placeholder="密码">
						     </div>
					     </div>
					     <div class="row">
						      <div class="form-group has-feedback col-md-6" style="display:none">
						     	<span class="glyphicon glyphicon-lock form-control-feedback"></span>
						        <input id="openid" name="openid" class="form-control">
						     </div>
					     </div>
					     
					</form>
					<div class="form-group has-feedback col-md-6">
				     	<button type="submit" class="btn btn-success btn-block btn-flat bg-maroon" style="margin-right: 10px" id="logInBtn">登陆</button>
		             </div>
		             <div class="form-group has-feedback col-md-6">
				     	<label style="font-size:12px;color:white;margin-bottom: 0;padding: 8px 3px 6px 0px;text-align: center;vertical-align: middle;white-space: nowrap;" id="logInMsg"></label>
		             </div>
				</div>
			</div>

			
		 </div>
		 </section>
 	</div>
	<!-- /.login-box -->
	<footer class="main-footer">
	    <div class="pull-right hidden-xs"><strong><small>版本 1.0</small></strong></div>
				<!-- Default to the left -->
				<p>
					<small><strong>天津港信息技术发展有限公司</strong> 版权所有</small>
				</p>
				<p>
					 <small><strong>  Copyright &copy; 2016 Tianjin Port Information
					Technology Co.,Ltd. All rights reserved.</strong></small>
				</p>
	   
   </footer>
  </div>
<script type="text/javascript">
  var openid = "${openid}";
//登陆
	$(document).ready(function() {

    $('#openid').val(openid); 
	
	$('#logInBtn').click(function() {
		var label = $("#logInMsg");
		//提交表单
		var userName = $("#username").val();
		var userPassword = $("#password").val();
		if (userName == "") {
			label.html("请输入用户名");
			return false;
		}
		if (userPassword == "") {
			label.html("请输入密码");
			return false;
		}
		$("#loginForm").ajaxSubmit({
			type : "post",
			url: baseUrl + "/wechat/bind/bindUser",
			dataType : "json",
			success : function(msg) {
				if (msg) {
					if (msg.code) {
						label.html(msg.msg);
						//window.location.href = baseUrl + "/home";
					} else {
						//box.html(msg.msg);
					}
				} else {
					label.html(msg.msg);
				}
			},
			error : function() {
				label.html("网络连接错误，请重试！");
			}
		});
		// 为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false
		return false;
	});

	$('#username').focus().keyup(function(event) {
		event.preventDefault();
		if (event.which == 13)
			$('#password').focus();
	});
	$('#password').keyup(function(event) {
		event.preventDefault();
		if (event.which == 13)
			$("#logInBtn").ajaxSubmit();
	});
});
</script>
</body>
</html>