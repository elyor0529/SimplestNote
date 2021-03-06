<%--
  Created by IntelliJ IDEA.
  User: Elyor
  Date: 8/11/2014
  Time: 4:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page errorPage="/404.jsp" contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3c.org/1999/xhtml"
      xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
    <title>Registration successfully | SimplestNote</title>

    <jsp:include page="../includes/header.jsp" flush="true"/>
    <jsp:include page="../includes/styles.html" flush="true"/>

</head>
<body>
<jsp:include page="../includes/menu.jsp" flush="true"/>
<div class="container">
    <div class="page-header">
        <h1>Registration successfully</h1>
    </div>

    <%
        Object result = request.getAttribute("register_result");
        if (result != null) {%>
    <p class="alert alert-success">
        Your registration was successful but you must now confirm your email address before you can log in. Please
        check
        your
        email and click on the link provided.
    </p>
    <%} else {%>
    <p class="alert alert-danger">
        Sorry,the page you were looking for doesn't exist anymore or might have been
        moved.
    </p>
    <%}%>
</div>
<jsp:include page="../includes/footer.jsp" flush="true"/>
</body>
</html>
