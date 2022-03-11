<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 목록</title>
	<style>
	.cls1	{
		text-decoration:	none;	}
	}
	.cls2	{
		text-align:			center;
		font-size:			30px;
	}
	</style>
</head>
<body>
	<table align="center" border="1" width="80%">
		<tr height="10" align="center" bgcolor="lightgreen">
			<td>글번호</td>
			<td>작성자</td>
			<td>제 목</td>
			<td>작성일</td>
		</tr>
		<c:choose>
			<%-- 게시글 데이터가 한 건도 없으면 --%>
			<c:when test="${empty articlesList}">
		<tr height="10">
			<td colspan="4">
				<p align="center">
					<b><span style="font-size:10pt;">등록된 글이 없습니다.</span></b>
				</p>
			</td>
		</tr>
			</c:when>
			<%-- 게시글 데이터가 있으면 --%>
			<c:when test="${!empty articlesList}">
				<c:forEach var="article"  items="${articlesList}" varStatus="articleNum">
					<tr align="center">
						<td width="5%">${articleNum.count}</td>
						<td width="10%">${article.id}</td>
						<td align="left" width="35%">
							<span style="padding-right:30px"></span>
							<c:choose>
								<%-- 답글인 경우 --%>
								<c:when test="${article.level > 1}">
									<c:forEach begin="1" end="${article.level}" step="1">
										<span style="padding-left:20px"></span>
									</c:forEach>
									<span style="font-size:12px;">[답글]</span>
									<a class="cls1" href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
								</c:when>
								<%-- 답글이 아닌 경우 --%>
								<c:otherwise>
									<a class="cls1" href="${contextPath}/board/viewArticle.do?articleNO=${article.articleNO}">${article.title}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td width="10%"><fmt:formatDate value="${article.writeDate}" pattern="yyyy년MM월dd일"/></td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
		
	</table>
	<a class="cls1" href="${contextPath}/board/articleForm.do"><p class="cls2">글쓰기</p></a>
</body>
</html>

<%
/*	varStatus="상태용 변수"
varStatus="articleNum"
${articleNum.current}	현재 for문의 해당하는 번호
${articleNum.index}		0 부터 시작하는 순서
${articleNum.count}		1 부터 시작하는 순서
${articleNum.first}		첫번째 인지의 여부
${articleNum.last}		마지막 인지의 여부
${articleNum.begin}		for문의 시작번호
${articleNum.end}		for문의 끝번호
${articleNum.step}		for문의 증가값

<fmt:formatDate value="${article.writeDate}" pattern="yyyy년MM월dd일 hh시mm분ss초"/>

http://localhost:8080/board01/listArticles.jsp

http://localhost:8080/board/listArticles.do

*/
%>