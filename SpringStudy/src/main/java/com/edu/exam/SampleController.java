package com.edu.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exam01")
public class SampleController {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController.class); 
	
	@RequestMapping("/doA")
	public void doA() {
		logger.info("doA가 실행되었습니다.....");
	}
	@RequestMapping("/doB")
	public void doB() {
		logger.info("doB가 실행되었습니다.....");
	}

}
