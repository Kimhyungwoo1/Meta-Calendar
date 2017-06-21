package com.ktds.metamong.calendar.dao;

import java.util.List;

import com.ktds.metamong.calendar.vo.CalendarDateCountVO;
import com.ktds.metamong.calendar.vo.CalendarSearchVO;
import com.ktds.metamong.calendar.vo.CalendarVO;

public interface CalendarDao {

	public static final String CD = "CalendarDao";

	public int insertNewCalendar(CalendarVO calendarVO);
	
	public List<CalendarVO> selectAllCalendarList(CalendarSearchVO calendarSearchVO);

	public int selectAllCalendarListCount(CalendarSearchVO calendarSearchVO);

	public CalendarVO selectOneCalendar(String calendarId);
	
	public int deleteCalendar(String calendarId);

	public int updateCalendar(CalendarVO calendarVO);
	
	// 吏�湲� �궇吏� �뜲�씠�꽣 遺덈윭�삤湲�
	public List<CalendarVO> selectOneNowDateCalendar(String target);
	
	//�빐�떦 �궇吏쒖뿉 以꾨굹�삤�뒗 荑쇰━
	public List<CalendarVO> selectHighlightsCalendar();
	
	//startDate �� endDate 怨꾩궛 荑쇰━
	public int insertDateSub(CalendarDateCountVO calendarDateCountVO);
	
	// 怨꾩궛 �뮘 怨꾩궛�씠 �맟�뒗吏� �븞�맟�뒗吏� �솗�씤�썑 諛붽씀�뒗 荑쇰━ 荑쇰━
	public int updateCalendarYN(CalendarVO calendarVO);

	public List<CalendarVO> selectTodayToDoList();
	
}
