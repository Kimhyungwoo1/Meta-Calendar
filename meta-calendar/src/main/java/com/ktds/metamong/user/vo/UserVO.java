package com.ktds.metamong.user.vo;

public class UserVO {

	// 각각 소셜 로그인을 구분해준다.
	public static final String DEAULT = "";
	public static final String FACEBOOK = "fb";
	public static final String KAKAO = "kko";
	public static final String NAVER = "nvr";
	public static final String GOOGLE = "ggl";

	private String loginType;
	
	private String userId;
	private String userName;
	private String userPassword;
	private String email;
	private String userCreateDate;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserCreateDate() {
		return userCreateDate;
	}

	public void setUserCreateDate(String userCreateDate) {
		this.userCreateDate = userCreateDate;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	@Override
	public String toString() {
		return "User [userName=" + userName + ", userId=" + userId + ", email=" + email + "]";
	}

}
