package com.edu.sec02.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-----------------------------------------------------------------------------------------------------------
// public class MemberController2
//-----------------------------------------------------------------------------------------------------------
@WebServlet("/member/*")
public class MemberController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	MemberDAO memberDAO;

	//-----------------------------------------------------------------------------------------------------------
	// init()
	//-----------------------------------------------------------------------------------------------------------
	public void init() throws ServletException {
		memberDAO = new MemberDAO();
	}
	//-----------------------------------------------------------------------------------------------------------
	// doGet()
	//-----------------------------------------------------------------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	//-----------------------------------------------------------------------------------------------------------
	// doPose()
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
		
		// nextPage : 포워드될 페이지를 저장할 변수
		String	nextPage = null;
		// 액션(요청되는 url매핑)을 구별하기 위해서
		String	action	= request.getPathInfo();
		
		if(action == null || action.equals("/listMembers.do")) {	// 회원들의 정보 목록
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test02/listMembers.jsp";
		} else if(action.equals("/addMember.do")) {	// 회원 가입 정보 처리
			String	id		= request.getParameter("id");
			String	pwd		= request.getParameter("pwd");
			String	name	= request.getParameter("name");
			String	email	= request.getParameter("email");
			
			// 회원 가입에 필요한 정보들을 생성자를 통해서 저장한다.
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			nextPage = "/member/listMembers.do";
		} else if(action.equals("/addMemberForm.do")) { // 회원 가입 폼
			nextPage = "/test02/addMember.jsp";
		} else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test02/listMembers.jsp";
		}
		// nextPage 변수를 통해서 포워딩을 공동으로 사용한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
	} // End - private void doHandle(HttpServletRequest request, HttpServletResponse response)
	
} // End - public class MemberController2

