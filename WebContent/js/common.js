

var commonUtil = {
		/**
		 *  ajax 替换或刷新 指定的 DIV 片段
		 *  @param divId 	：	DIV 片段  ID
		 *  @param url 		：	请求路径
		 */
		refreshDivModule : function ( divId, url ){
			
			$.ajax({
				type	:	"POST",
				url		:	url,
				dataType	:	"html",
				success	:	function( html ){
					$( "#"+divId ).html( html );
					
				}
			});
			
		},
		/**
		 * ajax 替换或刷新 指定的 DIV 片段
		 * @param url 
		 * @param id 替换divID
		 * @param opt 参数（{id : 1, name : name , title : tile }）
		 */
		refreshDivModule : function ( divId , url , opt ){
			$.ajax({
				type	:	"POST",
				url		:	url,
				data  :   opt,
				dataType	:	"html",
				success	:	function( html ){
					
					$( "#"+divId ).html( html );
					
				}
			});
		},
		/**
		 * 新打开一个页面
		 */
		openNewPage : function ( url ){
			
			window.open( url ,"_blank");
			
		},
		/**
		 * 刷新当前页面，可能是同一个，也可能不是
		 */
		refreshCurrPage : function ( url ){
			
			window.location.href	=	url;
			
		},
		
		signOutSystem : function (){
			
			location.href = ctxPath+"/open/login.index.html?_="+new Date().getTime();
			
		},
		pageJumpBar : function ( divId, page, fun , fungo ){
			var pageHtml = "";
			
			if( page == null ){
				pageHtml = "";
			}else if( page.totalCount == 0 || page.totalPageCount == 0 ){
				//pageHtml += "<li style='margin-right: 20px;color:#fff' >没有数据</li>";
				pageHtml = "";
			}else if ( page.totalPageCount == 1 ) {
				pageHtml = "";
			}else{
				if( page.totalPageCount > 10 ){
					pageHtml += '<a href="javascript:;" onclick="'+fun+'('+1+')'+'">首页</a>';
					if( page.currentPageNo < 6 ){
						for (var i = 1; i <= 10; i++) {
							if( i == page.currentPageNo ){
								pageHtml += '<a href="javascript:;" onclick="'+fun+'('+i+')'+'" class="a-sel">'+i+'</a>';	
							}else{
								pageHtml += '<a href="javascript:;" onclick="'+fun+'('+i+')'+'" >'+i+'</a>';	
							}
						}
					}else if( page.currentPageNo > (page.totalPageCount-5) ){
						var start = page.totalPageCount-9;
						for (var i = 0; i < 10; i++) {
							var step = start + i;
							if( step == page.currentPageNo ){
								pageHtml += '<a href="javascript:;" onclick="'+fun+'('+step+')'+'" class="a-sel">'+step+'</a>';	
							}else{
								pageHtml += '<a href="javascript:;" onclick="'+fun+'('+step+')'+'" >'+step+'</a>';	
							}
						}
					}else{
						var start = page.currentPageNo-5;
						for (var i = 0; i < 10; i++) {
							var step = start + i;
							if( step == page.currentPageNo ){
								pageHtml += '<a href="javascript:;" onclick="'+fun+'('+step+')'+'" class="a-sel">'+step+'</a>';	
							}else{
								pageHtml += '<a href="javascript:;" onclick="'+fun+'('+step+')'+'" >'+step+'</a>';	
							}
						}
					}
					
					pageHtml += '<a href="javascript:;" onclick="'+fun+'('+page.totalPageCount+')'+'">尾页</a>';
				}else{
					pageHtml += '<a href="javascript:;" onclick="'+fun+'('+1+')'+'">首页</a>';
					
					for (var i = 1; i <= page.totalPageCount; i++) {
						if( i == page.currentPageNo ){
							pageHtml += '<a href="javascript:;" onclick="'+fun+'('+i+')'+'" class="a-sel">'+i+'</a>';	
						}else{
							pageHtml += '<a href="javascript:;" onclick="'+fun+'('+i+')'+'" >'+i+'</a>';	
						}
					}
					
					pageHtml += '<a href="javascript:;" onclick="'+fun+'('+page.totalPageCount+')'+'">尾页</a>';
				}
				
				pageHtml += '<input type="text" class="page-txt" id="page_bar_go" />'+
				'<input type="button" value="GO" onclick="'+fungo+'( \'page_bar_go\' ,'+page.totalPageCount+')'+'" class="page-btn" />';
			}
			
			$(divId).html( pageHtml );
		}, 
		/**
		 * 弹出一个带确定和取消 按钮的确认弹出框
		 * @param title 弹出框标题
		 * @param msg 弹出框主体内容
		 * @param shade 弹出框点击遮罩层是否关闭
		 * @param btn1 弹出框第一个按钮名字 默认确定
		 * @param fun1 弹出框按钮1事件
		 * @param btn2 弹出框第二个按钮名字 默认取消
		 * @param fun2 弹出框按钮2事件
		 */
		confirm: function ( title, msg, shade, btn1, fun1, btn2, fun2 ){
			layer.confirm( 
				msg||"作者很懒没有写下要确定的内容" , 
				{ 
					title: title||'提示',
					shadeClose: shade||false, 
					btn: [
					      btn1||'确定',
					      btn2||'取消'
					      ]  
				}, 
				function (){
					layer.closeAll('dialog');
					fun1();
				}, 
				fun2
			);
		},
		
};

