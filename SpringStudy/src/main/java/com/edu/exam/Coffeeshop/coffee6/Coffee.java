package com.edu.exam.coffeeshop.coffee6;

public class Coffee {
	private	Americano americano;
	
	public void setCoffee(Americano ame) {
		americano = ame;
	}
	
	public void coffeeType() {
		System.out.println(americano.getName());
	}
}
