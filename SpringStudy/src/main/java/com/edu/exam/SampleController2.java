package com.edu.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam01")
public class SampleController2 {
	private static final Logger Logger = LoggerFactory.getLogger(SampleController2.class);

	@RequestMapping("/doC")
	public String doc(@ModelAttribute("msg") String msg) {

		Logger.info("doC가 실행되었습니다..." + msg);
		System.out.println("doC msg : " + msg);
		return "exam01/result";
	}
}
