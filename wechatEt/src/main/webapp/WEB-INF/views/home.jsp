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
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!-- <[if lt IE 9]> -->
<script src="${ctx}/assets/js/html5shiv.min.js"></script>
<script src="${ctx}/assets/js/respond.min.js"></script>
<!-- <![endif]> -->

</head>
<body class="hold-transition skin-blue-light sidebar-mini">
	<div class="wrapper">

		<!-- Main Header -->
		<header class="main-header">

			<!-- Logo -->
			<a href="#" class="logo"> <!-- mini logo for sidebar mini 50x50 pixels -->
				<!-- <span class="logo-mini"><i class="glyphicon glyphicon-check"></i></span>
				logo for regular state and mobile devices <span
				class="logo-lg"><i class="glyphicon glyphicon-check"></i> 隐患排查治理 </span> -->
			</a>

			<!-- Header Navbar -->
			<nav class="navbar navbar-static-top" role="navigation">
				<!-- Sidebar toggle button-->
				<a href="#" class="sidebar-toggle" data-toggle="offcanvas"
					role="button"> <span class="sr-only">Toggle navigation</span>
				</a>

				<div class="navbar-container">
					<div class="navbar-header pull-left">
						<a href="#" class="navbar-brand"> <b> 天津港微信管理系统 </b>
						</a>
						<!-- /.brand -->
					</div>
					<!-- /.navbar-header -->

				</div>
				<!-- /.container -->
				
				<!-- Navbar Right Menu -->
				<div class="navbar-custom-menu">
					<ul class="nav navbar-nav">
						<!-- 报警信息  隐患不排查，系统能觉察 -->
						<li class="dropdown notifications-menu">
				            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
				              <i class="glyphicon glyphicon-exclamation-sign text-yellow" style="font-size:1.2em"></i>
				              <span class="label label-warning">3</span>
				            </a>
				            <ul class="dropdown-menu">
				              <li class="header">3条 无排查记录公司 </li>
				              <li>
				                <!-- inner menu: contains the actual data -->
				                <ul class="menu">
				                  <li>
				                    <a href="#">
				                      <i class="fa fa-warning text-red"></i> 五公司 13天内无排查记录
				                    </a>
				                  </li>
				                  <li>
				                    <a href="#">
				                      <i class="fa fa-warning text-yellow"></i> 欧亚国际 8天内无排查记录
				                    </a>
				                  </li>
				                  <li>
				                    <a href="#">
				                      <i class="fa fa-warning text-green"></i> 二公司 4天内无排查记录
				                    </a>
				                  </li>
				                </ul>
				              </li>
				              <li class="footer"><a href="#">查看全部</a></li>
				            </ul>
				          </li>
						
						<!-- Notifications Menu -->
						
						<!-- Tasks Menu -->
						<!-- 报警信息  超期未整改 -->
						<li class="dropdown notifications-menu">
				            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
				              <i class="glyphicon glyphicon-bell text-red" style="font-size:1.2em"></i>
				              <span class="label label-warning">4</span>
				            </a>
				            <ul class="dropdown-menu">
				              <li class="header">4条 超期未整改隐患</li>
				              <li>
				                <!-- inner menu: contains the actual data -->
				                <ul class="menu">
				                  <li>
				                    <a href="#">
				                      <i class="fa fa-warning text-red"></i> 集装箱 4条超期未整改隐患
				                    </a>
				                  </li>
				                  <li>
				                    <a href="#">
				                      <i class="fa fa-warning text-red"></i> 欧亚国际 2条超期未制定整改方案
				                    </a>
				                  </li>
				                  <li>
				                    <a href="#">
				                      <i class="fa fa-warning text-yellow"></i> 二公司 1条超期未整改隐患
				                    </a>
				                  </li>
				                  <li>
				                    <a href="#">
				                      <i class="fa fa-warning text-yellow"></i> 远航矿石 1条超期未整改隐患
				                    </a>
				                  </li>
				                </ul>
				              </li>
				              <li class="footer"><a href="#">查看全部</a></li>
				            </ul>
				          </li>
						
						<!-- User Account Menu -->
						<li class="dropdown user user-menu">
							<!-- Menu Toggle Button --> <a href="#" class="dropdown-toggle"
							data-toggle="dropdown"> <!-- The user image in the navbar-->
								<span class="glyphicon glyphicon-user"></span> <!-- hidden-xs hides the username on small devices so only the image appears. -->
								<span class="hidden-xs">${userName}</span>
						</a>
							<ul class="dropdown-menu">
								<!-- The user image in the menu -->
								<li class="user-header"><img src="${ctx}/assets/img/avatar.png" class="img-circle"
									alt="头像">

									<p>${userName}</p></li>
								<!-- Menu Body -->
								<li class="user-body">
									<div class="row">
										<div class="col-xs-6 text-center">
											<a href="#"><i class="fa fa-user"></i> 个人资料</a>
										</div>
										<div class="col-xs-6 text-center">
											<a href="#"><i class="fa fa-key"></i> 修改密码</a>
										</div>

									</div> <!-- /.row -->
								</li>
								<!-- Menu Footer-->
								<li class="user-footer">
									<div class="pull-left">
										<span></span> <a id="btnRet" href="#"
											class="btn btn-default btn-flat"><span
											class="glyphicon glyphicon-th-large"></span> 平台主页</a>
									</div>
									<div class="pull-right">
										<a id="btnLogout" href="#" class="btn btn-default btn-flat"><span
											class="glyphicon glyphicon-off"></span> 退出</a>
									</div>
								</li>
							</ul>
						</li>
						<!-- Control Sidebar Toggle Button -->
						<li><a href="#" data-toggle="control-sidebar"><i
								class="fa fa-gears"></i></a></li>
					</ul>
				</div>
			</nav>
		</header>
		<!-- Left side column. contains the logo and sidebar -->
		<aside class="main-sidebar">

			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar user panel (optional) -->
				<!-- <div class="user-panel">
					<div class="pull-left image">
						<img src="" class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>Alexander Pierce</p>
						Status
						<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
					</div>
				</div> -->

				<!-- search form (Optional) -->
				<!-- <form action="#" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="text" name="q" class="form-control"
							placeholder="Search..."> <span class="input-group-btn">
							<button type="submit" name="search" id="search-btn"
								class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form> -->
				<!-- /.search form -->

				<!-- Sidebar Menu -->
				<ul class="sidebar-menu" id="menu">
					<li class="header">导航菜单</li>
				</ul>
				<!-- /.sidebar-menu -->
			</section>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper"  style="height: 100%;">
			<!-- Content Header (Page header) -->
			<section class="content-header">
