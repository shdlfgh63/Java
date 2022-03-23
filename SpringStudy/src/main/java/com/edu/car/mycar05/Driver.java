package com.edu.car.mycar05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Driver {

	public static void main(String[] args) {
		// 스프링을 통한 의존성 주입 : 스프링 설정 파일(XML)에서 속성을 주입한다.
		ApplicationContext context
			= new ClassPathXmlApplicationContext("com/edu/car/mycar05/appCtx.xml");
		
		// 사용할 Bean을 불러온다.
		Car car = context.getBean("car", Car.class);
		System.out.println(car.getTireBrand());

	}

}
