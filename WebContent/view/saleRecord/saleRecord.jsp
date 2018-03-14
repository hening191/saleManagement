<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@include file="../../commons/taglibs.jsp"%>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>销售管理——出售记录</title>
		<link rel="stylesheet" type="text/css" href="${ctxPath }/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${ctxPath }/css/pages.css" />
		<link rel="stylesheet" type="text/css" href="${ctxPath }/js/laydate/need/laydate.css" />
        <link rel="stylesheet" type="text/css" href="${ctxPath }/js/laydate/skins/molv/laydate.css" />
	</head>
	<body>
		<!-- 头部开始-->
       	<%@ include file="../commons/header.jsp" %>
        <!-- 头部结束 -->
		
		<!-- 内容区域 -->

	    <div class="content">
	        <div class="c-inner">
	            <div class="c-subNav clearfix">
					
               	 	<span>开始日期：</span><input id="saleStartDate"  class="laydate-icon time-inner" readonly autocomplete="off">&nbsp;&nbsp;&nbsp;&nbsp;
               		<span>结束日期：</span><input id="saleEndDate"  class="laydate-icon time-inner" readonly autocomplete="off">
	          		<div class="input-right fr " style="margin-top:-15px">
	                   	<input type="text" placeholder="请输入产品名称搜索" id="pro_name" autocomplete="off"/>
	                   	<a href="javascript:Sale.searchSaleName();"></a>
	               	</div>
	               	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a id="sumSalePrice">销售总额：</a>
	               	<div class="daor-btn fr">
	                    <a href="javascript:Sale.exportExcel();"><img src="${ctxPath}/img/icon-img/dar-icon.png" alt="" /></a>
	                </div>
	            </div>
	            
	            <!-- 查看记录表格 -->
	
	            <div class="c-table">
	                <table id = "pro_list">
	                    
	                </table>
	            </div>
	        
	        </div>
	    </div>

		
		<!-- 底部开始-->
       	<%@ include file="../commons/footer.jsp" %>
        <!-- 底部结束 -->
        
        <script type="text/javascript" src="${ctxPath }/js/jquery-1.11.2.min.js"></script>
    	<script type="text/javascript" src="${ctxPath }/js/saleRecord/saleRecord.js"></script>
    	<script type="text/javascript" src="${ctxPath }/js/layer/layer.js"></script>
    	<script type="text/javascript" src="${ctxPath }/js/laydate/laydate.js"></script>
		 <script>
			laydate.skin('dahong');
			/* laydate({
				elem: '#pro_time_trajectory', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
				event: 'focus', //响应事件。如果没有传入event，则按照默认的click
				isclear: true, //是否显示清空
				istoday: false, //是否显示今天
				issure: false, //是否显示确认
				max: laydate.now(),
				choose: function(dates){ //选择好日期的回调
					his.hisTrajectory();
				 }
			}); */
	
			var start = {  
		        elem: '#saleStartDate', //选择ID为START的input  
		        format: 'YYYY-MM-DD', //自动生成的时间格式  
		        max: laydate.now(), //最大日期  
		        istime: true, //必须填入时间  
		        istoday: false,  //是否是当天  
		        start: laydate.now(0,"YYYY-MM-DD"),  //设置开始时间为当前时间  
		        choose: function(datas){  
		             end.min = datas; //开始日选好后，重置结束日的最小日期  
		             end.start = datas; //将结束日的初始值设定为开始日  
		             Sale.findRecordList();
		        }  
		    };  
		    var end = {  
		        elem: '#saleEndDate',  
		        format: 'YYYY-MM-DD',  
		        max: laydate.now(),
		        istime: true,  
		        istoday: false,  
		        start: laydate.now(0,"YYYY-MM-DD"),  
		        choose: function(datas){  
		            start.max = datas; //结束日选好后，重置开始日的最大日期  
		            Sale.findRecordList();
		        }  
		    };  
		    laydate(start);  
		    laydate(end);
			
		    $('#saleStartDate').val(laydate.now(-30, 'YYYY-MM-DD'));
	        $('#saleEndDate').val(laydate.now(0, 'YYYY-MM-DD'));
		</script>
	</body>
</html>