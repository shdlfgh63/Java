package com.edu.exam.Coffeeshop.coffee3;

public class Coffee {
   private Americano americano;
   
   public void setCoffee(Americano americano) {
	   this.americano = americano;
   }
   
   public void coffeeType() {
	   System.out.println(americano.getName());
   }
}
