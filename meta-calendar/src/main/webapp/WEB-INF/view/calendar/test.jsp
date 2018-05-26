<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DdayAlertTestPage</title>
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
    
    <script type="text/javascript"
	src="<c:url value="/static/js/jquery-3.1.1.min.js"/> "></script>
<script type="text/javascript">
      var schedules = {
        data: [
          {start: 1, end: 4, days: 4, order: 0, name: "ABC"},
          {start: 8, end: 12, days: 5, order: 0, name: "가나다"},
          {start: 17, end: 25, days: 9, order: 1, name: "하하하"}
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
              var weekScheduleDay = schedule.days;

              if (isOver) {
                weekScheduleDay = (endDate + 1) - day;
                console.log("endDate : " + endDate);
                console.log("day : " + day);
                console.log("weekScheduleDay : " + weekScheduleDay);
              }

              var position = $(td).position();
              console.log(position)
              var schd = $("<div class='schedule " + schedule.name + "'>" + schedule.name + "</div>");
              $(schd).css({
                "top": (position.top + 20 + (schedule.order * 23) ) + "px",
                "width": ((weekScheduleDay * 100) - 13) + "px"
              });
              $(td).append(schd);

              if(isOver) {
                schedule.days = schedule.days - weekScheduleDay;
                schedule.start = endDate + 1;
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
				<td data-year='2017' data-month='6'>25</td>
				<td data-year='2017' data-month='6'>26</td>
				<td data-year='2017' data-month='6'>27</td>
				<td data-year='2017' data-month='6'>28</td>
				<td data-year='2017' data-month='6'>29</td>
				<td data-year='2017' data-month='6'>30</td>
				<td data-year='2017' data-month='7' class='end start'>1</td>
			</tr>
			<tr>
				<td data-year='2017' data-month='7' class='start'>2</td>
				<td data-year='2017' data-month='7'>3</td>
				<td data-year='2017' data-month='7'>4</td>
				<td data-year='2017' data-month='7'>5</td>
				<td data-year='2017' data-month='7'>6</td>
				<td data-year='2017' data-month='7'>7</td>
				<td data-year='2017' data-month='7' class='end'>8</td>
			</tr>
			<tr>
				<td data-year='2017' data-month='7' class='start'>9</td>
				<td data-year='2017' data-month='7'>10</td>
				<td data-year='2017' data-month='7'>11</td>
				<td data-year='2017' data-month='7'>12</td>
				<td data-year='2017' data-month='7'>13</td>
				<td data-year='2017' data-month='7'>14</td>
				<td data-year='2017' data-month='7' class='end'>15</td>
			</tr>
			<tr>
				<td data-year='2017' data-month='7' class='start'>16</td>
				<td data-year='2017' data-month='7'>17</td>
				<td data-year='2017' data-month='7'>18</td>
				<td data-year='2017' data-month='7'>19</td>
				<td data-year='2017' data-month='7'>20</td>
				<td data-year='2017' data-month='7'>21</td>
				<td data-year='2017' data-month='7' class='end'>22</td>
			</tr>
			<tr>
				<td data-year='2017' data-month='7' class='start'>23</td>
				<td data-year='2017' data-month='7'>24</td>
				<td data-year='2017' data-month='7'>25</td>
				<td data-year='2017' data-month='7'>26</td>
				<td data-year='2017' data-month='7'>27</td>
				<td data-year='2017' data-month='7'>28</td>
				<td data-year='2017' data-month='7' class='end'>29</td>
			</tr>
			<tr>
				<td data-year='2017' data-month='7' class='start'>30</td>
				<td data-year='2017' data-month='7' class='end'>31</td>
				<td data-year='2017' data-month='8'>1</td>
				<td data-year='2017' data-month='8'>2</td>
				<td data-year='2017' data-month='8'>3</td>
				<td data-year='2017' data-month='8'>4</td>
				<td data-year='2017' data-month='8'>5</td>
			</tr>
		</table>
	</div>
</body>
</html>