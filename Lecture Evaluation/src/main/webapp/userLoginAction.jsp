<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "user.UserDTO" %>
<%@ page import= "user.UserDAO" %>
<%@ page import= "util.SHA256" %>
<%@ page import= "java.io.PrintWriter" %>

<%

  request.setCharacterEncoding("utf-8");
  String userID = null;
  String userPassword=null;
  if(request.getParameter("userID") != null){
	  userID = request.getParameter("userID");
  }
  if(request.getParameter("userPassword") != null){
	  userPassword = request.getParameter("userPassword");
  }	  
  
  if(userID == null || userPassword==null || userID == "" || userPassword=="" ){
      PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('입력이 안된 사항이 있습니다.');");
      script.print("history.back();");
      script.print("</script>");
      script.close(); 
  }
  
  UserDAO userDAO = new UserDAO();
  int result = userDAO.login(userID,userPassword);
  if(result== 1){
	  session.setAttribute("userID", userID);
	  PrintWriter script = response.getWriter();   
      script.print("<script>");    
      script.print("location.href = 'index.jsp';");
      script.print("</script>");
      script.close();        
  }
  else if(result==0){
	  PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('비밀번호가 틀립니다.');");
      script.print("history.back();");
      script.print("</script>");
      script.close();      
   }
  else if(result==-1){
		  PrintWriter script = response.getWriter();   
	      script.print("<script>");
	      script.print("alert('존재하지 않는 아이디입니다');");
	      script.print("history.back();");
	      script.print("</script>");
	      script.close();
	   }
  else if(result==-2){
	  PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('데이터베이스 오류가 발생했습니다');");
      script.print("history.back();");
      script.print("</script>");
      script.close();
   }
   %>
