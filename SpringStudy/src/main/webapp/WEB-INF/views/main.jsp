<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib	prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%	request.setCharacterEncoding("UTF-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
  
  <!-- 메뉴바 하단에 페이지의 소개를 보여준다. -->
  <div class="container">
    <div class="jumbotron">
       <div class="container text-center">
          <h1>도서 쇼핑몰 </h1>
          <p>방문을 환영합니다.</p>
       </div>
     </div>
  </div>
  
  <!--  화면 중앙에 이미지를 보여준다. -->
  <div class="container">
      <div id="myCarousel" class="carouosel slide" data-ride="carousel">
          <!-- Indicators -->
             <ol class="carousel-indicators">
                 <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                 <li data-target="#myCarousel" data-slide-to="1"></li>
                 <li data-target="#myCarousel" data-slide-to="2"></li>
             </ol>
             
             <div class="carousel-inner">
                  <div class="item active">
                     <img class="img-responsive center-block" src="${contextPath}/resources/images/books01.jpg" width="100%" height="350px"/>
                     <!-- carousel에 설명을 달아준다. -->
                     <div class="carousel-caption">
                         <h2>책방 내부 시설 1</h2>
                     </div>
                  </div>
              </div>             
          <!-- Wrapper for slides -->
      </div>
  </div>
</body>
</html>