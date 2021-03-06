<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/css/mainPage.css"/>">

</head>
<body>
	<div id="menu">
		<ul>
		<li id="a">
			<li class="tab_admin tab_tag">
				<a href="<c:url value="/main"/> "> 
					<b><span>Home</span></b><em></em>
				</a>
			</li>
			<li class="tab_tag">
				<a href="<c:url value="/cal/list"/> "> 
					<b><span>Personal</span></b><em></em>
				</a>
			</li>
			<li class="tab_guestbook">
				<a href="<c:url value="/cal/list"/> "> 
					<b><span>Share</span></b><em></em>
				</a>
			</li>
			<li class="Login">
				<a href="<c:url value="/user/signin"/> "> 
					<b><span>Login</span></b><em></em>
				</a>
			</li>
			<li class="Join">
				<a href="<c:url value="/user/signup"/> ">
					<b><span>Join</span></b><em></em>
				</a>
			</li>
			
			<li class="icon">
				<a href="">
					<img src="http://amina.co.kr/img/sns/facebook.png" alt="Facebook" style="width: 35px;">
				</a> 
				<a href="<c:url value="/user/google"/>">
					<img src="http://amina.co.kr/img/sns/googleplus.png" alt="GooglePlus" style="width: 35px;">
				</a> 
				<img src="https://developers.kakao.com/assets/img/features/service/p_talk.png" alt="KakaoStory" 
					style="width: 35px;"> 
				<img src="http://amina.co.kr/img/sns/naver.png" alt="Naver" style="width: 35px;">
			</li>
		</ul>
	</div>

	<div class="gallery">
		<a href="<c:url value="/cal/list"/> ">
			<div target="_blank" >
				<img src="<c:url value="/static/img/3.jpg"/>" width="550" height="300">
			</div>
			<div class="desc">나의 캘린더</div>
		</a>
	</div>

	<div class="gallery">
		<a target="_blank" href="2.jpg"> 
			<img src="<c:url value="/static/img/4.jpg"/>" width="550" height="300">
		</a>
		<div class="desc">공유 캘린더</div>
	</div>

</body>
</html>
