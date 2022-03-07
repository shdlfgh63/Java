<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(function(){
	$("#checkJSON").click(function(){
		
		var jsonStr='{"age" : [30,29,28]}';
		
		var jsonInfo = JSON.parse(jsonStr);
		
		var output = "<h1>회원 나이</h1>";
		output +="=================================<br/>";
		
		for(var cnt in jsonInfo.age){
			output += jsonInfo.age[cnt] + "<br/>"
		}
		$("#output").html(output);
	});
});
	
</script>
<title>json02.jsp</title>
</head>
<body>
<h1>정수 자료형 배열로 저장한 후 화면에 출력해보자!</h1>
 <a id ="checkJSON" style="cursor: pointer">출력</a>
 <hr>
 <div id="output"></div>
</body>
</html>