package com.edu.forwad.sec06.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-----------------------------------------------------------------------------------------------------------
//loadOnStartup=1 ==> 서블릿들 간에 초기화 우선 순위
//여러 서블릿들 사이에 <load-on-starup>값이 동일한 경우 web.xml에 먼저 선언된 서블릿이 먼저 초기화 된다.
//-----------------------------------------------------------------------------------------------------------
@WebServlet(name="loadConfig", urlPatterns = {"/loadConfig"}, loadOnStartup=1)
public class LoadAppConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private	ServletContext context;
	//-----------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------
	public void init(ServletConfig config) throws ServletException {

		System.out.println("LoadAppConfig의 init 메서드를 호출하였습니다.");
		context	= config.getServletContext();
		String	menu_member	= context.getInitParameter("menu_member");
		String	menu_order	= context.getInitParameter("menu_order");
		String	menu_goods	= context.getInitParameter("menu_goods");
		
		context.setAttribute("menu_member",	menu_member);
		context.setAttribute("menu_order", 	menu_order);
		context.setAttribute("menu_goods", 	menu_goods);
	}

	//-----------------------------------------------------------------------------------------------------------
	//-----------------------------------------------------------------------------------------------------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String	menu_member	= (String) context.getAttribute("menu_member");
		String	menu_order	= (String) context.getAttribute("menu_order");
		String	menu_goods	= (String) context.getAttribute("menu_goods");
		
		out.print("<html>");
		out.print("<body>");
		out.print("<h1>파일에서 메뉴 불러오기</h1>");
		out.print("<table border=1 cellspacing=0>");
		out.print("<tr>메뉴 목록</tr>");
		out.print("<tr><td><h3>" + menu_member	+ "</h3></td></tr>");
		out.print("<tr><td><h3>" + menu_order	+ "</h3></td></tr>");
		out.print("<tr><td><h3>" + menu_goods	+ "</h3></td></tr>");
		out.print("</table>");
		out.print("</body>");
		out.print("</html>");

	}

}