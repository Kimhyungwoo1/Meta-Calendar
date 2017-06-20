package com.ktds.metamong.calendar.dao;

import java.util.Calendar;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.metamong.calendar.vo.CalendarDateCountVO;
import com.ktds.metamong.calendar.vo.CalendarSearchVO;
import com.ktds.metamong.calendar.vo.CalendarVO;

public class CalendarDaoImpl extends SqlSessionDaoSupport implements CalendarDao {

	@Override
	public int insertNewCalendar(CalendarVO calendarVO) {
		return getSqlSession().insert(CD + ".insertNewCalendar", calendarVO);
	}

	@Override
	public List<CalendarVO> selectAllCalendarList(CalendarSearchVO calendarSearchVO) {
		return getSqlSession().selectList(CD + ".selectAllCalendarList", calendarSearchVO);
	}

	@Override
	public int selectAllCalendarListCount(CalendarSearchVO calendarSearchVO) {
		return getSqlSession().selectOne(CD + ".selectAllCalendarListCount", calendarSearchVO);
	}

	@Override
	public CalendarVO selectOneCalendar(String calendarId) {
		return getSqlSession().selectOne(CD + ".selectOneCalendar", calendarId);
	}

	@Override
	public List<CalendarVO> selectOneNowDateCalendar(String target) {
		return getSqlSession().selectList(CD + ".selectOneNowDateCalendar", target);
	}

	@Override
	public List<CalendarVO> selectHighlightsCalendar() {
		return getSqlSession().selectList(CD + ".selectHighlightsCalendar");
	}

	@Override
	public int deleteCalendar(String calendarId) {
		return getSqlSession().delete(CD + ".deleteCalendar", calendarId);
	}

	@Override
	public int updateCalendar(CalendarVO calendarVO) {
		return getSqlSession().update(CD + ".updateCalendar", calendarVO);
	}

	@Override

	public int insertDateSub(CalendarDateCountVO calendarDateCountVO) {
		return getSqlSession().insert(CD + ".insertDateSub", calendarDateCountVO);
	}

	@Override
	public int updateCalendarYN(CalendarVO calendarVO) {
		return 0;
	}

	public List<CalendarVO> selectTodayToDoList() {
		return getSqlSession().selectList(CD + ".selectTodayToDoList");
	}


}
