<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: marsel
  Date: 26.06.16
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mibbrowser</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/style(1).css"/>" type="text/css" media="all">
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery.jcarousel.css"/>" type="text/css" media="all">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>" type="text/css" media="all">
    <link rel="stylesheet" href="<c:url value="/resources/css/normalize.css"/>" type="text/css" media="all">

</head>
<body>
<spring:url value="/mibbrowser/browser/get" var="browserUrl"/>

<div class="shell">
    <div class="border">


        <div id="navigation">
            <ul>
                <li></li>
                <li><a href="${index}">Browser</a></li>
                <li><a href="${snmpConfig}">Configuration</a></li>
                <li><a href="">Mibs</a></li>


            </ul>
            <div class="cl">&nbsp;</div>
        </div>



        <div id="main">
            <form:form method="post" modelAttribute="browserRequest" action="${browserUrl}" >
            Address:<br>
            <form:input type="text" path="address" />
            <br>
            <br> OID:<br>
            <form:input type="text" path="oid" />
            <br>
            <br>
                <input type="submit">
                </form:form>

            <div class="table">
                <div class="row header green">
                    <div class="cell">
                        oid
                    </div>
                    <div class="cell">
                        Value
                    </div>
                    <div class="cell">
                        MibName
                    </div>
                    <div class="cell">
                        Time
                    </div>

                </div>
                <c:if test="${null != browserResponse}" >
                <c:forEach var="variable" items="${browserResponse.getResponse().getVariableList()}" >
                <div class="row">
                    <div class="cell">
                        ${variable.getOid()}
                    </div>
                    <div class="cell">
                       ${variable.getValue()}
                    </div>
                    <div class="cell">
                       ${variable.getMibName()}
                    </div>
                    <div class="cell">
                       ${browserResponse.getTime()}
                    </div>
                </div>
                </c:forEach>
                </c:if>
            </div>
            </div>
             </div>
        </div>
         </body>
</html>