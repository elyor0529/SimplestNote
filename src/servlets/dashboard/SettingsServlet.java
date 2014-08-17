package servlets.dashboard;

import beans.LoginBean;
import db.UsersEntity;
import managers.UserManager;
import utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Elyor on 8/17/2014.
 */
public class SettingsServlet extends HttpServlet {

    private static final UserManager manager = new UserManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final LoginBean loginBean = SessionUtil.getLogin(session);

        if (loginBean == null) {
            response.sendRedirect("/pages/login.jsp");
        } else {
            final int id = (int) loginBean.getId();
            final UsersEntity entity = (UsersEntity) manager.get(id);
            final String email = request.getParameter("e_mail");
            final String firstName = request.getParameter("first_name");
            final String lastName = request.getParameter("last_name");
            final String surName = request.getParameter("surname");
            final String gender = request.getParameter("gender");
            final String address = request.getParameter("address");
            final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDay;

            try {
                birthDay = dateFormat.parse(request.getParameter("birth_day").trim());
            } catch (ParseException e) {
                birthDay = new Date();
            }

            entity.seteMail(email);
            entity.setFirstName(firstName);
            entity.setLastName(lastName);
            entity.setSurname(surName);
            entity.setBirthDay(new java.sql.Date(birthDay.getTime()));
            entity.setGender(gender);
            entity.setAddress(address);

            if (manager.update(entity)) {

                request.setAttribute("alert_result", "Success");

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/settings_success.jsp");

                rd.include(request, response);
            } else {
                request.setAttribute("alert_result", "We're sorry, this form fields is already registered");

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/settings.jsp");

                rd.include(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();

        if (!SessionUtil.isAuthorize(session)) {
            response.sendRedirect("/pages/login.jsp");
        } else {
            final LoginBean loginBean = SessionUtil.getLogin(session);
            final int id = (int) loginBean.getId();
            final UsersEntity user = (UsersEntity) manager.get(id);

            if (user == null) {
                response.sendRedirect("/404.jsp");
            } else {
                request.setAttribute("settings_result", user);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/settings.jsp");

                rd.include(request, response);
            }
        }
    }
}
