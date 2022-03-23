package com.edu.exam.coffeeshop.coffee4;

public class Coffee {
	private	HotAmericano ame;
	
	public Coffee() {
		ame = new HotAmericano();
	}
	
	public void coffeeType() {
		System.out.println(ame.getName());
	}
}
