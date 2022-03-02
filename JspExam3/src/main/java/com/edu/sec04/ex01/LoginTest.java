 package com.edu.sec04.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset= utf-8");
		PrintWriter out = response.getWriter();		
		HttpSession session = request.getSession();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		LoginImple loginUser = new  LoginImple(user_id,user_pw);
		
		if(session.isNew()) {
			session.setAttribute("loginUser", loginUser);
		}
		
		// 자바스크립트의 setTimeout()함수를 이용하여 5초마다 서빌릿에 재요청을 하여 현재 접속자수를 표시한다.
				out.println("<html>");
				out.println("<head>");
				out.println("<script type='text/javascript'>");
				out.println("setTimeout('history.go(0);', 5000");
				out.println("</script>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>아 이 디 : " + user_id + "</h1>");
				// 총 접속자 수를 브라우저에 보여준다.
				out.println("<h2>접속자 수 :" + LoginImple.total_user + "</h1>");
				out.println("</body>");
				out.println("</html>");
		
		
	}

}
