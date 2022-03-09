package com.edu.exam03.ex01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/json3")
public class JsonServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request,response); 		
	}
	

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		//배열을 최종적으로 저장할 JSONObjcect 객체를 생성
		JSONObject totalObject = new JSONObject();
		
		JSONArray membersArray = new JSONArray();
		JSONObject memberInfo = new JSONObject();
		
		memberInfo.put("name", "오수빈");
		memberInfo.put("age", 30);
		memberInfo.put("nickname", "인성박살");		
		membersArray.add(memberInfo);
		
		memberInfo = new JSONObject();
		memberInfo.put("name", "노일호");
		memberInfo.put("age", 30);
		memberInfo.put("nickname", "생불");		
		membersArray.add(memberInfo);
		
		//회원 정보를 저장한 배열을 배열 이름 members로 totalObject에 저장한다.
		totalObject.put("members",membersArray);
		
		
		JSONArray bookArray = new JSONArray();
		JSONObject bookInfo = new JSONObject();
		bookInfo.put("title", "자바 프로그래밍");
		bookInfo.put("writer", "김자바");
		bookInfo.put("price", 35000);
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8099/Images/자바.jpg");
		bookArray.add(bookInfo);
		
		bookInfo = new JSONObject();
		bookInfo.put("title", "모두의 파이썬");
		bookInfo.put("writer", "박파이");
		bookInfo.put("price", 27000);
		bookInfo.put("genre", "IT");
		bookInfo.put("image", "http://localhost:8099/Images/image2.jpg");
		bookArray.add(bookInfo);
		
		totalObject.put("books", bookArray);
		
		String jsonInfo= totalObject.toJSONString();
		System.out.println(jsonInfo); // 콘설에 출력
		writer.print(jsonInfo); //브라우저에게 totalObject 의 내용을 보여준다
	}

}
