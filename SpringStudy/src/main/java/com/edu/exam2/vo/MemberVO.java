package com.edu.exam2.vo;

//-----------------------------------------------------------------------------------------------------------
// 회원 클래스
//-----------------------------------------------------------------------------------------------------------
public class MemberVO {
	
	private	String	userid;
	private	String	userpw;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", userpw=" + userpw + "]";
	}
	

} // End - public class MemberVO
