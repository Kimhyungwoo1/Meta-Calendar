package com.ktds.metamong.calendar.biz;

import java.util.List;

import com.ktds.metamong.calendar.vo.CalendarDateCountVO;
import com.ktds.metamong.calendar.vo.CalendarListVO;
import com.ktds.metamong.calendar.vo.CalendarSearchVO;
import com.ktds.metamong.calendar.vo.CalendarVO;

public interface CalendarBiz {
	
	public boolean addNewCalendar(CalendarVO calendarVO);
	
	public CalendarListVO getAllCalendarList(CalendarSearchVO calendarSearchVO);
	
	public CalendarVO getOneCalendar(String calendarId);
	
	public boolean removeCalendar(String calendarId);
	
	public boolean updateCalendar(CalendarVO calendarVO);
	
	public List<CalendarVO> getOneNowDateCalendar(String target);
	
	public List<CalendarVO> getHighlightsCalendar();
	
	public boolean addDateSub(CalendarDateCountVO calendarDateCountVO);
	
	public boolean modifyCalendarYN(CalendarVO calendarVO);
	
	public List<CalendarVO> getAllTodayToDoList();

}
