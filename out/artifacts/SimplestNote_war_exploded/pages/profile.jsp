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
    Object result = request.getAttribute("profile_result");
%>
<html xmlns="http://www.w3c.org/1999/xhtml"
      xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
    <title>Profile | SimplestNote
    </title>

    <jsp:include page="../includes/header.jsp" flush="true"/>
    <jsp:include page="../includes/styles.html" flush="true"/>
    <jsp:include page="../includes/scripts.html" flush="true"/>

</head>

<body>
<jsp:include page="../includes/menu.jsp" flush="true"/>
<div class="container">
    <div class="page-header">
        <h1>Profile</h1>
    </div>

    <%if (result == null) {%>
    <p class="alert alert-danger">
        Sorry,this profile not yet!
    </p>
    <%
    } else {
        UsersEntity user = (UsersEntity) result;
    %>

    <table class="table">

        <tr>
            <th>
                First name:
            </th>
            <td>
                <%=user.getFirstName()%>
            </td>
        </tr>

        <tr>
            <th>
                Last name:
            </th>
            <td>
                <%=user.getLastName()%>
            </td>
        </tr>

        <tr>
            <th>
                Surname:
            </th>
            <td>
                <%=user.getSurname()%>
            </td>
        </tr>

        <tr>
            <th>
                E-mail:
            </th>
            <td>
                <%=user.geteMail()%>
            </td>
        </tr>

        <tr>
            <th>
                Birth day:
            </th>
            <td>
                <%=user.getBirthDay()%>
            </td>
        </tr>

        <tr>
            <th>
                Gender:
            </th>
            <td>
                <%=user.getGender()%>
            </td>
        </tr>

        <tr>
            <th>
                Address:
            </th>
            <td>
                <%=user.getAddress()%>
            </td>
        </tr>

    </table>

    <%}%>

</div>

<jsp:include page="../includes/footer.jsp" flush="true"/>

</body>
</html>
