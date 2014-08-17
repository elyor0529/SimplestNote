<%--
  Created by IntelliJ IDEA.
  User: Elyor
  Date: 8/11/2014
  Time: 4:18 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3c.org/1999/xhtml"
      xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
    <title>Not Found | SimplestNote</title>

    <jsp:include page="includes/header.jsp" flush="true"/>
    <jsp:include page="includes/styles.html" flush="true"/>
</head>
<body>

<jsp:include page="includes/menu.jsp" flush="true"/>

<div class="container">
    <div class="page-header">
        <h1>
            OOPS!
        </h1>
    </div>
    <p class="alert alert-danger">
        <%
            Object result = request.getAttribute("error_result");
            if (result != null) {%>
        <%=result.toString()%>
    </p>
    <%} else {%>
    Sorry,the page you were looking for doesn't exist anymore or might have been
    moved.
    <%}%>
    </p>
</div>

</body>
</html>
