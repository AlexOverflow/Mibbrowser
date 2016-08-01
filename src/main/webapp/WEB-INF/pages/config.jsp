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
          <li><a href="${browserUrl}">Browser</a></li>
          <li class="active"><a href="${configUrl}">Configuration</a></li>
          <li><a href="${mibUrl}">Mib</a></li>
        </ul>
      </div>
    </div>
  </nav>

  <div class="jumbotron">

    <form:form class="form-horizontal" method="post" modelAttribute="config" data-toggle="validator">
      <div class="form-group">
        <label for="port" class="col-xs-2 control-label">Port</label>
        <div class="col-xs-10">
          <form:input path="port" pattern="[0-9]+" maxlength="5" type="text" class="form-control" id="port" placeholder="Port" />
        </div>
      </div>
      <div class="form-group">
        <label for="retries" class="col-xs-2 control-label">Retries</label>
        <div class="col-xs-10">
          <form:input  path="retries" type="text"  pattern="[1-9][0-9]*" maxlength="5" class="form-control" id="retries" placeholder="Set retries number"   />
        </div>

      </div>

      <div class="form-group">
        <label for="timeOut" class="col-xs-2 control-label">Time Out</label>
        <div class="col-xs-10">
          <form:input  path="timeOut" type="text"  pattern="[1-9][0-9]*" maxlength="5" class="form-control" id="timeOut" placeholder="Set time out"   />
        </div>

      </div>

      <div class="form-group">
        <div class="col-xs-offset-2 col-xs-10">
          <button type="submit" class="btn btn-primary">Set</button>
        </div>
      </div>
    </form:form>




  </div>
</div>

</body>
</html>
