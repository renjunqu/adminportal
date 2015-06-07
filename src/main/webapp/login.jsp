<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta charset="utf-8" />
		<title>Mobility Beehive Network</title>

		<meta name="description" content="FutureMove Admin Portal |登录页面" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

		<!-- bootstrap & fontawesome -->
		<link rel="stylesheet" href="${contextPath}/static/dist/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${contextPath}/static/dist/css/font-awesome.min.css" />

		<!-- text fonts -->
		<link rel="stylesheet" href="${contextPath}/static/dist/css/ace-fonts.min.css" />

		<!-- ace styles -->
		<link rel="stylesheet" href="${contextPath}/static/dist/css/ace.min.css" />

		<!--[if lte IE 9]>
			<link rel="stylesheet" href="${contextPath}/static/dist/css/ace-part2.min.css" />
		<![endif]-->
		<link rel="stylesheet" href="${contextPath}/static/dist/css/ace-rtl.min.css" />

		<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${contextPath}/static/dist/css/ace-ie.min.css" />
		<![endif]-->

		<!-- HTML5 shim and Respond.min.js IE8 support of HTML5 elements and media queries -->
		<!--[if lt IE 9]>
		<script src="${contextPath}/static/dist/js/html5shiv.min.js"></script>
		<script src="${contextPath}/static/dist/js/respond.min.js"></script>
		<![endif]-->
	</head>

	<body class="login-layout light-login" style="background-color: #D4DBCC;background-image:none;">
		<div class="main-container">
			<div class="main-content">
				<div class="row" style="width:1024px;height:768px;margin: 0 auto;background-image: url('${contextPath}/static/assets/images/commons/loginBg.png');">
					<div class="col-sm-11 col-sm-offset-1">
						<div class="login-container" style="float: right;margin-top: 160px;margin-right: 0px;">
							 
							<div class="center">
								<h1>
									<span class="red">&nbsp;</span>
								</h1>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												Mobility Beehive Network
											</h4>

											<div class="space-6"></div>

											<form id="validationLoginForm" method="post" action="#">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="loginAccount" name="loginAccount" type="text" class="form-control" placeholder="邮箱" data-rel="tooltip" title="用户账号" data-placement="right" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input id="loginPassword" name="password" type="password" class="form-control" placeholder="密码" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<span id="loginTip" style="color:#A94442"></span>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">

														<button id="loginButton" type="button" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<span class="bigger-110">登录</span>
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

										</div><!-- /.widget-main -->



									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->

							</div><!-- /.position-relative -->
							<%--<div class="navbar-fixed-top align-right">--%>
								<%--<br />--%>
								<%--&nbsp;--%>
								<%--<a id="btn-login-dark" href="#">花瓣</a>--%>
								<%--&nbsp;--%>
								<%--<span class="blue">/</span>--%>
								<%--&nbsp;--%>
								<%--<a id="btn-login-blur" href="#">风车</a>--%>
								<%--&nbsp;--%>
								<%--<span class="blue">/</span>--%>
								<%--&nbsp;--%>
								<%--<a id="btn-login-light" href="#">阳光</a>--%>
								<%--&nbsp; &nbsp; &nbsp;--%>
							<%--</div>--%>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
			<div class="footer">
                <div class="footer-inner">
                    <!-- #section:basics/footer -->
                    <div class="footer-content-nobordertop">
                        <span class="bigger-120">
                            <span class="blue bolder">
                                 futureMove
                            </span>
                            &copy; 
                            2015
                        </span>
                    </div>
                    <!-- /section:basics/footer -->
                </div>
            </div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='${contextPath}/static/dist/js/jquery.min.js'>"+"<"+"/script>");
		</script>
		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
 			window.jQuery || document.write("<script src='${contextPath}/static/dist/js/jquery1x.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='${contextPath}/static/dist/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		
		<script type="text/javascript" src="${contextPath}/static/dist/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${contextPath}/static/dist/js/tooltip.min.js"></script>
		<script type="text/javascript" src="${contextPath}/static/dist/js/date-time/bootstrap-datepicker.min.js"></script>
		<script type="text/javascript" src="${contextPath}/static/dist/js/date-time/locales/bootstrap-datepicker.zh-CN.min.js"></script>

		<%--<script type="text/javascript" src="${contextPath}/static/dist/js/custom/login.min.js"></script>--%>



		<script type="text/javascript" src="${contextPath}/static/assets/js/custom/login.js"></script>
	</body>
</html>
