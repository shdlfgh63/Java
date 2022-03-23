package com.edu.exam.coffeeshop.coffee2;

public class Coffee {
	
	private Americano americano;
	
	public Coffee(Americano ame) {
		americano = ame;
	}
	
	public void coffeeType() {
		System.out.println(americano.getName());
	}
}
