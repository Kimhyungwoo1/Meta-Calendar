package com.ktds.metamong.goal.dao;

import java.util.List;

import com.ktds.metamong.goal.vo.GoalSearchVO;
import com.ktds.metamong.goal.vo.GoalVO;

public interface GoalDao {

	public static final String GO = "GoalDao";
	
	public int insertNewYearGoal(GoalVO goalVO);
	
	public int insertNewMonthGoal(GoalVO goalVO);
	
	public List<GoalVO> selectAllGoalList(GoalSearchVO goalSearchVO);
	
	public List<GoalVO> selectAllGoalView();
	
	public int selectAllGoalListCount(GoalSearchVO goalSearchVO);
	
	public GoalVO selectOneGoal(String goalId);
	
	public int deleteGoal(String goalId);
	
	public int updateGoal(GoalVO goalVO);	
}
