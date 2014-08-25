package servlets.account;

import db.UsersEntity;
import managers.UserManager;
import utils.ConvertHelper;
import utils.EmailSender;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Elyor on 8/11/2014.
 */
public class VerifyEmailServlet extends HttpServlet {

    private static final UserManager manager = new UserManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/404.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final long uid = Long.parseLong(ConvertHelper.ToString(request.getParameter("uid")));
        final String verification_code = ConvertHelper.ToString(request.getParameter("verification_code"));
        final UsersEntity entity = (UsersEntity) manager.get(uid);

        if (entity != null &&
                !entity.getIsVerified() &&
                entity.getVerificationCode().equalsIgnoreCase(verification_code)) {

            entity.setIsVerified(true);

            if (!manager.update(entity)) {
                request.setAttribute("error_result", "Verified error");

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/404.jsp");

                rd.include(request, response);
            } else {

                final String body = "<p>Hi " + entity.getFirstName() + "!</p>" +
                        "<p>Your SimplestNote for credentials:<br> User Name:" + entity.getUserName() + "<br> Password:" + entity.getPassword() + ".</p>" +
                        "<p>Please try login now : http://localhost:8080/loginServlet </p>" +
                        "<p>-----------------------------<br>Dear ,<br/> SimplestNote.</p>";
                final String subject = "Welcome to SimplestNote!";

                EmailSender.Send(entity.geteMail(), subject, body);

                request.setAttribute("register_result", "Success");

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/register_success.jsp");

                rd.include(request, response);
            }
        } else {
            request.setAttribute("error_result", "Verified error");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/404.jsp");

            rd.include(request, response);
        }
    }
}
