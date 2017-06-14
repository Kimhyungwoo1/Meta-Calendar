<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<!-- 부트스트랩 -->
<link href="<c:url value="/static/css/bootstrap.min.css" />" rel="stylesheet">
<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<head>
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/calendar_modify.css"/>">
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js" /> "></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/> "></script>
<script type="text/javascript">

	
	
	var day = new Date().getDate();
	var month = new Date().getMonth()+1;
	var year = new Date().getFullYear();
	var today = year + '/0' + month + '/0' + day; //오늘날짜 String형
	
	$().ready(function() {
		
		$.post("<c:url value="/cal/list" />", {}, function(data) {
			var check = false
			var listLength = data.length; 
			
			console.log(data);
			
				$(".rows").each(function(index, element) {
					
					var divDate = $(element).data('date');
					
					for ( i = 0; i < listLength; i++) {
						var event = '<div class="event-index" >' + data[i].calendarTitle + '</div>'
						if ( divDate + " 00:00:00.0" == data[i].startDate) {
							check = true;
							console.log(data[i]);
							console.log(element);
							if (check == true) {
								
							}
							
							$(element).append(event);
						} 
						else if( divDate + " 00:00:00.0" == data[i].endDate ) {
							$(element).append(event);
							console.log(element);
							check = false;
						}
						
					}
					
				});
			
		});
		
		$("#modifyButton").click(function() {
			$.post("<c:url value="/cal/update"/>", $("#modifyForm").serialize(), function(response) {
				if ( response == 'ok') {
					location.href="<c:url value="/cal/list"/>"
				}
				else{
					alert("실패");
				}
			});
		});
		
		$("#returnButton").click(function() {
			location.href="<c:url value="/cal/list" />"
		});
		
	});

	function getDayText(year, month){
		//
		var dayTd = [];
		// 날짜나오기전 앞에 빈칸
		var d1 = (year+(year-year%4)/4-(year-year%100)/100+(year-year%400)/400 
			+month*2+(month*5-month*5%9)/9-(month<3?year%4||year%100==0&&year%400?2:3:4))%7; 
		for (i = 0; i < 42; i++) { 
			if (i%7==0){
				dayTd += '</tr>\n<tr>'; 
			} 
			if (i < d1 || i >= d1+(month*9-month*9%8)/8%2+(month==2?year%4||year%100==0&&year%400?28:29:30)) {
				dayTd += '<td> </td>'; 
			}else{
				dayTd += '<td' + (i%7 ? '' : ' style="color:red; " ') + ' >'
						+ '<div data-date="' + year + '-' + month + '-' + ((i+1-d1) < 10 ? ("0" + (i+1-d1)) : (i+1-d1)) + '" class="rows">'
						+ '<div class="day-number" style=" padding: 6px; border: 6px; height: 50%; width: 120px">' + (i+1-d1) + '</div>'
						+ '<div class="day-bar"></div>'
						+ '</div>' 
						+ '</td>'; 
			}
		} 
		return dayTd;
	}
	//dayArray를 tag로 감싸서 text로 만듬
	
	function getCalendarText(y, m){
		var text = '<table style="height:100%;" class="table table-striped">\n<tr><td colspan=7 style="text-align:center">' 
		text += '<span onclick="showCalendar('+(y-1)+','+m+')"> << </span>'; 
		text += '<span onclick="showCalendar('+(m==1?(y-1)+','+12:y+','+(m-1))+')"> < </span>'; 
		text += '[' + y + '/' + ((m < 10) ? ('0' + m) : m) + ']'; 
		text += '<span onclick="showCalendar('+(m==12?(y+1)+','+1:y+','+(m+1))+')"> > </span>'; 
		text += '<span onclick="showCalendar('+(y+1)+','+m+')"> >> </span>'; 
		text += '</td>'; 
	
		var dayTd = getDayText(y,((m < 10) ? ('0' + m) : m)); 
		
		return text+dayTd + '</tr>\n</table>';
	
	}
	
	//날짜가 들어있는 array를 return
	
	function getDayArray(year, month){
		var arDay = [];
		var d1 = (year+(year-year%4)/4-(year-year%100)/100+(year-year%400)/400
			+month*2+(month*5-month*5%9)/9-(month<3?year%4||year%100==0&&year%400?2:3:4))%7;
		var i = 0;
		var date = "";
		for (i = 0; i < 42; i++) {
			if (i < d1 || i >= d1+(month*9-month*9%8)/8%2+(month==2?year%4||year%100==0&&year%400?28:29:30)) {
				date = "";
				arDay.push({"no":i, "date":date, "day":i%7});
			}else{
				date = (i+1-d1);
				arDay.push({"no":i, "date":date, "day":i%7});
			}
		}
		return arDay;
	}
	
	//'calendarDiv에 달력 출력'
	
	function showCalendar(y, m) { 
		text = getCalendarText(y, m);
		document.getElementById('calendarDiv').innerHTML = text; 
	} 

</script>

</head>
<body onload="showCalendar(2017,6)">
<div id="All" style="margin: 10px;">
	<div id="left">
		<div id="total">
			<div id="calendarDiv" style="font-family: Gulim; font-size: 20px;">

			</div>
		</div>
	</div>
	<div id="right">
		<h1>일정 수정</h1>
		<form id="modifyForm">
			<input type="hidden" name="calendarId" value="${oneCalendar.calendarId}" />
			<div class="row">
				<div class="col-md-11">
					<div class="form-group">
						<label for="calendarTitle">일정</label> <input type="text"
							id="calendarTitle" name="calendarTitle"
							placeholder="책 제목을 입력하세요." class="form-control"
							value="${oneCalendar.calendarTitle}">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-11">
					<div class="form-group">
						<label for="calendarSubTitle">메모</label> <input
							type="text" id="calendarSubTitle" name="calendarSubTitle"
							placeholder="메모를 입력하세요." class="form-control"
							value="${oneCalendar.calendarSubTitle}">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-11">
					<div class="form-group">
						<label for="startDate">시작 날짜</label> <input type="date"
							id="startDate" name="startDate" class="form-control"
							value="${oneCalendar.startDate}">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-11">
					<div class="form-group">
						<label for="endDate">끝 날짜</label> <input type="date"
							id="endDate" name="endDate" class="form-control"
							value="${oneCalendar.endDate}">
					</div>
				</div>
			</div>
			<input type="button" id="modifyButton" class="btn btn-default" data-dismiss="modal" value="수정"/>
			<input type="button" id="returnButton" class="btn btn-default" data-dismiss="modal" value="돌아가기"/>
		</form>
	</div>
</div>
</body>

</html>
