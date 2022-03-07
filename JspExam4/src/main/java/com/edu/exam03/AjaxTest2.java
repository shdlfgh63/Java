package com.edu.exam03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//-----------------------------------------------------------------------------------------------------------
//XML 데이터 연동하기
//-----------------------------------------------------------------------------------------------------------
@WebServlet("/ajaxTest2")
public class AjaxTest2 extends HttpServlet {
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
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		String	result = "";
		
		System.out.println("doHandle Start.....");
		// Client에 넘겨줄 자료를 xml형식으로 만든다.
		// Character Data : 문자 데이터를 말한다. <![CDATA[ ...... ]]>
		result 	= "<main>" 
				+ "<book>"
				+ "<title> <![CDATA[Java Programming]]> </title>"
				+ "<writer> <![CDATA[좋은 출판사 : 김자바 ]]> </writer>"
				+ "<image> <![CDATA[http://localhost:8099/Images/cat.jpg]]> </image>"
				+ "</book>"
				+ "<book>"
				+ "<title> <![CDATA[모두의 파이썬]]> </title>"
				+ "<writer> <![CDATA[멋진 출판사 : 박파이]]> </writer>"
				+ "<image> <![CDATA[http://localhost:8099/Images/image2.jpg]]> </image>"
				+ "</book>"
				+ "</main>";
		System.out.println(result);
		writer.print(result);
				
	}
}
