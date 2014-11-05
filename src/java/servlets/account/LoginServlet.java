package servlets.account;

import beans.LoginBean;
import db.UsersEntity;
import managers.UserManager;
import utils.ConvertHelper;
import utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Elyor on 8/10/2014.
 */
public class LoginServlet extends javax.servlet.http.HttpServlet {

    private static final UserManager manager = new UserManager();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        final String userName = ConvertHelper.ToString(request.getParameter("user_name"));
        final String password = ConvertHelper.ToString(request.getParameter("password"));
        final UsersEntity entity = (UsersEntity) manager.get(userName, password);
        final HttpSession httpSession = request.getSession();

        if (entity != null) {

            if (entity.getIsVerified() == null ||
                    !entity.getIsVerified()) {
                request.setAttribute("login_result", "Not verified");
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");

                rd.include(request, response);
            } else {
                final LoginBean loginBean = LoginBean.getLoginBean(entity);

                SessionUtil.setLogin(httpSession, loginBean);
                response.sendRedirect("/index.jsp");
            }
        } else {
            request.setAttribute("login_result", "We're sorry, user name or password error");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");

            rd.include(request, response);
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        final HttpSession httpSession = request.getSession();

        if (!SessionUtil.isAuthorize(httpSession)) {
            response.sendRedirect("/pages/login.jsp");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
}
