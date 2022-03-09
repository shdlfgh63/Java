package com.edu.sec02.ex01;

import java.sql.Date;

//-----------------------------------------------------------------------------------------------------------
// 회원 정보 객체
//-----------------------------------------------------------------------------------------------------------
public class MemberVO {
	
	private	String	id;			// 회원 아이디
	private	String	pwd;		// 회원 비밀번호
	private	String	name;		// 회원 이름
	private	String	email;		// 회원 이메일
	private	Date	joinDate;	// 회원 가입일자
	
	//-----------------------------------------------------------------------------------------------------------
	// 기본 생성자
	//-----------------------------------------------------------------------------------------------------------
	public MemberVO() {
		System.out.println("MemberVO 생성자 호출");
	}
	//-----------------------------------------------------------------------------------------------------------
	// 생성자
	//-----------------------------------------------------------------------------------------------------------
	public MemberVO(String id, String pwd, String name, String email) {
		super();
		this.id			= id;
		this.pwd		= pwd;
		this.name		= name;
		this.email		= email;
	}
	//-----------------------------------------------------------------------------------------------------------
	// 생성자
	//-----------------------------------------------------------------------------------------------------------
	public MemberVO(String id, String pwd, String name, String email, Date joinDate) {
		super();
		this.id			= id;
		this.pwd		= pwd;
		this.name		= name;
		this.email		= email;
		this.joinDate	= joinDate;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", joinDate=" + joinDate
				+ "]";
	}

} // End - public class MemberVO

