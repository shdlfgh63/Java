package spring.di.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import spring.di.entity.Exam;

public class InlineExamConsole implements ExamConsole {
	
	@Autowired(required = false)
	@Qualifier("exam1")
	private Exam exam;
	public InlineExamConsole() {
		// TODO Auto-generated constructor stub
	}
	
	public InlineExamConsole(Exam exam) {		
		this.exam = exam;
	}


	@Override
	public void print() {
		System.out.printf("total is %d,avg is %f\n",exam.total(),exam.avg());

	}
	
	@Override
	public void setExam(Exam exam) {
		this.exam= exam;
		
	}

}
