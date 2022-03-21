package jspPrj.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jspPrj.web.entity.Notice;

public class NoticeService {
    public List<Notice> getNoticeList(){
    	 
    	return getNoticeList("title","", 1);
    }
    public List<Notice> getNoticeList(int page){
   	 
    	return getNoticeList("title","",page);
    }
    public List<Notice> getNoticeList(String field, String query,int page){
   	 
    	List<Notice> list = new ArrayList<>();
    	
    	String sql= "SELECT * FROM (SELECT  ROWNUM, N.* FROM(SELECT * FROM NOTICE"
    			+ " ORDER BY REGDATE DESC) N) WHERE NUM BETWEEN ? AND ?" ;
    	

		
		String driver  ="oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:XE";	
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

    	
    	return null;
    }
    
    public int getNoticeCount() {
    	
    	return getNoticeCount();    	
    }
    
    public int getNoticeCount(String field, String query) {
    	return 0;
    }
    
    public Notice getNotice(int id) {
    	String sql = "SELECT * FROM NOTICE WHERE ID=?";
    	return null;
    }
    
    public Notice getNextNotice(int id) {
    	String sql="SELECT * FROM NOTICE WHERE ID = (SELECT ID FROM NOTICE"
    			+ " WHERE REGDATE > (SELECT REGDATE FROM NOTICE WHERE ID=3)"
    			+ " AND ROWNIM=1)";
    	return null;
    }
    
    public Notice getPrevNotice(int id) {
    	String sql="SELECT ID FROM (SELECT * FROM NOTICE ORDER BY REGDATE DESC) "+
                   "WHERE REGDATE < (SELECT REGDATE FROM NOTICE WHERE ID=3)" +
    			   " AND ROWNUM =1";
    	return null;
    }
}
