package servlets.tags;

import managers.NoteManager;
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
 * Created by Elyor on 8/25/2014.
 */
public class TagServlet extends HttpServlet {
    private static final NoteManager noteManager = new NoteManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/404.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final JSONArray json = new JSONArray();

        final List result = noteManager.getAllTags();

        if (result == null) {
            json.put("404");
        } else {
            final JSONArray notes = new JSONArray();

            for (Object o : result) {
                notes.put(ConvertHelper.ToString(o));
            }

            json.put(notes);
        }

        response.setContentType(Settings.REST_TYPE);
        response.getWriter().write(json.toString());
    }
}
