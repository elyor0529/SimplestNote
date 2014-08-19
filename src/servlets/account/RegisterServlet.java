package servlets.account;

import db.UsersEntity;
import managers.UserManager;
import utils.ConvertHelper;
import utils.EmailSender;
import utils.SessionUtil;
import utils.StringGenerator;

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
 * Created by Elyor on 8/10/2014.
 */
public class RegisterServlet extends HttpServlet {

    private static final UserManager manager = new UserManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String userName = StringGenerator.generateString(8, StringGenerator.Mode.ALPHA);
        final String password = StringGenerator.generateString(6, StringGenerator.Mode.NUMERIC);
        final String verifiedCode = StringGenerator.generateString(32, StringGenerator.Mode.ALPHANUMERIC);
        final String email = ConvertHelper.ToString(request.getParameter("e_mail"));
        final String firstName = ConvertHelper.ToString(request.getParameter("first_name"));
        final String lastName = ConvertHelper.ToString(request.getParameter("last_name"));
        final String surName = ConvertHelper.ToString(request.getParameter("surname"));
        final String gender = ConvertHelper.ToString(request.getParameter("gender"));
        final String address = ConvertHelper.ToString(request.getParameter("address"));
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay;

        try {
            birthDay = dateFormat.parse(ConvertHelper.ToString(request.getParameter("birth_day")));
        } catch (ParseException e) {
            birthDay = new Date();
        }

        UsersEntity entity = new UsersEntity();
        entity.seteMail(email);
        entity.setUserName(userName);
        entity.setPassword(password);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setSurname(surName);
        entity.setBirthDay(new java.sql.Date(birthDay.getTime()));
        entity.setGender(gender);
        entity.setAddress(address);
        entity.setIsVerified(false);
        entity.setVerificationCode(verifiedCode);

        final long uid = manager.insert(entity);

        if (uid > 0) {

            final String body = "<p>Hi " + entity.getFirstName() + "!</p>" +
                    "<p>Please click the following verification link to confirm your e-mail:http://localhost:8080/verifyEmail?uid=" + uid + "&verification_code=" + entity.getVerificationCode() + " .</p>" +
                    "<p>-----------------------------<br>Dear ,<br/> SimplestNote.</p>";
            final String subject = "Registration Verification";

            EmailSender.Send(entity.geteMail(), subject, body);

            request.setAttribute("register_result", "Success");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/register_success.jsp");

            rd.include(request, response);
        } else {
            request.setAttribute("register_result", "We're sorry, this form fields is already registered");

            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/register.jsp");

            rd.include(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession httpSession = request.getSession();

        if (!SessionUtil.isAuthorize(httpSession)) {
            response.sendRedirect("/pages/register.jsp");
        } else {
            response.sendRedirect("/404.jsp");
        }
    }
}
