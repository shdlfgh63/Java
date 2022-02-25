package com.edu.forwad.sec04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-----------------------------------------------------------------------------------------------------------
// public class ViewServlet
//-----------------------------------------------------------------------------------------------------------
@WebServlet("/viewMembers")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//-----------------------------------------------------------------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	//-----------------------------------------------------------------------------------------------------------
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	//-----------------------------------------------------------------------------------------------------------
	// get, post요청을 모두 이곳에서 처리한다.
	//-----------------------------------------------------------------------------------------------------------
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 바인딩해서 넘어온 request에서 회원 정보 데이터를 가져온다.
		List membersList = (List) request.getAttribute("memberList");
		
		out.print("<html>");
		out.print("<body>");
		out.print("<table border=1>");
		out.print("<tr align='center' bgcolor='lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이 름</td><td>이메일</td><td>가입일</td><td>탈퇴</td><td>수정</td>");
		out.print("</tr>");
		
		// 가져온 데이터를 반복해서 화면에 보여준다.
		for(int i = 0; i < membersList.size(); i++) {
			MemberVO memberVO = (MemberVO) membersList.get(i);
			String	id			= memberVO.getId();
			String	pwd			= memberVO.getPwd();
			String	name		= memberVO.getName();
			String	email		= memberVO.getEmail();
			Date	joinDate	= memberVO.getJoinDate();
			
			out.print("<tr>");
			out.print("<td>"	+ id		+ "</td>");
			out.print("<td>"	+ pwd		+ "</td>");
			out.print("<td>"	+ name		+ "</td>");
			out.print("<td>"	+ email		+ "</td>");
			out.print("<td>"	+ joinDate	+ "</td>");
			out.print("<td>"	+ "<a href='member?command=delMember&id="			+ id + "'>탈퇴</a></td>");
			out.print("<td>"	+ "<a href='/sec02/ex02/memberUpdateForm2.jsp?id="	+ id + "'>수정</a></td>");
			out.print("</tr>");
		}
		
		out.print("</table>");
		out.print("<a href='/sec02/ex02/MemberForm.html'>회원가입</a>");
		out.print("</body>");
		out.print("</html>");
		
		
	} // End - protected void doGet(HttpServletRequest request, HttpServletResponse response)
}
