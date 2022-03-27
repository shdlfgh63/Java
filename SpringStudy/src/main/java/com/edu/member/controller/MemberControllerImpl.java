package com.edu.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edu.member.service.MemberService;
import com.edu.member.vo.MemberVO;

@Controller("memberController")
public class MemberControllerImpl implements MemberController {
   
	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberVO memberVO;
	
	@RequestMapping(value="/member/memberForm.do",method=RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response)throws Exception{
	
	  ModelAndView mav = new ModelAndView();
	  mav.setViewName("/member/memberForm");
	  return mav;
	}

	@Override
	@RequestMapping(value="/member/addMember.do",method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
	   throws Exception {
		
		 request.setCharacterEncoding("UTF-8");
		 int result=0;
		 
		 result = memberService.addMember(memberVO);
		 
		 ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");		
		 return mav;
	}
}
