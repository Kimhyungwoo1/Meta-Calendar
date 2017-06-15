package com.ktds.metamong.user.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktds.metamong.user.service.UserService;
import com.ktds.metamong.user.vo.KakaoUserVO;
import com.ktds.metamong.user.vo.UserVO;

@Controller
public class KakaoController {
	
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/user/kakao/logout")
	public String actionLogoutKakaoPage(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/main";
	}
	
//	@RequestMapping(value="loginData.do", method={RequestMethod.GET, RequestMethod.POST})
//	public void loginData(HttpServletRequest req, @ModelAttribute UserVO userVO, HttpServletResponse resp) {
//		userVO = userService.addNewUser(userVO, req);
//		try {
//			Map<String, Object> resultMap = new HashMap<String, Object>();
//			resultMap.put("userVO", userVO);
//			JSONObject json = new JSONObject();
//			json.put("userVO", resultMap);
//			resp.setCharacterEncoding("UTF-8");
//			PrintWriter writer = resp.getWriter();
//			json.writeJSONString(resultMap, writer);
//			writer.flush();
//			writer.close();
//		} catch (IllegalAccessException | IllegalArgumentException 
//					| NoSuchMethodException | SecurityException e) {
//			e.printStackTrace();
//		}
//	}
	
	@RequestMapping(value="/user/kakao/session", method=RequestMethod.POST)
	public void actionKakaobuildSession(@RequestParam String token, HttpSession session, HttpServletResponse response) {
		
		KakaoUserVO userVO = (KakaoUserVO) session.getAttribute("_USER_");
		if ( userVO == null ) {
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
