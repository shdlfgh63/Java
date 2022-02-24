package com.edu.sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//-----------------------------------------------------------------------------------------------------------
// public class MemberDAO
//-----------------------------------------------------------------------------------------------------------
public class MemberDAO {
	
	// MySQL
	// private	static final String driver	= "com.mysql.jdbc.Driver";
	// private static final String	url		= "jdbc:mysql://localhost:3306/bookshopdb";
	
	// Oracle 
	private	static final String driver	= "oracle.jdbc.driver.OracleDriver";
	private static final String	url		= "jdbc:oracle:thin:@localhost:1521:XE";
	private	static final String user	= "scott";
	private static final String pwd		= "tiger";
	private Connection	conn;
	private Statement	stmt;
	
	//-----------------------------------------------------------------------------------------------------------
	// DB 연결
	//-----------------------------------------------------------------------------------------------------------
	private void connectionDB() {
		try {
			// 1. 드라이버 로딩
			Class.forName(driver);
			System.out.println("오라클 드라이버 로딩에 성공했습니다.");
			
			// 2. 컨넥션(연결)
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
			
			// 3. 질의준비
			stmt = conn.createStatement();
			System.out.println("Statement 생성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // End - private void connectionDB()
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 목록
	//-----------------------------------------------------------------------------------------------------------
	public List<MemberVO> listMembers() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			connectionDB();	// 드라이버로딩, DB연결, 질의 준비
			// 모든회원의 정보를 찾아주세요.
			String	query	= "select * from t_member order by name";
			ResultSet rs = stmt.executeQuery(query);
			
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
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	} // End - public List<MemberVO> listMembers()

} // End - public class MemberDAO












