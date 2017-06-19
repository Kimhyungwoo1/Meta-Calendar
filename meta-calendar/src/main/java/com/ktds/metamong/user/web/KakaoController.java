package com.ktds.metamong.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.jdbc.JDBCAppender;
import org.apache.log4j.spi.LoggingEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ktds.metamong.user.service.UserService;
import com.ktds.metamong.user.vo.KakaoUserVO;
import com.ktds.metamong.user.vo.UserVO;

@Controller
public class KakaoController extends JDBCAppender {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/user/kakao/logout")
	public String actionLogoutKakaoPage(HttpSession session) {

		session.invalidate();

		return "redirect:/main";
	}

	/*public void loginData(UserVO userVO, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<String, Object>();
		for (int i = 0; i < response.getBufferSize(); i++) {
			data.put("response", response);
		}
		System.out.println(data);
		userService.addNewUser(userVO);
		response.equals(userVO.getUserId());
		response.equals(userVO.getEmail());

	}*/
	
	@RequestMapping(value = "/user/kakao/passwordInfo", method = RequestMethod.POST)
	public String passwordInfo(UserVO user) {
		System.out.println("bb");
		user = new UserVO();
		user.setUserPassword(user.getUserPassword());
		System.out.println("passwordInfo : user = " + user);
		System.out.println("userPassword = " + user.getUserPassword());
//		return "";
		return "redirect:/user/kakao/loginUser";
	}
	
	@ResponseBody
	@RequestMapping(value = "/user/kakao/loginUser", method = RequestMethod.POST)
	public String registerPost(@RequestBody UserVO user) {
		UserVO userVO = new UserVO();

		//유저 정보 확인 
		UserVO userInfo = userService.getLoginOneUser(user.getUserId());
		System.out.println(userInfo);
		
//		최초 로그인
		 if(userInfo == null) {
			 System.out.println("이동");
		 	return "recirect:/user/kakao/inputPassword";
		 } else {
			user.setUserPassword(userInfo.getUserPassword());
		 
			System.out.println("aa");
			userVO.setUserId(user.getUserId());
			userVO.setUserName(user.getUserName());
			userVO.setEmail(user.getEmail());
			userVO.setUserPassword(user.getUserPassword());
			
			System.out.println("id = " + user.getUserId());
			System.out.println("userName = " + user.getUserName());
			System.out.println("email = " + user.getEmail());
			System.out.println("password = " + userVO.getUserPassword());
		
			return "redirect:/cal/list";
		 }
//		userService.addNewUser(userVO);
		
//		String beforeUser = user;
//		String[] splitData = new String[2];
//		splitData = user.split(",");
//		
//		System.out.println(splitData[0]);
//		System.out.println(splitData[1]);
//		System.out.println(splitData[2]);
//		
//		
//		String plusUser = splitData[0] + splitData[1] + splitData[2];
//		System.out.println(plusUser);
//		String[] splitData2 = new String[2];
//		splitData2 = plusUser.split("{");
//		System.out.println(splitData2[0]);
//		System.out.println(splitData2[1]);
//		System.out.println(splitData2[2]);
//		
//		
//		System.out.println("userVO = " + userVO);
		/*String userName = splitData[0];
		String userId = splitData[1];
		String email = splitData[2];
		
		userVO.setUserId(userId);
		userVO.setEmail(email);
		userVO.setUserName(userName);*/

		
		
//		user.setUserName(userName);
//		user.setUserId(userId);
//		user.setEmail(email);
		
//		userService.addNewUser(user);
//		System.out.println("userVO = " + user);

		
	}
	

	@RequestMapping(value="/user/kakao/inputPassword", method=RequestMethod.GET)
	public String viewPasswordInfoPage() {
		return "user/passwordInfo";
	}
	
	@RequestMapping(value="/user/kakao/inputPassword", method=RequestMethod.POST)
	public String doInputPassword(@RequestBody UserVO user, @RequestParam String password, String token, HttpSession session){
		System.out.println("aa");
		UserVO userVO = new UserVO();
		userVO.setUserId(user.getUserId());
		userVO.setUserName(user.getUserName());
		userVO.setEmail(user.getEmail());
		userVO.setUserPassword(password);
		
		
		System.out.println("id = " + user.getUserId());
		System.out.println("userName = " + user.getUserName());
		System.out.println("email = " + user.getEmail());
		System.out.println("password = " + userVO.getUserPassword());
		
		userService.addNewUser(user);
		
		// 세션처리
		KakaoUserVO kakaoUser = (KakaoUserVO) session.getAttribute("_USER_");
		if (kakaoUser == null) {
			kakaoUser = new KakaoUserVO();
		}

		kakaoUser.setLoginType(UserVO.KAKAO);
		kakaoUser.setAccessToken(token);
		session.setAttribute("_USER_", userVO);
		
		return "redirect:/cal/list";
	}
	

	@RequestMapping(value = "/user/kakao/session", method = RequestMethod.POST)
	public void actionKakaobuildSession(@RequestParam String token, HttpSession session, HttpServletResponse response) {

		KakaoUserVO userVO = (KakaoUserVO) session.getAttribute("_USER_");
		if (userVO == null) {
			userVO = new KakaoUserVO();
		}

		userVO.setLoginType(UserVO.KAKAO);
		userVO.setAccessToken(token);
		session.setAttribute("_USER_", userVO);

		try {
			PrintWriter writer = response.getWriter();
			writer.write("ok");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
