$(function(){
	Login.initInputFocus();
	//Login.initInputsBlur(); 
	Login.initInputsChange(); 
	Login.keyboardListener(); 
	Login.initRememberPwd(); 
})

var Login = {
	
	//记住密码事件、是否回填
	initRememberPwd : function(){
		$('#login_remember_pwd').click(function(event) {
	      $("login_remember_pwd").addClass('active');
	    });
		if($.cookie('HN_SALE_LOGIN_NAME')!=null){
			$("#login_remember_pwd").addClass("active"); 
			$("#login_name").val($.cookie('HN_SALE_LOGIN_NAME'));
			$("#login_pwd").val($.cookie('HN_SALE_PASSWORD'));
		}else{
			$("#login_remember_pwd").removeClass("active"); 
		}
	}, 
	
	//输入框聚焦
	initInputFocus : function(){
		$("#login_inputs input").each(function(){
			$(this).on('focus', function(){
				if( !$(this).parent().hasClass("erro") ){
					$(this).parent().addClass("active"); 
				}
			}); 
		})
	}, 
	
	//输入框值改变
	initInputsChange : function(){
		$("#login_inputs input").each(function(){
			$(this).on('input change', function(){
				$(this).parent().removeClass("erro"); 
				$(this).parent().addClass("active"); 
			}); 
		})
	},
	
	//输入框丢失焦点
	initInputsBlur : function(){
		$("#login_inputs input").each(function(){
			$(this).on('blur', function(){
				$(this).parent().removeClass("active"); 
			}); 
		})
	},
	
	//登录请求
	doLogin : function(){
		var loginName = $("#login_name").val(); 
		var loginPwd = $("#login_pwd").val(); 
		if( $("#login_name").val()=="" ){
			$("#login_name_div").removeClass("active");
			$("#login_name_div").addClass("erro");
			$("#login_name_info").text("请输入账号");
			return ;
		}
		
		if( $("#login_pwd").val()=="" ){
			$("#login_pwd_div").removeClass("active");
			$("#login_pwd_div").addClass("erro");
			$("#login_pwd_info").text("请输入密码");
			return ;
		}
		$.ajax({
			type : "post", 
			dataType : "json", 
			url : ctxPath + "/login/login.doLogin.html",
			data:{
				"loginName":loginName,
				"password":loginPwd
			},
			success:function(res){
				if(res.success==1){
					$.cookie('HN_SALE_LOGIN_STATUS', '404',{expires: 0.02});
					if( $("#login_remember_pwd").hasClass("active")  ){
						$.cookie('HN_SALE_LOGIN_NAME', loginName,{expires: 7});
						$.cookie('HN_SALE_PASSWORD', loginPwd,{expires: 7});
					}else{
						$.cookie('HN_SALE_LOGIN_NAME', null);
						$.cookie('HN_SALE_PASSWORD', null);
					}
					window.location.href = ctxPath+"/product/sale.product.html";
				}else{
					if( res.msg_type==0 ){
						$("#login_name_div").addClass("erro");
						$("#login_name_info").text( res.msg );
						return ;
					} else{
						$("#login_pwd_div").addClass("erro");
						$("#login_pwd_info").text( res.msg );
						return ;
					}
				}
			}
		})
	}, 
	
	//监听回车
	keyboardListener : function(){
		$(window).keydown(function(event){
			if(event.keyCode==13){
				$("#login_submit").focus(); 
				Login.doLogin(); 
			}
		});
	}
}
