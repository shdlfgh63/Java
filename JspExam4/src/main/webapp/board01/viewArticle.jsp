<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script>
	function backToList(obj) {
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit();
	}
	</script>
</head>
<body>

<form name="frmArticle" method="post" enctype="multipart/form-data">
	<table border="0" align="center">
		<tr>
			<td width="150" align="center" bgcolor="FF9933">글번호</td>
			<td>
				<input type="text" value="${article.articleNO}" disabled/>
				<input type="hidden" name="articleNO" value="${article.articleNO}"/>
			</td>
		</tr>
		<tr>
			<td width="150" align="center" bgcolor="FF9933">아이디</td>
			<td><input type="text" value="${article.id}" name="id" disabled/></td>
		</tr>
		<tr>
			<td width="150" align="center" bgcolor="FF9933">제 목</td>
			<td><input type="text" value="${article.title}" name="title" id="i_title" disabled/></td>
		</tr>
		<tr>
			<td width="150" align="center" bgcolor="FF9933">내용</td>
			<td><textarea rows="16" cols="80" name="content" id="i_content" disabled>${article.content}</textarea></td>
		</tr>
	</table>
</form>

</body>
</html>