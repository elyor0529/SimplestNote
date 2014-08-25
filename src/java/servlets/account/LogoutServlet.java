package servlets.account;

import utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Elyor on 8/11/2014.
 */
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession httpSession = request.getSession();

        if (!SessionUtil.isAuthorize(httpSession)) {
            response.sendRedirect("/404.jsp");
        } else {
            SessionUtil.setLogout(httpSession);

            response.sendRedirect("/index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/404.jsp");
    }
}
