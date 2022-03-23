package com.edu.exam2.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.exam2.vo.MemberVO;

@Controller
@RequestMapping("/exam01")
public class SampleController3 {
    
	private static final Logger Logger = LoggerFactory.getLogger(SampleController3.class);
	
	@RequestMapping("/doD")
	public String doD(Model model) {
		Logger.info("doD 실행.....");
		
		MemberVO memberVO = new MemberVO();
		memberVO.setUserid("userid001");
		memberVO.setUserpw("12345678");
		
		model.addAttribute("member",memberVO);
		
		Logger.info("MemberVO : "+memberVO.toString());
		
		return "exam01/data";
	}
	
	@RequestMapping("/doE")
	public String doE(Model model) {
		
		Logger.info("doE 실행....");
		
		Map<String, String> map = new HashMap<String,String>();
		map.put("userid", "userid99");
		map.put("userpw", "987654321");
		
		model.addAttribute("map",map);
		
		return "exam01/data";
	}
	
}
