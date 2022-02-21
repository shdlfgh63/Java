<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="javax.mail.*"%>
<%@ page import="java.util.Properties"%>
<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="util.Gmail"%>
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
  String reportTitle = null;
  String reportContent = null;
  if(request.getParameter("reportTitle")!=null){
	  reportTitle = request.getParameter("reportTitle");
  }
  if(request.getParameter("reportContent")!=null){
	  reportContent = request.getParameter("reportContent");
  }
  if(reportTitle==null || reportContent == null){
	  PrintWriter script = response.getWriter();   
      script.print("<script>");
      script.print("alert('입력이 안된 사항이 있습니다.');");
      script.print("history.back();");
      script.print("</script>");
      script.close(); 
  }
  
  
  String host ="http://localhost:8099/Lecture_Evaluation/";
  String from ="shdlfgh147@gmail.com";
  String to = "shdlfgh147@gmail.com";
  String subject = "강의 평가 사이트에서 접수된 신고 메일입니다";
  String content = "신고자 : "+userID +
		            "<br>제목 : "+reportTitle +
		            "<br>내용 : "+reportContent;
   
  
    Properties p = new Properties();
    p.put("mail.smtp.host", "smtp.gmail.com");
	p.put("mail.smtp.port", "465");
	p.put("mail.smtp.starttls.enable", "true");
	p.put("mail.smtp.auth", "true");
	p.put("mail.smtp.ssl.protocols", "TLSv1.2");
	p.put("mail.smtp.debug", "true");
	p.put("mail.smtp.socketFactory.port", "465");
	p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	p.put("mail.smtp.socketFactory.fallback", "false");
   
   try{
	   Authenticator auth = new Gmail();
	   Session ses = Session.getInstance(p,auth); 
	   ses.setDebug(true);
	   MimeMessage msg = new MimeMessage(ses);
	   msg.setSubject(subject);
	   Address fromAddr = new InternetAddress(from);
	   msg.setFrom(fromAddr);
	   Address toAddr = new InternetAddress(to);
	   msg.addRecipient(Message.RecipientType.TO,toAddr);
	   msg.setContent(content,"text/html;charset=utf-8");
	   Transport.send(msg);
	   
   }catch(Exception e){
	   e.printStackTrace();
	   PrintWriter script = response.getWriter();   
	      script.print("<script>");
	      script.print("alert('오류가 발생했습니다.');");
	      script.print("history.back();");
	      script.print("</script>");
	      script.close();	      
   }   
	   PrintWriter script = response.getWriter();   
	      script.print("<script>");
	      script.print("alert('정상적으로 신고 되었습니다.');");
	      script.print("history.back();");
	      script.print("</script>");
	      script.close();	     
   %>