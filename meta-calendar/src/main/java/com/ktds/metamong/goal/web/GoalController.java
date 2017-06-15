package com.ktds.metamong.goal.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.metamong.goal.service.GoalService;
import com.ktds.metamong.goal.vo.GoalVO;

@Controller
public class GoalController {
	
	Logger logger = LoggerFactory.getLogger(GoalController.class);

	private GoalService goalService;
	
	public void setGoalService(GoalService goalService) {
		this.goalService = goalService;
	}
	
	@RequestMapping(value="/goal/write", method=RequestMethod.POST)
	public void doWriteGoal(GoalVO goalVO, HttpServletResponse response) {
		
		boolean addGoal = goalService.addNewGoal(goalVO);
		
		try {
			PrintWriter write = response.getWriter();
			write.write("ok");
			write.flush();
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/goal/list", method=RequestMethod.POST)
	public List<GoalVO> viewListPage() {
		
		List<GoalVO> allGoal = goalService.getAllGoalView();
		return allGoal;
	}
	
	@RequestMapping("/goal/detail/{id}")
	public ModelAndView viewDetailArticle(@PathVariable String id){
		ModelAndView view = new ModelAndView();
		view.setViewName("calendar/goal_modify");
		
		GoalVO goal = goalService.getOneGoal(id);
		view.addObject("goal",goal);
		return view;
	}
	
	@RequestMapping(value="/goal/delete", method=RequestMethod.POST)
	public void doDeleteArticleAction(@RequestParam(value="goalId") String goalId, HttpServletResponse response){
		
		boolean delete = goalService.removeGoal(goalId);
		
		if(delete) {
			try {
				PrintWriter write = response.getWriter();
				write.write("ok");
				write.flush();
				write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				PrintWriter write = response.getWriter();
				write.write("fail");
				write.flush();
				write.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@RequestMapping(value="/goal/update", method=RequestMethod.POST)
	public void ActionUpdate(GoalVO goalVO, HttpServletResponse response) {
		
		boolean goal = goalService.modifyGoal(goalVO);
		
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
