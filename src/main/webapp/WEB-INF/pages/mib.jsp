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

<spring:url value="mibbrowser/browser" var="browserUrl"/>
<spring:url value="mibbrowser/mib" var="mibUrl"/>
<spring:url value="mibbrowser/config" var="configUrl"/>


<div class="container">

    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">MibBrowser</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                    <li><a href="http://bootstrap.imazin.ru/examples/navbar/#">Browser</a></li>
                    <li><a href="http://bootstrap.imazin.ru/examples/navbar/#">Configuration</a></li>
                    <li  class="active"><a href="http://bootstrap.imazin.ru/examples/navbar/#">Mib</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="jumbotron">
        <form role="form">
            <div class="form-group" style="width: 200px;">
                <input type="file" class="filestyle" data-icon="false" data-size="sm"><br>
                <input type="file" class="filestyle" data-icon="false" data-size="sm">
            </div>
        </form>

    </div>
</div>

</body>
</html>
