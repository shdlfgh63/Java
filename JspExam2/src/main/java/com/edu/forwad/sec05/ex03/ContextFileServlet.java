package com.edu.forwad.sec05.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/cfile")
public class ContextFileServlet extends HttpServlet {
   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  request.setCharacterEncoding("utf-8");
	  response.setContentType("text/html; charset=utf-8");
	  PrintWriter out = response.getWriter();
	  ServletContext context = getServletContext();
	  
	  //해당 위치의 파일을 읽어들인다.
	  InputStream is = context.getResourceAsStream("/WEB-INF/bin/ini.txt");
	  BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
	  
	  String menu = null;
	  String menu_member = null;
	  String menu_order = null;
	  String menu_goods = null;
	  
	  while((menu = buffer.readLine()) != null) {
		  StringTokenizer tokens = new StringTokenizer(menu,",");
		  menu_member=tokens.nextToken();
		  menu_order = tokens.nextToken();
		  menu_goods = tokens.nextToken();			  
	  }
	  
	    out.print("<html>");
		out.print("<body>");
		out.print("<h1>파일에서 메뉴 불러오기</h1>");
		out.print("<table border=1 cellspacing=0>");
		out.print("<tr>메뉴 목록</tr>");
		out.print("<tr><td><h3>"+menu_member+"</h3></td></tr>");
		out.print("<tr><td><h3>"+menu_order+"</h3></td></tr>");
		out.print("<tr><td><h3>"+menu_goods+"</h3></td></tr>");
		out.print("</body>");
		out.print("</html>");
	  
	  
  }
   
}
