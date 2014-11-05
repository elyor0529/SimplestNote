package servlets.notes;

import beans.LoginBean;
import db.NotesEntity;
import managers.NoteManager;
import managers.NoteVersionsManager;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import utils.ConvertHelper;
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
 * Created by Elyor on 8/18/2014.
 */
public class NoteServlet extends HttpServlet {

    private static final NoteManager noteManager = new NoteManager();

    /*
   * Change
   * */
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final JSONObject json = new JSONObject();

        try {
            if (!SessionUtil.isAuthorize(session)) {
                json.put("status", "503");
            } else {
                final LoginBean loginBean = SessionUtil.getLogin(session);
                final long userId = loginBean.getId();
                final long noteId = ConvertHelper.ToLong(request.getParameter("id"));
                final String title = ConvertHelper.ToString(request.getParameter("title"));
                final String content = ConvertHelper.ToString(request.getParameter("content"));
                final String tags = ConvertHelper.ToString(request.getParameter("tags"));
                final NotesEntity note = new NotesEntity();

                note.setId(noteId);
                note.setUserId(userId);
                note.setTitle(title);
                note.setContent(content);
                note.setTags(tags);

                if (!noteManager.update(note)) {
                    json.put("status", "404");
                } else {

                    final Object[] obj = (Object[]) noteManager.getNoteByUserId(userId, noteId);
                    final JSONObject noteJson = new JSONObject();

                    noteJson.put("id", noteId);
                    noteJson.put("title", ConvertHelper.ToString(obj[1]));
                    noteJson.put("create_date", ConvertHelper.ToDate(obj[5]));
                    noteJson.put("modified_date", ConvertHelper.ToDate(obj[6]));
                    noteJson.put("tags", ConvertHelper.ToString(obj[3]));
                    noteJson.put("content", ConvertHelper.ToString(obj[2]));
                    noteJson.put("version_id", ConvertHelper.ToBigInteger(obj[7]));
                    noteJson.put("version_count", ConvertHelper.ToBigInteger(obj[8]));

                    json.put("note", noteJson);
                    json.put("status", "200");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setContentType(Settings.REST_TYPE);
        response.getWriter().write(json.toString());
    }

    /*
   * Add
   * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final JSONObject json = new JSONObject();

        try {
            if (!SessionUtil.isAuthorize(session)) {
                json.put("status", "503");
            } else {
                final LoginBean loginBean = SessionUtil.getLogin(session);
                final long userId = loginBean.getId();
                final String title = ConvertHelper.ToString(request.getParameter("title"));
                final String content = ConvertHelper.ToString(request.getParameter("content"));
                final String tags = ConvertHelper.ToString(request.getParameter("tags"));
                final NotesEntity note = new NotesEntity();

                note.setUserId(userId);
                note.setTitle(title);
                note.setContent(content);
                note.setTags(tags);

                final long noteId = noteManager.insert(note);

                if (noteId == 0) {
                    json.put("status", "404");
                } else {

                    final Object[] obj = (Object[]) noteManager.getNoteByUserId(userId, noteId);
                    final JSONObject noteJson = new JSONObject();

                    noteJson.put("id", noteId);
                    noteJson.put("title", ConvertHelper.ToString(obj[1]));
                    noteJson.put("create_date", ConvertHelper.ToDate(obj[5]));
                    noteJson.put("modified_date", ConvertHelper.ToDate(obj[6]));
                    noteJson.put("tags", ConvertHelper.ToString(obj[3]));
                    noteJson.put("content", ConvertHelper.ToString(obj[2]));
                    noteJson.put("version_id", ConvertHelper.ToBigInteger(obj[7]));
                    noteJson.put("version_count", ConvertHelper.ToBigInteger(obj[8]));

                    json.put("note", noteJson);
                    json.put("status", "200");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setContentType(Settings.REST_TYPE);
        response.getWriter().write(json.toString());
    }

    /*
   * Delete
   * */
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final JSONObject json = new JSONObject();

        try {
            if (!SessionUtil.isAuthorize(session)) {
                json.put("status", "503");
            } else {
                final LoginBean loginBean = SessionUtil.getLogin(session);
                final long userId = loginBean.getId();
                final long noteId = ConvertHelper.ToInteger(request.getParameter("id"));

                if (!noteManager.delete(noteId, userId)) {
                    json.put("status", "404");
                } else {
                    json.put("id", noteId);
                    json.put("status", "200");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setContentType(Settings.REST_TYPE);
        response.getWriter().write(json.toString());
    }

    /*
    * List
    * */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final JSONObject json = new JSONObject();

        try {
            if (!SessionUtil.isAuthorize(session)) {
                json.put("status", "503");
            } else {
                final LoginBean loginBean = SessionUtil.getLogin(session);
                final long userId = loginBean.getId();
                final List result = noteManager.getNotesByUserId(userId);

                if (result == null) {
                    json.put("status", "404");
                } else {
                    final JSONArray notes = new JSONArray();

                    for (Object o : result) {
                        final Object[] obj = (Object[]) o;

                        final JSONObject note = new JSONObject();

                        note.put("id", ConvertHelper.ToBigInteger(obj[0]));
                        note.put("title", ConvertHelper.ToString(obj[1]));
                        note.put("create_date", ConvertHelper.ToDate(obj[5]));
                        note.put("modified_date", ConvertHelper.ToDate(obj[6]));
                        note.put("tags", ConvertHelper.ToString(obj[3]));
                        note.put("content", ConvertHelper.ToString(obj[2]));
                        note.put("version_id", ConvertHelper.ToBigInteger(obj[7]));
                        note.put("version_count", ConvertHelper.ToBigInteger(obj[8]));

                        notes.put(note);
                    }

                    json.put("notes", notes);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setContentType(Settings.REST_TYPE);
        response.getWriter().write(json.toString());
    }

}
