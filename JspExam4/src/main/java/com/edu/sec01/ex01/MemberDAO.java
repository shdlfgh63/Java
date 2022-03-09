package com.edu.sec01.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
   private DataSource        dataFactory;
   private Connection        conn;
   private PreparedStatement pstmt;
   private ResultSet rs=null;
   public MemberDAO() {
	   try {
		   
		   Context ctx = new InitialContext();
		   Context env = (Context) ctx.lookup("java:/comp/env");
		   dataFactory = (DataSource) env.lookup("jdbc/oracle");
		   
	} catch (Exception e) {
		e.printStackTrace();
	}
	 	   
   }
   
   public List<MemberVO> listMembers(){
	   List<MemberVO> membersList = new ArrayList();
	   try {
		   conn= dataFactory.getConnection();
		   String sql="";
		   sql="SELECT * FROM t_member ORDER BY joinDate DESC";
		   pstmt = conn.prepareStatement(sql);
		   rs = pstmt.executeQuery();
		   
		while(rs.next()) {
			 String id = rs.getString("id");
			 String pwd = rs.getString("pwd");
			 String name = rs.getString("name");
			 String email = rs.getString("email");
			 Date joinDate = rs.getDate("joinDate");
			 
			 MemberVO memberVO = new MemberVO(id,pwd,name,email,joinDate);
			 membersList.add(memberVO);
		  }
		rs.close();
		pstmt.close();
		conn.close();
	} catch (SQLException sql) {
		sql.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
	   
	   return membersList;
	   
   }



}
