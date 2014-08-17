<%@ page import="db.UsersEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Elyor
  Date: 8/10/2014
  Time: 10:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page errorPage="/404.jsp" contentType="text/html;charset=UTF-8" language="java" %>
<%
    UsersEntity user = (UsersEntity) request.getAttribute("settings_result");
    Object result = request.getAttribute("alert_result");
%>
<html xmlns="http://www.w3c.org/1999/xhtml"
      xmlns:jsp="http://java.sun.com/JSP/Page">
<head>

    <title>Settings | SimplestNote
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
        <h1>Manage your account</h1>
    </div>
    <div class="row">
        <form role="form" method="post" class="form-signup" action="/settingsServlet">

            <%if (result != null) {%>
            <p class="alert alert-warning">
                <%=result%>
            </p>
            <%}%>

            <div class="form-group">
                <label class="control-label">First name:</label>
                <input type="text" name="first_name" value="<%=user.getFirstName() %>" class="form-control"
                       required="required"/>
            </div>

            <div class="form-group">
                <label class="control-label">Last name:</label>
                <input type="text" name="last_name" value="<%=user.getLastName() %>" class="form-control"
                       required="required"/>
            </div>

            <div class="form-group">
                <label class="control-label">Surname:</label>
                <input type="text" name="surname" value="<%=user.getSurname() %>" class="form-control"/>
            </div>

            <div class="form-group">
                <label class="control-label">E-mail:</label>
                <input type="email" name="e_mail" value="<%=user.geteMail() %>" class="form-control"
                       required="required"/>
            </div>

            <div class="form-group">
                <label class="control-label">Birthday:</label>
                <input type="text" name="birth_day" value="<%=user.getBirthDay() %>" class="form-control datepicker"
                       required="required"/>
            </div>

            <div class="form-group">
                <label class="control-label">Gender:</label>

                <div class="checkbox">
                    <label><input type="radio" <%=user.getGender().equalsIgnoreCase("F") ? "checked" : "" %> value="F"
                                  name="gender"/> Female </label>
                    <label><input type="radio"  <%=user.getGender().equalsIgnoreCase("M") ? "checked" : "" %> value="M"
                                  name="gender"/> Male </label>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label">Address:</label>
                <textarea name="address" class="form-control">
                    <%=user.getAddress()%>
                </textarea>
            </div>

            <button class="btn btn-lg btn-success btn-block" type="submit">Save</button>

        </form>
    </div>
</div>

<jsp:include page="../includes/footer.jsp" flush="true"/>

</body>
</html>
