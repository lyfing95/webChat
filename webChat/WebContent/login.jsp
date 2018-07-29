<%@ page language="java" import="java.util.*,com.rjxy.webchat.entity.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>WebChat | 登陆</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <link href="static/source/css/login.css" rel='stylesheet' type='text/css' />
  <script src="static/plugins/jquery/jquery-2.1.4.min.js"></script>
  <script src="static/plugins/layer/layer.js"></script>
</head>
<body>

<h1>WebChat</h1>
<div class="login-form">
  <div class="close"> </div>
  <div class="head-info">
    <label class="lbl-1"></label>
    <label class="lbl-2"></label>
    <label class="lbl-3"></label>
  </div>
  <div class="clear"> </div>
  <div class="avtar"><img src="static/source/img/avtar.png" alt="点我注册"/></div>
  <form id="login-form" action="chatUser/login.do" method="post" onSubmit="return checkLoginForm()">
    <div class="key">
      <input type="text" id="username" name="userid" placeholder="请输入账号" >
    </div>

    <div class="key">
      <input type="password" id="password" name="password" placeholder="请输入密码">
    </div>
     
   
    <div style="height:70px;">  
     <div class="signin" style="float:left;width:50%;">
      <input type="submit" id="submit" value="登录" />
    </div>   
      </form>
     <div class="signin" style="float:left;width:50%;">
      <form action="register.jsp">
      <input type="submit" id="submit" value="注册" />
      </form>
    </div>    
    </div>
 </div>

<script>
  

  /**
   * check the login form before user login.
   * @returns {boolean}
   */
  function checkLoginForm(){
    var username = $('#username').val();
    var password = $('#password').val();
    if(isNull(username) && isNull(password)){
      $('#submit').attr('value','请输入账号和密码!!!').css('background','red');
      return false;
    }
    if(isNull(username)){
      $('#submit').attr('value','请输入账号!!!').css('background','red');
      return false;
    }
    if(isNull(password)){
      $('#submit').attr('value','请输入密码!!!').css('background','red');
      return false;
    }
    //if(username != 'Amaya' || password != '123456'){
    //	$('#submit').attr('value','账号或密码错误!!!').css('background','red');
    //	return false;
    //}
    else{
      $('#submit').attr('value','Logining~');
      return true;
    }
  }

  /**
   * check the param if it's null or '' or undefined
   * @param input
   * @returns {boolean}
   */
  function isNull(input){
    if(input == null || input == '' || input == undefined){
      return true;
    }
    else{
      return false;
    }
  }
</script>
</body>
</html>