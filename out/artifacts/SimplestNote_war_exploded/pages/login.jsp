<%--
  Created by IntelliJ IDEA.
  User: Elyor
  Date: 8/10/2014
  Time: 4:47 PM
  To change this template use File | utils.Settings | File Templates.
--%>
<%@ page errorPage="/404.jsp" contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3c.org/1999/xhtml"
      xmlns:jsp="http://java.sun.com/JSP/Page">
<head>

    <title>Log in | SimplestNote
    </title>

    <jsp:include page="../includes/header.jsp" flush="true"/>
    <jsp:include page="../includes/styles.html" flush="true"/>

</head>

<body>

<jsp:include page="../includes/menu.jsp" flush="true"/>

<div class="container">

    <form class="form-signin" role="form" method="post" action="/loginServlet">
        <h2 class="form-signin-heading">
            Sign In</h2>
        <%
            Object result = request.getAttribute("login_result");
            if (result != null) {%>
        <p class="alert alert-warning">
            <%=result%>
        </p>
        <%}%>
        <input type="text" name="user_name" class="form-control" placeholder="User name" required="required"/>
        <input type="password" name="password" class="form-control" placeholder="Password" required="required"/>

        <button class="btn btn-lg btn-primary btn-block" type="submit">
            Sign in
        </button>

        <div class="checkbox">
            <label>
                Don't have a account? <a href="/registerServlet">Sign up now</a>
            </label>
        </div>
    </form>
</div>
<!-- /container -->
<jsp:include page="../includes/footer.jsp" flush="true"/>

</body>
</html>
