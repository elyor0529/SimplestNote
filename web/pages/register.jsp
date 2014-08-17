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

    <title>Sign Up | SimplestNote
    </title>

    <jsp:include page="../includes/header.jsp" flush="true"/>
    <jsp:include page="../includes/styles.html" flush="true"/>
    <jsp:include page="../includes/scripts.html" flush="true"/>
</head>

<body>

<jsp:include page="../includes/menu.jsp" flush="true"/>

<!-- Begin page content -->
<div class="container">
    <div class="page-header">
        <h1>Create an account</h1>
    </div>
    <p class="alert alert-info">
        If you already account use that <a href="/loginServlet">sign in?</a>
    </p>

    <div class="row">
        <form role="form" method="post" class="form-signup" action="/registerServlet">

            <%
                Object result = request.getAttribute("register_result");
                if (result != null) {%>
            <p class="alert alert-warning">
                <%=result%>
            </p>
            <%}%>

            <div class="form-group">
                <label class="control-label">First name:</label>
                <input type="text" name="first_name" class="form-control" required="required"/>
            </div>

            <div class="form-group">
                <label class="control-label">Last name:</label>
                <input type="text" name="last_name" class="form-control" required="required"/>
            </div>

            <div class="form-group">
                <label class="control-label">Surname:</label>
                <input type="text" name="surname" class="form-control"/>
            </div>

            <div class="form-group">
                <label class="control-label">E-mail:</label>
                <input type="email" name="e_mail" class="form-control" required="required"/>
            </div>

            <div class="form-group">
                <label class="control-label">Birthday:</label>
                <input type="text" name="birth_day" class="form-control datepicker"
                       required="required"/>
            </div>

            <div class="form-group">
                <label class="control-label">Gender:</label>

                <div class="checkbox">
                    <label><input type="radio" value="F" name="gender"/> Female </label>
                    <label><input type="radio" value="M" name="gender"/> Male </label>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label">Address:</label>
                <textarea name="address" class="form-control">
                </textarea>
            </div>


            <button class="btn btn-lg btn-success btn-block" type="submit">Sign up</button>
        </form>
    </div>
</div>

<jsp:include page="../includes/footer.jsp" flush="true"/>
</body>
</html>
