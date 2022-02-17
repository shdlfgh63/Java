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
  String userEmail=null;
  if(request.getParameter("userID") != null){
	  userID = request.getParameter("userID");
  }
  if(request.getParameter("userPassword") != null){
	  userPassword = request.getParameter("userPassword");
  }	  
  if(request.getParameter("userEmail") != null){
	  userEmail = request.getParameter("userEmail");
  }
  
  if(userID == null || userPassword==null || userEmail ==null || userID == "" || userPassword=="" || userEmail ==""){
      PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('입력이 안된 사항이 있습니다.');");
      script.print("history.back();");
      script.print("</script>");
      script.close(); 
  }
  
  UserDAO userDAO = new UserDAO();
  int result = userDAO.join(new UserDTO(userID, userPassword, userEmail, SHA256.getSHA256(userEmail), false));
  if(result== -1){
	  PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('이미 존재하는 아이디입니다.');");
      script.print("history.back();");
      script.print("</script>");
      script.close();    
  }else{
	  session.setAttribute("userID", userID);
	  PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("location.href = 'emailSendAction.jsp'");
      script.print("</script>");
      script.close();
   }
   %>