var weakPwdArray = ["123456","123456789","111111","5201314","12345678","123123","password","1314520","123321","7758521","1234567","5211314","666666","520520","woaini","520131","11111111","888888","hotmail.com","112233","123654","654321","1234567890","a123456","88888888","163.com","000000","yahoo.com.cn","sohu.com","yahoo.cn","111222tianya","163.COM","tom.com","139.com","wangyut2","pp.com","yahoo.com","147258369","123123123","147258","987654321","100200","zxcvbnm","123456a","521521","7758258","111222","110110","1314521","11111111","12345678","a321654","111111","123123","5201314","00000000","q123456","123123123","aaaaaa","a123456789","qq123456","11112222","woaini1314","a123123","a111111","123321","a5201314","z123456","liuchang","a000000","1314520","asd123","88888888","1234567890","7758521","1234567","woaini520","147258369","123456789a","woaini123","q1q1q1q1","a12345678","qwe123","123456q","121212","asdasd","999999","1111111","123698745","137900","159357","iloveyou","222222","31415926","123456","111111","123456789","123123","9958123","woaini521","5201314","18n28n24a5","abc123","password","123qwe","123456789","12345678","11111111","dearbook","00000000","123123123","1234567890","88888888","111111111","147258369","987654321","aaaaaaaa","1111111111","66666666","a123456789","11223344","1qaz2wsx","xiazhili","789456123","password","87654321","qqqqqqqq","000000000","qwertyuiop","qq123456","iloveyou","31415926","12344321","0000000000","asdfghjkl","1q2w3e4r","123456abc","0123456789","123654789","12121212","qazwsxedc","abcd1234","12341234","110110110","asdasdasd","123456","22222222","123321123","abc123456","a12345678","123456123","a1234567","1234qwer","qwertyui","123456789a","qq.com","369369","163.com","ohwe1zvq","xiekai1121","19860210","1984130","81251310","502058","162534","690929","601445","1814325","as1230","zz123456","280213676","198773","4861111","328658","19890608","198428","880126","6516415","111213","195561","780525","6586123","caonima99","168816","123654987","qq776491","hahabaobao","198541","540707","leqing123","5403693","123456","123456789","111111","5201314","123123","12345678","1314520","123321","7758521","1234567","5211314","520520","woaini","520131","666666","RAND#a#8","hotmail.com","112233","123654","888888","654321","1234567890","a123456"];

var validateRegExp = {
	    decmal: "^([+-]?)\\d*\\.\\d+$", //浮点数
	    decmal1: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$", //正浮点数
	    decmal2: "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$", //负浮点数
	    decmal3: "^-?([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0)$", //浮点数
	    decmal4: "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$", //非负浮点数（正浮点数 + 0）
	    decmal5: "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$", //非正浮点数（负浮点数 + 0）
	    intege: "^-?[1-9]\\d*$", //整数
	    intege1: "^[1-9]\\d*$", //正整数
	    intege2: "^-[1-9]\\d*$", //负整数
	    num: "^([+-]?)\\d*\\.?\\d+$", //数字
	    num1: "^[1-9]\\d*|0$", //正数（正整数 + 0）
	    num2: "^-[1-9]\\d*|0$", //负数（负整数 + 0）
	    ascii: "^[\\x00-\\xFF]+$", //仅ACSII字符
	    chinese: "^[\\u4e00-\\u9fa5]+$", //仅中文
	    color: "^[a-fA-F0-9]{6}$", //颜色
	    date: "^\\d{4}(\\-|\\/|\.)\\d{1,2}\\1\\d{1,2}$", //日期
	    email: "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", //邮件
	    idcard: "^[1-9]([0-9]{14}|[0-9]{17})$", //身份证
	    ip4: "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$", //ip地址
	    letter: "^[A-Za-z]+$", //字母
	    letter_l: "^[a-z]+$", //小写字母
	    letter_u: "^[A-Z]+$", //大写字母
	    mobile: "^0?(13|15|18|14)[0-9]{9}$", //手机
	    notempty: "^\\S+$", //非空
	    password: "^.*[A-Za-z0-9\\w_-]+.*$", //密码
	    fullNumber: "^[0-9]+$", //数字
	    picture: "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$", //图片
	    qq: "^[1-9]*[1-9][0-9]*$", //QQ号码
	    rar: "(.*)\\.(rar|zip|7zip|tgz)$", //压缩文件
	    tel: "^[0-9\-()（）]{7,18}$", //电话号码的函数(包括验证国内区号,国际区号,分机号)
	    url: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$", //url
	    username: "^[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+$", //户名
	    deptname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$", //单位名
	    zipcode: "^\\d{6}$", //邮编
	    realname: "^[A-Za-z\\u4e00-\\u9fa5]+$", // 真实姓名
	    companyname: "^[A-Za-z0-9_()（）\\-\\u4e00-\\u9fa5]+$",
	    companyaddr: "^[A-Za-z0-9_()（）\\#\\-\\u4e00-\\u9fa5]+$",
	    companysite: "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&#=]*)?$"
};

var validateRules = {
		isNull: function (str) {
	        return (str == "" || typeof str != "string");
	    },
	    betweenLength: function (str, _min, _max) {
	        return (str.length >= _min && str.length <= _max);
	    },
	    isUid: function (str) {
	        return new RegExp(validateRegExp.username).test(str);
	    },
	    isPwd: function (str) {
	        return /^.*([\W_a-zA-z0-9-])+.*$/i.test(str);
	    },
	    isMobile: function (str) {
	        return new RegExp(validateRegExp.mobile).test(str);
	    },
	    isEmail: function (str) {
	        return new RegExp(validateRegExp.email).test(str);
	    },
	    weakPwd: function (str) {
	        for(var i = 0; i < weakPwdArray.length; i++) {
	            if(weakPwdArray[i] == str) {
	                return true;
	            }
	        }
	        return false;
	    }
};