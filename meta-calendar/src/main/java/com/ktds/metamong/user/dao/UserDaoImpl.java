package com.ktds.metamong.user.dao;

import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.ktds.metamong.user.vo.UserSearchVO;
import com.ktds.metamong.user.vo.UserVO;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao{

	@Override
	public int insertNewUser(UserVO userVO) {
		return getSqlSession().insert(US + ".insertNewUser", userVO);
	}

	@Override
	public List<UserVO> selectAllUserList(UserSearchVO userSearchVO) {
		return getSqlSession().selectList(US + ".selectAllUserList", userSearchVO);
	}
	
	@Override
	public int selectAllUserListCount(UserSearchVO userSearchVO) {
		return getSqlSession().selectOne(US + ".selectAllUserListCount", userSearchVO);
	}
	
	@Override
	public UserVO selectOneUser(UserVO userVO) {
		return getSqlSession().selectOne(US + ".selectOneUser", userVO);
	}

	@Override
	public int modifyUser(UserVO userVO) {
		return getSqlSession().update(US + ".modifyUser", userVO);
	}

	@Override
	public UserVO getLoginOneUser(String userId) {
		return getSqlSession().selectOne(US + ".getLoginOneUser", userId);
	}

	
}
