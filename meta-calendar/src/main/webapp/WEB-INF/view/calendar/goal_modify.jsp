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
<script type="text/javascript" src="<c:url value="/static/js/jquery-3.1.1.min.js" /> "></script>
<script type="text/javascript" src="<c:url value="/static/js/bootstrap.min.js"/> "></script>
<script type="text/javascript">
	$().ready(function() {
		$("#modifyButton").click(function() {
			console.log($("#modifyForm").serialize());
			 $.post("<c:url value="/goal/update"/>", $("#modifyForm").serialize(), function(response) {
				if ( response == "ok") {
					opener.document.location.reload();
					self.close();
				}
				else {
					alert("실패");
				} 
			});
		}); 
	});
</script>
</head>
<body style="margin-left: 30px;">

	<div style="width: 90%;">
		<h1>목표 수정</h1>
		<form id="modifyForm">
			<input type="hidden" name="goalId" value="${goal.goalId}" />
			<input type="hidden" name="goalCreateDate" value="${goal.goalCreateDate}" />
			<div class="row">
				<div class="col-md-11">
					<div class="form-group">
						<label for="goalTitle">일정</label> <input type="text"
							id="goalTitle" name="goalTitle"
							placeholder="목표를 입력하세요." class="form-control"
							value="${goal.goalTitle}">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-11">
					<div class="form-group">
						<label for="goalSubTitle">메모</label> <input type="text"
							id="goalSubTitle" name="goalSubTitle"
							placeholder="메모를 입력하세요." class="form-control"
							value="${goal.goalSubTitle}">
					</div>
				</div>
			</div>
			<input type="button" id="modifyButton" class="btn btn-default"
				data-dismiss="modal" value="수정" />
		</form>
	</div>
</body>
</html>