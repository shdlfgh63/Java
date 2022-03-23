package com.edu.exam2.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.exam2.vo.MemberVO;

//-----------------------------------------------------------------------------------------------------------
// public class SampleController3
//-----------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/exam01")
public class SampleController3 {

	private static final Logger logger = LoggerFactory.getLogger(SampleController3.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// public String doD(Model model)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping("/doD")
	public String doD(Model model) {
		logger.info("doD 실행.....");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid("userid001");
		memberVO.setUserpw("12345678");
		
		model.addAttribute("member", memberVO);
		
		logger.info("MemberVO : " + memberVO.toString());
		
		return "exam01/data"; // WEB-INF/views/exam01/data.jsp
	} // End - public String doD(Model model)

	//-----------------------------------------------------------------------------------------------------------
	// public String doE(Model model)
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping("/doE")
	public String doE(Model model) {
		
		logger.info("doE 실행.....");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", "userid99");
		map.put("userpw", "87654321");
		
		model.addAttribute("map", map);
		
		return "exam01/data";
		
	} // End - public String doE(Model model)
	
	
} // End - public class SampleController3











