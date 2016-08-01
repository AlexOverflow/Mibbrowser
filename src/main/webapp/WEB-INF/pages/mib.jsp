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
                    <li><a href="${configUrl}">Configuration</a></li>
                    <li  class="active"><a href="${mibUrl}">Mib</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="jumbotron">
        <form:form role="form" commandName="uploadForm" method="post" action="${uploadMib}" enctype="multipart/form-data" >
            <div class="form-group" style="width: 200px;">
                Upload mib file: <br>
                <input type="file" name="file" class="filestyle" data-icon="false" data-size="sm"><br>
                <button type="submit" class="btn btn-success">Upload</button>
            </div>
        </form:form>



        <div class="table-responsive" style="width: 400px">
            <table class="table table-striped">
                <tbody>
                <c:forEach var="mib" items="${mibList}">
                        <tr>
                                <td>${mib}<td>
                            <td><a href="${deleteMib}/${mib}"><button type="submit" class="btn btn-danger">Delete</button></a></td>
                            <td></td>
                            <td></td>
                                    </tr>

                    </c:forEach>


                </tbody>
            </table>
        </div>

    </div>
</div>

</body>
</html>
