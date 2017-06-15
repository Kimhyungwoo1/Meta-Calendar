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
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/> "></script>
	<script type="text/javascript" src="<c:url value="/static/js/naverLogin_implicit-1.0.3-min.js"/>" charset="utf-8"></script>
	<script type="text/javascript">
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
									/* db = response;
									console.log(db);
									return db; */
								},
								fail: function(error) {
									console.log(JSON.stringify(error));
								}
							});
						}
						
						/* $.post("<c:url value="/user/kakao/session" />", {
							token : token
						}, function(response) {
							if (response == "ok") {
								location.reload();
							}
						}) */
				},
				fail : function(err) {
					console.log(err);
				}
			});
			
		};
		
		
		// 페이스북 로그인
		
	</script>
	<!-- 페이스북 -->
	<!-- 
	<script type="text/javascript">
	function statusChangeCallback(response) {
		console.log('statusChangeCallback');
		console.log(response);
		if (response.status === 'connected') {
			testAPI();
		}
		else if (response.status === 'not_authorized') {
			document.getElementById('status').innerHTML = 'Please log' + 'into this app.';
		}
		else {
			document.getElementById('status').innerHTML = 'Please log' + 'into Facebook.';
		}
	}
	
	/* function checkLoginStatue() {
		FB.getLoginStatus(function(response) {
		    statusChangeCallback(response);
		});
	} */
	
	window.fbAsyncInit = function() {
	    FB.init({
	      appId      : '646587625535673',
	      cookie     : true,
	      xfbml      : true,
	      version    : 'v2.8'
	    });
	    
	    FB.getLoginStatus(function(response) {
	    	statusChangeCallback(response);
	    	console.log("ss")
	    	loadUserCredentials(response);
	    });
	    
	    FB.AppEvents.logPageView();
	};

	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id)) return;
		js = d.createElement(s); js.id = id;
		js.src = "//connect.facebook.net/ko_KR/sdk.js#xfbml=1&version=v2.9&appId=646587625535673";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
	
    function testAPI() {
	    console.log('Welcome! Fetching your information....');
	    FB.api('/me', function(response) {
		    console.log('Successful login for : ' + response.name);
		    document.getElementById('status').innerHTML = 
			  'Thanks for logging in, ' + response.name + '!';
	    	//console.log(JSON.stringify(response));
		   
	    });
	  
	    FB.getAuthResponse('/me', "");
	    
	    //var token = response.authResponse.accessToken;
	    
	    var token =""; 
	    console.log("aa");
		
    }
    function loadUserCredentials(response) { 
    	console.log("bb");
    	//token = window.localStorage.getItem(LOCAL_TOKEN_KEY); 
    	//token = Facebook.Auth.getAccessToken();
    	token = response.authResponse.accessToken;
    	console.log(token);
    	/* if (token) { 
    		useCredentials(token); 
    		System.out.println("Token : " + token);
    	}  */
    	$.post("<c:url value="/user/facebook/session" />", {
			token : token
    	}, function() {});
    }
	</script>
	-->
</head>
<body>
	<c:choose>
		<c:when test="${ sessionScope._USER_.loginType eq '' }">
			<a href="<c:url value="/user/update/${user.userId}" />">정보수정</a><br/>
		</c:when>
		<%-- <c:when test="${ sessionScope._USER_.loginType eq 'fb' }">
			<form id="facebookLogout">
				<input type="button" value="로그아웃"/>
			</form>
			<script type="text/javascript">
				$().ready(function() {
					$("#facebookLogout").find("input[type=button]").click(function () {
						$("#facebookLogout").attr({
							method:"post",
							action:"<c:url value="/user/facebook/logout"/> "
						});
						$("#facebookLogout").submit();
					});
				});
			</script>
		</c:when> --%>
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
						<a id="custom-login-btn" href="javascript:loginWithKakao()"> 
							<img src="//mud-kage.kakao.com/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" width="235px;" />
						</a>
						<!-- 
						<div href="<c:url value="/user/facebook/session"/>">
							<fb:login-button style="width: 187px; margin-right: 45px;" class="fb-login-button" scope="public_profile,email" data-size="large" data-show-faces="false" data-auto-logout-link="false" data-use-continue-as="false" onclick="checkLoginState();">
							</fb:login-button>
						</div>	
						<div id="status">
						</div>
						 -->
					</div>
				</div>
			</div>
			<script type="text/javascript">
				var naver_id_login = new naver_id_login("xRetYdib8e35Loz5rIBq", "http://localhost:8080/meta-calendar/user/callback");
				var state = naver_id_login.getUniqState();
				naver_id_login.setButton("green", 3,50);
				naver_id_login.setDomain("http://localhost:8080/meta-calendar/user/naver/signIn");
				naver_id_login.setState(state);
				naver_id_login.setPopup();
				naver_id_login.init_naver_id_login();
			</script>
		</c:otherwise>
	</c:choose>
	
</body>
</html>