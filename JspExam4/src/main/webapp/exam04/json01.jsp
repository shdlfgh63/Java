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
		var jsonStr = '{"name":["노일호","오수빈","노민호"]}';
		
		var jsonInfo = JSON.parse(jsonStr);
		
		var output = "<h1>회원 명단</h1>"
		output +="=================================<br/>";
		
		for(var cnt in jsonInfo.name){
			output+=jsonInfo.name[cnt] + "<br/>"
		}
		$("#output").html(output);
	});
});

</script>
<title>Insert title here</title>
</head>
<body>
   <h1>JSON 배열 문자열을 저장한 후 웹 페이지에 출력해보자!</h1>
   <a id="checkJSON" style="cursor:pointer">출력</a>
   <hr>
   <div id="output"></div>
   
</body>
</html>