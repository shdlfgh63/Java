<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>Insert title here</title>
<script>
$(function(){
	$("#checkJson").click(function(){
		$.ajax({
			type: "post",
			async: false,
			url: "${contextPath}/json3",
			success: function(data, textStatus){
				var jsonInfo = JSON.parse(data);
				var memberInfo = "<h1>회원 정보</h1>"
				memberInfo +="==============================<br/>";
				for(var i in jsonInfo.members){
					memberInfo += "이름 : "+ jsonInfo.members[i].name+"<br/>";
					memberInfo += "나이 : "+ jsonInfo.members[i].age+"<br/>";
					memberInfo += "성별 : "+ jsonInfo.members[i].gender+"<br/>";
					memberInfo += "별명 : "+ jsonInfo.members[i].nickname+"<br/>"+"<br/>";		
				}
				
				var booksInfo = "<br/><br/><h1>도서 정보</h1>"
					booksInfo +="==============================<br/>";
				for(var i in jsonInfo.books){
					booksInfo += "제목 : "+ jsonInfo.books[i].title+"<br/>";
					booksInfo += "지은이 : "+ jsonInfo.books[i].writer+"<br/>";
					booksInfo += "가격 : "+ jsonInfo.books[i].price+"<br/>";
					booksInfo += "장르 : "+ jsonInfo.books[i].genre+"<br/>";
					imageUrl = jsonInfo.books[i].image;
					booksInfo +="<img src='"+imageUrl+"'/><br/><br/>";					
				}
				$("#output").html(memberInfo+"<br/>"+booksInfo);
			},
			error:   function(data, textStatus){
				alert("데이터 전송 중 문제가 발생하였습니다.");
			},
		});
	});
});
</script>
</head>
<body>
   <a id="checkJson" style = "cursor:pointer">데이터 수신하기</a>
   <hr>
   <div id="output"></div>
   <table border=1 width=400>
        <tr>
           <td>제목</td>
           <td>저자</td>
        </tr>
   </table>
</body>
</html>