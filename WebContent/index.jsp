<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title> 销售管理系统 </title>
	<script type="text/javascript">
     	location.href="<%=basePath %>login/login.login.html?_=<%=Math.random() %>";
	</script>
  </head>
  
  <body>
    This is my JSP page.  to login page  <br>
    
  </body>
</html>
