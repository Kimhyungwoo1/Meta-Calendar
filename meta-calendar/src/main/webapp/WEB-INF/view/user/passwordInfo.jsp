<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>추가 정보 입력</title>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/> "></script>
<script type="text/javascript">
	$().ready(function() {
		$("#passwordForm").find("input[type=button]").click(function () {
			$("#passwordForm").attr({
				method:"post",
				action:"<c:url value="/user/kakao/passwordInfo"/> "
			});
			$("#passwordForm").submit();
		});
	});
</script>
</head>
<body>
	<form id="passwordForm">
		비밀번호 : <input type="password" name="userPassword" /><br/>
		<input type="button" value="입력완료" />
	</form>
</body>
</html>