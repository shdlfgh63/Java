
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDAO"%>
<%@ page import="evaluation.EvaluationDAO"%>
<%@ page import="likey.LikeyDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%!
  public static String getClientIP(HttpServletRequest request){
	String ip = request.getHeader("X-FOWARDED-FOR");
	if(ip==null || ip.length()==0){
	   ip = request.getHeader("Proxy-Client-IP");
	}
	if(ip==null || ip.length()==0){
		   ip = request.getHeader("WL-Proxy-Client-IP");
	}
	if(ip==null || ip.length()==0){
		   ip = request.getRemoteAddr();
	}
	return ip;
}
%>
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
  LikeyDAO likeyDAO = new LikeyDAO();
  int result = likeyDAO.like(userID, evaluationID, getClientIP(request));
	  if(result==1){
		  result = evaluationDAO.like(evaluationID);
		  if(result==1){
			  PrintWriter script = response.getWriter();   
		      script.print("<script>");
		      script.print("alert('추천이 완료되었습니다.');");
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
	      script.print("alert('이미 추천을 누른 글입니다.');");
	      script.print("history.back();");
	      script.print("</script>");
	      script.close();  
	  } 
  
 
   %>