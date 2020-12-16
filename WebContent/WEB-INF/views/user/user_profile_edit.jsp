<%@page import="com.myclass.entity.User"%>
<%@page import="com.myclass.entity.Role"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<%
	List<Role> roles = (List<Role>)request.getAttribute("roles");
	User user = (User) request.getAttribute("user");
%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16"
	href="<%=request.getContextPath()%>/static/plugins/images/favicon.png">
<title>Pixel Admin</title>
<!-- Bootstrap Core CSS -->
<link
	href="<%=request.getContextPath()%>/static/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Menu CSS -->
<link
	href="<%=request.getContextPath()%>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<!-- animation CSS -->
<link href="<%=request.getContextPath()%>/static/css/animate.css"
	rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/static/css/style.css"
	rel="stylesheet">
<!-- color CSS -->
<link
	href="<%=request.getContextPath()%>/static/css/colors/blue-dark.css"
	id="theme" rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/static/css/custom.css">
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
	<!-- Preloader -->
	<div class="preloader">
		<div class="cssload-speeding-wheel"></div>
	</div>
	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top m-b-0">
			<div class="navbar-header">
				<a class="navbar-toggle hidden-sm hidden-md hidden-lg "
					href="javascript:void(0)" data-toggle="collapse"
					data-target=".navbar-collapse"> <i class="fa fa-bars"></i>
				</a>
				<div class="top-left-part">
					<a class="logo" href="index.html"> <b> <img
							src="<%=request.getContextPath()%>/static/plugins/images/pixeladmin-logo.png"
							alt="home" />
					</b> <span class="hidden-xs"> <img
							src="<%=request.getContextPath()%>/static/plugins/images/pixeladmin-text.png"
							alt="home" />
					</span>
					</a>
				</div>
				<ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
					<li>
						<form role="search" class="app-search hidden-xs">
							<input type="text" placeholder="Search..." class="form-control">
							<a href=""> <i class="fa fa-search"></i>
							</a>
						</form>
					</li>
				</ul>
				<ul class="nav navbar-top-links navbar-right pull-right">
					<li>
						<div class="dropdown">
							<a class="profile-pic dropdown-toggle" data-toggle="dropdown"
								href="#"> <img
								src="<%=request.getContextPath()%>/static/plugins/images/users/varun.jpg"
								alt="user-img" width="36" class="img-circle" /> <b
								class="hidden-xs">Setting</b>
							</a>
							<ul class="dropdown-menu">
								<li><a href="<%=request.getContextPath()%>/user/view">Thông tin cá nhân</a></li>
								<li><a href="<%= request.getContextPath() %>/user/info">Thống kê công việc</a></li>
								<li class="divider"></li>
								<li><a href="<%=request.getContextPath()%>/logout">Đăng xuất</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
			<!-- /.navbar-header -->
			<!-- /.navbar-top-links -->
			<!-- /.navbar-static-side -->
		</nav>
		<!-- Left navbar-header -->
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse slimscrollsidebar">
				<ul class="nav" id="side-menu">
					<li style="padding: 10px 0 0;"><a
						href="<%=request.getContextPath()%>/home" class="waves-effect"><i
							class="fa fa-clock-o fa-fw" aria-hidden="true"></i><span
							class="hide-menu">Dashboard</span></a></li>
					<li><a href="<%=request.getContextPath()%>/user"
						class="waves-effect"><i class="fa fa-user fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
					</li>
					<li><a href="<%=request.getContextPath()%>/role"
						class="waves-effect"><i class="fa fa-modx fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Quyền</span></a></li>
					<li><a href="<%=request.getContextPath()%>/job"
						class="waves-effect"><i class="fa fa-table fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Dự án</span></a></li>
					<li><a href="<%=request.getContextPath()%>/task"
						class="waves-effect"><i class="fa fa-table fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Công việc</span></a></li>
					<li><a href="<%=request.getContextPath()%>/blank"
						class="waves-effect"><i class="fa fa-columns fa-fw"
							aria-hidden="true"></i><span class="hide-menu">Blank Page</span></a>
					</li>
					<li><a href="<%=request.getContextPath()%>/user/info" class="waves-effect"><i
							class="fa fa-pencil-square-o" aria-hidden="true">     </i><span
							class="hide-menu"> Thống Kê Công Việc </span></a></li>
				</ul>
			</div>
		</div>
		<!-- Left navbar-header end -->
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row bg-title">
					<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
						<h4 class="page-title">Thông Tin thành viên</h4>
					</div>
				</div>
				<!-- /.row -->
				<!-- .row -->
				<div class="row">
					<div class="col-md-2 col-12"></div>
					<div class="col-md-8 col-xs-12">
						<div class="white-box">
							<form class="form-horizontal form-material" method="POST" action="<%=request.getContextPath()%>/user/edit">
								<div class="form-group">
									<label class="col-md-12">Full Name</label>
									<div class="col-md-12">
										<input type="text" value="${user.fullname}"
											name="fullname" class="form-control form-control-line">
											<input type="hidden" value="<%=user.getId() %>" name="id" >
									</div>
								</div>
								<div class="form-group">
									<label for="example-email" class="col-md-12">Email</label>
									<div class="col-md-12">
										<input type="email" placeholder="Enter Email" name="email" value="${user.email}"
											class="form-control form-control-line" name="example-email"
											id="example-email">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-12">Password</label>
									<div class="col-md-12">
										<input type="password" name="password" value="${user.password}"
											placeholder="Enter Password"
											class="form-control form-control-line">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-12 ">Avatar Link</label>
									<div class="col-md-12">
										<input type="text" placeholder="Enter Avatar Link" name="avatar" value="${user.avatar}"
											class="form-control form-control-line">
									</div>
								</div>
								<div class="form-group">
									<div class="col-sm-12 text-center">
										<button type="submit" class="btn btn-success">Update User</button>
										<a href="<%=request.getContextPath()%>/user" class="btn btn-primary">Quay lại</a>
									</div>
								</div>
							</form>
						</div>
					</div>
					<div class="col-md-2 col-12"></div>
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
			<footer class="footer text-center"> 2018 &copy; myclass.com
			</footer>
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->
	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/static/plugins/bower_components/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/static/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Menu Plugin JavaScript -->
	<script
		src="<%=request.getContextPath()%>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
	<!--slimscroll JavaScript -->
	<script
		src="<%=request.getContextPath()%>/static/js/jquery.slimscroll.js"></script>
	<!--Wave Effects -->
	<script src="<%=request.getContextPath()%>/static/js/waves.js"></script>
	<!-- Custom Theme JavaScript -->
	<script src="<%=request.getContextPath()%>/static/js/custom.min.js"></script>

</body>
</html>