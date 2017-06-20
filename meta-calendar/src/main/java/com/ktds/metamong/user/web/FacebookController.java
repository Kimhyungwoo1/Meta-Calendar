package com.ktds.metamong.user.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktds.metamong.user.vo.FacebookUserVO;
import com.ktds.metamong.user.vo.UserVO;

@Controller
public class FacebookController {
	
	@RequestMapping("/user/facebook/logout")
	public String actionLogoutFacebookPage(HttpSession session) {
		session.invalidate();
		return "redirect:/user/main";
	}
	
	@RequestMapping(value="/user/facebook/session", method=RequestMethod.POST)
	public String actionFacebookbuildSession(@RequestParam String token, HttpSession session) {
		
		System.out.println(token);
		
		FacebookUserVO userVO = (FacebookUserVO) session.getAttribute("_USER_");
		if ( userVO == null ) {
			userVO = new FacebookUserVO();
		}
		
		userVO.setLoginType(UserVO.FACEBOOK);
		userVO.setAccessToken(token);
		System.out.println("accessToken : " + token);
		session.setAttribute("_USER_", userVO);
		
		return "redirect:/main";
	}
	
}
