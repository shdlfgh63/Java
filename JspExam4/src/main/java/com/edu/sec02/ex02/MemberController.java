package com.edu.sec02.ex02;

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
// public class MemberController
//-----------------------------------------------------------------------------------------------------------
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
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
			nextPage = "/test03/listMembers.jsp";
		} else if(action.equals("/addMemberForm.do")) { // 회원 가입 폼
			nextPage = "/test03/addMember.jsp";
		} else if(action.equals("/addMember.do")) {	// 회원 가입 정보 처리
			String	id		= request.getParameter("id");
			String	pwd		= request.getParameter("pwd");
			String	name	= request.getParameter("name");
			String	email	= request.getParameter("email");
			
			// 회원 가입에 필요한 정보들을 생성자를 통해서 저장한다.
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			
			// 회원 가입 메시지를 attribute에 저장한다.
			request.setAttribute("msg", "addMember");
			
			nextPage = "/member/listMembers.do";
		} else if(action.equals("/modMemberForm.do")) { // 회원 정보 수정 폼
			// 수정할 회원의 아이디를 가져온다.
			String	id = request.getParameter("id");
			// 요청된 아이디에 해당하는 정보를 DAO를 통해서 가져온다.
			MemberVO memInfo = memberDAO.findMember(id);
			request.setAttribute("memInfo", memInfo);	// 찾은 회원의 정보를 request에 담는다.
			nextPage = "/test03/modMember.jsp"; 	// 수정화면으로 이동
		} else if(action.equals("/modMember.do")) { 	//회원 정보 수정 작업
			// 넘겨 받은 회원의 정보를 MemberVO에 담아서 DAO에 넘겨준다.
			String 	id		= request.getParameter("id");
			String	pwd		= request.getParameter("pwd");
			String	name	= request.getParameter("name");
			String	email	= request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.modMember(memberVO);
			request.setAttribute("msg", "modMember");
			nextPage = "/member/listMembers.do";
		} else if(action.equals("/delMember.do")) {		//회원 정보 삭제 작업
			String id = request.getParameter("id");
			memberDAO.delMember(id);
			request.setAttribute("msg", "delMember");
			nextPage = "/member/listMembers.do";
		} else {
			List<MemberVO> membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		}
		// nextPage 변수를 통해서 포워딩을 공동으로 사용한다.
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
	} // End - private void doHandle(HttpServletRequest request, HttpServletResponse response)
	
} // End - public class MemberController


