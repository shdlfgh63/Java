<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.edu.sec02.ex02.MemberVO" %>
<%@ page import="com.edu.sec02.ex02.MemberDAO" %>
<%
String id =request.getParameter("id");
MemberDAO memberDAO = new MemberDAO();
MemberVO member = memberDAO.getMember(id);
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원 정보 수정</title>
	<script type="text/javascript">
	function checkSendMember() {
		// alert("회원입력 버튼이 눌렸습니다.");
		var formMember = document.formMember;
		var	id		= formMember.id.value;
		var	pwd		= formMember.pwd.value;
		var	name	= formMember.name.value;
		var	email	= formMember.email.value;
		
		if(id.length == 0 || id == "") {
			alert("아이디는 꼭 입력하셔야 합니다.");
		} else if(pwd.length == 0 || pwd == "") {
			alert("비밀번호는 꼭 입력하셔야 합니다.");
		} else if(name.length == 0 || name == "") {
			alert("이름은 꼭 입력하셔야 합니다.");
		} else if(email.length == 0 || email == "") {
			alert("이메일은 꼭 입력하셔야 합니다.");
		} else {
			formMember.method = "post";
			formMember.action = "/member";
			formMember.submit();
		}
	}
	</script>
</head>
<body>
	<h1>회원정보수정</h1><hr>
	<form name="formMember">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" value="<%=member.getId()%>" readonly/></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd" value="<%=member.getPwd() %>"/></td>
			</tr>
			<tr>
				<td>이 름</td>
				<td><input type="text" name="name" value="<%=member.getName() %>"/></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="email" value="<%=member.getEmail() %>"/></td>
			</tr>
		</table>
		<input type="button" value="수정하기" onclick="checkSendMember()"/>
		<input type="reset"  value="다시입력"/>
		<input type="hidden" name="command" value="upMember"/>
	</form>
</body>
</html>
