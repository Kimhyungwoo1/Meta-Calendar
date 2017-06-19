package com.ktds.metamong.user.service;

import com.ktds.metamong.user.biz.UserBiz;
import com.ktds.metamong.user.vo.UserListVO;
import com.ktds.metamong.user.vo.UserSearchVO;
import com.ktds.metamong.user.vo.UserVO;

public class UserServiceImpl implements UserService {

	private UserBiz userBiz;
	
	public void setUserBiz(UserBiz userBiz) {
		this.userBiz = userBiz;
	}

	@Override
	public boolean addNewUser(UserVO userVO) {
		return userBiz.addNewUser(userVO);
	}

	@Override
	public UserListVO getAllUser(UserSearchVO userSearchVO) {
		return userBiz.getAllUser(userSearchVO);
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userBiz.getOneUser(userVO);
	}

	@Override
	public boolean updateUser(UserVO userVO) {
		return userBiz.updateUser(userVO);
	}

	@Override
	public UserVO getLoginOneUser(String userId) {
		return userBiz.getLoginOneUser(userId);
	}

}
