package com.edu.sec03.ex01;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;


//@WebFilter("/*")
public class EncoderFilter extends HttpFilter implements Filter {

    ServletContext context;    
	
    public EncoderFilter() {
       
    }

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("UTF-8 인코딩 필터 초기화...");
		context = fConfig.getServletContext();
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("doFilter가 호출되었습니다.");
		
		//한글 인코딩 설정 작업을 한다
		request.setCharacterEncoding("utf-8");
		
		//웹 어플리케이션 컨텍스트 이름을 가져온다.
		String context = ((HttpServletRequest)request).getContextPath();
		
		//웹 브라우저에서 요청한 URI를 가져온다.
		String pathInfo= ((HttpServletRequest)request).getRequestURI();
		
		//요청 URI의 실제 경로를 가져온다.
		String realPath = request.getRealPath(pathInfo);
		
		String msg= "Context 정보 : " + context +  "\n"
				       + "URI정보 : " + pathInfo + "\n"
			          + "물리적 정보 : " +realPath;
		System.out.println(msg);
		
		long begin = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		//작업 요청 전과 후의 시각 차를 구해서 작업 수행 시간을 구한다.
		System.out.println("작업 소요시간 : "+(end-begin)+"ms");
	}
	
	public void destroy() {
		System.out.println("destroy가 호출되었습니다.");
	}


}
