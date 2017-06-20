package com.ktds.metamong.user.service;

import com.ktds.metamong.user.vo.SocialUserVO;
import com.ktds.metamong.user.vo.UserListVO;
import com.ktds.metamong.user.vo.UserSearchVO;
import com.ktds.metamong.user.vo.UserVO;

public interface UserService {

	public boolean addNewUser(UserVO userVO);
	
	public UserListVO getAllUser(UserSearchVO userSearchVO);
	
	// 패스워드까지 있을 경우 사용
	// 회원가입 이후 로그인할 때 사용
	public UserVO getOneUser(UserVO userVO);
	
	// 로그인 시 패스워드 없을 경우 사용
	// sns를 이용해서 최초 로그인 시도할 때 사용
	public UserVO getLoginOneUser(String userId);
	
	public boolean updateUser(UserVO userVO);
	
	public SocialUserVO selectSocialOneUser(SocialUserVO socialUserVO);
	
	public boolean addSocialNewUser(SocialUserVO socialUserVO);
}
