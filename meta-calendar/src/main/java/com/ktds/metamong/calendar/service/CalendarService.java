package com.ktds.metamong.calendar.service;

import java.util.List;

import com.ktds.metamong.calendar.vo.CalendarDateCountVO;
import com.ktds.metamong.calendar.vo.CalendarListVO;
import com.ktds.metamong.calendar.vo.CalendarSearchVO;
import com.ktds.metamong.calendar.vo.CalendarVO;

public interface CalendarService {

	public boolean addNewCalendar(CalendarVO calendarVO);
	
	public CalendarListVO getAllCalendarList(CalendarSearchVO calendarSearchVO);
	
	public CalendarVO getOneCalendar(String calendarId);
	
	public List<CalendarVO> getOneNowDateCalendar(String target);
	
	public List<CalendarVO> getHighlightsCalendar(CalendarDateCountVO calendarDateCountVO, CalendarVO calendarVO);
	
	public boolean removeCalendar(String calendarId);

	public boolean updateCalendar(CalendarVO calendarVO);
	
	public List<CalendarVO> getAllTodayToDoList();
}

