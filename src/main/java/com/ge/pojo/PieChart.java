package com.ge.pojo;

import java.util.ArrayList;
import java.util.List;

public class PieChart {

	List<PieChartData> values = new ArrayList<>();

	public PieChart(List<PieChartData> values) {
		super();
		this.values = values;
	}

	public List<PieChartData> getValues() {
		return values;
	}

	public void setValues(List<PieChartData> values) {
		this.values = values;
	}

}

