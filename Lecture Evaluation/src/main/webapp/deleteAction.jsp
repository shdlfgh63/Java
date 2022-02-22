
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO"%>
<%@ page import="evaluation.EvaluationDAO"%>
<%@ page import="likey.LikeyDTO"%>
<%@ page import="java.io.PrintWriter"%>

<%
  UserDAO userDAO = new UserDAO();
  String userID=null;
  if(session.getAttribute("userID") != null){
	  userID = (String) session.getAttribute("userID");
  }
  if(userID == null){
	  PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('로그인을 해주세요.');");
      script.print("location.href = 'userLogin.jsp'");
      script.print("</script>");
      script.close(); 
  }
  request.setCharacterEncoding("utf-8");
  String evaluationID = null;
  String reportContent = null;
  if(request.getParameter("evaluationID")!=null){
	  evaluationID = request.getParameter("evaluationID");
  }
  EvaluationDAO evaluationDAO= new EvaluationDAO();
  if(userID.equals(evaluationDAO.getUserID(evaluationID))){
	  int result = new EvaluationDAO().delete(evaluationID);
	  if(result==1){
		  PrintWriter script = response.getWriter();   
	      script.print("<script>");
	      script.print("alert('삭제가 완료되었습니다.');");
	      script.print("location.href = 'index.jsp'");
	      script.print("</script>");
	      script.close();  
	  }else{
		  PrintWriter script = response.getWriter();   
	      script.print("<script>");
	      script.print("alert('데이터베이스 오류가 발생했습니다.');");
	      script.print("history.back();");
	      script.print("</script>");
	      script.close();  
	  }
  }else{
	  PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('자신이 쓴 글만 삭제 가능합니다.');");
      script.print("history.back();");
      script.print("</script>");
      script.close(); 
  }
 
   %>