<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, com.edu.sec01.ex01.*" %>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>

<c:choose>
	<c:when test='${msg == "addMember"}'>
		<script>
		window.onload=function() {
			alert("회원정보등록이 완료되었습니다.");
		}
		</script>
	</c:when>
	<c:when test='${msg == "modMember"}'>
		<script>
		window.onload=function() {
			alert("회원정보수정이 완료되었습니다.");
		}
		</script>
	</c:when>
	<c:when test='${msg == "delMember"}'>
		<script>
		window.onload=function() {
			alert("회원정보삭제가 완료되었습니다.");
		}
		</script>
	</c:when>
</c:choose>


	<meta charset="UTF-8">
	<title>회원 정보</title>
	<style>
	.cls1 {	/* 회원 정보 제목	*/
		font-size:		40px;
		text-align:		center;
	}
	.cls2 {
		font-size:		20px;
		text-align:		center;
	}
	</style>
</head>
<body>

<p class="cls1">회원 정보</p>
<table align="center" border="1">
	<tr align="center" bgcolor="lightgreen">
		<td width="7%"><b>아이디</b></td>
		<td width="7%"><b>비밀번호</b></td>
		<td width="7%"><b>이 름</b></td>
		<td width="7%"><b>이메일</b></td>
		<td width="7%"><b>가입일자</b></td>
		<td width="7%"><b>수정</b></td>
		<td width="7%"><b>삭제</b></td>
	</tr>
<c:choose>
	<c:when test="${empty membersList}">
	<tr>
		<td colspan="5" align="center">
			<h3>등록된 회원이 없습니다.</h3>
		</td>
	</tr>
	</c:when>
	<c:when test="${!empty membersList}">
		<c:forEach var="member" items="${membersList}">
			<tr align="center">
				<td>${member.id }</td>
				<td>${member.pwd }</td>
				<td>${member.name }</td>
				<td>${member.email }</td>
				<td>${member.joinDate }</td>
				<td><a href="${contextPath}/member/modMemberForm.do?id=${member.id}">수정</a></td>
				<td><a href="${contextPath}/member/delMember.do?id=${member.id}">삭제</a></td>
			</tr>
		</c:forEach>
	</c:when>
</c:choose>
</table>
	<a href="${contextPath}/member/addMemberForm.do"><p class="cls2">회원 가입</p></a>
</body>
</html>