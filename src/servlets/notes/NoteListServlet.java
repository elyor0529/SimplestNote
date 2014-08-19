package servlets.notes;

import beans.LoginBean;
import beans.NoteBean;
import managers.NoteManager;
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
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Elyor on 8/18/2014.
 */
public class NoteListServlet extends HttpServlet {

    private static final NoteManager noteManager = new NoteManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final HttpSession session = request.getSession();
        final LoginBean loginBean = SessionUtil.getLogin(session);
        final long userId = loginBean.getId();
        int start = Integer.parseInt(ConvertHelper.ToString(request.getParameter("start")));
        int length = Integer.parseInt(ConvertHelper.ToString(request.getParameter("length")));
        final int draw = Integer.parseInt(ConvertHelper.ToString(request.getParameter("draw")));
        final int size = noteManager.getNoteCountByUserId(userId);

        if (length == -1) {
            length = size;
            start = 0;
        }

        final List result = noteManager.getNotesByUserId(userId, length, start);
        final JSONObject json = new JSONObject();

        try {

            if (result == null) {
                json.put("error", "404");
            } else {

                final ArrayList<NoteBean> list = new ArrayList<NoteBean>();

                for (Object o : result) {
                    final Object[] obj = (Object[]) o;
                    final NoteBean noteBean = new NoteBean();

                    noteBean.setVersionCount(new BigInteger(ConvertHelper.ToString(obj[0])));
                    noteBean.setId(new BigInteger(ConvertHelper.ToString(obj[1])));
                    noteBean.setTitle(ConvertHelper.ToString(obj[2]));
                    noteBean.setContent(ConvertHelper.ToString(obj[3]));
                    noteBean.setTags(ConvertHelper.ToString(obj[4]));
                    noteBean.setUserId(new BigInteger(ConvertHelper.ToString(obj[5])));
                    noteBean.setCreateDate(new Timestamp(ConvertHelper.ToDate(obj[6]).getTime()));
                    noteBean.setModifiedDate(new Timestamp(ConvertHelper.ToDate(obj[7]).getTime()));
                    noteBean.setVersionId(new BigInteger(ConvertHelper.ToString(obj[8])));

                    list.add(noteBean);
                }

                final JSONArray notes = new JSONArray();

                for (NoteBean o : list) {

                    final JSONObject note = new JSONObject();

                    note.put("id", o.getId());
                    note.put("title", o.getTitle());
                    note.put("create_date", o.getCreateDate());
                    note.put("modified_date", o.getModifiedDate());

                    notes.put(note);
                }

                json.put("data", notes);
                json.put("draw", draw);

                json.put("recordsTotal", size);
                json.put("recordsFiltered", size);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        response.setContentType(Settings.REST_TYPE);
        response.getWriter().write(json.toString());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/404.jsp");
    }
}
