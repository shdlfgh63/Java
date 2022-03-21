<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<%	request.setCharacterEncoding("utf-8"); %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
	#tr_btn_modify { /* 수정반영버튼을 않보이게 한다.	*/
		display:	none;
	}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
	function backToList(obj) {
		obj.action="${contextPath}/board/listArticles.do";
		obj.submit();
	}
	/* 수정하기 버튼을 눌렀을 겨우 텍스트 박스를 활성화시킨다.	*/
	function fn_enable(obj) {
		document.getElementById("i_title").disabled = false;
		document.getElementById("i_content").disabled = false;
		if(document.getElementById("i_imageFileName")) {
			document.getElementById("i_imageFileName").disabled = false;
		}
		document.getElementById("tr_btn_modify").style.display = "block";
		document.getElementById("tr_btn").style.display = "none";
	}
	
	/* 수정반영하기 버튼을 누르면 컨트롤러에 수정된 데이터를 전송한다.	*/
	function fn_modify_article(obj) {
		obj.action = "${contextPah}/board/modArticle.do";
		obj.submit();
	}
	
	/* 삭제하기 버튼을 누르면 articleNO를 전달해서 함수를 실행시킨다.	*/
	function fn_remove_article(url, articleNO) {
		
		/* 자바스크립트를 이용하여 동적으로 <form>태그를 만든다.	*/
		var form = document.createElement("form");
		form.setAttribute("method",	"post");
		form.setAttribute("action",	url);
		/* 콘트롤러에 넘겨줄 게시글 번호를 <input>태그를 생성하여 넣는다.	*/
		var articleNOInput = document.createElement("input");
		articleNOInput.setAttribute("type",		"hidden");
		articleNOInput.setAttribute("name",		"articleNO");
		articleNOInput.setAttribute("value",	articleNO);
		
		alert("articleNO ===> " + articleNO);

		/* 동적으로 생성된 <input> 태그를 동적으로 생성한 <form>태그에 append한다.	*/
		form.appendChild(articleNOInput);
		document.body.appendChild(form);
		form.submit();
	}
	
	/* 답글 쓰기 화면 */
	function fn_reply_form(url, parentNO) {
		var form = document.createElement("form");
		form.setAttribute("method",	"post");
		form.setAttribute("action",	url);
		var parentNOInput = document.createElement("input");
		parentNOInput.setAttribute("type",	"hidden");
		parentNOInput.setAttribute("name",	"parentNO");
		parentNOInput.setAttribute("value",	parentNO);
		
		//alert("parentNO ===> " + parentNO);
		
		document.body.appendChild(form);
		form.appendChild(parentNOInput);
		form.submit();
	}
	</script>
</head>
<body>

<form name="frmArticle" method="post" enctype="multipart/form-data">
	<table border="0" align="center">
		<tr>
			<td width="150" align="center" bgcolor="#FF9933">글번호</td>
			<td>
				<input type="text" value="${article.articleNO}" disabled/>
				<%-- 글 수정시 글 번호를 컨트롤러로 전송하기 위해서 미리 <hidden> 태그를 이용해서 글 번호를 저장한다.--%>
				<input type="hidden" name="articleNO" value="${article.articleNO}"/>
			</td>
		</tr>
		<tr>
			<td width="150" align="center" bgcolor="#FF9933">아이디</td>
			<td><input type="text" value="${article.id}" name="id" disabled/></td>
		</tr>
		<tr>
			<td width="150" align="center" bgcolor="#FF9933">제 목</td>
			<td><input type="text" value="${article.title}" name="title" id="i_title" disabled/></td>
		</tr>
		<tr>
			<td width="150" align="center" bgcolor="#FF9933">내용</td>
			<td><textarea rows="16" cols="80" name="content" id="i_content" disabled>${article.content}</textarea></td>
		</tr>
		<c:if test="${not empty article.imageFileName && article.imageFileName != 'null'}">
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933" rowspan="2">이미지</td>
			<td>
				<%-- 이미지를 수정하는 것에 대비해서 원래 이미지 파일의 이름을 <hidden>태그에 저장해 놓는다. --%>
				<input type="hidden" name="originalFileName" value="${article.imageFileName}"/>
				<!-- FileDownController 서블릿에 이미지 파일 이름과 글 번호를 전송해서 이미지를 <img> 태그에 보여준다. -->
				<img src="${contextPath}/download.do?imageFileName=${article.imageFileName}&articleNO=${article.articleNO}" id="preview"/><br/>
			</td>
		</tr>
		<tr>
			<%-- 수정된 이미지 파일 이름을 전송한다. --%>
			<td><input type="file" name="imageFileName" id="i_imageFileName" disabled onchange="readRUL(this);"/></td>
		</tr>
		</c:if>
		<tr>
			<td width="20%" align="center" bgcolor="#FF9933">등록일자</td>
			<td><input type="text" value="<fmt:formatDate value="${article.writeDate}"/>" disabled/></td>
		</tr>
		<tr id="tr_btn_modify">
			<td colspan="2" align="center">
				<input type="button" value="수정반영하기"	onclick="fn_modify_article(frmArticle)"/>
				<input type="button" value="취소"			onclick="backToList(frmArticle)"/>
			</td>
		</tr>
		<tr id="tr_btn">
			<td colspan="2" align="center">
				<input type="button" value="수정하기" 	 onclick="fn_enable(this.form)"/>
				<input type="button" value="삭제하기" 	 onclick="fn_remove_article('${contextPath}/board/removeArticle.do', ${article.articleNO})"/>
				<input type="button" value="게시글목록" onclick="backToList(this.form)"/>
				<input type="button" value="답글쓰기"   onclick="fn_reply_form('${contextPath}/board/replyForm.do', ${article.articleNO})"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>












