package com.ktds.metamong.goal.vo;

import java.util.List;

import com.ktds.metamong.common.web.Pager;
import com.ktds.metamong.common.web.PagerFactory;

public class GoalListVO {

	private Pager pager;
	private List<GoalVO> goalList;

	public Pager getPager() {
		if ( pager == null ) {
			pager = PagerFactory.getPager(Pager.ORACLE);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<GoalVO> getGoalList() {
		return goalList;
	}

	public void setGoalList(List<GoalVO> goalList) {
		this.goalList = goalList;
	}

}
