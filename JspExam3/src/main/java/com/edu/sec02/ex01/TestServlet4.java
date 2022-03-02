package com.edu.sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/*")
public class TestServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String context = request.getContextPath();        // context 이름만 가져온다.
		String url = request.getRequestURL().toString();  // 전체 url을 가져온다.
		String mapping = request.getServletPath();        // 서블릿 매핑 이름만 가져온다.
		String uri = request.getRequestURI();             //uri를 가져온다
		
		out.print("<html>");
		out.print("<head>");
		out.print("<title>Test Servlet 4</title>");
		out.print("</head>");
		out.print("<body>");
		out.print("<body bgcolor='magenta'>");
		out.print("<h1>Test Servlet 4</h1>");
		out.print("<h2>컨텍스트 명 : " + context +"</h2>");
		out.print("<h2>전체경로 : " + url +"</h2>");
		out.print("<h2>매핑 명 : " + mapping +"</h2>");
		out.print("<h2>URI : " + uri +"</h2>");
		out.print("</body>");
		out.print("</html>");
	}

}
