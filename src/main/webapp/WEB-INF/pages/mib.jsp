<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
</head>
<body>

<c:forEach var="mib" items="${mibList}">
    ${mib}
</c:forEach>

<spring:url value="mibbrowser/mib/add" var="addMibUrl" />

<form:form method="post"
           commandName="uploadForm" action="${addMibUrl}" enctype="multipart/form-data">

    <p>Select files to upload. Press Add button to add more file inputs.</p>

    <input id="addFile" type="button" value="Add File" />
    <table id="fileTable">
        <tr>
            <td><input type="file" name="file" /></td>
        </tr>
    </table>
    <br/><input type="submit" value="Upload" />
</form:form>
</body>
</html>
