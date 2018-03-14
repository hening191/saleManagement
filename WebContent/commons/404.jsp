<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd"> 
<%@include file="taglibs.jsp"%>
<html version="-//W3C//DTD HTML 4.01 Transitional//EN">
    <head>
		<title> ${configMap.project_name } </title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="Cache-Control" content="no-store"/>
		<meta http-equiv="Pragma" content="no-cache"/>
		<meta http-equiv="Expires" content="0"/>
		<meta name="keywords" content=" ${configMap.project_name } ${configMap.school_name } LEO BJMF "/>
		<link rel="dns-prefetch" href="//${ctxPath }">
		
		<link rel="icon" href="${ctxPath }/styleRoot/${stylePath }/images/logo.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="${ctxPath }/styleRoot/${stylePath }/images/logo.ico" type="image/x-icon" />
		<link rel="apple-touch-icon-precomposed" href="${ctxPath }/styleRoot/${stylePath }/images/screen_icon.png">
		
		<link href="${ctxPath}/styleRoot/${stylePath }/css/page.css" rel="stylesheet" type="text/css" />
		<link href="${ctxPath}/styleRoot/${stylePath }/css/common.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="${ctxPath}/styleRoot/${stylePath }/css/skin-0.css" />
		
	</head>
    <body>

    <!-- 404 开始 -->

        <div class="page404-wrap">
            <!-- 头部logo -->
            <div class="header"><h1><img src="${ctxPath}/styleRoot/${stylePath }/img/login/logo.png" alt="" /></h1></div>
    
            <!-- 登录框 -->
            <div class="content">
                <img src="${ctxPath}/styleRoot/${stylePath }/img/page-img/erro404.png" alt="" />
                <div class="into-btn"><a href="${ctxPath}">进入首页</a></div>
            </div>

             <!-- 底部区 -->
			<%@ include file="../view/commons/footer.jsp" %>
        </div>


      <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
      <script type="text/javascript" src="js/myPage.js"></script>
    </body>


</html>