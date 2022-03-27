package com.edu.member.service;

import org.springframework.dao.DataAccessException;

import com.edu.member.vo.MemberVO;

public interface MemberService {
    
	public int addMember(MemberVO memberVO) throws DataAccessException;
}
