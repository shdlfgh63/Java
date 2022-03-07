<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	$(function() {
		alert("aaaa");
		$("#checkJson").click(function() {
			alert("bbbb");
			var jsonStr = '{"members": ['
						+ '{"name":"홍길동", "age":22, "gender":"남자", "nickname":"의적"},'
						+ '{"name":"이수일", "age":28, "gender":"남자", "nickname":"바람둥이"}'
						+ ']}';
			var jsonInfo = JSON.parse(jsonStr);
			var output = "<h1>회원 정보</h1>";
			output += "=======================================<br/>";
			for(var i in jsonInfo.members) {
				output	+= "<h3>이름 : "	+ jsonInfo.members[i].name		+ "</h3>";
				output	+= "<h3>나이 : "	+ jsonInfo.members[i].age		+ "</h3>";
				output	+= "<h3>성별 : "	+ jsonInfo.members[i].gender	+ "</h3>";
				output	+= "<h3>별명 : "	+ jsonInfo.members[i].nickname	+ "</h3>";
			}
			$("#output").html(output);
		});
	});
	</script>
</head>
<body>
	<h1>JSON 배열의 요소에 JSON 객체를 저장한 후에</h1>
	<h1>다시 배열에 접근하여 JSON 객체의 속성 값을 출력해보자!</h1>
	<a id="checkJson" style="cursor:pointer">출력</a>
	<hr>
	<div id="output"></div>
</body>
</html>