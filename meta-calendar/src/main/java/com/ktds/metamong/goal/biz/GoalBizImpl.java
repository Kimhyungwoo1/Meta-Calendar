package com.ktds.metamong.goal.biz;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ktds.metamong.common.web.Pager;
import com.ktds.metamong.goal.dao.GoalDao;
import com.ktds.metamong.goal.vo.GoalListVO;
import com.ktds.metamong.goal.vo.GoalSearchVO;
import com.ktds.metamong.goal.vo.GoalVO;

public class GoalBizImpl implements GoalBiz {
	
	private Logger logger = LoggerFactory.getLogger(GoalBizImpl.class);

	private GoalDao goalDao;
	
	public void setGoalDao(GoalDao goalDao) {
		this.goalDao = goalDao;
	}

	@Override
	public boolean addNewYearGoal(GoalVO goalVO) {
		return goalDao.insertNewYearGoal(goalVO) > 0;
	}
	
	@Override
	public boolean addNewMonthGoal(GoalVO goalVO) {
		return goalDao.insertNewMonthGoal(goalVO) > 0;
	}
	
	@Override
	public GoalListVO getAllGoalList(GoalSearchVO goalSearchVO) {
		
		GoalListVO goalListVO = new GoalListVO();
		Pager pager = goalListVO.getPager();
		pager.setPageNumber(goalSearchVO.getPageNo());
		
		int listCount = goalDao.selectAllGoalListCount(goalSearchVO);
		goalSearchVO.setPageNo(listCount);
		
		goalSearchVO.setEndArticleNumber(pager.getEndArticleNumber());
		goalSearchVO.setStartArticleNumber(pager.getStartArticleNumber());
		
		if ( listCount != 0 ) {
			List<GoalVO> goalList = goalDao.selectAllGoalList(goalSearchVO);
			goalListVO.setGoalList(goalList);
		}
		else {
			goalListVO.setGoalList(new ArrayList<GoalVO>());
		}
		
		return goalListVO;
	}
	
	@Override
	public List<GoalVO> getAllGoalView() {
		return goalDao.selectAllGoalView();
	}

	@Override
	public GoalVO getOneGoal(String goalId) {
		return goalDao.selectOneGoal(goalId);
	}

	@Override
	public boolean removeGoal(String goalId) {
		return goalDao.deleteGoal(goalId) > 0;
	}

	@Override
	public boolean modifyGoal(GoalVO goalVO) {
		return goalDao.updateGoal(goalVO) > 0;
	}

	
	
}
