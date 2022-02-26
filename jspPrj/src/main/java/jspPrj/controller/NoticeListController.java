package jspPrj.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jspPrj.web.entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Notice> list = new ArrayList<>();
		
		String driver  ="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";
		String sql = "SELECT * FROM NOTICE";
		String user = "rho";
		String pwd = "0000";
		
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,user,pwd);
			Statement stmt = conn.createStatement();
			ResultSet rs =stmt.executeQuery(sql);

			while(rs.next()){ 
				int id = rs.getInt("id");
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
				
				list.add(notice);	
				
		   } 	

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);

		request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp").forward(request, response);
	}

}
