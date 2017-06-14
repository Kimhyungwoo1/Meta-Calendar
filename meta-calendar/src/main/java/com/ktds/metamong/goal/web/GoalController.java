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
		view.setViewName("goal/detail");
		
		GoalVO goal = goalService.getOneGoal(id);
		view.addObject("goal",goal);
		return view;
	}
	
	@RequestMapping(value="/goal/delete", method=RequestMethod.POST)
	public String doDeleteArticleAction(@RequestParam(value="goalId") String goalId){
		
		
		return null;
	}
	@RequestMapping(value="/goal/update/{goalId}", method=RequestMethod.GET)
	public ModelAndView viewUpdatePage(@PathVariable String goalId) {
		ModelAndView view = new ModelAndView();
		
		GoalVO goalVO = goalService.getOneGoal(goalId);
		
		view.setViewName("goal/update");
		view.addObject("goal", goalVO);
		
		return view;
	}
	
	@RequestMapping(value="/goal/update/{goalId}", method=RequestMethod.POST)
	public String ActionUpdate(GoalVO goalVO, @PathVariable String goalId) {
		
		goalVO.setGoalId(goalId);
		boolean goal = goalService.modifyGoal(goalVO);
		
		return "redirect:/goal/list";
	}
	
}
