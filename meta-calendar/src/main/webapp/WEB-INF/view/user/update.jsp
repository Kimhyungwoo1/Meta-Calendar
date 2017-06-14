<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js"/> "></script>
<script type="text/javascript">
	$().ready(function() {
		$("#updateForm").find("input[type=button]").click(function () {
			$("#updateForm").attr({
				method:"post",
				action:"<c:url value="/user/update/${user.userId}" />"
			});
			$("#updateForm").submit();
		});
	});
</script>
</head>
<body>

	<h1>회원 정보 수정</h1>
	
	<form id="updateForm">
		<input type="text" name="userPassword" placeholder="수정할 비밀번호를 입력하세요." />
		<input type="text" name="email" value="${user.email}" placeholder="수정할 이메일을 입력하세요." />
		<input type="button" value="확인" />
	</form>

</body>
</html>