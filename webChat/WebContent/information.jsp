<%@ page language="java" import="java.util.*,com.rjxy.webchat.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>WebChat | 个人信息</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="static/plugins/amaze/css/amazeui.min.css">
    <link rel="stylesheet" href="static/plugins/amaze/css/admin.css">
    <link rel="stylesheet" href="static/plugins/contextjs/css/context.standalone.css">
    <script src="static/plugins/jquery/jquery-2.1.4.min.js"></script>
    <script src="static/plugins/amaze/js/amazeui.min.js"></script>
    <script src="static/plugins/amaze/js/app.js"></script>
    <script src="static/plugins/layer/layer.js"></script>
    <script src="static/plugins/laypage/laypage.js"></script>
    <script src="static/plugins/contextjs/js/context.js"></script>
</head>
<body>
<header class="am-topbar admin-header">
    <div class="am-topbar-brand">
        <i class="am-icon-weixin"></i> <strong>WebChat</strong> <small>网页聊天室</small>
    </div>
    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>
    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    张三 <span class="am-icon-caret-down"></span>
                </a>
           </li>
        </ul>
    </div>
</header>
<div class="am-cf admin-main">
    <!-- sidebar start -->
	<div class="admin-sidebar am-offcanvas" id="admin-offcanvas">
		<div class="am-offcanvas-bar admin-offcanvas-bar">
			<ul class="am-list admin-sidebar-list">
				<li><a href="index.html"><span class="am-icon-commenting"></span> 聊天</a></li>
				<li><a href="information.html" class="am-cf"><span class="am-icon-book"></span> 个人信息<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
				<li class="admin-parent">
					<a class="am-cf" data-am-collapse="{target: '#collapse-nav'}"><span class="am-icon-cogs"></span> 设置 <span class="am-icon-angle-right am-fr am-margin-right"></span></a>
					<ul class="am-list am-collapse admin-sidebar-sub am-in" id="collapse-nav">
						<li><a href="info-setting.html"><span class="am-icon-group"></span> 个人设置</a></li>
						<li><a href="user_space.html"><span class="am-icon-cog"></span>心情动态</a></li>                 
					</ul>
				</li>
			   <li><a href="friend.html"><span class="am-icon-globe"></span>好友动态</a></li>        
				<li><a href="log.html"><span class="am-icon-inbox"></span> 系统日志<span class="am-badge am-badge-secondary am-margin-right am-fr">24</span></a></li>
				<li><a href="login.html"><span class="am-icon-sign-out"></span> 注销</a></li>
			</ul>
			<div class="am-panel am-panel-default admin-sidebar-panel">
				<div class="am-panel-bd">
					<p><span class="am-icon-tag"></span> Welcome</p>
					<p>欢迎使用WebChat聊天室~</p>
				</div>
			</div>
		</div>
	</div>
    <!-- sidebar end -->

    <!-- content start -->
    <div class="admin-content">
        <div class="am-cf am-padding">
            <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">个人信息</strong> / <small>info</small></div>
        </div>
        <div class="am-tabs am-margin" data-am-tabs>
            <ul class="am-tabs-nav am-nav am-nav-tabs">
                <li class="am-active"><a href="#tab1">个人信息</a></li>
            </ul>
            <div class="am-tabs-bd">
                <div class="am-tab-panel am-fade am-in am-active" id="tab1">
                    <div class="am-g"> 
                        <div class="am-u-md-3"><b>昵称:</b></div>
                        <div class="am-u-md-3">
                       ${loginUser.nickname}
                      </div>
                        <div class="am-u-md-6" style="float: right">
                            <img class="am-circle" src="static/source/img/robot.jpg" width="140" height="140" alt="张三"/>
                        </div>

                        <div class="am-u-md-3"><b> ${loginUser.sex}:</b></div>
                        <div class="am-u-md-3">男 
                        </div>
                        <div class="am-u-md-3"><b>出生日期:</b></div>
                        <div class="am-u-md-3"> ${loginUser.lasttime}</div>
                        <div class="am-u-md-3"><b>简介:</b></div>
                        <div class="am-u-md-3"> 
                                ${loginUser.profile }
                        </div>
                        <div class="am-u-md-3"><b>注册时间</b></div>
                        <div class="am-u-md-3"> ${loginUser.firsttime }</div>
                        <div class="am-u-md-3"><b>最后登录</b></div>
                        <div class="am-u-md-3"> ${loginUser.lasttime}</div>
                    </div>
                </div>
            </div>
    </div>
    <!-- content end -->
</div>
<a href="#" class="am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}">
    <span class="am-icon-btn am-icon-th-list"></span>
</a>
<footer style="text-align: center">
    <hr>
<p class="am-padding-left">© 2018 <a href="http://www.qianchengzhidao.com">夏季实训</a>. </p>
</footer>
</body>
</html>
