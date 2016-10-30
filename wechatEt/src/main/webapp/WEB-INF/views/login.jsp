<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>天津港散杂货费收综合管理系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="/common/wx-header.jsp"%>
  <%@ include file="/common/wx-js-lib.jsp"%>
  <%@ include file="/common/taglibs.jsp"%>
  
  <link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/login.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!-- <[if lt IE 9]> -->
  <script src="${ctx}/assets/js/html5shiv.min.js"></script>
  <script src="${ctx}/assets/js/respond.min.js"></script>
  <!-- <![endif]> -->
</head>
<body class="hold-transition" >
	<div class="wrapper" >
		<div class="content-wrapper" >
		<section >
	
	
		<div class="row bg" style="height:450px">	
			<!-- <nav class="navbar navbar-fixed-top transparent" style="background: transparent none repeat scroll 0% 0%;"> -->
			<div class="row">
				<div class="col-md-1 col-sm-1">
					<%-- <img src="${ctx}/assets/imgs/tjport.jpg" class="logo-image" alt="User Image"> --%>
				</div>
				<div class="col-md-5 col-md-offset-6 col-sm-5 col-sm-offset-6" style="margin-top: 10px">
					<form  id="loginForm">
						<div class="form-group has-feedback col-md-4">
							<span class="glyphicon glyphicon-user form-control-feedback"></span>
					        <input type="text" id="username" name="username" class="form-control" placeholder="用户名">
					     </div>
					     <div class="form-group has-feedback col-md-4">
					     	<span class="glyphicon glyphicon-lock form-control-feedback"></span>
					        <input type="password" id="password" name="password" class="form-control" placeholder="密码">
					     </div>
					     <div class="form-group has-feedback col-md-2">
					     	<button type="submit" class="btn btn-success btn-block btn-flat bg-maroon" style="margin-right: 10px" id="logInBtn">登陆</button>
			             </div>
					</form>
				</div>
			</div>
			 
			<div class="container">
				<p class="tjg-bt">天津港散杂货费收综合管理系统</p>
			</div>

			
		 </div>
		 <div class="row bottom-icon">
		 	<div class="col-md-2 row-center">
		 		<i class="fa fa-ship text-red fa-size"></i>
	        	<h3>航次管理</h3>
	        	<p>船舶添加、停泊费计算等功能</p>
		 	</div>
		 	<div class="col-md-2 row-center">
		 		<i class="fa fa-truck text-green fa-size"></i>
	        	<h3>舱单管理</h3>
	        	<p>舱单、陆运单等费用计算</p>
		 	</div>
		 	<div class="col-md-2 row-center">
		 		<i class="fa fa-money text-yellow fa-size"></i>
	        	<h3>发票管理</h3>
	        	<p>发票生成、作废等功能</p>
		 	</div>
		 	<div class="col-md-2 row-center">
		 		<i class="fa fa-cny text-blue fa-size"></i>
	        	<h3>费率管理</h3>
	        	<p>基价、优惠合同等费率维护</p>
		 	</div>
		 	<div class="col-md-2 row-center">
		 		<i class="fa fa-bar-chart text-purple fa-size"></i>
	        	<h3>报表分析</h3>
	        	<p>手动、自动生成统计报表</p>
		 	</div>
		 	
		 	<div class="col-md-2 row-center">
		 		<i class="fa fa-mobile-phone text-beige fa-size"></i>
	        	<h3>移动APP</h3>
	        	<p>费率、报表等数据查询</p>
		 	</div>
	 	</div>
	
	 
 	</section>
 	</div>
	<!-- /.login-box -->
	<footer class="main-footer">
	    <div class="pull-right hidden-xs"><strong><small>版本 1.0</small></strong></div>
				<!-- Default to the left -->
	    <strong><small><strong>天津港信息技术发展有限公司</strong> 版权所有  Copyright &copy; 2016 Tianjin Port Information
					Technology Co.,Ltd. All rights reserved.</small></strong>
   </footer>
  </div>
<script>
//登陆
$(document).ready(function() {

	$('#logInBtn').click(function() {
		//提交表单
		var userName = $("#username").val();
		var userPassword = $("#password").val();
		var label = document.getElementById("rInfo");
		if (userName == "") {
			label.innerText = "请输入用户名";
			return false;
		}
		if (userPassword == "") {
			label.innerText = "请输入密码";
			return false;
		}
		
		$("#loginForm").ajaxSubmit({
			type : "post",
			url: baseUrl + "/login",
			dataType : "json",
			success : function(msg) {
				if (msg) {
					if (msg.code) {
						//box.html(msg.msg);
						window.location.href = baseUrl + "/home";
					} else {
						//box.html(msg.msg);
					}
				} else {
					//box.html("发生异常！");
				}
			},
			error : function() {
				box.html("网络连接错误，请重试！");
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