package com.edu.exam.Coffeeshop.coffee1;

public class Coffee {
  private HotAmericano ame;
  private IceAmericano ime;
  
  public Coffee() {
	  ame= new HotAmericano();
  }
  
  public void coffeeType() {
	  System.out.println(ame.getName());	  
  }
}
