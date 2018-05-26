<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport"content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
	<meta name="google-signin-client_id" content="818893027331-ss8am5r339qu1uespfmobqt23i0hgj41.apps.googleusercontent.com">
	<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/signIn.css"/>">
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/> "></script>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<script type="text/javascript" src="<c:url value="/static/js/naverLogin_implicit-1.0.3-min.js"/>" charset="utf-8"></script>
	<script type="text/javascript" src="<c:url value="/static/js/hello.js"/>" ></script>
	<script type="text/javascript" >
		// 사용할 앱의 JavaScript 키를 설정해 주세요.
		Kakao.init('5d7adc9cad9832f6fdbdfad38bb42591');
		function loginWithKakao() {
			// 로그인 창을 띄웁니다.
			Kakao.Auth.login({
				success : function(authObj) {
					console.log("authObj = " + authObj);
					var token = Kakao.Auth.getAccessToken();
					console.log("token2 = " + token);
					var db = {};
					
						if (token != null) {
							Kakao.API.request({
								url: '/v1/user/me',
								success: function(response) {
									console.log(response);
									$('#custom-login-btn').data(response)
									alert(response.properties.nickname+'님 환영합니다.');
									alert(response.kaccount_email);
									alert(response.id);
									alert(response.properties.profile_image);
									alert(response.properties.thumbnail_image); 
									
									var userName = response.properties.nickname;
									var email = response.kaccount_email;
									var userId = response.id;
									var loginProfileImage = response.properties.profile_image;
									var loginThumbnailImage = response.properties.thumbnail_image;
									var user = {
										userName : userName
										, email : email
										, userId : userId
										, loginProfileImage : loginProfileImage
										, loginThumbnailImage : loginThumbnailImage
									};
								
									$.ajax({
										type: 'POST',
										url: '<c:url value="/user/kakao/loginUser"/>',
										contentType: 'application/json; charset=UTF-8',
										data: JSON.stringify(user)
									}).done(function(response){
										if(response == "fail"){
											
											location.href="/meta-calendar/user/kakao/inputPassword";
										} 
									}); 
									
/* 									$.ajax({
										type: 'GET',
										url: '<c:url value="/user/kakao/inputPassword"/>',
										async: false,
									}).done(function(response){
										console.log("비밀번호 입력하는 곳으로 갔니?");
										alert('user.userPassword = ' + response.userPassword);
									}); */
									/* var param = $(this).serializeArray();
									var test = $.param(param); */

									
									$.post("<c:url value="/user/kakao/loginUser"/>", {
										"userName" : userName
										, "email" : email
										, "userId" : userId
										, "loginProfileImage" : loginProfileImage
										, "loginThumbnailImage" : loginThumbnailImage
									}, function(response){
										alert("user.userName = " + user.userName);
									});
									
									var userPassword = response.userPassword;
									$.post("<c:url value="/user/kakao/loginUser"/>", {
										"userName" : userName
										, "email" : email
										, "userId" : userId
										, "loginProfileImage" : loginProfileImage
										, "loginThumbnailImage" : loginThumbnailImage
									}, function(response) {
										alert("password success?")
									}, 'json')
									.done(function(response) {
										alert("user.userName = " + user.userName);
										alert("user.email = " + user.email);
										alert("user.userId = " + user.userId);
									});

								},
								fail: function(error) {
									console.log(JSON.stringify(error));
								}
							});
						}
						
						$.post("<c:url value="/user/kakao/session" />", {
							token : token
						}, function(response) {
							if (response == "ok") {
								location.href("<c:url value="/cal/list"/>");
								//location.reload();
							}
						})
				},
				fail : function(err) {
					console.log(err);
				}
			});
			
		};
		
		function login(){
			hello('google').login({scope: 'email'}).then(function(auth) {
				hello(auth.network).api('/me').then(function(r) {
					console.log(JSON.stringify(auth));
					accessToken = auth.authResponse.access_token;
					console.log(accessToken);
					getGoogleMe(); // 로그인 하자마자 바로 사용자 정보 호출한다.
				});
			});
		}
		
		function getGoogleMe(){
			hello('google').api('me').then(
					function(json) {
						console.log(JSON.stringify(json));
						name = json.name;
					email = json.email;
					domain = json.domain;
					thumbnail = json.thumbnail;
						console.log('name   : ' + name);
					console.log('email  : ' + email);
					console.log('domain : ' + domain);
					console.log('thumbnail : ' + thumbnail);
					loginComplete();// JSNI에 정의 되어있다.
					}, 
					function(e) {
					console.log('me error : ' + e.error.message);
				});
		}
		
		function logout(){
			hello('google').logout().then(
					function() {
						console.log('logout');
					},
					function(e) {
						console.log('Signed out error: ' + e.error.message);
				});
		}
		
		function loginWithNaver() {
			if (token != null) {
				if (resultCode == "00") {
					var userId = response.id;
					var email = response.email;
					var userName = response.name;
					var profile_image = response.profile_image;
					var enc_id = response.enc_id;
					var age = response.age;
					var gender = response.gender;
					var name = response.nickname;
					var birthday = response.birthday;
					var user = {
						userId : userId
						, email : email
						, userName : userName
						, profile_image : profile_image
						, enc_id : enc_id
						, age : age
						, gender : gender
						, name : name
						, birthday : birthday
					};
					
					$.post("<c:url value="/user/naver/userInfo"/>", {
						"userId" : userId
						, "email" : email
						, "userName" : userName
						, "profile_image" : profile_image
						, "enc_id" : enc_id
						, "age" : age
						, "gender" : gender
						, "name" : name
						, "birthday" : birthday
					}, function(response){
						alert("user.userName = " + user.userName);
					});
				} 
				else {
					console.log(JSON.stringify(message));
				}
			}
			
		}
		
		// 페이스북 로그인
		
	</script>

