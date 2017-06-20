package com.ktds.metamong.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.jdbc.JDBCAppender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.ktds.metamong.user.service.UserService;
import com.ktds.metamong.user.vo.KakaoUserVO;
import com.ktds.metamong.user.vo.SocialUserVO;
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

	//SocialUserVO 사용 (비밀번호 없는 테이블)
	@RequestMapping(value = "/user/kakao/loginUser", method = RequestMethod.POST)
	public String registerPost(SocialUserVO socialUserVO, HttpServletRequest request) {
		

		//유저 정보 확인 
		UserVO userInfo = userService.getLoginOneUser(socialUserVO.getUserId());
		System.out.println(userInfo);
		
//		최초 로그인
		 if(userInfo == null) {
			 boolean insertSocialUser = userService.addSocialNewUser(socialUserVO);
			 socialUserVO.setLoginType(UserVO.KAKAO);
			 System.out.println("insertSocialUser = " + insertSocialUser);
			 
			 if ( insertSocialUser ) {
				HttpSession session = request.getSession();
				session.setAttribute("_USER_", insertSocialUser);
				return "redirect:/cal/list";
			 }
			 else {
				return "redirect:/main";
			 }
			 /*try {
					Map<String, Object> map = new HashMap<String, Object>();
				    map.put("status", "success");
				    map.put("user", user);
				    Gson gson = new Gson();
				     
				    String json = gson.toJson(map);
					
				    PrintWriter write = response.getWriter();
					write.write(json.toString());
					write.flush();
					write.close();
				} catch (IOException e) {
				}*/
		 } else {
			System.out.println("aa");
			socialUserVO.setUserId(socialUserVO.getUserId());
			socialUserVO.setUserName(socialUserVO.getUserName());
			socialUserVO.setEmail(socialUserVO.getEmail());
			
			System.out.println("id = " + socialUserVO.getUserId());
			System.out.println("userName = " + socialUserVO.getUserName());
			System.out.println("email = " + socialUserVO.getEmail());
		
			return "redirect:/cal/list";
		 }
	}
	
	
	@RequestMapping(value = "/user/kakao/passwordInfo", method = RequestMethod.POST)
	public String passwordInfo(SocialUserVO socialUser) {
		System.out.println("bb");
		UserVO userVO = new UserVO();
		userVO.setUserPassword(userVO.getUserPassword());
		System.out.println("passwordInfo : SocialUser = " + socialUser);
		System.out.println("userPassword = " + userVO.getUserPassword());
		return "";
//		return "redirect:/user/kakao/loginUser";
	}
	
	@RequestMapping(value="/user/kakao/inputPassword", method=RequestMethod.GET)
	public String viewPasswordInfoPage() {
		return "user/passwordInfo";
	}
	
	@ResponseBody
	@RequestMapping(value="/user/kakao/inputPassword", method=RequestMethod.POST)
	public String doInputPassword(@RequestBody SocialUserVO socialUser, @RequestParam String password, String token, HttpSession session, HttpServletResponse response){
		System.out.println("aa");
		UserVO userVO = new UserVO();
		userVO.setUserId(socialUser.getUserId());
		userVO.setUserName(socialUser.getUserName());
		userVO.setEmail(socialUser.getEmail());
		userVO.setUserPassword(password);
		
		
		System.out.println("id = " + socialUser.getUserId());
		System.out.println("userName = " + socialUser.getUserName());
		System.out.println("email = " + socialUser.getEmail());
		System.out.println("password = " + userVO.getUserPassword());
		
		userService.addNewUser(userVO);
		
		// 세션처리
		KakaoUserVO kakaoUser = (KakaoUserVO) session.getAttribute("_USER_");
		if (kakaoUser == null) {
			kakaoUser = new KakaoUserVO();
		}

		kakaoUser.setLoginType(UserVO.KAKAO);
		kakaoUser.setAccessToken(token);
		session.setAttribute("_USER_", userVO);
		
		try {
			PrintWriter write = response.getWriter();
			write.write("fail");
			write.flush();
			write.close();
		} catch (IOException e) {
		}
		
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
