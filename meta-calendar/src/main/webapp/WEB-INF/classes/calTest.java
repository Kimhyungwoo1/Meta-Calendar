package com.ktds.metamong.calendar.web;

import java.util.Calendar;

public class calTest {
		
	public static Calendar getNowCalendar(int year, int month) {
		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		return cal;
	}
	
	public static int printCalendar(Calendar cal, StringBuffer str) {
		int blankTd = 0;
		
		for ( int i = 1; i <= cal.getMaximum(Calendar.DAY_OF_MONTH); i++ ) {
			cal.set(Calendar.DAY_OF_MONTH, i);
			blankTd = cal.get(Calendar.DAY_OF_WEEK);
			
			boolean isEnd = blankTd % 7 == 0 || i == cal.getMaximum(Calendar.DAY_OF_MONTH);
			boolean isStart = blankTd % 7 == 1 || i == 1;
			
			writeDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, i, isEnd, isStart, str);
			
			if ( isEnd && i != cal.getMaximum(Calendar.DAY_OF_MONTH) ) {
				str.append("\t</tr>\n\t<tr>\n");
			}
		}
		
		return blankTd;
	}
	
	public static void printPrevAndNext(Calendar cal, int duration, StringBuffer str) {
		for ( int i = 0; i < duration ; i++ ) {
			cal.add(Calendar.DAY_OF_MONTH,  +1);
			writeDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), false, false, str);
		}
	}
	
	public static void writeDate(int year, int month, int date, boolean isEnd, boolean isStart, StringBuffer str) {
		String startOrEnd = " class='";
		
		if ( isEnd ) {
			startOrEnd += "end";
		}
		if( isStart ){
			if ( startOrEnd.length() > " class='".length() ) {
				startOrEnd += " ";
			}
			startOrEnd += "start";
		}
		
		if ( startOrEnd.length() > " class='".length() ) {
			startOrEnd += "'";
		}
		else {
			startOrEnd = "";
		}
		
		str.append("\t\t<td data-year='"+year+"' data-month='"+month+"'"+startOrEnd+">" + date + "</td>\n");
	}
	
}