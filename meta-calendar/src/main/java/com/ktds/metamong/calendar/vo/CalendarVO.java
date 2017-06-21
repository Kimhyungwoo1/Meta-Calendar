package com.ktds.metamong.calendar.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class CalendarVO {

	private String calendarId;
	
	@NotEmpty(message = "일정 제목을 입력하세요.")
	private String calendarTitle;
	@NotEmpty(message = "일정에 대한 설명을 입력하세요.")
	private String calendarSubTitle;
	@NotEmpty(message = "시작 날짜를 입력하세요.")
	private int startDateInt;
	@NotEmpty(message = "종료 날짜를 입력하세요.")
	private int endDateInt;
	private String startDate;
	private String endDate;
	
	private String gps;

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
