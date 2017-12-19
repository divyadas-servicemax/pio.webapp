package com.ge.util;

public class ApplicationConstants {

	public static final String ROLL_NUMBER = "roll_number";
	public static final String HALF_YEARLY_MARKS = "half_yearly_marks";
	public static final String ASSIGNMENTS_COUNT = "assigments_count";
	public static final String INTERNAL_EXAM_MARKS = "internal_exam_marks";
	public static final String ATTENDANCE_PERCENTAGE = "attendance_percentage";
	public static final String RESULT = "result";
	public static final String PASS = "PASS";
	public static final String FAIL = "FAIL";
	public static final String UNKNOWN_STATUS = "Result unknown";
	public static final String POST = "POST";
	
//	public static final String PREDICTIONIO_URL = "http://localhost:8080/pio_webapp/api/predict";
	
	public static final String PREDICTIONIO_URL = "https://poc-engine-classification.herokuapp.com/queries.json";
	
	public static final String POSTGRES_JDBC_CLASS = "org.postgresql.Driver";
	//public static final String POSTGRES_JDBC_URL = "jdbc:postgresql://localhost:5432/pio_poc";
	//public static final String DB_USER = "piopoc";
	//public static final String DB_PASSWORD = "piopoc";
	
	public static final String POSTGRES_JDBC_URL = "jdbc:postgresql://ec2-107-21-201-57.compute-1.amazonaws.com:5432/ddj6cpggjk0b5p";
	public static final String DB_USER = "auplgyidlitzax";
	public static final String DB_PASSWORD = "034fb63d1064d86b3f90519ea9b6238ab2a5367a128b29884054df4d24e7166a";
	
	public static final String POSTGRES_JDBC_URL_1 = "jdbc:postgresql://ec2-107-21-201-57.compute-1.amazonaws.com:5432/ddj6cpggjk0b5p?user=auplgyidlitzax&password=034fb63d1064d86b3f90519ea9b6238ab2a5367a128b29884054df4d24e7166a&sslmode=require";

	
	public static final String PREDICTED_RESULT_TABLE = "predicted_result";
	
}
