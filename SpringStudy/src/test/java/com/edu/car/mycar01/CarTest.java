package com.edu.car.mycar01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarTest {

	@Test
	void test() {
		// fail("Not yet implemented");
		
		System.out.println("JUnit Test 입니다.");
	}
	
	@Test
	void OneToHundred() {
		int result = 0;
		for(int i = 1; i <= 100; i++) {
			result += i;
		}
		System.out.println("1 부터 100까지 더한 값은 : " + result);
	}

	@Test
	void carTest01() {
		
		Car car = new Car();
		System.out.println(car.getTireBrand());
		assertEquals("현재 장착된 타이어는 한국에서 만든 타이어" , car.getTireBrand());
	}
	
	
} // End - class CarTest
