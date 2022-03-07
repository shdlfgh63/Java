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

//-----------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------
@WebServlet("/json2")
public class JsonServlet2 extends HttpServlet {
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
	//-----------------------------------------------------------------------------------------------------------
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		
		// 배열을 저장할 totalObject를 선언한다.
		JSONObject	totalObject		= new JSONObject();
		// memberInfo JSON 객체를 저장할 membersArray 배열을 선언한다.
		JSONArray	membersArray	= new JSONArray();
		// 회원 한 명의 정보가 들어갈 memberInfo JSON 객체를 선언한다.
		JSONObject	memberInfo		= new JSONObject();
		
		// 회원 정보를 name/value 의 쌍으로 저장한다.
		memberInfo.put("name", 		"이수일");
		memberInfo.put("age", 		"28");
		memberInfo.put("gender", 	"남자");
		memberInfo.put("nickname",	"멋쟁이");
		// 배열에 입력한다.
		membersArray.add(memberInfo);
		
		memberInfo = new JSONObject();
		memberInfo.put("name", 		"심순애");
		memberInfo.put("age", 		"24");
		memberInfo.put("gender", 	"여자");
		memberInfo.put("nickname",	"멋진여자");
		membersArray.add(memberInfo);
		
		// totalObject에 members라는 name으로 membersArray를 value로 저장시킨다.
		totalObject.put("members", membersArray);
		
		// JSONObject를 문자열로 변환한다.
		String jsonInfo = totalObject.toJSONString();
		System.out.println(jsonInfo);
		// JSON 데이터를 브라우저로 전송한다.
		writer.print(jsonInfo);
	}
}
