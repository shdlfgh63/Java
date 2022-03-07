package com.edu.exam03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//-----------------------------------------------------------------------------------------------------------
//회원 아이디 중복 검사
//-----------------------------------------------------------------------------------------------------------
@WebServlet(description = "회원 아이디 중복 검사", urlPatterns = { "/mem" })
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
	//-----------------------------------------------------------------------------------------------------------
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		String id = (String) request.getParameter("id");
		System.out.println("의뢰받은 id : " + id);
		
		MemberDAO memberDAO = new MemberDAO();
		// memberDAO에게 id가 존재하는지 검사를 의뢰한다.
		boolean overLappedID = memberDAO.overlappedID(id);
		
		if(overLappedID == true) {
			writer.print("Not_usable");	// 이미 사용 중인 아이디
		} else {
			writer.print("usable");		// 사용가능한 아이디
		}
		
	}
}