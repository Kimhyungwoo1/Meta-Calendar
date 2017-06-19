<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>




<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/css-social-buttons/1.3.0/css/zocial.min.css">

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style>
body,h1 {font-family: "Raleway", Arial, sans-serif}
h1 {letter-spacing: 6px}
.w3-row-padding img {margin-bottom: 12px}

.modal-header, h4, .close {
      background-color: #5cb85c;
      color:white !important;
      text-align: center;
      font-size: 30px;
  }
  .modal-footer {
      background-color: #f9f9f9;
  }
</style>
<script type="text/javascript" src="/meta-calendar/static/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
	$().ready(function(){
		$("#signInForm").find("input[type=submit]").click(function(){
			$("#signInForm").attr({
				"method" : "post",
				"action" : "/user/signin"
			});
			$("#signInForm").submit();
		});
	});
</script>

<link rel="stylesheet" type="text/css"
	href="<c:url value="/static/css/spinkit.css"/>">
<style>
#p {     position: fixed;
    text-align: center;
    padding: 20px 20px 30px 20px;
    top: 500px;
    left: 327px;
    bottom: 500px;}

#s {
    position: fixed;
    text-align: center;
    padding: 20px 20px 30px 20px;
    top: 500px;
    right: 325px;
    bottom: 500px;
}

</style>
</head>
<body>



<i class="fa fa-facebook-official w3-hover-opacity"></i>
  <i class="fa fa-google w3-hover-opacity"></i>
  <i class="fa fa-snapchat w3-hover-opacity"></i>
  <i class="fa fa-pinterest-p w3-hover-opacity"></i>
  <i class="fa fa-twitter w3-hover-opacity"></i>
  <i class="fa fa-linkedin w3-hover-opacity"></i>
  
  
<!-- !PAGE CONTENT! -->
<div class="w3-content" style="max-width:1500px height:25px">

<!-- Header -->
<header class="w3-panel w3-center w3-opacity" style="padding:25px 16px">
  
  <div class="w3-padding-32">
    <div class="w3-bar w3-border">
      <a href="" class="w3-bar-item w3-button w3-light-grey">Home</a>
      <a href="" class="w3-bar-item w3-button">Personal</a>
      <a href="" class="w3-bar-item w3-button w3-hide-small">Share</a>
      <button type="button" class="w3-bar-item w3-button" data-toggle="modal" data-target="#loginmodal">LogIn</button>
     </div>
  </div>
				<a href=""><img src="http://amina.co.kr/img/sns/facebook.png" alt="Facebook"
					style="width: 20px;"> </a>
				<a href="" ><img src="http://amina.co.kr/img/sns/googleplus.png" alt="GooglePlus"
					style="width: 20px;"> </a>
				<a href="" ><img src="https://developers.kakao.com/assets/img/features/service/p_talk.png"
					alt="KakaoStory" style="width: 20px;"> </a>
				<a href="" ><img src="http://amina.co.kr/img/sns/naver.png" alt="Naver"
					style="width: 20px;"></a>
  
  
  
  
  
  
  
  <h1 class="w3-xlarge">META-CALENDAR</h1>
</header>

 <!--modal-->
<div class="modal fade" id="loginmodal" role="dialog" tabindex="-1" role="dialog" 
   aria-labelledby="myModalLabel" aria-hidden="true">
    	  <div class="modal-dialog">
				<div class="loginmodal-container">
				  <form id="signInForm">
					<input type="text" name="userId" placeholder="ID를 입력하세요.">
					<input type="password" name="userPassword" placeholder="비밀번호를 입력하세요"/>
					<input type="submit" name="login" class="loginmodal-submit" value="LogIn">
				  </form>
				  <div class="login-help">
					<a href="/user/signup">SignUp</a> 
				  </div>
				</div>
			</div>
		  </div>
















<ul>
	<div class="sk-cube-grid">
	  <div class="sk-cube sk-cube1"></div>
	  <div class="sk-cube sk-cube2"></div>
	  <div class="sk-cube sk-cube3"></div>
	  <div class="sk-cube sk-cube4"></div>
	  <div class="sk-cube sk-cube5"></div>
	  <div class="sk-cube sk-cube6"></div>
	  <div class="sk-cube sk-cube7"></div>
	  <div class="sk-cube sk-cube8"></div>
	  <div class="sk-cube sk-cube9"></div>
	</div>
	  <div id="p">나의 캘린더</div>


	<div class="sk-folding-cube">
	  <div class="sk-cube1 sk-cube"></div>
	  <div class="sk-cube2 sk-cube"></div>
	  <div class="sk-cube4 sk-cube"></div>
	  <div class="sk-cube3 sk-cube"></div>
	</div>
  <div id="s">공유 캘린더</div>
</ul>
</body>
</html>
