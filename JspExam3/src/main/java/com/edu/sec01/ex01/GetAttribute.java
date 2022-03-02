package com.edu.sec01.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/get")
public class GetAttribute extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		
		ServletContext ctx = getServletContext();
		HttpSession session = request.getSession();
		
		String ctxMsg = (String)ctx.getAttribute("context");
		String sesMsg = (String)session.getAttribute("session");
		String reqMsg = (String)request.getAttribute("request");
		
		out.print("<h1>context 값 : " +ctxMsg+ "</h1>");
		out.print("<h1>session 값 : " +sesMsg+ "</h1>");
		out.print("<h1>request 값 : " +reqMsg+ "</h1>");
		
	}

}
