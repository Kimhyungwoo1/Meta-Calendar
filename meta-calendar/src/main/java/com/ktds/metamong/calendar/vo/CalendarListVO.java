package com.ktds.metamong.calendar.vo;

import java.util.List;

import com.ktds.metamong.common.web.Pager;
import com.ktds.metamong.common.web.PagerFactory;

public class CalendarListVO {

	private Pager pager;
	private List<CalendarVO> calendarList;

	public Pager getPager() {
		if ( pager == null ) {
			pager = PagerFactory.getPager(Pager.ORACLE);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<CalendarVO> getCalendarList() {
		return calendarList;
	}

	public void setCalendarList(List<CalendarVO> calendarList) {
		this.calendarList = calendarList;
	}

}