<!-- 				<h1 id="contentHeader"></h1> -->
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-home text-blue"></i></a></li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content" id="indexContent"  style="height: 100%;">

				<!-- Your Page Content Here -->

			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->

		<!-- Main Footer -->
		<footer class="main-footer">
			<!-- To the right -->
			<div class="pull-right hidden-xs"><strong><small>版本 1.0</small></strong></div>
			<!-- Default to the left -->
			 <strong><small><strong>天津港信息技术发展有限公司</strong> 版权所有  Copyright &copy; 2015 Tianjin Port Information
				Technology Co.,Ltd. All rights reserved.</small></strong>
		</footer>

		<!-- Control Sidebar -->
		<!-- <aside class="control-sidebar control-sidebar-dark">
			Create the tabs
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li class="active"><a href="#control-sidebar-home-tab"
					data-toggle="tab"><i class="fa fa-home"></i></a></li>
				<li><a href="#control-sidebar-settings-tab" data-toggle="tab"><i
						class="fa fa-gears"></i></a></li>
			</ul>
			Tab panes
			<div class="tab-content">
				Home tab content
				<div class="tab-pane active" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript::;"> <i
								class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
						</a></li>
					</ul>
					/.control-sidebar-menu

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li><a href="javascript::;">
								<h4 class="control-sidebar-subheading">
									Custom Template Design <span
										class="label label-danger pull-right">70%</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger"
										style="width: 70%"></div>
								</div>
						</a></li>
					</ul>
					/.control-sidebar-menu

				</div>
				/.tab-pane
				Stats tab content
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab
					Content</div>
				/.tab-pane
				Settings tab content
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading"> Report panel
								usage <input type="checkbox" class="pull-right" checked>
							</label>

							<p>Some information about this general settings option</p>
						</div>
						/.form-group
					</form>
				</div>
				/.tab-pane
			</div>
		</aside> -->
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

	<!-- REQUIRED JS SCRIPTS -->

	<script type="text/javascript">
		var name = "${userName}";
		var pid = "${pid}";
	</script>

	 <%@ include file="/common/wx-js-lib.jsp"%>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/assets/js/dist/home.js"></script>
</body>
</html>