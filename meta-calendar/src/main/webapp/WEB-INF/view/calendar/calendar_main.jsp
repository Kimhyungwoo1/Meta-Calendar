<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<link rel="stylesheet" type="text/css" href="<c:url value="/static/css/calendar_main.css"/>">
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js" /> "></script>
<script type="text/javascript"src="<c:url value="/static/js/bootstrap.min.js"/> "></script>
<script type="text/javascript">

	var day = new Date().getDate();
	var month = new Date().getMonth()+1;
	var year = new Date().getFullYear();
	var today = year + '/0' + month + '/0' + day; //오늘날짜 String형
	
	var yearMonth = year + (month < 10 ? "0" + month : month)
	
	
	$().ready(function() {
		
		showCalendar(year, month)
		
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
		
		$.post("<c:url value="/goal/list"/> ", {}, function(data) {
			for (i = 0; i < data.length; i++) {
				var titleText = '<tr>'
					titleText += '<th>'
					titleText += '<div>'+ data[i].goalTitle +'</div>'
					titleText += '</th>'
					titleText += '<th>'
					titleText += '<div>'+ data[i].goalSubTitle +'</div>'
					titleText += '</th>'
					titleText += '<th>'
					titleText += '<div class="goalModify" data-id="' + data[i].goalId + '">수정<div>'
					titleText += '</th>'
					titleText += '<th>'
					titleText += '<div class="goalDelete" data-id="' + data[i].goalId + '">삭제<div>'
					titleText += '</th>'
					titleText += '</tr>'
				
				
				if ( data[i].goalTerm != null) {
					
					if(data[i].goalTerm == "Y" && data[i].goalCreateDate == year) {
						$("#yearTbody").append(titleText);
					}
					else if(data[i].goalTerm == "M" && data[i].goalCreateDate == yearMonth) {
						$("#monthTbody").append(titleText);
					}
					
				}
			}
		});
		
		$("#goalWriteButton").click(function() {
			var goal = $("#goalForm").serialize();
			
			$.post("<c:url value="/goal/write" />", $("#goalForm").serialize(), function(response) {
				if ( response == "ok" ) {
					location.reload();
				}
				else {
					alert("실패~");
				}
			});
			
		})
		
		$("#yearTbody").on("click", ".goalModify", function() {
			var goalId = $(this).data('id');
			window.open("<c:url value="/goal/detail/"/>" + goalId, "modifyWindow", "width: 300px, height:100px");
		});
		
		$("#yearTbody").on("click", ".goalDelete", function() {
			var goalId = $(this).data('id');
			$.post("<c:url value="/goal/delete"/>", {goalId:goalId}, function(res) {
				if (res == "ok") {
					location.reload();
				}
				else {
					alert("실패");
				}
			});
		});
		
		$("#monthTbody").on("click", ".goalModify", function() {
			var goalId = $(this).data('id');
			window.open("<c:url value="/goal/detail/"/>" + goalId, "modifyWindow", "width: 300px, height:100px");
			
		});
		
		$("#monthTbody").on("click", ".goalDelete", function() {
			var goalId = $(this).data('id');
			$.post("<c:url value="/goal/delete"/>", {goalId:goalId}, function(res) {
				if (res == "ok") {
					location.reload();
				}
				else {
					alert("실패");
				}
			});
		});
		
		$.post("<c:url value="/cal/dday" />", {
			//사용자 id를 parameter로 전달해서 해당 사용자의 당일 일정 list를 받아온다.
		}, function(data) {
			if(data != null){
				console.log(data);
				alertDday(data);
			}
		});
		
		$("#todayTbody").on("click", "#calendarModify", function() {
			var calendarId = $(this).data('id');
			window.open("<c:url value="/cal/detail/" />" + calendarId, "modifyWindow", "width:300px, height:300px" );
			
		});
		
		$("#todayTbody").on("click", "#calendarDelete", function() {
			
			var calendarId = $(this).data('id');
			
			$.post("<c:url value="/cal/delete" />", {calendarId : calendarId}, function(response) {
				if ( response == 'ok' ) {
					location.reload();
				}
				else {
					alert("실패");
				}
			});
		});
		
		$(".modal-footer").find("#writeButton").click(function() {
			$.post("<c:url value="/cal/write" />", $("#writeForm").serialize(), function(response) {
				if ( response == "ok" ){
					location.reload();
				}
				else {
					alert("모든 항목을 입력해주세요.");
					$("#insertModal").modal("show");
				}
			});
		});
		
		//클릭하면 서버에서 json형태로 데이터가 온다.
		$(".rows").click(function() {
			
			var target = $(this).data('date');
			 $.post("<c:url value="/cal/onecal"/> ",{target : target}, function(data) {
				
				if ( data != null ) {
					var text
					var listLength = data.length
					
					$("#todayTbody").html("");
					for( i = 0; i < listLength; i++ ) {
						var id = data[i].calendarId
						text = '<tr>'
						text += '<th>'
						text += data[i].calendarTitle
						text += '</th>'
						text += '<th>'
						text += data[i].calendarSubTitle
						text += '</th>'
						text += '<th>'
						text += '<div id="calendarModify" data-id="' + data[i].calendarId + '">수정</div>'
						text += '</th>'
						text += '<th>'
						text += '<div id="calendarDelete" data-id="' + data[i].calendarId + '">삭제</div>'
						text += '</th>'
						text += '</tr>'
						$("#todayTbody").append(text);
					}
					
				}
				
				if ( data == null || data == "" ) {
					$("#todayTbody").html("<h3 >일정이 없습니다.</h3>");
					
				}
			}); 
		});
	});
	
	function alertDday(data){
		var ddayList;
		var subTitle
		for(var i = 0; i < data.length; i++){
			ddayList = data[i].calendarTitle
			subTitle = data[i].calendarSubTitle
			alert("오늘의 할일\n" + ddayList + " " + subTitle);
		}
	}

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
		text += '<a href="javascript:void(0);" data-toggle="modal" data-target="#insertModal" style="float: right; font-size: 16px; padding: 0px;">일정 추가</a> '
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
<body >
	<div id="All" style="margin: 10px;">
	<div id="left">
		<div id="total">
			<div id="calendarDiv" style="font-family: Gulim; font-size: 20px;">

			</div>
		</div>

		<div id="detail">
			<div id="number">${calendarList[0].calendarTitle}</div>
			<h2>Today</h2>
			<table class="table table-striped">
				<colgroup>
					<col width="20%">
					<col width="*">
				</colgroup>
				<thead>
					<tr>
						<th>일정제목</th>
						<th>메모</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody id="todayTbody">

				</tbody>
			</table>
		</div>
	</div>
	<div id="right">
		<div id="year" style="height: 40%; margin-bottom: 10px; word-break:normal;">
			<a href="javascript:void(0);" data-toggle="modal" data-target="#goalModal"
				style="float: right; font-size: 16px;">목표 추가</a>
			<h3>연간 목표</h3>
			<table class="table">
				<colgroup>
					<col width="30%">
					<col width="45%">
					<col width="15%">
					<col width="15%">
				</colgroup>
				<thead>
					<tr>
						<th>목표</th>
						<th>메모</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody	id="yearTbody">

				</tbody>
			</table>
		</div>
		<div id="month" style="height: 40%; margin-bottom: 10px; word-break:normal; ">
			<h3>월간 목표</h3>
			<table class="table">
					<colgroup>
						<col width="30%">
						<col width="45%">
						<col width="15%">
						<col width="15%">
					</colgroup>
					<thead>
						<tr>
							<th>목표</th>
							<th>메모</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody	id="monthTbody">
	
					</tbody>
			</table>
		</div>
	</div>
