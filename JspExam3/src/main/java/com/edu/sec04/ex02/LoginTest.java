 package com.edu.sec04.ex02;

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
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List user_list = new ArrayList();
	
	ServletContext context = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset= utf-8");
		
		context=getServletContext();
		PrintWriter out = response.getWriter();		
		HttpSession session = request.getSession();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		LoginImple loginUser = new  LoginImple(user_id,user_pw);
		
		if(session.isNew()) {
			session.setAttribute("loginUser", loginUser);
			user_list.add(user_id);
			context.setAttribute("user_list",user_list);
		}
		
		// 자바스크립트의 setTimeout()함수를 이용하여 5초마다 서빌릿에 재요청을 하여 현재 접속자수를 표시한다.
				out.println("<html>");
				out.println("<head>");
			//	out.println("<script type='text/javascript'>");
			//	out.println("setTimeout('history.go(0);', 5000");
		    //	out.println("</script>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h1>아 이 디 : " + loginUser.user_id + "</h1>");
				// 총 접속자 수를 브라우저에 보여준다.
				out.println("<h2>접속자 수 :" + LoginImple.total_user + "</h2>");
				
				out.println("<h2>접속자들의 아이디</h2>");
				List list= (ArrayList)context.getAttribute("user_list");
				for (int i = 0; i < list.size(); i++) {
					out.println(list.get(i)+"<br/>");
				}
				out.println("<a href='logout?user_id="+user_id+"'>로그아웃</a>");
				out.println("</body>");
				out.println("</html>");
		
		
	}

}
