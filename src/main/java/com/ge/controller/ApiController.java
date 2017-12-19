package com.ge.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ge.pojo.Student;
import com.ge.service.PredictionService;
import com.ge.service.VisualisationService;
import com.ge.util.ApplicationConstants;
import com.ge.util.JsonUtil;

@Path("/api")
public class ApiController {

	final static Logger logger = Logger.getLogger(ApiController.class);

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String status() {
		logger.info("START - ApiController.status , Test web service, GET  \n");
		String message = "Application is up and running";
		return message;
	}

	@POST
	@Path("/predict")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String predictStudentResult(String studentJsonString) {
		String predictedResult = "";
		// "name":"Jim","rollNumber":11,"halfYearlyMarks":60,"attendancePercentage":89,"assigmentsCount":2,"internalExamMarks":89,"result":"Pass"
		try {
			if (StringUtils.isNotEmpty(studentJsonString)) {
				JSONObject student = new JSONObject(studentJsonString);
				String name = student.getString("name");
				int rollNumber = student.getInt("rollNumber");
				int halfYearlyMarks = student.getInt("halfYearlyMarks");
				int attendancePercentage = student.getInt("attendancePercentage");
				int assigmentsCount = student.getInt("assigmentsCount");
				int internalExamMarks = student.getInt("internalExamMarks");

				Student studentObject = new Student(name, rollNumber, halfYearlyMarks, attendancePercentage, assigmentsCount,
						internalExamMarks, ApplicationConstants.UNKNOWN_STATUS);
				predictedResult = new PredictionService().predictStudentResult(studentObject);
				
				//predictedResult= "PASS";
				
				JSONObject resultJson = new JSONObject();
				resultJson.put("message", predictedResult);
				predictedResult = resultJson.toString();
				
				
			} else {
				predictedResult = "INVALID_INPUT";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			predictedResult = "INVALID_INPUT";
		}

		return predictedResult;
	}

	@GET
	@Path("/train")
	public String train() {

		return "Training initiated";
	}

	// Visualization API's
	@GET
	@Path("/pieChartData")
	@Produces(MediaType.APPLICATION_JSON)
	public String getPieChartData(@Context HttpHeaders headers) {
		String result = new VisualisationService().getPieChartData();
		return result;
	}


	@GET
	@Path("/tabularData/{param}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTabularData(@PathParam("param") String resultStatus) {
		String result = new VisualisationService().getTableData(resultStatus);
		return result;
	}

}
