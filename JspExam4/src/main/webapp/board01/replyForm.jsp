<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>답글 달기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	$(function() {
		/* alert("replyForm 시작"); */
	});
	
	function readURL(input) {
		if(input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}

	function backToList(obj) {
		obj.action = "${contextPath}/board/listArticles.do";
		obj.submit();
	}

	</script>
</head>
<body>
	<h1 style="text-align:center">답글 쓰기</h1>
	<form name="frmReply" method="post" action="${contextPath}/board/addReply.do"
				enctype="multipart/form-data">
		<table align="center">
			<tr>
				<td align="right">글쓴이</td>
				<td colspan="2"><input type="text" size="20" maxlength="50" value="hong" disabled/></td>
			</tr>
			<tr>
				<td align="right">제 목</td>
				<td colspan="2"><input type="text" size="66" maxlength="100" name="title"/></td>
			</tr>
			<tr>
				<td align="right" valign="top">내 용</td>
				<td colspan="2"><textarea name="content" rows="10" cols="100" maxlength="4000"></textarea></td>
			</tr>
			<tr>
				<td align="right">이미지파일 첨부</td>
				<td><input type="file" name="imageFileName" onchange="readURL(this);"/></td>
				<td><img id="preview" width="200" height="200"/></td>

			</tr>	
			<tr>
				<td align="right"></td>
				<td>
					<input type="submit" value="답글반영하기"/>
					<input type="button" value="취소" onClick="backToList(this.form)" />
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>











