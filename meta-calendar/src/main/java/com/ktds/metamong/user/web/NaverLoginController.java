package com.ktds.metamong.user.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.metamong.user.vo.NaverUserVO;
import com.ktds.metamong.user.vo.UserVO;

@Controller
public class NaverLoginController {

	@RequestMapping("/user/callback")
	public ModelAndView callback() throws IOException {
		return new ModelAndView("user/callback");
	}

	@RequestMapping("/user/naver/savetoken")
	public void saveToken(@RequestParam String accessToken, HttpSession session) {
		
		NaverUserVO naverUserVO = (NaverUserVO) session.getAttribute("_USER_");
		if ( naverUserVO == null ) {
			naverUserVO = new NaverUserVO();
		}
		
		naverUserVO.setLoginType(UserVO.NAVER);
		naverUserVO.setAccessToken(accessToken);
		session.setAttribute("_USER_", naverUserVO);
	}

	@RequestMapping(value = "/user/naver/signout", method = RequestMethod.POST)
	public String naverLogout(HttpSession session) {
		NaverUserVO naverUserVO = (NaverUserVO) session.getAttribute("_USER_");

		String logoutUrl = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=xRetYdib8e35Loz5rIBq&client_secret=zJXYOfDI2C&access_token="
				+ naverUserVO.getAccessToken() + "&service_provider=NAVER";

		try {
			URL url = new URL(logoutUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		session.invalidate();
		return "redirect:/main";
	}

}
