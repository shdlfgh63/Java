<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 수정</title>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
	<form class="form-horizontal" method="post" name="memUpdateForm" action="${contextPath}/member/modMember.do">
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-5">
				<h2 align="center">회원 정보 수정</h2>
			</div>
		</div>
		<div class="form-group">
			<label for="id" class="col-sm-3 control-label">아이디</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="id" name="id" maxlength="10" value="${member.id}" readonly/>
			</div>
		</div>
		<div class="form-group">
			<label for="id" class="col-sm-3 control-label">비밀번호</label>
			<div class="col-sm-3">
				<input type="password" class="form-control" id="pwd" name="pwd" maxlength="20" value="${member.pwd}" placeholder="비밀번호 입력"/>
			</div>
		</div>
		<div class="form-group">
			<label for="id" class="col-sm-3 control-label">이  름</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="name" name="name" maxlength="50" value="${member.name}" placeholder="이름 입력"/>
			</div>
		</div>
		<div class="form-group">
			<label for="id" class="col-sm-3 control-label">이메일</label>
			<div class="col-sm-3">
				<input type="email" class="form-control" id="email" name="email" maxlength="50" value="${member.email}" placeholder="이메일 입력"/>
			</div>
		</div>
		<div class="form-group">
			<label for="id" class="col-sm-3 control-label">가입일자</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" id="joinDate" name="joinDate" value="${member.joinDate}" placeholder="가입일자" readonly/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-3 col-sm-4">
				<button type="reset"	class="btn btn-warning">다시입력</button>
				<button type="submit"	class="btn btn-primary">정보수정</button>
			</div>
		</div>
	</form>
</div>

</body>
</html>













