package com.edu.car.mycar06;

import org.springframework.beans.factory.annotation.Autowired;

public class Car {
	
	// 스프링을 통한 의존 주입 : @Autowired를 통한 속성 주입
	@Autowired
	Tire tire;
	
	// Tire tire = new Tire();
	
	public String getTireBrand() {
		return "장착된 타이어는 " + tire.getBrand();
	}
}
