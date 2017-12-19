package com.ge.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.ge.pojo.PieChart;
import com.ge.pojo.PieChartData;
import com.ge.pojo.Student;
import com.ge.util.ApplicationConstants;
import com.ge.util.JsonUtil;

public class PredictedResultDao {

	final static Logger logger = Logger.getLogger(PredictedResultDao.class);

	public boolean insertPredictedResult(Student student) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		boolean status = false;
		try {
			Class.forName(ApplicationConstants.POSTGRES_JDBC_CLASS);
			//connection = DriverManager.getConnection(ApplicationConstants.POSTGRES_JDBC_URL,
				//	ApplicationConstants.DB_USER, ApplicationConstants.DB_PASSWORD);
			
			connection = DriverManager.getConnection(ApplicationConstants.POSTGRES_JDBC_URL_1);

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
			preparedStatement.setString(8, student.getResult().toUpperCase());

			preparedStatement.setString(3, student.getName());

			preparedStatement.executeUpdate();

			/*
			 * String sql =
			 * "INSERT INTO predicted_result (roll_no,name,half_yearly_marks,attendance_percentage,internal_exam_marks,assignment_count,result) "
			 * + "VALUES (" + student.getRollNumber() + ",'" + student.getName()
			 * + "'," + student.getHalfYearlyMarks() + "," +
			 * student.getAttendancePercentage() + "," +
			 * student.getInternalExamMarks() + "," +
			 * student.getInternalExamMarks() + ",'" + student.getResult() +
			 * "');"; System.out.println(sql); //statement.executeUpdate(sql);
			 */
			status = true;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			preparedStatement.close();
			connection.close();
		}

		return status;

	}

	public String getPieChartDataFromDB() throws SQLException {
		String result = "";
		Connection connection = null;
		Statement statement = null;
		int passCount, failCount;
		ResultSet resultSet = null;

		PieChart pieChart = null;
		PieChartData pieChartData = null;
		List<PieChartData> dataList = new ArrayList<>();
		
		List<Object> finalDataList = new ArrayList<>();

		try {
			Class.forName(ApplicationConstants.POSTGRES_JDBC_CLASS);
			//connection = DriverManager.getConnection(ApplicationConstants.POSTGRES_JDBC_URL_1,
					//ApplicationConstants.DB_USER, ApplicationConstants.DB_PASSWORD);
			
			connection = DriverManager.getConnection(ApplicationConstants.POSTGRES_JDBC_URL_1);
			
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select count(*) from predicted_result  where result = 'PASS';");
			while (resultSet.next()) {
				passCount = resultSet.getInt(1);
				pieChartData = new PieChartData("Pass", passCount);
				dataList.add(pieChartData);

			}
			resultSet = statement.executeQuery("select count(*) from predicted_result  where result = 'FAIL';");
			while (resultSet.next()) {
				failCount = resultSet.getInt(1);
				pieChartData = new PieChartData("Fail", failCount);
				dataList.add(pieChartData);
			}
			pieChart = new PieChart(dataList);
			finalDataList.add(pieChart);
			result = JsonUtil.convertObjectToString(finalDataList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			resultSet.close();
			statement.close();
			connection.close();
		}
		logger.info(result);
		return result;
	}

	public String getTabularData(String resultStatus) throws SQLException {
		String tableData = "";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String resultString = resultStatus.toUpperCase();

		List<Student> studentList = new ArrayList<>();

		Student student = null;

		try {
			Class.forName(ApplicationConstants.POSTGRES_JDBC_CLASS);
			//connection = DriverManager.getConnection(ApplicationConstants.POSTGRES_JDBC_URL,
					//ApplicationConstants.DB_USER, ApplicationConstants.DB_PASSWORD);
			
			connection = DriverManager.getConnection(ApplicationConstants.POSTGRES_JDBC_URL_1);
			
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select roll_no,name,half_yearly_marks,attendance_percentage,internal_exam_marks,"
							+ "assignment_count,result from predicted_result  where result = '" + resultString + "';");
			while (resultSet.next()) {
				int rollNumber = resultSet.getInt("roll_no");
				int halfYearlyMarks = resultSet.getInt("half_yearly_marks");
				int attendancePercentage = resultSet.getInt("attendance_percentage");
				int internalExamMarks = resultSet.getInt("internal_exam_marks");
				int assignmentsCount = resultSet.getInt("assignment_count");

				String name = resultSet.getString("name");
				String result = resultSet.getString("result");

				student = new Student(name, rollNumber, halfYearlyMarks, attendancePercentage, assignmentsCount,
						internalExamMarks, result);
				studentList.add(student);

			}

			tableData = JsonUtil.convertObjectToString(studentList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			resultSet.close();
			statement.close();
			connection.close();
		}
		logger.info(tableData);
		return tableData;
	}

	public static void main(String[] args) {
		System.out.println("test");
		try {
			// System.out.println(new
			// PredictedResultDao().getPieChartDataFromDB());
			System.out.println(new PredictedResultDao().getTabularData("pass"));
			System.out.println(new PredictedResultDao().getTabularData("faIl"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
