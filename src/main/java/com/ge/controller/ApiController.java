package com.ge.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.ge.pojo.Student;
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
		String status = "";
		// "name":"Jim","rollNumber":11,"halfYearlyMarks":60,"attendancePercentage":89,"assigmentsCount":2,"internalExamMarks":89,"result":"Pass"
		try {
			if (StringUtils.isNotEmpty(studentJsonString)) {
				JSONObject student = JsonUtil.getJsonObject(studentJsonString);
				String name = student.getString("name");
				int rollNumber = student.getInt("rollNumber");
				int halfYearlyMarks = student.getInt("halfYearlyMarks");
				int attendancePercentage = student.getInt("attendancePercentage");
				int assigmentsCount = student.getInt("assigmentsCount");
				int internalExamMarks = student.getInt("internalExamMarks");

				Student s = new Student(name, rollNumber, halfYearlyMarks, attendancePercentage, assigmentsCount,
						internalExamMarks, ApplicationConstants.UNKNOWN_STATUS);
				
				
				
				System.out.println(s);
			} else {
				status = "INVALID_INPUT";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return status;
	}

	// Visualization API's
	@GET
	@Path("/pieChartData")
	// @Produces(MediaType.APPLICATION_JSON)
	public String getFilesList(@Context HttpHeaders headers) {
		logger.info("START - UIServicesControllerV1.getFilesList , GET  - files\n");

		return "aa";// JsonUtil.convertObjectToString("");
	}

}
