<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>DdayAlertTestPage</title>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/> "></script>
<script type="text/javascript">
$().ready(function(){
	$("#valiTestForm").find("input[type=button]").click(function(){
		$("#valiTestForm").attr({
			"method" : "post",
			"action" : "<c:url value="/cal/test"/>"
		});
	$("#valiTestForm").submit();
	});
});
</script>
</head>
<body>

	<form:form commandName="valiTestForm">
		<input type="text" name="calendarTitle" placeholder="일정 이름" /><br/>
		<form:errors path="calendarTitle"></form:errors><br/>
		
		<input type="text" name="calendarSubTitle" placeholder="일정 요약"/><br/>
		<form:errors path="calendarSubTitle"></form:errors><br/>
		
		<input type="date" name="startDate"/><br/>
		<form:errors path="startDate"></form:errors><br/>
		
		<input type="date" name="endDate"/><br/>
		<form:errors path="endDate"></form:errors><br/>
		
		<input type="button" value="등록" />
	</form:form>

</body>
</html>