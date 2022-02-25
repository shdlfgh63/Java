package com.edu.forwad.sec04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//-----------------------------------------------------------------------------------------------------------
// 두 서블릿 간의 회원 정보 조회 데이터 바인딩.
//-----------------------------------------------------------------------------------------------------------
@WebServlet("/member")
public class MemberServlet extends HttpServlet {
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
		
		MemberDAO dao = new MemberDAO();
		
		String	command	= request.getParameter("command");
		
		if(command != null && command.equals("addMember")) {	// 회원 가입
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
		} else if(command != null && command.equals("delMember")) { // 회원 탈퇴
			System.out.println("회원 탈퇴 요청.....");
			System.out.println("ID : " + request.getParameter("id"));

			String	id	= request.getParameter("id");
			dao.delMember(id);
		} else if(command != null && command.equals("upMember")) { // 회원 정보 수정
			String	id		= request.getParameter("id");
			String	pwd		= request.getParameter("pwd");
			String	name	= request.getParameter("name");
			String	email	= request.getParameter("email");
			
			MemberVO memberVO = new MemberVO();
			memberVO.setId(id);
			memberVO.setPwd(pwd);
			memberVO.setName(name);
			memberVO.setEmail(email);
			
			// 수정 요청이 된 데이터를 dao에게 넘겨서 작업하게 한다.
			dao.upMember(memberVO);
		}
		
		List memberList = dao.listMembers();			// 모든 회원의 정보를 가져온다.
		request.setAttribute("memberList", memberList);	// 가져온 회원들의 정보를 memberList에 담는다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewMembers");
		dispatcher.forward(request, response);
		
		
		
		
	} // End - private void doHandle(HttpServletRequest request, HttpServletResponse response)
	
} // End - public class MemberServlet 

