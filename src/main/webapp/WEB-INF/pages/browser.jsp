<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
	<title>Mibbrowser</title>

	<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value="/resources/css/navbar.css"/>" type="text/css" media="all">
	<link rel="stylesheet" href="<c:url value="/resources/css/normalize.css"/>" type="text/css" media="all">

	<script src="<c:url value="/resources/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/resources/js/bootstrap-filestyle.min.js"/>"></script>


</head>
<body>

<spring:url value="/mibbrowser/browser" var="browserUrl"/>
<spring:url value="/mibbrowser/mib" var="mibUrl"/>
<spring:url value="/mibbrowser/config" var="configUrl"/>

<spring:url value="/mibbrowser/mib/add" var="uploadMib"/>
<spring:url value="/mibbrowser/mib/delete" var="deleteMib"/>


<div class="container">

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">MibBrowser</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li  class="active"><a href="${mibUrl}">Browser</a></li>
					<li><a href="${configUrl}">Configuration</a></li>
					<li><a href="${mibUrl}">Mib</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron">
		<form role="form">
			<div class="form-group">
				<label for="address">ADDRESS</label>
				<form:input id="address" path="hostAddress" class="form-control" />
				</div>>
			<div class="form-group">
				<label for="oid">OID</label>
				<form:input id="oid" path="oid" class="form-control" />
			</div>

			<div class="form-group">
			<form:select path="command" class="form-control input-lg">
				<form:option selected="selected" value="get">GET</form:option>
				<form:option value="next">GET_NEXT</form:option>
				<form:option value="walk">WALK</form:option>
			</form:select>
			</div>

			<div class="form-group">
				<input type="submit" class="btn btn-info" value="Send" />
			</div>
		</form>

		<c:forEach var="mib" items="${mibList}">
			${mib} <a href="${deleteMib}/${mib}">delete</a>
		</c:forEach>
	</div>
</div>

</body>
</html>
