package com.ktds.metamong.user.biz;

import java.util.ArrayList;
import java.util.List;

import com.ktds.metamong.common.web.Pager;
import com.ktds.metamong.user.dao.UserDao;
import com.ktds.metamong.user.vo.UserListVO;
import com.ktds.metamong.user.vo.UserSearchVO;
import com.ktds.metamong.user.vo.UserVO;

public class UserBizImpl implements UserBiz {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean addNewUser(UserVO userVO) {
		return userDao.insertNewUser(userVO) > 0;
	}

	@Override
	public UserListVO getAllUser(UserSearchVO userSearchVO) {
		
		UserListVO userListVO = new UserListVO();
		Pager pager = userListVO.getPager();
		pager.setPageNumber(userSearchVO.getPageNo());
		
		int userCount = userDao.selectAllUserListCount(userSearchVO);
		pager.setTotalArticleCount(userCount);
		
		userSearchVO.setEndArticleNumber(pager.getEndArticleNumber());
		userSearchVO.setStartArticleNumber(pager.getStartArticleNumber());
		
		if( userCount > 0 ) {
			List<UserVO> userList = userDao.selectAllUserList(userSearchVO);
			userListVO.setUserList(userList);
		}
		else {
			userListVO.setUserList(new ArrayList<UserVO>());
		}
		
		return userListVO;
	}

	@Override
	public UserVO getOneUser(UserVO userVO) {
		return userDao.selectOneUser(userVO);
	}

	@Override
	public boolean updateUser(UserVO userVO) {
		return userDao.modifyUser(userVO) > 0;
	}

	@Override
	public UserVO getLoginOneUser(String userId) {
		return userDao.getLoginOneUser(userId);
	}
	
}
