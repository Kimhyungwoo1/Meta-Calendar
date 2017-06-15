package com.ktds.metamong.calendar.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class CalendarVO {

	private String calendarId;
	
	@NotEmpty(message = "���� �̸��� �Է��ϼ���")
	private String calendarTitle;
	@NotEmpty(message = "�� ������ �Է��ϼ���")
	private String calendarSubTitle;
	@NotEmpty(message = "���� ��¥�� �Է��ϼ���")
	private String startDate;
	@NotEmpty(message = "���� ��¥�� �Է��ϼ���")
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

}
