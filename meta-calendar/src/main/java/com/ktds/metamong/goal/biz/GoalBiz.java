package com.ktds.metamong.goal.biz;

import java.util.List;

import com.ktds.metamong.goal.vo.GoalListVO;
import com.ktds.metamong.goal.vo.GoalSearchVO;
import com.ktds.metamong.goal.vo.GoalVO;

public interface GoalBiz {

	public boolean addNewYearGoal(GoalVO goalVO);
	
	public boolean addNewMonthGoal(GoalVO goalVO);
	
	public GoalListVO getAllGoalList(GoalSearchVO goalSearchVO);
	
	public List<GoalVO> getAllGoalView();
	
	public GoalVO getOneGoal(String goalId);
	
	public boolean removeGoal(String goalId);
	
	public boolean modifyGoal(GoalVO goalVO);
}
