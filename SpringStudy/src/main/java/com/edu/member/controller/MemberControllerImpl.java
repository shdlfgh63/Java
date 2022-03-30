package com.edu.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edu.member.service.MemberService;
import com.edu.member.vo.MemberVO;

//-----------------------------------------------------------------------------------------------------------
// public class MemberControllerImpl implements MemberController
//-----------------------------------------------------------------------------------------------------------
@Controller("memberController")
public class MemberControllerImpl implements MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberControllerImpl.class);
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private MemberVO memberVO;
	
	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 폼
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/memberForm.do", method=RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/memberForm");
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 가입 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/addMember.do", method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		System.out.println("Controller에서 받은 memberVO ==> " + memberVO);
		System.out.println("file encoding : " + System.getProperty("file.encoding"));		
		
		int	result	= 0;
		
		result = memberService.addMember(memberVO);
	
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 회원 전체 목록 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		List membersList = memberService.listMembers();	// 회원 전체 목록 자료를 가져온다.
		System.out.println("membersList ==> " + membersList);
		
		ModelAndView mav = new ModelAndView("/member/listMembers");
		//mav.setViewName("/member/memberForm");
		mav.addObject("membersList", membersList);
		
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 삭제
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/removeMember.do", method=RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		memberService.removeMember(id);
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 조회
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/updateMemberForm.do", method=RequestMethod.GET)
	public ModelAndView updateMemberForm(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		memberVO = memberService.selectMember(id);
		System.out.println("updateMemberForm memberVO ==> " + memberVO);
		
		ModelAndView mav = new ModelAndView("/member/updateMemberForm");
		mav.addObject("member", memberVO);
		
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 아이디에 해당하는 회원 정보 수정하기
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/modMember.do", method=RequestMethod.POST)
	public ModelAndView modMember(@ModelAttribute("memberVO") MemberVO memberVO, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.info("회원정보수정하기 콘트롤러에 진입했습니다.");
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		System.out.println("Form에서 받은 memberVO ==> " + memberVO);
		
		int result = memberService.modMember(memberVO);
		
		ModelAndView mav = new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 로그인 화면 띄우기
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping(value="/member/loginForm.do", method=RequestMethod.POST)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/member/loginForm");
		return mav;
	}

	//-----------------------------------------------------------------------------------------------------------
	// 로그인 처리
	//-----------------------------------------------------------------------------------------------------------
	@Override
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, 
			RedirectAttributes rAttr, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mav = new ModelAndView();

		// 로그인 정보가 데이터베이스에 존재하는지에 따라 처리를 다르게 한다.
		memberVO = memberService.login(member);
		
		if(memberVO != null) {	// 로그인 정보에 해당하는 자료가 존재한다면
			// 세션을 발급한다.
			HttpSession session = request.getSession();
			session.setAttribute("member", memberVO);
			session.setAttribute("isLogon", true);
			mav.setViewName("redirect:/member/listMembers.do");
		} else {	// 로그인 정보에 해당하는 자료가 존재하지 않는다면
			rAttr.addAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");
		}
		return mav;
	}


	
	
	
	
} // End - public class MemberControllerImpl implements MemberController

