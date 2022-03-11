<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set  var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 쓰기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	// jQuery를 이용하여 이미지 파일을 첨부할 때 미리보기 기능을 구현한다. 
	function readURL(input) {
		if(input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	
	function backToList(obj) {	// 게시글 목록보기
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit();
	}
	</script>
</head>
<body>
	<h1 style="text-align:center">게시글 쓰기</h1>
	<form name="articleForm" method="post" action="${contextPath}/board/addArticle.do"
				enctype="multipart/form-data">
		<table border="0" align="center">
			<tr>
				<td align="right">글제목</td>
				<td colspan="2"><input type="text" size="68" maxlength="100" name="title"/></td>
			</tr>		
			<tr>
				<td align="right">글내용</td>
				<td colspan="2"><textarea name="content" rows="10" cols="100" maxlength="4000"></textarea></td>
			</tr>	
			<tr>
				<td align="right">이미지파일 첨부</td>
				<td><input type="file" name="imageFileName" onchange="readURL(this);"/></td>
				<td><img id="preview" src="#" width="200" height="200"/></td>
			</tr>	
			<tr>
				<td align="right"></td>
				<td colspan="2">
					<input type="submit" value="게시글 쓰기"/>
					<input type="button" value="게시글 목록보기" onClick="backToList(this.form)"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
