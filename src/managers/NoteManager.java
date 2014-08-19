package managers;

import db.NotesEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by Elyor on 8/16/2014.
 */
public class NoteManager implements ManagerImpl {
    private static Session session;

    public NoteManager(Session session) {
        this.session = session;
    }

    public NoteManager() {
        this(HibernateUtil.getSession());
    }

    @Override
    public Session getSession() {
        return session;
    }

    public List getAllByUserId(long userId, int limit, int offset) {
        final String sql = "SELECT * FROM notes WHERE  parent_id IS NULL AND user_id=:user_id LIMIT :limit OFFSET :offset";
        final SQLQuery query = session.createSQLQuery(sql);

        query.addEntity(NotesEntity.class);
        query.setParameter("user_id", userId);
        query.setParameter("limit", limit);
        query.setParameter("offset", offset);

        return query.list();
    }

    public List getAllByUserId(long userId) {
        final String sql = "from NotesEntity where  parentId is null AND userId=:user_id";
        final Query query = session.createQuery(sql);

        query.setParameter("user_id", userId);

        return query.list();
    }

    public int getNoteCountByUserId(long userId) {
        final String sql = "SELECT COUNT(nv.note_id) AS version_count,\n" +
                "\t   n.id,\n" +
                "\t   n.title,\n" +
                "\t   n.content,\n" +
                "\t   n.tags, \n" +
                "\t   n.user_id,\n" +
                "       n.create_date,\n" +
                "       nv.modified_date,\n" +
                "\t   nv.id AS version_id\n" +
                "FROM note_versions AS nv\n" +
                "INNER JOIN notes AS n ON n.id=nv.note_id\n" +
                "WHERE n.user_id=:user_id AND n.parent_id IS NULL\n" +
                "GROUP BY nv.note_id\n" +
                "ORDER BY nv.id DESC";
        final SQLQuery query = session.createSQLQuery(sql);

        query.setParameter("user_id", userId);

        final List list = query.list();

        return list == null ? 0 : list.size();
    }

    public List getNotesByUserId(long userId, int limit, int offset) {
        final String sql = "SELECT COUNT(nv.note_id) AS version_count,\n" +
                "\t   n.id,\n" +
                "\t   n.title,\n" +
                "\t   n.content,\n" +
                "\t   n.tags, \n" +
                "\t   n.user_id,\n" +
                "       n.create_date,\n" +
                "       nv.modified_date,\n" +
                "\t   nv.id AS version_id\n" +
                "FROM note_versions AS nv\n" +
                "INNER JOIN notes AS n ON n.id=nv.note_id\n" +
                "WHERE n.user_id=:user_id AND n.parent_id IS NULL\n" +
                "GROUP BY nv.note_id\n" +
                "ORDER BY nv.id DESC " +
                "LIMIT :limit OFFSET :offset";
        final SQLQuery query = session.createSQLQuery(sql);

        query.setParameter("user_id", userId);
        query.setParameter("limit", limit);
        query.setParameter("offset", offset);

        return query.list();
    }

    public Object getByUserId(long userId, long id) {
        final String sql = "from NotesEntity where  parentId is null  AND userId=:user_id AND id=:id";
        final Query query = session.createQuery(sql);

        query.setParameter("id", id);
        query.setParameter("user_id", userId);

        final List result = query.list();

        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public List getAll() {
        final String sql = "from NotesEntity where  parentId is null";
        final Query query = session.createQuery(sql);

        return query.list();
    }

    @Override
    public Object get(long id) {
        return null;
    }

    @Override
    public long insert(Object o) {
        return 0;
    }

    @Override
    public boolean update(Object o) {
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }
}
