<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix = "s" uri="/struts-tags" %>
<%String basePath = request.getContextPath(); %>
<c:set var="random" value="<%=Math.random() %>"></c:set>
<c:set var="ctxPath" value="<%=basePath%>"></c:set>
<c:set var="tipSkin" value="default"></c:set>
<script type="text/javascript">
	ctxPath = '<%=basePath%>';
</script>