</div>
<!-- insert 모달 창 -->
	<div class="modal fade" id="insertModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">일정</h4>
				</div>

				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-md-10">
								<form:form commandName="writeForm">
									<div class="row">
										<div class="col-md-7">
											<div class="form-group">
												<label for="calendarTitle">일정</label> 
												<input type="text" id="calendarTitle" name="calendarTitle"
													placeholder="일정을 입력하세요." class="form-control">
												<form:errors path="calendarTitle"></form:errors>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-7">
											<div class="form-group">
												<label for="calendarSubTitle">메모</label> <input
													type="text" id="calendarSubTitle" name="calendarSubTitle"
													placeholder="메모를 입력하세요." class="form-control">
												<form:errors path="calendarSubTitle"></form:errors>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3">
											<div class="form-group">
												<label for="startDate">시작 날짜</label> 
												<input type="date" id="startDate" name="startDate" class="form-control"><br/>
												<form:errors path="startDate"></form:errors>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label for="endDate">끝 날짜</label> <input type="date"
													id="endDate" name="endDate" class="form-control">
												<form:errors path="endDate"></form:errors>
											</div>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="writeButton" class="btn btn-default"
						data-dismiss="modal">추가</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 목표 insert 모달 창 -->
	<div class="modal fade" id="goalModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">일정</h4>
				</div>

				<div class="modal-body">
					<div class="container">
						<div class="row">
							<div class="col-md-10">
								<form id="goalForm">
									<div class="row">
										<div class="col-md-7">
											<div class="form-group">
												<label for="goalTitle">일정</label> <input type="text"
													id="goalTitle" name="goalTitle"
													placeholder="목표를 입력하세요." class="form-control">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-7">
											<div class="form-group">
												<label for="goalSubTitle">메모</label> <input
													type="text" id="goalSubTitle" name="goalSubTitle"
													placeholder="메모를 입력하세요." class="form-control">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-7">
											<label for="">목표종류</label><br/>
											<div class="btn-group" data-toggle="buttons">
												<label class="btn btn-primary">
													<input type="radio" name="year" id="year" autocomplete="off"> 연간목표
												</label>
												<label class="btn btn-primary">
													<input type="radio" name="month" id="month" autocomplete="off"> 월간목표
												</label>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" id="goalWriteButton" class="btn btn-default"
						data-dismiss="modal">추가</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
</body>

</html>
