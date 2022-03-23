package com.edu.bmi;

import java.util.ArrayList;

//-----------------------------------------------------------------------------------------------------------
// 개인 정보
//-----------------------------------------------------------------------------------------------------------
public class MyInfo {

	private	String				name;
	private	double				height;
	private	double				weight;
	private	ArrayList<String>	hobby;
	private	BMICalculator		bmiCalculator;
	
	public void bmiCalculation() {
		bmiCalculator.bmiCalculation(weight, height);
	}
	
	public void getInfo() {
		System.out.println("이  름 : " + name);
		System.out.println("키     : " + height);
		System.out.println("몸무게 : " + weight);
		System.out.println("취  미 : " + hobby);
		
		// BMI 지수를 계산해서 결과를 보여준다.
		bmiCalculation();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public ArrayList<String> getHobby() {
		return hobby;
	}

	public void setHobby(ArrayList<String> hobby) {
		this.hobby = hobby;
	}

	public BMICalculator getBmiCalculator() {
		return bmiCalculator;
	}

	public void setBmiCalculator(BMICalculator bmiCalculator) {
		this.bmiCalculator = bmiCalculator;
	}
	
	@Override
	public String toString() {
		return "MyInfo [name=" + name + ", height=" + height + ", weight=" + weight + ", hobby=" + hobby
				+ ", bmiCalculator=" + bmiCalculator + "]";
	}
	
	
} // End - public class MyInfo






