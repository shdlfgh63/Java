package com.edu.sec03.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//-----------------------------------------------------------------------------------------------------------
// 파일 다운로드
//-----------------------------------------------------------------------------------------------------------
@WebServlet("/download.do")
public class FileDownController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String ARTICLE_IMAGE_REPO = "C:\\data\\article_image";

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
		
		// 이미지 파일명과 글 번호를 가져온다.
		String	imageFileName 	= (String) request.getParameter("imageFileName");
		String	articleNO		= (String) request.getParameter("articleNO");
		System.out.println("imagerFileName ==> " + imageFileName);
		
		OutputStream out = response.getOutputStream();
		
		String path = ARTICLE_IMAGE_REPO + "\\" + articleNO + "\\" + imageFileName;
		File imageFile = new File(path);
		
		response.setHeader("Cache-Controle", "no-cache");
		// 이미지 파일을 내려 받을 때 필요한 response에 헤더 정보를 설정한다.
		response.addHeader("Content-disposition", "attachement;fileName=" + imageFileName);
		
		FileInputStream in = new FileInputStream(imageFile);
		// 버퍼를 이용해서 한 번에 8kb씩 전송을 한다.
		byte[] buffer = new byte[1024*8];
		while(true) {
			int count = in.read(buffer);
			if(count == -1)
				break;
			out.write(buffer, 0, count);
		}
		//-----------------------------------------------------------------------------------------------------------
		// 스트림을 사용해서 파일을 다운받으면 반드시 닫아주어야 파일이 삭제된다.
		//-----------------------------------------------------------------------------------------------------------
		in.close();
		out.close();
	}
	
} // End - public class FileDownController





