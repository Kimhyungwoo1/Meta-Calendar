package com.ktds.metamong.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.metamong.user.service.UserService;
import com.ktds.metamong.user.vo.UserVO;

@Controller
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/user/signup", method=RequestMethod.GET)
	public String viewsignUpPage() {
		return "user/signUp";
	}
	
	@RequestMapping(value="/user/signup", method=RequestMethod.POST)
	public String actionAddNewUserPage(UserVO userVO) {
		
		boolean signUp = userService.addNewUser(userVO);
		
		return "redirect:/user/signin";
	}
	
	@RequestMapping(value="/user/signin", method=RequestMethod.GET)
	public String viewSignInPage() {
		return "user/signIn";
	}
	
	@RequestMapping(value="/user/signin", method=RequestMethod.POST)
	public String actionSignInPage(UserVO userVO, HttpServletRequest request) {
		UserVO signIn = userService.getOneUser(userVO);
		userVO.setLoginType(UserVO.DEAULT);
		
		if ( signIn != null ) {
			HttpSession session = request.getSession();
			session.setAttribute("_USER_", signIn);
			return "redirect:/main";
		}
		else {
			return "redirect:/user/signin";
		}
		
	}
	
	@RequestMapping(value="/user/update/{userId}", method=RequestMethod.GET)
	public ModelAndView viewUpdatePage(UserVO userVO, @PathVariable String userId, HttpServletRequest request) {
		ModelAndView view = new ModelAndView();
		
		HttpSession session = request.getSession();
		UserVO user = (UserVO) session.getAttribute("_USER_");
		
		view.setViewName("user/update");
		view.addObject("user", user);
		
		return view;
	}
	
	@RequestMapping(value="/user/update/{userId}", method=RequestMethod.POST)
	public String ActionUpdateUser(UserVO userVO, @PathVariable String userId) {
		
		userVO.setUserId(userId);
		boolean updateUser = userService.updateUser(userVO);
		
		return "redirect:/main";
	}
	
	@RequestMapping("/main")
	public ModelAndView main(HttpServletRequest request, HttpSession session) {
		ModelAndView view = new ModelAndView();
		
		view.setViewName("user/main");
		
		 
		return view;
	}
	
}
