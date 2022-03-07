<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
 #(function(){
	$("#checkJson").click(function(){
		var jsonStr = '{"name": "홍길동", "age":22, "gender":"남자","nickname":"의적"}';
		var jsonObj = JSON.parse(jsonStr);
		var output= "<h1>회원 정보</h1>"
		
		output += "==============================<br/>";
		output += "<h2>이름 : "+jsonObj.name+"</h2>";
		output += "<h2>나이 : "+jsonObj.age+"</h2>";
		output += "<h2>성별 : "+jsonObj.gender+"</h2>";
		output += "<h2>닉네임 : "+jsonObj.nickname+"</h2>";
		$("#output").html(output);
	});
 });
</script>
<title>json03.jsp</title>
</head>
<body>
<h1>JSON 객체에 회원 정보를 저장한후 다시 보내기</h1>
 <a id="checkJson" style="cursor:pointer">출력</a>
 <hr>
 <div id="output"></div>
</body>
</html>