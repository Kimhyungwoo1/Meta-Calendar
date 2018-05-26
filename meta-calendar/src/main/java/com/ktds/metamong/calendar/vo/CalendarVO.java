package com.ktds.metamong.calendar.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class CalendarVO {

	private String calendarId;
	
	@NotEmpty(message = "���� ������ �Է��ϼ���.")
	private String calendarTitle;
	@NotEmpty(message = "������ ���� ������ �Է��ϼ���.")
	private String calendarSubTitle;
	@NotEmpty(message = "���� ��¥�� �Է��ϼ���.")
	private int startDateInt;
	@NotEmpty(message = "���� ��¥�� �Է��ϼ���.")
	private int endDateInt;
	private int startDateYear;
	private int startDateMonth;
	private int startDateDay;
	private int endDateYear;
	private int endDateMonth;
	private int endDateDay;
	private String startDate;
	private String endDate;
	private int other;
	
	private String gps;
	
	public void setStartDateDay(int startDateDay) {
		this.startDateDay = startDateDay;
	}
	
	public int getStartDateDay() {
		return startDateDay;
	}

	public int getEndDateDay() {
		return endDateDay;
	}
	
	public void setEndDateDay(int endDateDay) {
		this.endDateDay = endDateDay;
	}
	
	public void setOther(int other) {
		this.other = other;
	}
	
	public int getOther() {
		return other;
	}
	
	public int getStartDateYear() {
		return startDateYear;
	}

	public void setStartDateYear(int startDateYear) {
		this.startDateYear = startDateYear;
	}

	public int getStartDateMonth() {
		return startDateMonth;
	}

	public void setStartDateMonth(int startDateMonth) {
		this.startDateMonth = startDateMonth;
	}

	public int getEndDateYear() {
		return endDateYear;
	}

	public void setEndDateYear(int endDateYear) {
		this.endDateYear = endDateYear;
	}

	public int getEndDateMonth() {
		return endDateMonth;
	}

	public void setEndDateMonth(int endDateMonth) {
		this.endDateMonth = endDateMonth;
	}

	public String getCalendarId() {
		return calendarId;
	}

	public void setCalendarId(String calendarId) {
		this.calendarId = calendarId;
	}

	public String getCalendarTitle() {
		return calendarTitle;
	}

	public void setCalendarTitle(String calendarTitle) {
		this.calendarTitle = calendarTitle;
	}

	public String getCalendarSubTitle() {
		return calendarSubTitle;
	}

	public void setCalendarSubTitle(String calendarSubTitle) {
		this.calendarSubTitle = calendarSubTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public int getStartDateInt() {
		return startDateInt;
	}

	public void setStartDateInt(int startDateInt) {
		this.startDateInt = startDateInt;
	}

	public int getEndDateInt() {
		return endDateInt;
	}

	public void setEndDateInt(int endDateInt) {
		this.endDateInt = endDateInt;
	}

}
