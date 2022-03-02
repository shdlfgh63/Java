package com.edu.sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/login")
public class LoginTest extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//POST 방식으로 한글 전송 시 인코딩 작업을 생략한다.
		//request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("LoginTest Servlet에서 post로 login요청을 받았습니다.");
		
		String user_name = request.getParameter("user_name");
		String user_pw = request.getParameter("user_pw");
		
		out.print("<html>");
		out.print("<body>");
		out.print("<h1>POST 방식으로 받아온 이름과 비밀번호</h1>");
		out.print("<h2>이름 : "+user_name+"</h2>");
		out.print("<h2>비밀번호 : "+user_pw+"</h2>");
		out.print("</body>");
		out.print("</html>");
		for (int i = 0; i < 1000000; i++) {
			for(int j=0;j<1000000;j++) {
				;
			}
			
		}
	}

}
