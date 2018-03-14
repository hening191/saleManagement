<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="header">
        <div class="hd-inner clearfix">
            <div class="fl logo"><a style="font-size: 22px; color:#fff;padding:0 20px;">销售管理系统</a></div>
            <div class="fl nav">
                <ul id="head_style_class_id">
                    <li id="header_product_sock_id"><a href="javascript:commonUtil.refreshCurrPage( '${ctxPath }/product/sale.product.html?_t=${random }' );">库存管理</a></li>
                    <li id="header_product_sale_id"><a href="javascript:commonUtil.refreshCurrPage( '${ctxPath }/sale/sale.sale.html?_t=${random }' );">销售记录</a></li>
                    <li id="header_product_buy_id"><a href="javascript: commonUtil.refreshCurrPage( '${ctxPath }/buy/sale.buy.html?_t=${random }' );">入库记录</a></li>
                    <li id="header_product_operationLog_id"><a href="javascript: commonUtil.refreshCurrPage( '${ctxPath }/operationLog/sale.operationLog.html?_t=${random }' );">异常操作</a></li>
                </ul>
            </div>
            <div class="fr tui-c" ><a href="javascript: commonUtil.refreshCurrPage( '${ctxPath }/login/login.login.html?_t=${random }' );"><img src="${ctxPath }/img/icon-img/tui-c.png" alt="" />退出</a></div>
        </div>
    </div>
    
    <script type="text/javascript" src="${ctxPath }/js/common.js"></script>