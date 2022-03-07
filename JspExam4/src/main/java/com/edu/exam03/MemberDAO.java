package com.edu.exam03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//-----------------------------------------------------------------------------------------------------------
//회원 아이디가 DB에 존재하는지 알아본다.
//-----------------------------------------------------------------------------------------------------------
public class MemberDAO {
	
	private	Connection			conn;
	private	PreparedStatement	pstmt;
	private	DataSource			dataFactory;
	
	//-----------------------------------------------------------------------------------------------------------
	// 생성자를 통해서 커넥션풀을 준비한다.
	//-----------------------------------------------------------------------------------------------------------
	public MemberDAO() {
		try {
			Context	ctx	= new InitialContext();
			Context	env	= (Context) ctx.lookup("java:/comp/env");
			dataFactory	= (DataSource) env.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//-----------------------------------------------------------------------------------------------------------
	// 사용자 아이디를 입력받아서 데이터베이스에 존재하는지 알려준다.
	//-----------------------------------------------------------------------------------------------------------
	public boolean overlappedID(String id) {
		boolean	result = false;
		
		try {
			// DB연결정보를 가져온다.
			conn = dataFactory.getConnection();
			
			String query = "";
			query	 = "SELECT DECODE(count(*), 1, 'true', 'false') as result ";
			query	+= "FROM t_member ";
			query	+= "WHERE id=? ";
			pstmt	= conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet	rs = pstmt.executeQuery();
			rs.next();
			result	= Boolean.parseBoolean(rs.getString("result"));
			
			// 작업이 모두 끝나면 자원들을 닫는다.
			rs.close();
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
} // End - public class MemberDAO

