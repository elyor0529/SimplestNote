package servlets.dashboard;

import beans.LoginBean;
import db.UsersEntity;
import managers.UserManager;
import utils.ConvertHelper;
import utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Elyor on 8/17/2014.
 */
public class ProfileServlet extends HttpServlet {

    private static final UserManager manager = new UserManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/404.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String userId = ConvertHelper.ToString(request.getParameter("id"));
        final int id;

        if (userId == null || userId.isEmpty()) {

            final HttpSession session = request.getSession();
            final LoginBean loginBean = SessionUtil.getLogin(session);

            if (loginBean == null) {
                response.sendRedirect("/pages/login.jsp");
            }

            id = (int) loginBean.getId();

        } else {
            id = Integer.parseInt(userId);
        }

        final UsersEntity user = (UsersEntity) manager.get(id);

        if (user == null) {
            response.sendRedirect("/404.jsp");
        } else {
            request.setAttribute("profile_result", user);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/profile.jsp");

            rd.include(request, response);
        }

    }
}
