<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/main.css"/>">

</head>
<body>

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
