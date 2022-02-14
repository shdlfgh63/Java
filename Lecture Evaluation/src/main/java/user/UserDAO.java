package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import util.DatabaseUtil;

public class UserDAO {
	
  public int login(String userID, String userPassword) {
	  String SQL = "SELECT userPassword from USER WHERE userID= ?";
	  Connection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  try {
		  conn= DatabaseUtil.getConnection();
		  pstmt = conn.prepareStatement(SQL);
		  pstmt.setString(1, userID);
		  rs=pstmt.executeQuery();
		  if(rs.next()) {
			  if(rs.getString(1).equals(userPassword)) {
				  return 1; //로그인 성공
			  }else {
				  return 0; //비밀번호 틀림
			  }
		  }
		  return -1; //아디 없음
		  
	  }catch(Exception e) {
		  e.printStackTrace();
	
    } finally {
    	try {if(conn !=null) conn.close();}catch(Exception e) {e.printStackTrace();}
    	try {if(conn !=null) conn.close();}catch(Exception e) {e.printStackTrace();}
    	try {if(rs !=null) rs.close();}catch(Exception e) {e.printStackTrace();}
    }
	  
	  
	  
	  return -2; //데이터베이스 오류
  }
  
  public int join(UserDTO user) {
		String SQL = "INSERT INTO USER VALUES(?, ?, ?, ?, false)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserEmail());
			pstmt.setString(4, user.getUserEmailHash());
			
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally { //성공 후 함수 해제해서 서버 무리안주기
			try { if(conn != null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try { if(pstmt != null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try { if(rs != null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1;//회원가입 실패
	}
  
  public boolean getUserEmailCheked(String userID) {
	  String SQL = "select userEmailcheked from user where userID=?";
	  Connection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  try {
		  conn= DatabaseUtil.getConnection();
		  pstmt = conn.prepareStatement(SQL);
		  pstmt.setString(1, userID);		  
		  rs= pstmt.executeQuery();
		  
		  if(rs.next()) {
			  return rs.getBoolean(1);
		  }
		 				  
	  }catch(Exception e) {
		  e.printStackTrace();
	
    } finally {
    	try {if(conn !=null) conn.close();}catch(Exception e) {e.printStackTrace();}
    	try {if(conn !=null) conn.close();}catch(Exception e) {e.printStackTrace();}
    	try {if(rs !=null) rs.close();}catch(Exception e) {e.printStackTrace();}
    }
	  
	  
	  return false; //데이터베이스 오류
  
  
 }
  
  public String getUserEmail(String userID) {
	  String SQL = "select userEmail from user where userID=?";
	  Connection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  try {
		  conn= DatabaseUtil.getConnection();
		  pstmt = conn.prepareStatement(SQL);
		  pstmt.setString(1, userID);		  
		  rs = pstmt.executeQuery();
		  return rs.getString(1);
		 		 				  
	  }catch(Exception e) {
		  e.printStackTrace();
	
    } finally {
    	try {if(conn !=null) conn.close();}catch(Exception e) {e.printStackTrace();}
    	try {if(conn !=null) conn.close();}catch(Exception e) {e.printStackTrace();}
    	try {if(rs !=null) rs.close();}catch(Exception e) {e.printStackTrace();}
    }
	  
	  
	  return null; //데이터베이스 오류
  
  
 }
  
  public boolean setUserEmailCheked(String userID) {
	  String SQL = "update user set userEmailCheked=true where userID=?";
	  Connection conn = null;
	  PreparedStatement pstmt = null;
	  ResultSet rs = null;
	  try {
		  conn= DatabaseUtil.getConnection();
		  pstmt = conn.prepareStatement(SQL);
		  pstmt.setString(1, userID);		  
		  pstmt.executeUpdate();
		  return true;
		 		 				  
	  }catch(Exception e) {
		  e.printStackTrace();
	
    } finally {
    	try {if(conn !=null) conn.close();}catch(Exception e) {e.printStackTrace();}
    	try {if(conn !=null) conn.close();}catch(Exception e) {e.printStackTrace();}
    	try {if(rs !=null) rs.close();}catch(Exception e) {e.printStackTrace();}
    }
	  
	  
	  return false; //데이터베이스 오류
  
  
 }
  
  
}