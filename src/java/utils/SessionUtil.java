package utils;

import beans.LoginBean;

import javax.servlet.http.HttpSession;

/**
 * Created by Elyor on 8/10/2014.
 */
public class SessionUtil {

    public static LoginBean getLogin(HttpSession session) {
        final Object o = session.getAttribute(Settings.SESSION_AUTHORIZE_KEY);

        return o != null ? (LoginBean) o : null;
    }

    public static boolean isAuthorize(HttpSession session) {
        return getLogin(session) != null;
    }

    public static void setLogin(HttpSession session, LoginBean loginBean) {
        if (loginBean == null)
            return;

        session.setMaxInactiveInterval(Settings.SESSION_IN_ACTIVE_INTERVAL);
        session.setAttribute(Settings.SESSION_AUTHORIZE_KEY, loginBean);
    }

    public static void setLogout(HttpSession session) {
        session.removeAttribute(Settings.SESSION_AUTHORIZE_KEY);
        session.invalidate();
    }

}
