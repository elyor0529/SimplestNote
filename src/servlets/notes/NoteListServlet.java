package servlets.notes;

import beans.LoginBean;
import db.NotesEntity;
import db.UsersEntity;
import managers.NoteManager;
import managers.UserManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.SessionUtil;
import utils.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Elyor on 8/17/2014.
 */
public class NoteListServlet extends HttpServlet {

    private static final NoteManager noteManager = new NoteManager();
    private static final UserManager userManager = new UserManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final HttpSession session = request.getSession();
        final LoginBean loginBean = SessionUtil.getLogin(session);
        JSONObject json = new JSONObject();
        try {

            if (loginBean == null) {
                json.put("message", "authorize");
            } else {
                final long userId = loginBean.getId();
                final UsersEntity userEntity = (UsersEntity) userManager.get(userId);

                if (userEntity == null) {
                    json.put("message", "not found");
                } else {

                    JSONArray notes = new JSONArray();
                    final int limit = request.getParameter("limit").isEmpty()
                            ? Settings.PAGING.LIMIT
                            : Integer.parseInt(request.getParameter("limit"));
                    final int offset = request.getParameter("offset").isEmpty()
                            ? Settings.PAGING.OFFSET
                            : Integer.parseInt(request.getParameter("offset"));
                    final List result = noteManager.getLimitsByUserId(userId, limit, offset);

                    for (Object o : result) {

                        NotesEntity entity = (NotesEntity) o;
                        JSONObject note = new JSONObject();

                        note.put("id", entity.getId());
                        note.put("title", entity.getTitle());
                        note.put("create_date", entity.getCreateDate());
                        note.put("content", entity.getContent());
                        note.put("tags", entity.getTags());

                        notes.put(note);
                    }

                    json.put("notes", notes);

                }
            }
            response.setContentType(Settings.REST_TYPE);
            response.getWriter().write(json.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/404.jsp");
    }
}
