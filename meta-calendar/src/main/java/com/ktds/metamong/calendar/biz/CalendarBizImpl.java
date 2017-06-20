package com.ktds.metamong.calendar.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.metamong.calendar.dao.CalendarDao;
import com.ktds.metamong.calendar.vo.CalendarDateCountVO;
import com.ktds.metamong.calendar.vo.CalendarListVO;
import com.ktds.metamong.calendar.vo.CalendarSearchVO;
import com.ktds.metamong.calendar.vo.CalendarVO;
import com.ktds.metamong.common.web.Pager;

public class CalendarBizImpl implements CalendarBiz {

	private CalendarDao calendarDao;
	
	public void setCalendarDao(CalendarDao calendarDao) {
		this.calendarDao = calendarDao;
	}

	@Override
	public boolean addNewCalendar(CalendarVO calendarVO) {
		return calendarDao.insertNewCalendar(calendarVO) > 0;
	}

	@Override
	public CalendarListVO getAllCalendarList(CalendarSearchVO calendarSearchVO) {
		
		CalendarListVO calendarListVO = new CalendarListVO();
		Pager pager = calendarListVO.getPager();
		pager.setPageNumber(calendarSearchVO.getPageNo());
		
		int listCount = calendarDao.selectAllCalendarListCount(calendarSearchVO);
		calendarSearchVO.setPageNo(listCount);
		
		calendarSearchVO.setEndArticleNumber(pager.getEndArticleNumber());
		calendarSearchVO.setStartArticleNumber(pager.getStartArticleNumber());
		
		if ( listCount != 0 ) {
			List<CalendarVO> calendarList = calendarDao.selectAllCalendarList(calendarSearchVO);
			calendarListVO.setCalendarList(calendarList);
		}
		else {
			calendarListVO.setCalendarList(new ArrayList<CalendarVO>());
		}
		
		return calendarListVO;
	}

	@Override
	public CalendarVO getOneCalendar(String calendarId) {
		return calendarDao.selectOneCalendar(calendarId);
	}
	
	@Override
	public List<CalendarVO> getOneNowDateCalendar(String target) {
		return calendarDao.selectOneNowDateCalendar(target);
	}
	
	@Override
	public List<CalendarVO> getHighlightsCalendar() {
		return calendarDao.selectHighlightsCalendar();
	}

	@Override
	public boolean removeCalendar(String calendarId) {
		return calendarDao.deleteCalendar(calendarId) > 0;
	}

	@Override
	public boolean updateCalendar(CalendarVO calendarVO) {
		return calendarDao.updateCalendar(calendarVO) > 0;
	}

	@Override
	public boolean addDateSub(CalendarDateCountVO calendarDateCountVO) {
		return calendarDao.insertDateSub(calendarDateCountVO) > 0;
	}

	@Override
	public boolean modifyCalendarYN(CalendarVO calendarVO) {
		return calendarDao.updateCalendarYN(calendarVO) > 0;
	}
	
	public List<CalendarVO> getAllTodayToDoList() {
		return calendarDao.selectTodayToDoList();
	}


}
