<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@include file="../../commons/taglibs.jsp"%>
<html lang="en">
    <head>
        <title>销售管理——登录页面</title>
        <link rel="stylesheet" type="text/css" href="${ctxPath }/css/common.css" />
        <link rel="stylesheet" type="text/css" href="${ctxPath }/css/login.css" />
    </head>
 <body>

    <div class="bg_linear">

        <div class="login_wrap">
            

            <!-- 内容区域 -->
            <div class="login_content clearfix">
                <div class="right_inner">
                    <h2 class="title">销售管理</h2>
                    <div class="input_wrap"  id="login_inputs">
                        <!-- 信息错误时  给div追加一个erro类名 -->
                        <div class="input input_u active" id="login_name_div">
                            <input type="text" placeholder="账号"  id="login_name"/>
                            <span id="login_name_info" class="erro_info"></span>
                        </div>
                        <!-- 获取焦点时  给div追加一个active类名 -->
                        <div class="input input_p active"  id="login_pwd_div">
                            <input type="password" placeholder="密码"  id="login_pwd"/>
                             <span id="login_pwd_info" class="erro_info"></span>
                        </div>
                        <div class="chose_wrap clearfix">
                            <a href="javascript:;" class="fl left_btn" id="login_remember_pwd">记住密码</a>
                        </div>
                        <div class="button_wrap">
                            <a href="javascript:;" id="login_submit"  onclick="Login.doLogin()">登录</a>
                        </div>
                    </div>
                </div>
            </div>
            
            <!-- 底部版权 -->
            <div class="login_footer">
                <div class="footer_inner">
                    <div class="left">
                        <p>技术支持：贺宁</p>
                    </div>
                    
                    <div class="right">

                        <p>18511626963</p>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script type="text/javascript" src="${ctxPath }/js/jquery-1.11.2.min.js"></script>
    <script type="text/javascript" src="${ctxPath }/js/login/login.js"></script>
     <script type="text/javascript" src="${ctxPath }/js/myPage.js"></script>
     <script type="text/javascript" src="${ctxPath }/js/jquery.cookie.js"></script>
 </body>
</html>
	
	
	
