package com.edu.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//-----------------------------------------------------------------------------------------------------------
// public class SampleController2
//-----------------------------------------------------------------------------------------------------------
@Controller
@RequestMapping("/exam01")
public class SampleController2 {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleController2.class);
	
	//-----------------------------------------------------------------------------------------------------------
	// @ModelAttribute("msg") 는 주소창에서 msg라는 파라미터의 값을 가져온다.
	// http://localhost:8099/exam01/doC?msg=hello
	// 이렇게 주소창에 적혀있는 경우 자동적으로 msg의 값인 hello를 가져오게 된다.
	//-----------------------------------------------------------------------------------------------------------
	@RequestMapping("/doC")
	public String doc(@ModelAttribute("msg") String msg) {
		
		logger.info("doC가 실행되었습니다....." + msg);
		System.out.println("doC msg : " + msg);
		
		// 문자열이 사용될 경우에는 문자열.jsp 파일을 찾아서 실행하게 된다.
		// void 타입과는 다르게 return 타입이 String인 경우는 리턴하는 문자열이 View 이름이 된다.
		return "exam01/result";	
	}
	
	//-----------------------------------------------------------------------------------------------------------
	// 구구단
	//-----------------------------------------------------------------------------------------------------------
	// @RequestMapping("gugu.do") // GET, POST방식에 상관 없다.
	@RequestMapping(value="gugu.do", method=RequestMethod.GET)
	// public String gugu(int dan, String good, Model model) {
	// public String gugu(@RequestParam int dan, String good, Model model) {
	public String gugu(@RequestParam(defaultValue = "2") int dan, String good, Model model) {
		
		// Model : 데이터를 담는 그릇 역할을 한다. map 구조로 저장이 된다.
		// model.addAttribute("변수명", 값);
		
		//-----------------------------------------------------------------------------------------
		// Spring에서는 get방식에서 아래 줄과 같은 방식으로 사용하지 않는다.
		// int dan = Integer.parseInt(request.getParameter("dan");
		//-----------------------------------------------------------------------------------------
		// <a href=${path}/gugu.do?dan=8">구구단</a>
		// public String gugu(HttpServletRequest request, Model model)을 사용하지 않고,
		// public String gugu(int dan, Model model)을 사용한다.
		
		// 값을 넘겨주는 페이지에서 값은 String으로 넘어오지만,
		// Spring에서는 정수형으로 잘 받는다.(이러한 과정이 스프링에 잘 녹아있다.)
		
		// public String gugu(int dan, Model model) 으로 사용해도 된다.
		// 변수명만 맞으면 매개변수의 위치는 상관이 없다.
		// http://localhost:8089/exam01/gugu.do?good=hello&dan=8
		// public String gugu(int dan, String good, Model model)
		
		// public String gugu(int dan, String good, Model model) 
		// public String gugu(@RequestParam int dan, String good, Model model) 
		// 실제로 @RequestParam이 숨어 있는 것이기 때문에 기술하지 않아도 된다.

		
		System.out.println("문자열 전송 값 : " + good);
		
		String result	= "";
		for(int i = 1; i <= 9; i++) {
			result += dan + " x " + i + " = " + dan*i + "<br/>";
		}
		
		model.addAttribute("result", result);
		return "exam01/gugudan";
	}
	

} // End - public class SampleController2 











