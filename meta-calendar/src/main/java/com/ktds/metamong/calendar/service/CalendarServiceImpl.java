package com.ktds.metamong.calendar.service;

import java.util.List;

import com.ktds.metamong.calendar.biz.CalendarBiz;
import com.ktds.metamong.calendar.vo.CalendarDateCountVO;
import com.ktds.metamong.calendar.vo.CalendarListVO;
import com.ktds.metamong.calendar.vo.CalendarSearchVO;
import com.ktds.metamong.calendar.vo.CalendarVO;

public class CalendarServiceImpl implements CalendarService {

	private CalendarBiz calendarBiz;
	
	public void setCalendarBiz(CalendarBiz calendarBiz) {
		this.calendarBiz = calendarBiz;
	}

	@Override
	public boolean addNewCalendar(CalendarVO calendarVO) {
		return calendarBiz.addNewCalendar(calendarVO);
	}

	@Override
	public CalendarListVO getAllCalendarList(CalendarSearchVO calendarSearchVO) {
		return calendarBiz.getAllCalendarList(calendarSearchVO);
	}

	@Override
	public CalendarVO getOneCalendar(String calendarId) {
		return calendarBiz.getOneCalendar(calendarId);
	}

	@Override
	public List<CalendarVO> getOneNowDateCalendar(String target) {
		return calendarBiz.getOneNowDateCalendar(target);
	}
	
	@Override
	public List<CalendarVO> getHighlightsCalendar(CalendarDateCountVO calendarDateCountVO, CalendarVO calendarVO) {
		return calendarBiz.getHighlightsCalendar();
	}

	@Override
	public boolean removeCalendar(String calendarId) {
		return calendarBiz.removeCalendar(calendarId);
	}

	@Override
	public boolean updateCalendar(CalendarVO calendarVO) {
		return calendarBiz.updateCalendar(calendarVO);
	}

	@Override
	public List<CalendarVO> getAllTodayToDoList() {
		return calendarBiz.getAllTodayToDoList();
	}

}
