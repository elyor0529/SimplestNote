<%--
  Created by IntelliJ IDEA.
  User: Elyor
  Date: 8/10/2014
  Time: 4:34 PM
  To change this template use File | utils.Settings | File Templates.
--%>
<%@ page errorPage="/404.jsp" contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3c.org/1999/xhtml"
      xmlns:jsp="http://java.sun.com/JSP/Page">
<head>

    <title>Home Page | SimplestNote</title>

    <jsp:include page="includes/header.jsp" flush="true"/>
    <jsp:include page="includes/styles.html" flush="true"/>
    <jsp:include page="includes/scripts.html" flush="true"/>

</head>

<body>

<jsp:include page="includes/menu.jsp" flush="true"/>

<div class="container">
    <div style="margin-top: 80px;text-align: center;">
        <h1>Project "SimplestNote"</h1>
    </div>

    <div class="page-header">
        <h2>Preamble</h2>
    </div>
    <p class="lead">
        The course projects are inspired by real-world needs, and they usually refer to similar sites already on the
        web. Students must follow the specifications given by this document, but they could also refine them through an
        interaction with the teacher and the analysis of similar websites. In any case, the final realisation must be
        completely original. The published information must be well organized and accessible, to satisfy the different
        kinds of users of a public website.
    </p>

    <div class="page-header">
        <h2>Site Specifications</h2>
    </div>
    <p class="lead">
        SimplestNote is a simple online notepad with note-sharing features. The site is accessible to registered users
        only. Each user has a virtual notebook, consisting of an arbitrary number of pages, which we shall call notes in
        the following. Each note has a title and a content (both plain text). The length of the title may be limited,
        whereas the content length should be unlimited. Notes are also associated with their creation date, last
        modification date, version number (corresponding to the number of times the note has been updated) and an
        arbitrary number of servlets.tags, defined as words or short sequences of words (e.g., "news", "work" or "private
        messages"). The system stores all the versions of each note, so as to prevent unwanted changes and provide a
        note history. It will be possible to share a note with other users of the system, making it appear in their
        notepads and possibly allowing them to modify it.
    </p>

</div>
<!-- /container -->

<jsp:include page="includes/footer.jsp" flush="true"/>

</body>
</html>
