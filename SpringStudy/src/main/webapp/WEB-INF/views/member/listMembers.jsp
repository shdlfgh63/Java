<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
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

<div class="container">
	<table class="table table-bordered table-striped table-hover" 
		style="width: 1200px; margin: auto;">
		<tr class="info">
			<td align="center" width="80"><b>아이디</b></td>
			<td align="center" width="80"><b>비밀번호</b></td>
			<td align="center" width="80"><b>이    름</b></td>
			<td align="center" width="300"><b>이메일</b></td>
			<td align="center" width="120"><b>가입일자</b></td>
			<td align="center" width="60"><b>수정</b></td>
			<td align="center" width="60"><b>삭제</b></td>
		</tr>
	<c:forEach var="member" items="${membersList}"	>
		<tr>
			<td align="center">${member.id}</td>
			<td align="center">${member.pwd}</td>
			<td align="center">${member.name}</td>
			<td align="left">${member.email}</td>
			<td align="center">${member.joinDate}</td>
			<td align="center"><a href="${contextPath}/member/updateMemberForm.do?id=${member.id}">수정</a></td>
			<td align="center"><a href="${contextPath}/member/removeMember.do?id=${member.id}">삭제</a></td>
		</tr>
	</c:forEach>
	</table>
</div>

</body>
</html>






