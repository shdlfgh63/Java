package com.edu.car.mycar03;

public class Driver {

	public static void main(String[] args) {

		// Tire tire = new KoreaTire();
		Tire tire = new EnglandTire();
		
		Car car = new Car();
		// 속성을 통한 의존성 주입
		car.setTire(tire);
		
		System.out.println(car.getTireBrand());

	}

}
