<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>side</title>
	<style>
	.no-underline	{
		text-decoration:	none;
		
	}
	</style>
</head>
<body>
	<h1>사이드 메뉴</h1>
	<h2>
	
		<a href="#" class="no-underline">회원 관리</a>
		<a href="#" class="no-underline">판매 관리</a>
		<a href="#" class="no-underline">구매 관리</a>
	
	</h2>
</body>
</html>
















