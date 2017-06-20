package com.ktds.metamong.calendar.service;

import java.util.Calendar;
import java.util.List;

import com.ktds.metamong.calendar.biz.CalendarBiz;
import com.ktds.metamong.calendar.vo.CalendarDateCountVO;
import com.ktds.metamong.calendar.vo.CalendarListVO;
import com.ktds.metamong.calendar.vo.CalendarSearchVO;
import com.ktds.metamong.calendar.vo.CalendarVO;
import com.sun.media.jfxmedia.logging.Logger;

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

//	@Override
//	public String showCalendar(int year, int month) {
//		
//		Calendar cal = getNowCalendar(year, month);
//		
//		int blankTd = cal.get(Calendar.DAY_OF_WEEK);
//		cal.add(Calendar.DAY_OF_MONTH,  -blankTd);
//		
//		StringBuffer str = new StringBuffer();
//		str.append("<table style='height:100%; width=:100%;' id='mainCalendar' class='table table-striped'>\n");
//		str.append("\t<tr>\n");
//		printPrevAndNextMonth(str, year, month);
//		printPrevAndNext(cal, blankTd-1, str);
//		cal.add(Calendar.DAY_OF_MONTH, +1);
//		
//		blankTd = printCalendar(cal, str);
//		
//		printPrevAndNext(cal, 7 - blankTd, str);
//		str.append("\t</tr>\n");
//		str.append("</table>\n");
//		
//		return str.toString();
//	}
//		
//	public static Calendar getNowCalendar(int year, int month) {
//		Calendar cal = Calendar.getInstance();
//		
//		cal.set(Calendar.YEAR, year);
//		cal.set(Calendar.MONTH, month - 1);
//		cal.set(Calendar.DAY_OF_MONTH, 1);
//		
//		return cal;
//	}
//	
//	public static String printPrevAndNextMonth(StringBuffer str, int year, int month) {
//		
//		str.append("<div><div class='prev' style='display:inline-block;'> < </div> "+ year + "," + month +"<div class='next' style='display:inline-block;'> > </div></div>");
//		
//		return str.toString();
//	}
//	
//	public static int printCalendar(Calendar cal, StringBuffer str) {
//		int blankTd = 0;
//		
//		for ( int i = 1; i <= cal.getMaximum(Calendar.DAY_OF_MONTH); i++ ) {
//			cal.set(Calendar.DAY_OF_MONTH, i);
//			blankTd = cal.get(Calendar.DAY_OF_WEEK);
//			
//			boolean isEnd = blankTd % 7 == 0 || i == cal.getMaximum(Calendar.DAY_OF_MONTH);
//			boolean isStart = blankTd % 7 == 1 || i == 1;
//			
//			writeDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, i, isEnd, isStart, str);
//			
//			if ( isEnd && i != cal.getMaximum(Calendar.DAY_OF_MONTH) ) {
//				str.append("\t</tr>\n\t<tr>\n");
//			}
//		}
//		
//		return blankTd;
//	}
//	
//	public static void printPrevAndNext(Calendar cal, int duration, StringBuffer str) {
//		for ( int i = 0; i < duration ; i++ ) {
//			cal.add(Calendar.DAY_OF_MONTH,  +1);
//			writeDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), false, false, str);
//		}
//	}
//	
//	public static void writeDate(int year, int month, int date, boolean isEnd, boolean isStart, StringBuffer str) {
//		String startOrEnd = " class='";
//		
//		if ( isEnd ) {
//			startOrEnd += "end";
//		}
//		if( isStart ){
//			if ( startOrEnd.length() > " class='".length() ) {
//				startOrEnd += " ";
//			}
//			startOrEnd += "start";
//		}
//		
//		if ( startOrEnd.length() > " class='".length() ) {
//			startOrEnd += "'";
//		}
//		else {
//			startOrEnd = "";
//		}
//		
//		str.append("\t\t<td data-year='"+year+"' data-month='"+month+"'"+startOrEnd+">" + date + "</td>\n");
//	}
}


