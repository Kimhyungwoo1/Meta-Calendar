package com.ktds.metamong.goal.service;

import java.util.List;

import com.ktds.metamong.goal.vo.GoalListVO;
import com.ktds.metamong.goal.vo.GoalSearchVO;
import com.ktds.metamong.goal.vo.GoalVO;

public interface GoalService {
	
	public boolean addNewGoal(GoalVO goalVO);
	
	public GoalListVO getAllGoalList(GoalSearchVO goalSearchVO);
	
	public List<GoalVO> getAllGoalView();
	
	public GoalVO getOneGoal(String goald);
	
	public boolean removeGoal(String goalId);
	
	public boolean modifyGoal(GoalVO goalVO);
}
