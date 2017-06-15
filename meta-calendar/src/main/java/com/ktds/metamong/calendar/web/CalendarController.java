package com.ktds.metamong.calendar.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.metamong.calendar.service.CalendarService;
import com.ktds.metamong.calendar.vo.CalendarDateCountVO;
import com.ktds.metamong.calendar.vo.CalendarVO;

@Controller
public class CalendarController {

	private Logger logger = LoggerFactory.getLogger(CalendarController.class);

	private CalendarService calendarService;

	public void setCalendarService(CalendarService calendarService) {
		this.calendarService = calendarService;
	}

	@RequestMapping(value = "/cal/list", method = RequestMethod.GET)
	public ModelAndView viewListPage(CalendarDateCountVO calendarDateCountVO, CalendarVO calendarVO) {

		List<CalendarVO> calendarList = calendarService.getHighlightsCalendar(calendarDateCountVO, calendarVO);
		// CalendarVO oneCalendar = calendarService.getOneCalendar(calendarId);

		ModelAndView view = new ModelAndView();

		view.setViewName("calendar/calendar_main");
		view.addObject("calendarList", calendarList);

		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/cal/list", method = RequestMethod.POST)
	public List<CalendarVO> sendAllList(CalendarDateCountVO calendarDateCountVO, CalendarVO calendarVO) {

		List<CalendarVO> calendarList = calendarService.getHighlightsCalendar(calendarDateCountVO, calendarVO);

		return calendarList;
	}

	@RequestMapping(value = "/cal/write", method = RequestMethod.POST)
	public String doWriteCalendar(@RequestParam(value = "calendarTitle") String calendarTitle,
			@RequestParam(value = "calendarSubTitle") String calendarSubTitle,
			@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate,
			HttpServletResponse response, HttpServletRequest request) {

		CalendarVO calendarVO = new CalendarVO();
		calendarVO.setCalendarTitle(calendarTitle);
		calendarVO.setCalendarSubTitle(calendarSubTitle);
		calendarVO.setStartDate(startDate);
		calendarVO.setEndDate(endDate);

		boolean isSuccess = calendarService.addNewCalendar(calendarVO);

		if (isSuccess) {
			try {
				PrintWriter write = response.getWriter();
				write.write("ok");
				write.flush();
				write.close();
			} catch (IOException e) {
			}
		}

		return "redirect:/cal/list";
	}

	@RequestMapping("/cal/delete")
	public void doDeleteArticleAction(@RequestParam(value = "calendarId") String calendarId,
			HttpServletResponse response) {

		System.out.println(calendarId);
		boolean isSuccess = calendarService.removeCalendar(calendarId);

		if (isSuccess) {
			try {
				PrintWriter write = response.getWriter();
				write.write("ok");
				write.flush();
				write.close();
			} catch (IOException e) {
			}
		}
	}

	@RequestMapping(value = "/cal/detail/{calendarId}", method = RequestMethod.GET)
	public ModelAndView viewDetailArticle(@PathVariable String calendarId) {

		logger.info("aa : " + calendarId);

		CalendarVO oneCalendar = calendarService.getOneCalendar(calendarId);

		ModelAndView view = new ModelAndView();
		view.setViewName("calendar/calendar_modify");
		view.addObject("oneCalendar", oneCalendar);

		return view;
	}

	@RequestMapping(value = "/cal/update", method = RequestMethod.POST)
	public void ActionUpdate(HttpServletResponse response, CalendarVO calendarVO) {

		boolean calendarUpdate = calendarService.updateCalendar(calendarVO);

		if (calendarUpdate) {
			try {
				PrintWriter write = response.getWriter();
				write.write("ok");
				write.flush();
				write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 기능 테스트를 위한 페이지 - 규동
	@RequestMapping(value = "/cal/test", method = RequestMethod.GET)
	public String testPage() {
		return "calendar/test";
	}

	@RequestMapping(value = "/cal/test", method = RequestMethod.POST)
	public String doTestPage(
			@Valid @ModelAttribute("valiTestForm") Errors errors,
			CalendarVO calendarVO,
			HttpServletResponse response, HttpServletRequest request) {

		if (errors.hasErrors()) {
			return "/cal/test";
		}

		CalendarVO calendar = new CalendarVO();

		calendar.setCalendarTitle(calendarVO.getCalendarTitle());
		calendar.setCalendarSubTitle(calendarVO.getCalendarSubTitle());
		calendar.setStartDate(calendarVO.getStartDate());
		calendar.setEndDate(calendarVO.getEndDate());

		boolean isSuccess = calendarService.addNewCalendar(calendar);

		if (isSuccess) {
			try {
				PrintWriter write = response.getWriter();
				write.write("ok");
				write.flush();
				write.close();
			} catch (IOException e) {
			}
		}

		return "redirect:/cal/list";

	}

	@ResponseBody
	@RequestMapping(value = "/cal/dday", method = RequestMethod.POST)
	public List<CalendarVO> alertTest() {

		List<CalendarVO> toDoList = calendarService.getAllTodayToDoList();

		return toDoList;
	}

	@RequestMapping("/cal/delete/{id}")
	public String doDeleteArticleAction(@PathVariable String id) {

		if (calendarService.removeCalendar(id)) {
			return "redirect:/cal/list";
		} else {
			return "";
		}
	}

	@RequestMapping(value = "/cal/update/{calendarId}", method = RequestMethod.GET)
	public ModelAndView viewUpdatePage(@PathVariable String calendarId) {
		ModelAndView view = new ModelAndView();

		CalendarVO calVO = calendarService.getOneCalendar(calendarId);

		view.setViewName("cal/update");
		view.addObject("cal", calVO);

		return view;
	}

	@RequestMapping(value = "/cal/update/{calendarId}", method = RequestMethod.POST)
	public String ActionUpdate(CalendarVO CalendarVO, @PathVariable String calendarId) {

		CalendarVO.setCalendarId(calendarId);
		boolean isSuccess = calendarService.updateCalendar(CalendarVO);

		return "redirect:/cal/list";
	}

	@RequestMapping("/cal/testdesign")
	public String testDesign() {
		return "calendar/calendar_design";
	}

	// Json�쑝濡� �뙆�씪�쓣 蹂대궡以��떎. (spring�뿉�꽌 蹂대궡二쇰뒗 json�삎�깭 �뜲�씠�꽣�뒗
	// parse�빐以꾪븘�슂�뾾�씠 諛붾줈 �벝�닔�엳�떎.)
	@ResponseBody
	@RequestMapping(value = "/cal/onecal", method = RequestMethod.POST)
	public List<CalendarVO> actionOnecalendar(HttpServletResponse response,
			@RequestParam(value = "target") String target) {

		System.out.println(target);

		List<CalendarVO> oneCalendar = calendarService.getOneNowDateCalendar(target);

		return oneCalendar;
	}

	@RequestMapping("/")
	public String mainPage(){
		return "common/mainPage";
	}

}
