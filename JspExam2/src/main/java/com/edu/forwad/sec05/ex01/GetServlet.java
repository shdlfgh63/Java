package com.edu.forwad.sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cget")
public class GetServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		ServletContext context = getServletContext();
		
		List member =(ArrayList) context.getAttribute("member");
		String name =(String)member.get(0);
		int age = (int) member.get(1);

        out.print("<html>");
        out.print("<body>");
        out.print("<h1>ServletContext에서 바인딩된 데이터 가져오기</h1>");
        out.print("<h2>이름 : "+name+"</h2>");
        out.print("<h2>나이 : "+age+"</h2>");
        out.print("</body>");
        out.print("</html>");
	    
	}

}
