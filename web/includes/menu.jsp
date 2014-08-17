<%@ page import="utils.SessionUtil" %>

<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">
                <span class="glyphicon glyphicon-home"></span>
                SimplestNote</a>

        </div>

        <div class="navbar-collapse collapse">

            <ul class="nav navbar-nav">

                <li><a href="/help.jsp">
                    <span class="glyphicon glyphicon-book"></span>
                    Help</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <%
                    if (SessionUtil.isAuthorize(session)) {
                %>


                <li>
                    <a href="/profileServlet">
                        <span class="glyphicon glyphicon-user"></span>
                        Welcome <%=SessionUtil.getLogin(session).getFullName()%>
                    </a>
                </li>

                <li><a href="/settingsServlet">
                    <span class="glyphicon glyphicon-cog"></span>
                    Settings</a></li>

                <li>
                    <a id="logoutAnchor" href="#"
                       onclick="javascript:if(!confirm('You are sure to logout?'))return; document.getElementById('logoutForm').submit(); ">
                        <span class="glyphicon glyphicon-log-out"></span>
                        Log Out
                    </a>

                    <form action="/logoutServlet" id="logoutForm" method="post">
                    </form>
                </li>

                <%
                } else {
                %>

                <li><a href="/loginServlet">
                    <span class="glyphicon glyphicon-log-in"></span>
                    Log In</a></li>

                <% }
                %>
            </ul>
        </div>
        <!--/.navbar-collapse -->

    </div>
</div>

