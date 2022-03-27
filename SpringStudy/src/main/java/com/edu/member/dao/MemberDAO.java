package com.edu.member.dao;

import org.springframework.dao.DataAccessException;

import com.edu.member.vo.MemberVO;

public interface MemberDAO {
 
	public int insertMember(MemberVO memberVO) throws DataAccessException;
}
