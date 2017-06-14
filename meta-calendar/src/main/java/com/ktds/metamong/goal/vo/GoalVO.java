package com.ktds.metamong.goal.vo;

public class GoalVO {
	
	private String goalId;
	private String goalTitle;
	private String goalSubTitle;
	private String goalTerm;
	private String goalCreateDate;
	
	private String year;
	private String month;
	private String week;
	
	public String getGoalCreateDate() {
		return goalCreateDate;
	}
	
	public void setGoalCreateDate(String goalCreateDate) {
		this.goalCreateDate = goalCreateDate;
	}

	public String getGoalTerm() {
		return goalTerm;
	}

	public void setGoalTerm(String goalTerm) {
		this.goalTerm = goalTerm;
	}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getGoalId() {
		return goalId;
	}
	public void setGoalId(String goalId) {
		this.goalId = goalId;
	}
	public String getGoalTitle() {
		return goalTitle;
	}
	public void setGoalTitle(String goalTitle) {
		this.goalTitle = goalTitle;
	}
	public String getGoalSubTitle() {
		return goalSubTitle;
	}

	public void setGoalSubTitle(String goalSubTitle) {
		this.goalSubTitle = goalSubTitle;
	}
}
