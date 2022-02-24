package com.edu.sec01.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.edu.sec01.ex02.MemberVO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberDAO
//-----------------------------------------------------------------------------------------------------------
public class MemberDAO {

	// Oracle 
	private	static final String driver	= "oracle.jdbc.driver.OracleDriver";
	private static final String	url		= "jdbc:oracle:thin:@localhost:1521:XE";
	private	static final String user	= "scott";
	private static final String pwd		= "tiger";
	private Connection			conn;
	private PreparedStatement	pstmt;

	//-----------------------------------------------------------------------------------------------------------
	// DB 접속
	//-----------------------------------------------------------------------------------------------------------
	private void connectionDB() {
		
		try {
			Class.forName(driver);
			System.out.println("Oracle Driver Loading.....");
			
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection.....");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // End - private void connectionDB()
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	public List<MemberVO> listMembers() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			connectionDB();
			String query = "SELECT * FROM T_MEMBER ORDER BY NAME";

			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String	id			= rs.getString("id");
				String	pwd			= rs.getString("pwd");
				String	name		= rs.getString("name");
				String	email		= rs.getString("email");
				Date	joinDate	= rs.getDate("joinDate");
				
				MemberVO	vo		= new MemberVO();
				vo.setId(rs.getString("id"));
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
		
	} // End - public List listMembers()

} // End - public class MemberDAO











