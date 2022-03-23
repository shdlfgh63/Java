package com.edu.bmi;

//-----------------------------------------------------------------------------------------------------------
// BMI(Body Mass Index : 체질량 지수) 계산기
//-----------------------------------------------------------------------------------------------------------
public class BMICalculator {

	private	double	lowWeight;		// 저체중
	private	double	normal;			// 정상
	private	double	overWeight;		// 과체중
	private	double	obesity;			// 비만
	
	public void bmiCalculation(double weight, double height) {
		double	h		= height * 0.01;
		double	result	= weight / (h*h);
		
		System.out.println("BMI 지수 : " + (int) result);
		
		if(result > obesity) {
			System.out.println("비만입니다.");
		} else if(result > overWeight) {
			System.out.println("과체중입니다.");
		} else if(result > normal) {
			System.out.println("정상입니다.");
		} else {
			System.out.println("저체중입니다.");
		}
	}
	
	public double getLowWeight() {
		return lowWeight;
	}
	public void setLowWeight(double lowWeight) {
		this.lowWeight = lowWeight;
	}
	public double getNormal() {
		return normal;
	}
	public void setNormal(double normal) {
		this.normal = normal;
	}
	public double getOverWeight() {
		return overWeight;
	}
	public void setOverWeight(double overWeight) {
		this.overWeight = overWeight;
	}
	public double getObesity() {
		return obesity;
	}
	public void setObesity(double obesity) {
		this.obesity = obesity;
	}
	
	@Override
	public String toString() {
		return "BMICalculator [lowWeight=" + lowWeight + ", normal=" + normal + ", overWeight=" + overWeight
				+ ", obesity=" + obesity + "]";
	}
	
	
} // End - public class BMICalculator
