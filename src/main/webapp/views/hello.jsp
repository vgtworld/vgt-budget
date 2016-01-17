<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="vgt" uri="/WEB-INF/vgt.tld" %>

<t:basic title="Hello World page">
	<h1>Hello World!</h1>
	<p>Current time: <vgt:SimpleDatetime date="${model.currentDate}"/></p>
</t:basic>
