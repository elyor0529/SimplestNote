<%@ page import="utils.SessionUtil" %>
<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]>
<script src="../resources/js/ie8-responsive-file-warning.js"></script>
<![endif]-->
<script src="../resources/js/ie-emulation-modes-warning.js"></script>

<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
<script src="../resources/js/ie10-viewport-bug-workaround.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="../resources/js/html5shiv.min.js"></script>
<script src="../resources/js/hrespond.min.js"></script>
<![endif]-->

<%
    if (SessionUtil.isAuthorize(session)) {
%>

<div class="modal" id="logoutModal" tabindex="-1" role="dialog"
     aria-labelledby="logoutModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-body">
                <h4>
                    You are sure to logout?
                </h4>
            </div>
            <div class="modal-footer">
                <form action="/logoutServlet" method="post">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close
                    </button>
                    <button type="submit" class="btn btn-warning">OK</button>
                </form>
            </div>
        </div>
    </div>
</div>
<%
    }%>
