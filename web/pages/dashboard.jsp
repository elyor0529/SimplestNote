<%--
  Created by IntelliJ IDEA.
  User: Elyor
  Date: 8/10/2014
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page errorPage="/404.jsp" contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3c.org/1999/xhtml"
      xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
    <title>Dashboard | SimplestNote
    </title>

    <jsp:include page="../includes/header.jsp" flush="true"/>
    <jsp:include page="../includes/styles.html" flush="true"/>

</head>

<body>
<jsp:include page="../includes/menu.jsp" flush="true"/>
<div class="container">
    <jsp:include page="../includes/notes.jsp" flush="true"/>
</div>

<jsp:include page="../includes/footer.jsp" flush="true"/>

</body>
</html>
