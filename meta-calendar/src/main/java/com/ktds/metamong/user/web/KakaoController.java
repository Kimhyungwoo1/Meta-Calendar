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

	public void loginData(UserVO userVO, HttpServletResponse response) {
		Map<String, Object> data = new HashMap<String, Object>();
		for (int i = 0; i < response.getBufferSize(); i++) {
			data.put("response", response);
		}
		System.out.println(data);
		userService.addNewUser(userVO);
		response.equals(userVO.getUserId());
		response.equals(userVO.getEmail());

	}

	@ResponseBody
	@RequestMapping(value = "/user/kakao/loginUser", method = RequestMethod.POST)
	public String registerPost(UserVO userVO) {

		//UserVO userVO = new UserVO();
		UserVO user = new UserVO();
		
		user.setUserId(userVO.getUserId());
		user.setEmail(userVO.getEmail());
		user.setUserName(userVO.getUserName());

		userService.addNewUser(user);

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
