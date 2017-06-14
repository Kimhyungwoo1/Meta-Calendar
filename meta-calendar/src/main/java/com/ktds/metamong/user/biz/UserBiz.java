package com.ktds.metamong.user.biz;

import com.ktds.metamong.user.vo.UserListVO;
import com.ktds.metamong.user.vo.UserSearchVO;
import com.ktds.metamong.user.vo.UserVO;

public interface UserBiz {

	public boolean addNewUser(UserVO userVO);
	
	public UserListVO getAllUser(UserSearchVO userSearchVO);
	
	public UserVO getOneUser(UserVO userVO);
	
	public boolean updateUser(UserVO userVO);

}
