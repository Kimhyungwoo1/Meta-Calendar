<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
table {
	border-collapse: collapse;
}

td {
	height: 100px;
	vertical-align: top;
}

#calendar {
	overflow: hidden;
}

.schedule {
	background-color: blue;
	color: white;
	padding-left: 10px;
	position: absolute;
}
</style>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">

		var schedules = {
			data:	[
						{start: 1, end: 4, days: 4, order: 0, name: "ABC"},
						{start: 8, end: 12, days: 5, order: 0, name: "가나다"},
						{start: 11, end: 13, days: 3, order: 1, name: "하하하"}
					]
		};

		$().ready(function() {
			for( var i in schedules.data ) {
				var schedule = schedules.data[i];

				var startDate = 0;
				var endDate = 0;
				$("td").each(function(index, td) {
				var day = parseInt($(td).text());

				startDate = $(td).is(".start") ? day : startDate;
				endDate = $(td).is(".end") ? day : endDate;
				endDate = parseInt(endDate);

				if(startDate > endDate) {
					if(startDate == 1) {
						endDate = endDate + (7 - (index % 7));
					} else {
					endDate = endDate + 7;
					}
				}

				if ( day == schedule.start ) {

				var isOver = (index % 7) + schedule.days > 6;
				//console.log(schedule.name, day, "startDate : " + startDate, "endDate : " + endDate, isOver);
				var weekScheduleDay = schedule.days;

				if (isOver) {
					weekScheduleDay = (endDate + 1) - day;
					//console.log("weekScheduleDay : " + weekScheduleDay);
				}
				var position = $(td).position();

				var schd = $("<div class='schedule " + schedule.name + "'>" + schedule.name + "</div>");

				$(schd).css({
					"top": (position.top + 20 + (schedule.order * 23) ) + "px",
					"width": ((weekScheduleDay * 100) - 13) + "px"
				});
				$(td).append(schd);
				
				if(isOver) {
					schedule.days = schedule.days - weekScheduleDay;
					schedule.start = endDate + 1;
					//console.log(schedule.name, "OverDate : " + schedule.overDate, "schedule.days : " + schedule.days);
				}
				}
				});
			}
		});

</script>
</head>
<body>

	<div id="calendar" style="width: 700px;">
		<table>
			<colgroup>
				<col width="100" />
				<col width="100" />
				<col width="100" />
				<col width="100" />
				<col width="100" />
				<col width="100" />
				<col width="100" />
			</colgroup>
			<tr>
				<td data-year='2017' data-month='5'>28</td>
				<td data-year='2017' data-month='5'>29</td>
				<td data-year='2017' data-month='5'>30</td>
				<td data-year='2017' data-month='5'>31</td>
				<td data-year='2017' data-month='6' class='start'>1</td>
				<td data-year='2017' data-month='6'>2</td>
				<td data-year='2017' data-month='6' class='end'>3</td>
			</tr>
			<tr>
				<td data-year='2017' data-month='6' class='start'>4</td>
				<td data-year='2017' data-month='6'>5</td>
				<td data-year='2017' data-month='6'>6</td>
				<td data-year='2017' data-month='6'>7</td>
				<td data-year='2017' data-month='6'>8</td>
				<td data-year='2017' data-month='6'>9</td>
				<td data-year='2017' data-month='6' class='end'>10</td>
			</tr>
			<tr>
				<td data-year='2017' data-month='6' class='start'>11</td>
				<td data-year='2017' data-month='6'>12</td>
				<td data-year='2017' data-month='6'>13</td>
				<td data-year='2017' data-month='6'>14</td>
				<td data-year='2017' data-month='6'>15</td>
				<td data-year='2017' data-month='6'>16</td>
				<td data-year='2017' data-month='6' class='end'>17</td>
			</tr>
			<tr>
				<td data-year='2017' data-month='6' class='start'>18</td>
				<td data-year='2017' data-month='6'>19</td>
				<td data-year='2017' data-month='6'>20</td>
				<td data-year='2017' data-month='6'>21</td>
				<td data-year='2017' data-month='6'>22</td>
				<td data-year='2017' data-month='6'>23</td>
				<td data-year='2017' data-month='6' class='end'>24</td>
			</tr>
			<tr>
				<td data-year='2017' data-month='6' class='start'>25</td>
				<td data-year='2017' data-month='6'>26</td>
				<td data-year='2017' data-month='6'>27</td>
				<td data-year='2017' data-month='6'>28</td>
				<td data-year='2017' data-month='6'>29</td>
				<td data-year='2017' data-month='6'>30</td>
				<td data-year='2017' data-month='7' class='end'>31</td>
			</tr>
		</table>
	</div>
</body>
</html>