<%@ page import="utils.SessionUtil" %>
<%--
  Created by IntelliJ IDEA.
  User: Elyor
  Date: 8/17/2014
  Time: 1:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    if (!SessionUtil.isAuthorize(session)) {
        response.sendRedirect("/loginServlet");
    } else {
        response.sendRedirect("/dashboardServlet");
    }
%>
