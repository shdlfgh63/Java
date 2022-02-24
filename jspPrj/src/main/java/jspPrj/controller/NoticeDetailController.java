package jspPrj.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspPrj.web.entity.Notice;
@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		String driver  ="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String sql = "SELECT * FROM NOTICE WHERE ID=?";
		String user = "rho";
		String pwd = "0000";
		
		

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,user,pwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs =pstmt.executeQuery();

			rs.next();

			String title = rs.getString("TITLE"); 
			Date regdate = rs.getDate("REGDATE"); 	
			String writerID = rs.getString("WRITER_ID");
			String hit = rs.getString("HIT"); 
			String content = rs.getString("CONTENT"); 
			String files = rs.getString("FILES");
			
			Notice notice = new Notice(
					id,
					title,
					regdate,					
					writerID,					
					hit,
					files,
					content);
			
			request.setAttribute("n", notice);
			/*
			request.setAttribute("title", title);
			request.setAttribute("regdate", regdate);
			request.setAttribute("writerID", writerID);
			request.setAttribute("hit", hit);
			request.setAttribute("files", files);
			request.setAttribute("content", content);
			 */
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
		// 전이하기 위한 두가지 방법
		//redirect
		
		//forward
		
		request.getRequestDispatcher("/notice/detail.jsp").forward(request, response);
		
	}

}
