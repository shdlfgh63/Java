package likey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DatabaseUtil;

public class LikeyDAO {
    public int like(String userID, String evaluationID, String userIP) {
    	String SQL = "INSERT INTO likey VALUES( ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DatabaseUtil.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, evaluationID);
			pstmt.setString(3, userIP);			
			return pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally { //성공 후 함수 해제해서 서버 무리안주기
			try { if(conn != null) conn.close();} catch(Exception e) {e.printStackTrace();}
			try { if(pstmt != null) pstmt.close();} catch(Exception e) {e.printStackTrace();}
			try { if(rs != null) rs.close();} catch(Exception e) {e.printStackTrace();}
		}
		return -1;//추천중복 오류	
    }
    
}
