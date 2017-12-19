package com.ge.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ge.db.PredictedResultDao;
import com.ge.pojo.Student;
import com.ge.util.ApplicationConstants;
import com.ge.util.JsonUtil;
import com.ge.util.RestInvoker;

public class PredictionService {

	final static Logger logger = Logger.getLogger(PredictionService.class);

	public String predictStudentResult(Student student) {

		String studentQueryString = getAttributeJson(student);
		String predictedResult = "";

		try {

			RestInvoker restInvoker = new RestInvoker();

			String result = restInvoker.postData(studentQueryString);

			// Parse the result json. and pick up the result value

			JSONObject resultJson = new JSONObject(result);
			double label = resultJson.getDouble("label");
			
			predictedResult = label > 0 ? ApplicationConstants.PASS : ApplicationConstants.FAIL;

			//predictedResult = label.equals("1") ? ApplicationConstants.PASS : ApplicationConstants.FAIL;

			student.setResult(predictedResult);

			// save this to db
			boolean insertStatus = new PredictedResultDao().insertPredictedResult(student);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
		}
		return predictedResult;
	}

	public static void main(String[] args) {
		System.out.println("test");
		Student s = new Student("Tim1", 3, 11, 22, 33, 44, "PASS");
		try {
			// new PredictionService().predictStudentResult(s);
			String label = "1";
			String predictedResult = label.equals("1") ? ApplicationConstants.PASS : ApplicationConstants.FAIL;
			System.out.println(predictedResult);
			label = "0";
			predictedResult = label.equals("1") ? ApplicationConstants.PASS : ApplicationConstants.FAIL;
			System.out.println(predictedResult);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
