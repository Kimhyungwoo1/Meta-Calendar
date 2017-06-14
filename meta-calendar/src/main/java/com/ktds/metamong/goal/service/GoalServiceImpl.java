package com.ktds.metamong.goal.service;

import java.util.List;

import com.ktds.metamong.goal.biz.GoalBiz;
import com.ktds.metamong.goal.vo.GoalListVO;
import com.ktds.metamong.goal.vo.GoalSearchVO;
import com.ktds.metamong.goal.vo.GoalVO;

public class GoalServiceImpl implements GoalService {

	private GoalBiz goalBiz;
	
	public void setGoalBiz(GoalBiz goalBiz) {
		this.goalBiz = goalBiz;
	}

	@Override
	public boolean addNewGoal(GoalVO goalVO) {
		
		if (goalVO.getYear() != null) {
			goalVO.setGoalTerm("Y");
			return goalBiz.addNewYearGoal(goalVO);
		}
		else if ( goalVO.getMonth() != null ) {
			goalVO.setGoalTerm("M");
			return goalBiz.addNewMonthGoal(goalVO);
		}
		else {
			return false;
		}
		
		
	}

	@Override
	public GoalListVO getAllGoalList(GoalSearchVO goalSearchVO) {
		return goalBiz.getAllGoalList(goalSearchVO);
	}
	
	@Override
	public List<GoalVO> getAllGoalView() {
		return goalBiz.getAllGoalView();
	}

	@Override
	public GoalVO getOneGoal(String goalId) {
		return goalBiz.getOneGoal(goalId);
	}

	@Override
	public boolean removeGoal(String goalId) {
		return goalBiz.removeGoal(goalId);
	}

	@Override
	public boolean modifyGoal(GoalVO goalVO) {
		return goalBiz.modifyGoal(goalVO);
	}

	
	
}
