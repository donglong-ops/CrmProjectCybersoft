<%@page import="com.myclass.dto.JobDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<%
	String contextPath = request.getContextPath();
	List<JobDto> taskNotDone = (List<JobDto>) request.getAttribute("TaskNotDone");
	List<JobDto> taskDoing = (List<JobDto>) request.getAttribute("TaskDoing");
	List<JobDto> taskDone = (List<JobDto>) request.getAttribute("TaskDone");
%>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" type="image/png" sizes="16x16" href="<%= request.getContextPath() %>/static/plugins/images/favicon.png">
    <title>Pixel Admin</title>
    <!-- Bootstrap Core CSS -->
    <link href="<%= request.getContextPath() %>/static/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Menu CSS -->
    <link href="<%= request.getContextPath() %>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.css" rel="stylesheet">
    <!-- animation CSS -->
    <link href="<%= request.getContextPath() %>/static/css/animate.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="<%= request.getContextPath() %>/static/css/style.css" rel="stylesheet">
    <!-- color CSS -->
    <link href="<%= request.getContextPath() %>/static/css/colors/blue-dark.css" id="theme" rel="stylesheet">
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
                    <a class="navbar-toggle hidden-sm hidden-md hidden-lg " href="javascript:void(0)" data-toggle="collapse" data-target=".navbar-collapse">
                        <i class="fa fa-bars"></i>
                    </a>
                    <div class="top-left-part">
                        <a class="logo" href="<%= request.getContextPath() %>/home">
                            <b>
                                <img src="<%= request.getContextPath() %>/static/plugins/images/pixeladmin-logo.png" alt="home" />
                            </b>
                            <span class="hidden-xs">
                                <img src="<%= request.getContextPath() %>/static/plugins/images/pixeladmin-text.png" alt="home" />
                            </span>
                        </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-left m-l-20 hidden-xs">
                        <li>
                            <form role="search" class="app-search hidden-xs">
                                <input type="text" placeholder="Search..." class="form-control"> 
                                <a href=""><i class="fa fa-search"></i>
                                </a>
                            </form>
                        </li>
                    </ul>
                    <ul class="nav navbar-top-links navbar-right pull-right">
                        <li>
                            <div class="dropdown">
                                <a class="profile-pic dropdown-toggle" data-toggle="dropdown" href="#"> 
                                    <img src="<%= request.getContextPath() %>/static/plugins/images/users/varun.jpg" alt="user-img" width="36" class="img-circle" />
                                    <b class="hidden-xs">Setting</b> 
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a href="<%= request.getContextPath() %>/user/view">Thông tin cá nhân</a></li>
                                    <li><a href="<%= request.getContextPath() %>/user/info">Thống kê công việc</a></li>
                                    <li class="divider"></li>
                                    <li><a href="<%= request.getContextPath() %>/logout">Đăng xuất</a></li>
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
                    <li style="padding: 10px 0 0;">
                        <a href="<%= request.getContextPath() %>/home" class="waves-effect"><i class="fa fa-clock-o fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Dashboard</span></a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/user" class="waves-effect"><i class="fa fa-user fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Thành viên</span></a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/role" class="waves-effect"><i class="fa fa-modx fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Quyền</span></a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/job" class="waves-effect"><i class="fa fa-table fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Dự án</span></a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/task" class="waves-effect"><i class="fa fa-table fa-fw"
                                aria-hidden="true"></i><span class="hide-menu">Công việc</span></a>
                    </li>
                    <li>
                        <a href="<%= request.getContextPath() %>/blank" class="waves-effect"><i class="fa fa-columns fa-fw"
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
                        <h4 class="page-title">Chi tiết Dự Án</h4>
                    </div>
                </div>
                <!-- /.row -->
                <!-- .row -->
                <div class="row">
                    <div class="col-md-4 col-xs-12">
                        <div class="white-box">
                            <div class="user-bg"> <img width="100%" alt="user" src="<%= request.getContextPath() %>/static/plugins/images/large/img1.jpg">
                                <div class="overlay-box">
                                    <div class="user-content">
                                    	<h2>Welcome</h2>
                                        <a href="javascript:void(0)"><img src="<%= request.getContextPath() %>/static/plugins/images/users/genu.jpg"
                                                class="thumb-lg img-circle" alt="img"></a>
                                        <h4 class="text-white">${sessionScope.USER_LOGIN.fullname }</h4>
                                        <h5 class="text-white">${sessionScope.USER_LOGIN.email }</h5>
                                    </div>
                                </div>
                            </div>
                            
                        </div>
                    </div>
                    <div class="col-md-8 col-xs-12">
                        <!-- BEGIN THỐNG KÊ -->
	<div class="row">
		<!--col -->
		<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
			<div class="white-box">
				<div class="col-in row">
					<div class="col-xs-12">
						<h3 class="counter text-right m-t-15 text-danger text-center"><%= taskNotDone.size() %></h3>
                    </div>
                    <div class="col-xs-12">
						<i data-icon="E" class="linea-icon linea-basic"></i>
						<h5 class="text-muted vb text-center">CHƯA BẮT ĐẦU</h5>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="progress">
							<div class="progress-bar progress-bar-danger" role="progressbar"
								aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
								style="width: 20%"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.col -->
		<!--col -->
		<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
			<div class="white-box">
				<div class="col-in row">
					<div class="col-xs-12">
						<h3 class="counter text-right m-t-15 text-megna text-center"><%= taskDoing.size() %></h3>
                    </div>
                    <div class="col-xs-12">
						<i class="linea-icon linea-basic" data-icon="&#xe01b;"></i>
						<h5 class="text-muted vb text-center">ĐANG THỰC HIỆN</h5>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="progress">
							<div class="progress-bar progress-bar-megna" role="progressbar"
								aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
								style="width: 50%"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.col -->
		<!--col -->
		<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
			<div class="white-box">
				<div class="col-in row">
					<div class="col-xs-12">
						<h3 class="counter text-right m-t-15 text-primary text-center"><%= taskDone.size() %></h3>
                    </div>
                    <div class="col-xs-12">
						<i class="linea-icon linea-basic" data-icon="&#xe00b;"></i>
						<h5 class="text-muted vb text-center">HOÀN THÀNH</h5>
					</div>
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="progress">
							<div class="progress-bar progress-bar-primary" role="progressbar"
								aria-valuenow="40" aria-valuemin="0" aria-valuemax="100"
								style="width: 30%"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- /.col -->
	</div>
	<!-- END THỐNG KÊ -->

                    </div>
                </div><br />
                <!-- /.row -->
                <!-- BEGIN DANH SÁCH CÔNG VIỆC -->
                <h4>DANH SÁCH CÔNG VIỆC</h4>
                <div class="row">
                    <div class="col-md-4">
                        <div class="white-box">
                            <h3 class="box-title">Chưa thực hiện</h3>
                            <% for(JobDto dto: taskNotDone){ %>
                            <div class="message-center">
                                <a href="#">
                                    <div class="mail-contnet">
                                        <h5><%=dto.getName() %></h5>
                                        <span class="mail-desc"></span>
                                        <span class="time">Bắt đầu: <%=dto.getStart_date() %></span>
                                        <span class="time">Kết thúc: <%=dto.getEnd_date() %></span>
                                    </div>
                                </a> 
                            </div>
                            <%} %>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="white-box">
                            <h3 class="box-title">Đang thực hiện</h3>
                             <% for(JobDto dto: taskDoing){ %>
                            <div class="message-center">
                                <a href="#">
                                    <div class="mail-contnet">
                                        <h5><%=dto.getName() %></h5>
                                        <span class="mail-desc"></span>
                                        <span class="time">Bắt đầu: <%=dto.getStart_date() %></span>
                                        <span class="time">Kết thúc: <%=dto.getEnd_date() %></span>
                                    </div>
                                </a> 
                            </div>
                            <%} %>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="white-box">
                            <h3 class="box-title">Đã hoàn thành</h3>
                             <% for(JobDto dto: taskDone){ %>
                            <div class="message-center">
                                <a href="#">
                                    <div class="mail-contnet">
                                        <h5><%=dto.getName() %></h5>
                                        <span class="mail-desc"></span>
                                        <span class="time">Bắt đầu: <%=dto.getStart_date() %></span>
                                        <span class="time">Kết thúc: <%=dto.getEnd_date() %></span>
                                    </div>
                                </a> 
                            </div>
                            <%} %>
                        </div>
                    </div>
                </div>
                <!-- END DANH SÁCH CÔNG VIỆC -->
            </div>
            <!-- /.container-fluid -->
            <footer class="footer text-center"> 2018 &copy; myclass.com </footer>
        </div>
        <!-- /#page-wrapper -->
    </div>
    <!-- /#wrapper -->
    <!-- jQuery -->
    <script src="<%= request.getContextPath() %>/static/plugins/bower_components/jquery/dist/jquery.min.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<%= request.getContextPath() %>/static/bootstrap/dist/js/bootstrap.min.js"></script>
    <!-- Menu Plugin JavaScript -->
    <script src="<%= request.getContextPath() %>/static/plugins/bower_components/sidebar-nav/dist/sidebar-nav.min.js"></script>
    <!--slimscroll JavaScript -->
    <script src="<%= request.getContextPath() %>/static/js/jquery.slimscroll.js"></script>
    <!--Wave Effects -->
    <script src="<%= request.getContextPath() %>/static/js/waves.js"></script>
    <!-- Custom Theme JavaScript -->
    <script src="<%= request.getContextPath() %>/static/js/custom.min.js"></script>
</body>
</html>