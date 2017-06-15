<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<style>
ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #999;
}

li {
    float: left;
}

li a, .dropbtn {
    display: inline-block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover, .dropdown:hover .dropbtn {
    background-color: red;
}

li.dropdown {
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
    display: block;
}
</style>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/mainPage.css"/>">

</head>
<body>
<ul>
  <li class="dropdown">
    <a href="javascript:void(0)" class="dropbtn">Home</a>
    <div class="dropdown-content">
      <a href="#">Link 1</a>
      <a href="#">Link 2</a>
      <a href="#">Link 3</a>
    </div>
  <li><a href="#home">Join</a></li>
  <li><a href="#news">Login</a></li>
    
<li class="print-hide view-icon view-padding">
			<img src="http://amina.co.kr/img/sns/facebook.png" alt="Facebook" title="">
			<img src="http://amina.co.kr/img/sns/googleplus.png" alt="GooglePlus" title=""> 
			<img src="https://developers.kakao.com/assets/img/features/service/p_talk.png" alt="KakaoStory" title=""> 
			<img src="http://amina.co.kr/img/sns/naver.png"	alt="Naver">
	</li>
  </li>
</ul>


<p>나만의 캘린더와 공유 캘린더 둘 중 하나를 선택해 시작하십시요!</p>


	<!-- <div id="social">
	 <a target="_blank"/>
		<a href="<c:url value="/cal/list" /> ">
	<img style="width: 235px;" src="<c:url value="/static/img/1.jpg"/> "></a></div>-->







<div class="gallery">
  <a target="_blank" href="1.jpg">
  <img src="<c:url value="/static/img/3.jpg"/>" width="550" height="300" > </a>
  <div class="desc">나의 캘린더</div>
</div>

<div class="gallery">
  <a target="_blank" href="2.jpg">
  <img src="<c:url value="/static/img/4.jpg"/>" width="550" height="300" > </a>
  <div class="desc">공유 캘린더 </div>
</div>

</body>
</html>
