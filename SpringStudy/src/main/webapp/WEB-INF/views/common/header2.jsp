<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%	request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>header</title>
	
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
	
</head>
<body>

<nav class="navbar navbar-inverse navbar-sticky-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-car"></span>
				<span class="icon-car"></span>
				<span class="icon-car"></span>
			</button>
			<a class="navbar-brand" href="#">
				<img alt="Brand" src="${contextPath}/resources/images/logo.gif"/ style="width:170px; height:40px"; />도서쇼핑몰</a>
		</div>
		<div class="form-group collapse navbar-collapse" id="myNavbar">
			<form class="navbar-form navbar-right" method="post" action="${contextPath}/member/loginForm.do">
				<div class="form-group">
					<input type="text" class="form-control" name="id" size="10" maxlength="10" placeholder="아이디"/>
					<input type="password" class="form-control" name="passwd" size="12" maxlength="20" placeholder="비밀번호"/>
				</div>
				<a href="" class="btn btn-success"><span class="glyphicon glyphicon-list-alt"></span> 책방 둘러보기</a>
				<button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-log-in"></span> 로그인</button>
				<a href="${contextPath}/member/memberForm.do"" class="btn btn-warning"><span class="glyphicon glyphicon-user"></span> 회원가입</a>
				<a href="${contextPath}/member/listMembers.do"" class="btn btn-info"><span class="glyphicon glyphicon-user"></span> 회원목록</a>
			</form>
		</div>
	</div>
</nav>


<table border="0" width="100%">
	<tr>
		<td>
			<a href="${contextPath}/main.do">
				<img src="${contextPath}/resources/images/logo.gif"/>
			</a>
		</td>
		<td>
			<h1><font size="30">Book Shop</font></h1>
		</td>
		<td>
			<c:choose>
				<c:when test="${isLogOn == true && member != null}">
					<h3>${member.name}님, 환영합니다.</h3>
					<a href="${contextPath}/member/logout.do"><h3>로그아웃</h3></a>
				</c:when>
				<c:otherwise>
					<a href="${contextPath}/member/loginForm.do"><h3>로그인</h3></a>
				</c:otherwise>
			</c:choose>
		</td>
	</tr>
</table>

</body>
</html>




