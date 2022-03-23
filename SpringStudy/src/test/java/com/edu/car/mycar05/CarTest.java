package com.edu.car.mycar05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.edu.car.mycar06.Car;

@ContextConfiguration("appCtx.xml")
class CarTest {

	@Autowired
	Car car;

	@Test
	void test() {
		// fail("Not yet implemented");
	
		System.out.println(car.getTireBrand());
	}

}
