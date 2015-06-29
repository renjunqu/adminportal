<%--
add by figo
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"></c:set>

<!-- ajax layout which only needs content area -->
<div class="page-header" style="margin-top: 200px;text-align: center;">
	<h1>
		Soda 运营平台欢迎您
	</h1>
</div>

<script type="text/javascript">
	var scripts = [ null ]
	$('.page-content-area').ace_ajax('loadScripts', scripts, function() {

	});
</script>
