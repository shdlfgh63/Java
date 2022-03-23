package com.edu.exam.coffeeshop.coffee5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CoffeeShop {

	public static void main(String[] args) {

		ApplicationContext	ctx	
			= new GenericXmlApplicationContext("com/edu/exam/coffeeshop/coffee5/ApplicationContext.xml");

		Coffee coffee = ctx.getBean("coffee", Coffee.class);
		coffee.coffeeType();
	}

}
