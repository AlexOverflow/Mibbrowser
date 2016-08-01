<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
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
	<script src="<c:url value="/resources/js/validator.js"/>"></script>



</head>
<body>

<spring:url value="/mibbrowser/browser" var="browserUrl"/>
<spring:url value="/mibbrowser/mib" var="mibUrl"/>
<spring:url value="/mibbrowser/browser/config" var="configUrl"/>

<spring:url value="/mibbrowser/mib/add" var="uploadMib"/>
<spring:url value="/mibbrowser/mib/delete" var="deleteMib"/>


<div class="container">

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${browserUrl}">MibBrowser</a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li  class="active"><a href="${browserUrlUrl}">Browser</a></li>
					<li><a href="${configUrl}">Configuration</a></li>
					<li><a href="${mibUrl}">Mib</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron">

		<form:form class="form-horizontal" method="post" modelAttribute="request" data-toggle="validator">
			<div class="form-group">
				<label for="address" class="col-xs-2 control-label">Address</label>
				<div class="col-xs-10">
					<form:input path="hostAddress" pattern="[1-9]+(\.[0-9]+)+" type="text" class="form-control" id="address" placeholder="Host address" />
				</div>
			</div>
			<div class="form-group">
				<label for="oid" class="col-xs-2 control-label">OID</label>
				<div class="col-xs-10">
					<form:input  path="oid" type="text"  pattern="[1-9]+(\.[0-9]+)+" class="form-control" id="oid" placeholder="OID"   />
				</div>

			</div>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-10" style="width: 200px">
					<form:select path="command" class="form-control">
						<form:options items="${commandValues}" />
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-10">
					<button type="submit" class="btn btn-primary">Send</button>
				</div>
			</div>
		</form:form>



		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
				<tr>
					<th>OID</th>
					<th>Mib Name</th>
					<th>Value</th>
					<th>Time</th>
				</tr>
				</thead>
				<tbody>
            <c:if test="${null != response}" >
				<c:forEach var="mibVar" items="${response.getMibVariables()}">
					<tr>
						<td>${mibVar.getOid()}</td>
						<td>${mibVar.getMibName()}</td>
						<td>${mibVar.getOidValue()}</td>
						<td>${response.getTime()}</td>
					</tr>
				</c:forEach>
			</c:if>
				</tbody>
			</table>
		</div>
	</div>
</div>

</body>
</html>
