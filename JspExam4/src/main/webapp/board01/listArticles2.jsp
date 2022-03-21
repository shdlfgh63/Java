<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"	uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" 	value="${pageContext.request.contextPath}"/>
<%-- HashMpa으로 저장해서 넘어온 값들의 이름이 길기때문에 <c:set>태그를 이용해서 
	 각 값들을 짧은 변수이름으로 저장한다. --%>
<c:set var="articleList"	value="${articlesMap.articleList}"/>
<c:set var="totArticles"	value="${articlesMap.totArticles}"/>
<c:set var="section"		value="${articlesMap.section}"/>
<c:set var="pageNum"		value="${articlesMap.pageNum}"/>

<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시글 목록 ${totArticles}</title>
	<style>
	.no-uline	{	text-decoration:	none;	}
	/* 선택된 페이지 번호를 빨간색으로 표시한다.	*/
	.sel-page	{	text-decoration:	none;	color:		red;	}
	.cls1		{	text-decoration:	none;	}
	.cls2		{	text-align:			center;	font-size:	30px;	}
	</style>
</head>
<body>
	<h1 align="center">게시글 목록 (Paging)</h1>
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
	
	<div class="cls2">
		<c:if test="${totArticles != null}">
			<%-- 글의 개수가 100개를 넘을 경우 --%>
			<c:when test="${totArticles > 100}"> 
				<c:forEach var="page" begin="1" end="10" step="1">
					<c:if test="${section > 1 && page == 1}">
						<%-- ????????????????????????????????????????????? --%>
						<a class="no-uline" href="${contextPath}/board/listArticles.do
						?section=${section-1}&pageNum=${(section-1)*10+1}">[이전]</a>
					</c:if>
					<a class="no-uline" href="${contextPath}/board/listArticles.do
					?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
					<c:if test="${page == 10}">
						<a class="no-uline" href="${contextPath}/board/listArticles.do
						?section=${section+1}&pageNum=${section*10+1}">[다음]</a>
					</c:if>
				</c:forEach>
			</c:when>	
			
			<%-- 등록된 글의 개수가 100개인 경우는 첫 번째 섹션의 10개 페이지만 표시하면 된다. --%>
			<c:when test="${totArticles == 100}">
				<c:forEach var="page" begin="1" end="10" step="1">
					<a class="no-uline" href="#">${page}</a>
				</c:forEach>
			</c:when>
				
			<%-- 등록된 글의 개수가 100개 미만인 경우는 
				 전체 글수를 10으로 나누어 구한 몫에 1을 더한 페이지까지 표시한다.--%>
			<c:when test="${totArticles < 100}">
				<c:forEach var="page" begin="1" end="${totArticles / 10 + 1}" step="1">
					<c:choose>
						<%-- 페이지 번호와 컨트롤러에서 넘어온 페이지 값이 같으면 빨간색으로 표시한다. --%>
						<c:when test="${page = pageNum}">
							<a class="sel-page" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${page}</a>
						</c:when>
						<c:otherwise>
							<a class="no-uline" href="${contextPath}/board/listArticles.do?section=${section}&pageNum=${page}">${page}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:when>
		</c:if>
	</div>
	
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













