package com.ge.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ge.pojo.Student;
import com.ge.util.ApplicationConstants;

public class PredictionService {

	final static Logger logger = Logger.getLogger(PredictionService.class);

	protected String predictStudentResult(Student student) {

		String studentQueryString = getAttributeJson(student);

		//RestInvoker restInvoker = new RestInvoker();
		//String result = restInvoker.postData(studentQueryString);
		String predictedResult = "PASS";
		student.setResult(predictedResult);
		
		
		// save this to db
		boolean insertStatus = insertPredictedResult(student);

		return predictedResult;
	}

	protected boolean insertPredictedResult(Student student) {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		boolean status = false;
		try {
			Class.forName(ApplicationConstants.POSTGRES_JDBC_CLASS);
			connection = DriverManager.getConnection(ApplicationConstants.POSTGRES_JDBC_URL, "piopoc", "piopoc");

			statement = connection.createStatement();
		    Calendar calendar = Calendar.getInstance();

		    java.sql.Timestamp timeStamp = new java.sql.Timestamp(calendar.getTime().getTime());

		    
		    String sqlQuery = "INSERT INTO predicted_result (created_at,roll_no,name,half_yearly_marks,attendance_percentage,internal_exam_marks,assignment_count,result)"
		    		+ " VALUES (?,?,?,?,?,?,?,?)";
		    preparedStatement = connection.prepareStatement(sqlQuery);
		    preparedStatement.setTimestamp(1, timeStamp);
		    preparedStatement.setInt(2, student.getRollNumber());
		    preparedStatement.setString(3, student.getName());
		    preparedStatement.setInt(4, student.getHalfYearlyMarks());
		    preparedStatement.setInt(5, student.getAttendancePercentage());
		    preparedStatement.setInt(6, student.getInternalExamMarks());
		    preparedStatement.setInt(7, student.getAssigmentsCount());
		    preparedStatement.setString(8, student.getResult());

		    preparedStatement.setString(3, student.getName());

		    preparedStatement.executeUpdate();
		    preparedStatement.close();
		    
			/*String sql = "INSERT INTO predicted_result (roll_no,name,half_yearly_marks,attendance_percentage,internal_exam_marks,assignment_count,result) "
					+ "VALUES (" + student.getRollNumber() + ",'" + student.getName() + "',"
					+ student.getHalfYearlyMarks() + "," + student.getAttendancePercentage() + ","
					+ student.getInternalExamMarks() + "," + student.getInternalExamMarks() + ",'" + student.getResult()
					+ "');";
			System.out.println(sql);
			//statement.executeUpdate(sql);*/
			status = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return status;

	}

	public static void main(String[] args) {
		System.out.println("test");
		Student s = new Student("Tim1", 3, 11, 22, 33, 44, "PASS");
		new PredictionService().insertPredictedResult(s);
	}

	static String getAttributeJson(Student student) {
		JSONObject queryString = new JSONObject();
		// { "attr0":21, "attr1":50, "attr2":3, "attr3":55}

		queryString.put("attr0", student.getHalfYearlyMarks());
		queryString.put("attr1", student.getAttendancePercentage());
		queryString.put("attr2", student.getAssigmentsCount());
		queryString.put("attr3", student.getInternalExamMarks());

		return queryString.toString();
	}
}