</head>
<body>
	<c:choose>
		<c:when test="${ sessionScope._USER_.loginType eq '' }">
			<a href="<c:url value="/user/update/${user.userId}" />">정보수정</a><br/>
		</c:when>
		<c:when test="${ sessionScope._USER_.loginType eq 'kko' }">
			<input type="button" value="로그아웃" id="logout"/>
			<script type="text/javascript">
				$().ready(function() {
					$("#logout").click(function () {
						Kakao.Auth.logout(function () {
							location.href="<c:url value="/user/kakao/logout"/>"
						});
					});
				});
			</script>
		</c:when>
		<c:when test="${ sessionScope._USER_.loginType eq 'ggl' }">
			<form id="googleLogOut">
				<input type="button" value="로그아웃"/>
			</form>
			<script type="text/javascript">
				$().ready(function() {
					$("#googleLogOut").find("input[type=button]").click(function () {
						$("#googleLogOut").attr({
							method:"post",
							action:"<c:url value="/user/google/signout"/> "
						});
						$("#googleLogOut").submit();
					});
				});
			</script>
		</c:when>
		<c:when test="${ sessionScope._USER_.loginType eq 'nvr' }">
			<form id="naverLogOut">
				<input type="button" value="로그아웃"/>
			</form>
			<script type="text/javascript">
				$().ready(function() {
					$("#naverLogOut").find("input[type=button]").click(function () {
						$("#naverLogOut").attr({
							method:"post",
							action:"<c:url value="/user/naver/signout"/> "
						});
						$("#naverLogOut").submit();
					});
				});
			</script>
		</c:when>
		<c:otherwise>
			<div id="background">
				<div id="total">
					<div id="meta-calendar">
						<h1>mata-calendar </h1>
						<a href="<c:url value="/user/signin" />">로그인</a>
						<a href="<c:url value="/user/signup" />">회원가입</a>
					</div>
					<div id="social">
						<a href="<c:url value="/user/google" /> ">
							<img style="width: 235px;" src="<c:url value="/static/img/btn_google_signin_light_normal_web@2x.png"/> ">
						</a>
						<div id="naver_id_login"></div>
						<a id="custom-login-btn" href="<c:url value="javascript:loginWithKakao()"/>"> 
							<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="235px;" />
						</a>
					</div>
				</div>
			</div>
			<button onclick="login()">Google LogIn</button>
			<button onclick="logout()">Google Logout</button>
			<form id="naver_id_login" href="<c:url value="javascript:loginWithNaver()"/>">
			<script type="text/javascript">
				var naver_id_login = new naver_id_login("xRetYdib8e35Loz5rIBq", "http://localhost:8080/meta-calendar/user/callback");
				var state = naver_id_login.getUniqState();
				naver_id_login.setButton("green", 3,50);
				naver_id_login.setDomain("http://localhost:8080/meta-calendar/user/naver/signIn");
				naver_id_login.setState(state);
				naver_id_login.setPopup();
				naver_id_login.init_naver_id_login();
			</script>
			</form>
		</c:otherwise>
	</c:choose>
	
</body>
</html>