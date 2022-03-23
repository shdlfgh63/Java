package com.edu.car.mycar06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

// @RunWith(SpringJunit4ClassRunner.class)
@ExtendWith(SpringExtension.class) // JUnit 5 버전일 경우
@ContextConfiguration(locations="appCtx.xml")
// @ContextConfiguration(locations="classpath:appCtx.xml")
class CarTest {

	@Autowired
	Car car;

	@Test
	void test() {
		// fail("Not yet implemented");
	
		System.out.println(car.getTireBrand());
	}
	
	@BeforeAll // @BeforeEach
	static void beforeTest() {
		System.out.println("메서드가 실행되기 전에 나타납니다.");
	}
	@AfterAll // @AfterEach
	static void afterTest() {
		System.out.println("메서드가 종료된 후에 나타납니다.");
	}
	
	@Test
	void test2() {
		System.out.println("연습용 메서드 입니다.");
	}
	

} // End - class CarTest
