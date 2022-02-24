package com.edu.sec01.ex01;

import java.sql.Date;

//-----------------------------------------------------------------------------------------------------------
// 회원 정보
//-----------------------------------------------------------------------------------------------------------
public class MemberVO {
	
	private	String	id;			// 회원 아이디
	private	String	pwd;		// 비밀번호
	private	String	name;		// 이름
	private	String	email;		// 이메일
	private	Date	joinDate;	// 가입일자
	
	// 기본 생성자
	public MemberVO() {
		System.out.println("MemberVO 생성자를 호출합니다.");
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
		return "MemberVO 의 값이 보고 싶어요. [id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email + ", joinDate=" + joinDate
				+ "]";
	}

} // End - public class MemberVO













