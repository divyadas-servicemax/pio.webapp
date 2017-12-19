package com.ge.pojo;

public class PieChartData {
	String label;
	int count;

	public PieChartData(String label, int count) {
		super();
		this.label = label;
		this.count = count;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "PieChart [label=" + label + ", count=" + count + "]";
	}
}