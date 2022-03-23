package com.edu.exam.coffeeshop.coffee4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CoffeeShop {

	public static void main(String[] args) {
		// Coffee coffee	= new Coffee();
		// coffee.coffeeType();
		ApplicationContext ctx	
			= new GenericXmlApplicationContext("com/edu/exam/coffeeshop/coffee4/ApplicationContext.xml");
		
		// Coffee coffee = (Coffee) ctx.getBean("coffee");	// 직접 형변환하는 방법
		Coffee coffee = ctx.getBean("coffee", Coffee.class);// 클래스 정보를 같이 넘겨주는 방법
		coffee.coffeeType();
		
	}

}
