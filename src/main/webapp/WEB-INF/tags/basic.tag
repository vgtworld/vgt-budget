<%@ tag description="Basic template" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" type="java.lang.String" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/3.3.5/css/bootstrap.min.css">
	<script src="${pageContext.request.contextPath}/static/jquery/1.9.1/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<title>${title}</title>
</head>
<body>
<div class="content">
	<jsp:doBody/>
</div>
</body>
</html>