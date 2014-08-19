package servlets.rest;

import db.NotesEntity;
import db.UsersEntity;
import managers.NoteManager;
import managers.UserManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ConvertHelper;
import utils.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Elyor on 8/16/2014.
 */
public class NotepadGetListServlet extends HttpServlet {

    private static final NoteManager noteManager = new NoteManager();
    private static final UserManager userManager = new UserManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/404.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String userName = ConvertHelper.ToString(request.getParameter("user"));
        final String password = ConvertHelper.ToString(request.getParameter("pass"));
        final UsersEntity userEntity = (UsersEntity) userManager.get(userName, password);
        final JSONObject json = new JSONObject();

        try {
            if (userEntity == null) {
                json.put("error", "404");
            } else {
                final JSONArray notes = new JSONArray();
                final long userId = userEntity.getId();


                final int limit = request.getParameter("limit").isEmpty()
                        ? Settings.PAGING.LIMIT
                        : Integer.parseInt(request.getParameter("limit"));
                final int offset = request.getParameter("offset").isEmpty()
                        ? Settings.PAGING.OFFSET
                        : Integer.parseInt(request.getParameter("offset"));
                final List result = noteManager.getAllByUserId(userId, limit, offset);

                for (Object o : result) {

                    final NotesEntity entity = (NotesEntity) o;
                    final JSONObject note = new JSONObject();

                    note.put("id", entity.getId());
                    note.put("title", entity.getTitle());
                    note.put("create_date", entity.getCreateDate());
                    note.put("content", entity.getContent());
                    note.put("tags", ConvertHelper.ToString(entity.getTags()));

                    notes.put(note);
                }

                json.put("notes", notes);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setContentType(Settings.REST_TYPE);
        response.getWriter().write(json.toString());

    }
}
