package com.ge.service;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ge.db.PredictedResultDao;

public class VisualisationService {
	final static Logger logger = Logger.getLogger(VisualisationService.class);

	public String getPieChartData() {
		String pieChartData = "";
		try {
			pieChartData = new PredictedResultDao().getPieChartDataFromDB();
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return pieChartData;
	}
	
	public String getTableData(String result) {
		String tableData = "";
		try {
			tableData = new PredictedResultDao().getTabularData(result);
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
		}
		return tableData;
	}
}
