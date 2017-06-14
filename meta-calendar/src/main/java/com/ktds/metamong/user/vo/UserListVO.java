package com.ktds.metamong.user.vo;

import java.util.List;

import com.ktds.metamong.common.web.Pager;
import com.ktds.metamong.common.web.PagerFactory;

public class UserListVO {

	private Pager pager;
	private List<UserVO> userList;

	public Pager getPager() {
		if ( pager == null ) {
			pager = PagerFactory.getPager(Pager.ORACLE);
		}
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public List<UserVO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}

}
