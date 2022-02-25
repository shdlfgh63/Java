package com.edu.sec02.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



//-----------------------------------------------------------------------------------------------------------
// public class MemberDAO
//-----------------------------------------------------------------------------------------------------------
public class MemberDAO {

	// Oracle 
	/*
	private	static final String driver	= "oracle.jdbc.driver.OracleDriver";
	private static final String	url		= "jdbc:oracle:thin:@localhost:1521:XE";
	private	static final String user	= "scott";
	private static final String pwd		= "tiger";
	*/
	
	private Connection			conn;
	private PreparedStatement	pstmt;
	private	DataSource			dataFactory;

	//-----------------------------------------------------------------------------------------------------------
	// DB 접속
	//-----------------------------------------------------------------------------------------------------------
	/*
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
	*/
	
	//-----------------------------------------------------------------------------------------------------------
	// 생성자
	//-----------------------------------------------------------------------------------------------------------
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // End - public MemberDAO()
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 목록 가져오기
	//-----------------------------------------------------------------------------------------------------------
	public List<MemberVO> listMembers() {
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		try {
			// connectionDB();
			conn = dataFactory.getConnection();
			// String query = "SELECT * FROM T_MEMBER ORDER BY ID";
			String query = "";
			query  = " SELECT * FROM T_MEMBER ";			
			query += " ORDER BY ID ";
			
			pstmt=conn.prepareStatement(query);
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
	
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입
	//-----------------------------------------------------------------------------------------------------------
    public void addMember(MemberVO memberVO) {
    	try {
    	conn=dataFactory.getConnection();
    	
    	String id = memberVO.getId();
    	String pwd = memberVO.getPwd();
    	String name = memberVO.getName();
    	String email = memberVO.getEmail();
    	String query = "INSERT INTO T_MEMBER(id,pwd,name,email) VALUES (?,?,?,?)";
    	
    	pstmt=conn.prepareStatement(query);
		
		 pstmt.setString(1, id);
		 pstmt.setString(2, pwd);
		 pstmt.setString(3, name);
		 pstmt.setString(4, email);
		 pstmt.executeUpdate();
		 
		 
		 pstmt.close();
		 conn.close();
	    	
    	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
  //-----------------------------------------------------------------------------------------------------------
  // 회원 탈퇴 
  //-----------------------------------------------------------------------------------------------------------
    public void delMember(String id) {
    	try {
			conn = dataFactory.getConnection();
			
			String query ="DELETE FROM T_MEMBER WHERE ID=? ";
			pstmt=conn.prepareStatement(query);
			
			 pstmt.setString(1, id);
			
			 pstmt.executeUpdate();
			 
			 
			 pstmt.close();
			 conn.close();
		    	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
   //-----------------------------------------------------------------------------------------------------------
   // id에 해당하는 회원 정보 가져오기 
   //-----------------------------------------------------------------------------------------------------------
    public MemberVO getMember(String id) {
    	MemberVO memberVO;
    	memberVO = new MemberVO();
  	
    	try {
			conn = dataFactory.getConnection();
			
			String query ="SELECT * FROM T_MEMBER WHERE ID=? ";
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String pwd  = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				
				memberVO.setId(rs.getString("id"));
				memberVO.setPwd(pwd);
				memberVO.setName(rs.getString("name"));
				memberVO.setEmail(email);
				memberVO.setJoinDate(joinDate);
			}
			
			 pstmt.close();
			 conn.close();
		    	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return memberVO;
    }
    //-----------------------------------------------------------------------------------------------------------
    // id에 해당하는 회원 정보 수정하기
    //-----------------------------------------------------------------------------------------------------------
    public void upMember(MemberVO memberVO) {
    	
    	try {
			conn = dataFactory.getConnection();
						
			String		id			= memberVO.getId();
			String		pwd			= memberVO.getPwd();
			String		name		= memberVO.getName();
			String		email		= memberVO.getEmail();
			
			String query = "UPDATE t_member SET pwd=?,name=?,email=? WHERE id=?";
			pstmt = conn.prepareStatement(query);
			
			
			pstmt.setString(1, pwd);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
    	}catch (Exception e) {
			e.printStackTrace();
		}   	
    }
} // End - public class MemberDAO











