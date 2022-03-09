package com.edu.sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//-----------------------------------------------------------------------------------------------------------
// public class MemberDAO
//-----------------------------------------------------------------------------------------------------------
public class MemberDAO {
	
	private	DataSource			dataFactory;
	private	Connection			conn;
	private	PreparedStatement	pstmt;
	
	//-----------------------------------------------------------------------------------------------------------
	// public MemberDAO()
	//-----------------------------------------------------------------------------------------------------------
	public MemberDAO() {
		try {
			Context	ctx	= new InitialContext();
			Context	env	= (Context) ctx.lookup("java:/comp/env");
			dataFactory	= (DataSource) env.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} // End -public MemberDAO()
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원들의 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	public List<MemberVO> listMembers() {
		List<MemberVO> membersList = new ArrayList();
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			
			// 모든 회원들의 모든 정보를 가입일자의 내림차순으로 가져오자.
			String	sql	= "SELECT * FROM t_member ORDER BY joinDate DESC ";
			pstmt 	= conn.prepareStatement(sql);
			rs 		= pstmt.executeQuery();
			
			while(rs.next()) {
				String	id			= rs.getString("id"); // "id" <== 컬럼명
				String	pwd			= rs.getString("pwd");
				String	name		= rs.getString("name");
				String	email		= rs.getString("email");
				Date	joinDate	= rs.getDate("joinDate");
				MemberVO memberVO	= new MemberVO(id, pwd, name, email, joinDate);
				membersList.add(memberVO);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return membersList;
	} // End - public List<MemberVO> listMembers()
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입
	//-----------------------------------------------------------------------------------------------------------
	public void addMember(MemberVO member) {
		try {
			conn = dataFactory.getConnection();
			String	id			= member.getId();
			String	pwd			= member.getPwd();
			String	name		= member.getName();
			String	email		= member.getEmail();
			String	sql	= "";
			sql  = "INSERT INTO t_member(id, pwd, name, email, joinDate) VALUES(?,?,?,?,sysdate) ";
			pstmt	= conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // End - public void addMember(MemberVO member)
	
} // ENd - public class MemberDAO



