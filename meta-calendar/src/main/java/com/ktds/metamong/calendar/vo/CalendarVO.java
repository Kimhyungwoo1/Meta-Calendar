package com.ktds.metamong.calendar.vo;

import javax.validation.constraints.NotNull;

public class CalendarVO {

	private String calendarId;
	
	@NotNull
	private String calendarTitle;
	@NotNull
	private String calendarSubTitle;
	@NotNull
	private String startDate;
	@NotNull
	private String endDate;
	private String gps;
	private String countYN;
	
	public String getCountYN() {
		return countYN;
	}
	
	public void setCountYN(String countYN) {
		this.countYN = countYN;
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

}
