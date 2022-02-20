<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "user.UserDAO" %>
<%@ page import= "util.SHA256" %>
<%@ page import= "java.io.PrintWriter" %>

<%

  request.setCharacterEncoding("utf-8");
  String code = null;
  if(request.getParameter("code") != null){
	  code = request.getParameter("code");
  }
  UserDAO userDAO = new UserDAO();
  String userID= null;
  
  if(session.getAttribute("userID") != null){
	  userID = (String)session.getAttribute("userID");
  }
  
  if(userID == null){
      PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('로그인을 해주세요.');");
      script.print("location.href = 'userLogin.jsp'");
      script.print("</script>");
      script.close(); 
  }
  
  String userEmail = userDAO.getUserEmail(userID);
  boolean isRight = (new SHA256().getSHA256(userEmail).equals(code))? true : false;
  if(isRight == true){
	  userDAO.setUserEmailCheked(userID);
	  PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('인증에 성공했습니다.');");
      script.print("location.href = 'index.jsp'");
      script.print("</script>");
      script.close();    
  }else{
	  PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('유효하지 않는 코드입니다.');");
      script.print("location.href = 'index.jsp'");
      script.print("</script>");
      script.close();    
	  
  }
   %>
