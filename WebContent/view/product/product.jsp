<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@include file="../../commons/taglibs.jsp"%>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<title>销售管理——库存管理</title>
		<link rel="stylesheet" type="text/css" href="${ctxPath }/css/common.css" />
		<link rel="stylesheet" type="text/css" href="${ctxPath }/css/pages.css" />
	</head>
	<body>
		<!-- 头部开始-->
       	<%@ include file="../commons/header.jsp" %>
        <!-- 头部结束 -->
		
		<!-- 内容区域 -->

	    <div class="content">
	        <div class="c-inner">
	            <div class="c-subNav clearfix">
					<div class="button-list fl">
                   		<a href="javascript:Product.showAdd();" class="btn-icon0" >入库</a>
                   		<a href="javascript:Product.showSale();" class="btn-icon1" id="sale_pro">出售</a>
                   		<a href="javascript:;" class="btn-icon3" id="delete_pro" style="cursor:not-allowed">删除</a>
                   		
                   		<!-- 编辑功能作为预留在暗格的应急功能，不开放 -->
                   		<a href="javascript:;" class="btn-icon4" id="edit_pro" style="cursor:not-allowed">编辑</a>
                   		<!-- 编辑功能作为预留在暗格的应急功能，不开放 -->
                   		
               	 	</div>
	          		<div class="input-right fr ">
	                   	<input type="text" placeholder="请输入产品名称搜索" id="pro_name" autocomplete="off"/>
	                   	<a href="javascript:Product.searchName();"></a>
	               	</div>
	            </div>
	            
	
	            <div class="c-table">
	                <table id = "pro_list">
	                    
	                </table>
	            </div>
	        
	        </div>
	    </div>

		
		<!-- 底部开始-->
       	<%@ include file="../commons/footer.jsp" %>
        <!-- 底部结束 -->
        
        <!-- 添加/编辑记录 弹窗-->
	    <div class="layer-wrap" style="display:none" id="proDailog">
	        <div class="add-people">
	            <a href="javascript:Product.closeDailog();" class="close-btn"></a>
	            <div class="title" id="dailogTitle"></div>
	            <div class="inner">
	                <div class="input clearfix">
	                    <label class="fl">产品</label>
	                    <input type="text" id="product" class="fl" autocomplete="off"/>
	                </div>
	                <div class="input clearfix" id="sale_sock_div" style="display:none">
	                    <label class="fl">库存量</label>
	                    <input type="text" id="pro_sock" class="fl" autocomplete="off" disabled="true"/>
	                </div>
	                <div class="input clearfix">
	                    <label class="fl">数量</label>
	                    <input type="text" id="add_count" class="fl" autocomplete="off"/>
	                </div>
	                <div class="input clearfix" id="sale_price_div">
	                    <label class="fl">价格</label>
	                    <input type="text" id="pro_price" class="fl" autocomplete="off"/>
	                </div>
	                <div class="input clearfix" id="sale_totalPrice_div" style="display:none">
	                    <label class="fl">总价</label>
	                    <input type="text" id="pro_totalPrice" class="fl" autocomplete="off"/>
	                </div>
	                <div class="input clearfix" id="sale_prePrice_div">
	                    <label class="fl">优惠价</label>
	                    <input type="text" id="pro_prePrice" class="fl" autocomplete="off"/>
	                </div>
	                <div class="input clearfix" id="sale_source_div">
	                    <label class="fl">来源</label>
	                    <input type="text" id="pro_source" class="fl" autocomplete="off"/>
	                </div>
	                <div class="input clearfix" id="sale_customer_div" style="display:none">
	                    <label class="fl">客户</label>
	                    <input type="text" id="pro_customer" class="fl" autocomplete="off"/>
	                </div>
	            </div>
	            <div class="btn-a"><a href="javascript:;" id="savePro">确定</a></div>
	        </div>
	    </div>
	    
        <script type="text/javascript" src="${ctxPath }/js/jquery-1.11.2.min.js"></script>
    	<script type="text/javascript" src="${ctxPath }/js/product/product.js"></script>
    	<script type="text/javascript" src="${ctxPath }/js/layer/layer.js"></script>
    	
	</body>
</html>