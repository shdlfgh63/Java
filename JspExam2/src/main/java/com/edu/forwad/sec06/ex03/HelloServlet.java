package com.edu.forwad.sec06.ex03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	
	
	public void init(ServletConfig config) throws ServletException {
		String greeting = config.getInitParameter("greeting");
		System.out.println(greeting);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("<h1>Hello JSP</h1>");
	}

}
