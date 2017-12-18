package com.ge.pojo;

import java.util.ArrayList;
import java.util.List;

import com.ge.util.ApplicationConstants;
import com.ge.util.JsonUtil;

public class Student {

	String name;
	int rollNumber;
	int halfYearlyMarks;
	int attendancePercentage;
	int assigmentsCount;
	int internalExamMarks;
	String result;

	public Student(String name, int rollNumber, int halfYearlyMarks, int attendancePercentage, int assigmentsCount,
			int internalExamMarks, String result) {
		super();
		this.name = name;
		this.rollNumber = rollNumber;
		this.halfYearlyMarks = halfYearlyMarks;
		this.attendancePercentage = attendancePercentage;
		this.assigmentsCount = assigmentsCount;
		this.internalExamMarks = internalExamMarks;
		this.result = result;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(int rollNumber) {
		this.rollNumber = rollNumber;
	}

	public int getHalfYearlyMarks() {
		return halfYearlyMarks;
	}

	public void setHalfYearlyMarks(int halfYearlyMarks) {
		this.halfYearlyMarks = halfYearlyMarks;
	}

	public int getAttendancePercentage() {
		return attendancePercentage;
	}

	public void setAttendancePercentage(int attendancePercentage) {
		this.attendancePercentage = attendancePercentage;
	}

	public int getAssigmentsCount() {
		return assigmentsCount;
	}

	public void setAssigmentsCount(int assigmentsCount) {
		this.assigmentsCount = assigmentsCount;
	}

	public int getInternalExamMarks() {
		return internalExamMarks;
	}

	public void setInternalExamMarks(int internalExamMarks) {
		this.internalExamMarks = internalExamMarks;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public static void main(String[] args) {
		System.out.println("test");

		Student s = new Student("Jim", 11, 60, 89, 2, 89, ApplicationConstants.PASS);
		
		Student s1 = new Student("Tim", 12, 60, 89, 5, 45, ApplicationConstants.FAIL);
		
		List<Student> studentList = new ArrayList<>();
		studentList.add(s);
		studentList.add(s1);
		
		String aa = JsonUtil.convertObjectToString(studentList);
		System.out.println(aa);
		
		PieChart p1 = new PieChart(ApplicationConstants.PASS, 11);
		PieChart p2 = new PieChart(ApplicationConstants.FAIL, 11);
		
		List<PieChart> pieChartList = new ArrayList<>();
		pieChartList.add(p1);
		pieChartList.add(p2);
		
		String bb = JsonUtil.convertObjectToString(pieChartList);
		System.out.println(bb);
		
		

	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", rollNumber=" + rollNumber + ", halfYearlyMarks=" + halfYearlyMarks
				+ ", attendancePercentage=" + attendancePercentage + ", assigmentsCount=" + assigmentsCount
				+ ", internalExamMarks=" + internalExamMarks + ", result=" + result + "]";
	}

	
}
