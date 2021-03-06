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
    <jsp:include page="../includes/scripts.html" flush="true"/>

</head>

<body>
<jsp:include page="../includes/menu.jsp" flush="true"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2 col-md-2 sidebar">

            <ul class="list-group">
                <li class="list-group-item">
                    <a href="/index.jsp">
                        Notes</a>
                </li>
                <li class="list-group-item">
                    <a href="/api.jsp">
                        Api</a>
                </li>
            </ul>
        </div>
        <div class="col-sm-10 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <div class="row">

                <!--
                  Notes
                  -->
                <jsp:include page="../includes/notes.jsp" flush="false"/>

            </div>
        </div>
    </div>
</div>
<jsp:include page="../includes/footer.jsp" flush="true"/>

</body>
</html>
