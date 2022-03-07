<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	$(function() {
		$("#checkJson").click(function() {
			var _jsonInfo = '{"name":"홍길동", "age":22, "gender":"남자", "nickname":"의적"}';
			$.ajax({
				type:		"post",
				async:		false,
				url:		"${contextPath}/json1",
				data:		{jsonInfo: _jsonInfo},
				success:	function(data, textStatus) {
					alert("데이터 전송이 완료되었습니다.");
				},
				error:		function(data, textStatus) {
					alert("데이터 전송 중 오류가 발생하였습니다.");
				},
				complete:	function(data, textStatus) {
					alert("작업이 완료되었습니다.");
				}
			});
		});
	});
	</script>
</head>
<body>
	<a id="checkJson" style="cursor:pointer">전송</a>
	<hr>
	<div id="output"></div>
</body>
</html>




