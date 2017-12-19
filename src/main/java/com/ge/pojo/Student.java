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
	int assignmentCount;
	int internalExamMarks;
	String result;

	public Student(String name, int rollNumber, int halfYearlyMarks, int attendancePercentage, int assignmentCount,
			int internalExamMarks, String result) {
		super();
		this.name = name;
		this.rollNumber = rollNumber;
		this.halfYearlyMarks = halfYearlyMarks;
		this.attendancePercentage = attendancePercentage;
		this.assignmentCount = assignmentCount;
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
		return assignmentCount;
	}

	public void setAssigmentsCount(int assignmentCount) {
		this.assignmentCount = assignmentCount;
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
		
		

	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", rollNumber=" + rollNumber + ", halfYearlyMarks=" + halfYearlyMarks
				+ ", attendancePercentage=" + attendancePercentage + ", assignmentCount=" + assignmentCount
				+ ", internalExamMarks=" + internalExamMarks + ", result=" + result + "]";
	}

	
}
