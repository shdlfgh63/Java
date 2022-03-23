package com.edu.exam.Coffeeshop.coffee2;

public class Coffee {
   HotAmericano hme;
   IceAmericano ime;
   
   private Americano americano;
   
   public Coffee(Americano ame) {
	   americano = ame;
   }
   
   public void coffeeType() {
	   System.out.println(americano.getName());
   }
}
