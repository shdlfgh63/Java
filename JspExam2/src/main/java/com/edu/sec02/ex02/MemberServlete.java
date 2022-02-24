package com.edu.sec02.ex02;

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
// public class MemberServlet
//-----------------------------------------------------------------------------------------------------------
@WebServlet("/member")
public class MemberServlete extends HttpServlet {
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
	// doHandle()
	//-----------------------------------------------------------------------------------------------------------
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter	out	= response.getWriter();
		MemberDAO	dao = new MemberDAO(); 
		
		String	command	= request.getParameter("command");
		
		// 회원 가입
		if(command != null && command.equals("addMember")) {
			// 회원 가입 화면에서 보내준 값을 가져온다.
			String	id		= request.getParameter("id");
			String	pwd		= request.getParameter("pwd");
			String	name	= request.getParameter("name");
			String	email	= request.getParameter("email");
			
			// 회원 가입 화면에서 받은 값들을 MemberVO 객체에 담는다.
			MemberVO memberVO	= new MemberVO();
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);
			// 회원정보를 담아서 dao에게 회원가입을 하게 한다.
			dao.addMember(memberVO);
		  }else if(command != null && command.equals("delMember")) {
			  System.out.println("회원 탈퇴 요청....");
			  String id = request.getParameter("id");
			  dao.delMember(id);
		}
		
		// 회원 정보를 가져온다.
		List<MemberVO>	list	= dao.listMembers();
		
		out.print("<html><body>");
		out.print("<table border=1>");
		out.print("<tr align='center' bgcolor='lightgrey'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이 름</td><td>이메일</td><td>가입일자</td><td>탈퇴</td><td>수정</td></tr>");
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
								+ "<td>"	+ joinDate	+ "</td>"
								+ "<td>"	+ "<a href='/member?command=delMember&id="+ id +"'>탈퇴</a>"+ "</td>"
								+ "<td>"	+ "<a href='/sec02/ex02/memberUpdateForm.jsp?id="+ id +"'>수정</a>"+ "</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		out.print("<a href='/sec02/ex02/MemberForm.html'>회원 등록하기</a>");
		out.print("</body></html>");

		
	} // End - doHandle()

} // End - public class MemberServlet



