package com.edu.exam.coffeeshop.coffee3;

public class Coffee {
	
	private	Americano americano;
	
	public void setCoffee(Americano americano) {
		this.americano = americano;
	}
	
	public void coffeeType() {
		System.out.println(americano.getName());
	}
	
}
