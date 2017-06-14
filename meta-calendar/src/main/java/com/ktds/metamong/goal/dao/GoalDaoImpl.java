package com.ktds.metamong.goal.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.metamong.goal.vo.GoalSearchVO;
import com.ktds.metamong.goal.vo.GoalVO;

public class GoalDaoImpl extends SqlSessionDaoSupport implements GoalDao {

	@Override
	public int insertNewYearGoal(GoalVO GoalVO) {
		return getSqlSession().insert(GO + ".insertNewYearGoal", GoalVO);
	}
	
	@Override
	public int insertNewMonthGoal(GoalVO GoalVO) {
		return getSqlSession().insert(GO + ".insertNewMonthGoal", GoalVO);
	}
	
	@Override
	public List<GoalVO> selectAllGoalList(GoalSearchVO GoalSearchVO) {
		return getSqlSession().selectList(GO + ".selectAllGoalList", GoalSearchVO);
	}
	
	@Override
	public List<GoalVO> selectAllGoalView() {
		return getSqlSession().selectList(GO + ".selectAllGoalView");
	}

	@Override
	public int selectAllGoalListCount(GoalSearchVO GoalSearchVO) {
		return getSqlSession().selectOne(GO + ".selectAllGoalListCount", GoalSearchVO);
	}

	@Override
	public GoalVO selectOneGoal(String goalId) {
		return getSqlSession().selectOne(GO + ".selectOneGoal", goalId);
	}

	@Override
	public int deleteGoal(String GoalId) {
		return getSqlSession().delete(GO + ".deleteGoal", GoalId);
	}

	@Override
	public int updateGoal(GoalVO GoalVO) {
		return getSqlSession().update(GO + ".updateGoal", GoalVO);
	}

}
