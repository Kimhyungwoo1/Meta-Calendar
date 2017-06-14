<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->

<!-- 부트스트랩 -->
<link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet">


<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js" /> "></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/> "></script>
<script type="text/javascript">
	$().ready(function() {
	
	});
</script>
</head>
<body>
	<nav class="navbar navbar-default">
		<div class="container">
			<a class="navbar-brand" href="#">MetaMong</a>
			<ul class="nav navbar-nav">
				<li> <a href="<c:url value="/memo/write"/> ">메모 등록</a></li>
				<li> <a href="<c:url value="/goal/write"/> ">목표 등록</a></li>
				<li class="active"> <a href="<c:url value="/cal/write"/> ">일정 등록</a></li>
			</ul>
		</div>
	</nav>
	
	<div class="container">
			
			<table class="table table-striped">
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>일정아이디</th>
						<th>일정제목</th>
						<th>일정부제</th>
						<th>시작날짜</th>
						<th>끝나는날짜</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${calendarListVO.calendarList}" var="cal">
					<tr>
						<td>${cal.calendarId}</td>
						<td><a href="<c:url value="/cal/detail/${cal.calendarId}"/>">${cal.calendarTitle}</a></td>
						<td>${cal.calendarSubTitle}</td>
						<td>${cal.startDate}</td>
						<td>${cal.endDate}</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<form id="searchForm">
			<nav>
				${pager.getPagingList("pageNo", "@", "&laquo;", "&raquo;", "searchForm")}
			</nav>
			</form>
			
		</div>

</body>
</html>