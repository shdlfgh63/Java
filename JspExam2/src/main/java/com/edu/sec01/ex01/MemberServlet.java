package com.edu.sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
//-----------------------------------------------------------------------------------------------------------
/*
<servlet>
	<servlet-name>member-get-servlet</servlet-name>
	<servlet-class>com.edu.sec01.ex01.MemberServlet</servlet-class>
</servlet>
<servlet-mapping>
	<servlet-name>member-get-servlet</servlet-name>
	<url-pattern>/member</url-pattern>
</servlet-mapping>
*/
//-----------------------------------------------------------------------------------------------------------
// public class MemberServlet
//-----------------------------------------------------------------------------------------------------------
//@WebServlet("/member")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//-----------------------------------------------------------------------------------------------------------
	// protected void doGet(HttpServletRequest request, HttpServletResponse response)
	//-----------------------------------------------------------------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter		out		= response.getWriter();
		MemberDAO		dao		= new MemberDAO();
		
		// 회원 정보를 가져온다.
		List<MemberVO>	list	= dao.listMembers();
		
		out.print("<html><body>");
		out.print("<table border=1>");
		out.print("<tr align='center' bgcolor='lightgrey'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이 름</td><td>이메일</td><td>가입일자</td></tr>");
		for(int i = 0; i < list.size(); i++) {
			MemberVO	memberVO	= (MemberVO) list.get(i);
			String		id			= memberVO.getId();
			String		pwd			= memberVO.getPwd();
			String		name		= memberVO.getName();
			String		email		= memberVO.getEmail();
			Date		joinDate	= memberVO.getJoinDate();
			
			out.print("<tr>"	+ "<td>"	+ id 		+ "</td>" 
								+ "<td>"	+ pwd		+ "</td>"
								+ "<td>"	+ name		+ "</td>"
								+ "<td>"	+ email		+ "</td>"
								+ "<td>"	+ joinDate	+ "</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("</body></html>");
		
	} // End - protected void doGet(HttpServletRequest request, HttpServletResponse response)

} // End - public class MemberServlet



