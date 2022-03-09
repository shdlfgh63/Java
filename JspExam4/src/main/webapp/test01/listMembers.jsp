<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.edu.sec01.ex01.*" %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<% request.setCharacterEncoding("utf-8"); %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>회원 정보</title>
<style>
 .cls1{
    font-size: 40px;
    text-align: center;
 }
 .cls2{
    font-size: 20px;
    text-align: center;
 }
</style>
</head>
<body>
   <p class="cls1">회원 정보</p>
   <table border="1" align="center">
     <tr align="center" bgcolor="lightgreen">
       <td width="7%">아이디</td>
       <td width="7%">비밀번호</td>
       <td width="7%">이 름</td>
       <td width="7%">이메일</td>
       <td width="7%">가입일자</td>
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
        <c:forEach var="member" items="${membersList }">
            <tr>
                <td>${member.id }</td>
                <td>${member.pwd }</td>
                <td>${member.name }</td>
                <td>${member.email }</td>
                <td>${member.joinDate }</td>
            </tr>            
        </c:forEach>
   </c:when>     
</c:choose>
</table>
  <p class="cls2"><a href="#">회원 가입</a></p>
</body>
</html>