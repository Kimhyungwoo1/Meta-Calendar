package com.ktds.metamong.user.dao;

import java.util.List;

import com.ktds.metamong.user.vo.SocialUserVO;
import com.ktds.metamong.user.vo.UserSearchVO;
import com.ktds.metamong.user.vo.UserVO;

public interface UserDao {

	public static final String US = "UserDao";
	
	public int insertNewUser(UserVO userVO);
	
	public List<UserVO> selectAllUserList(UserSearchVO userSearchVO);
	
	public int selectAllUserListCount(UserSearchVO userSearchVO);
	
	public UserVO selectOneUser(UserVO userVO);
	
	public UserVO getLoginOneUser(String userId);
	
	public int modifyUser(UserVO userVO);
	
	public SocialUserVO selectSocialOneUser(SocialUserVO socialUserVO);
	
	public int insertSocialNewUser(SocialUserVO socialUserVO);
	
}
