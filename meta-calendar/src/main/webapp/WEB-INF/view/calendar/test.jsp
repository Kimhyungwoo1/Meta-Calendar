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

var month = new Date().getMonth()+1;
var year = new Date().getFullYear();
$().ready(function(){
	
	$.post("<c:url value="/cal/test"/>",{
		year : year,
		month : month
	}, function(data) {
		$("body").append(data);
	});
	
	$("body").on("click", ".prev", function() {
		$.post("<c:url value="/cal/testTest"/>",{
			year : year,
			month : month
		}, function(data) {
			console.log(data);
		})
	});
	
});
</script>
</head>
<body>

</body>
</html>