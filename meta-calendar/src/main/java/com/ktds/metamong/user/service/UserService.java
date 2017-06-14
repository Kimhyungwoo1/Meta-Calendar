package com.ktds.metamong.user.service;

import com.ktds.metamong.user.vo.UserListVO;
import com.ktds.metamong.user.vo.UserSearchVO;
import com.ktds.metamong.user.vo.UserVO;

public interface UserService {

	public boolean addNewUser(UserVO userVO);
	
	public UserListVO getAllUser(UserSearchVO userSearchVO);
	
	public UserVO getOneUser(UserVO userVO);
	
	public boolean updateUser(UserVO userVO);
	
}
