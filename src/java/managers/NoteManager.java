package managers;

import db.NoteVersionsEntity;
import db.NotesEntity;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by Elyor on 8/16/2014.
 */
public class NoteManager implements ManagerImpl {
    private static Session session;
    private static NoteVersionsManager versionsManager;
    private static NotePermissionsManager permissionsManager;

    public NoteManager(Session session) {
        this.session = session;
        versionsManager = new NoteVersionsManager(session);
        permissionsManager = new NotePermissionsManager(session);
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

    public List getAllTags() {
        final String sql = "SELECT SUBSTRING_INDEX(SUBSTRING_INDEX(t.tags, ' ', n.n), ' ', -1) tag\n" +
                "  FROM notes t CROSS JOIN \n" +
                "(\n" +
                "   SELECT a.N + b.N * 10 + 1 n\n" +
                "     FROM \n" +
                "    (SELECT 0 AS N UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) a\n" +
                "   ,(SELECT 0 AS N UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9) b\n" +
                "    ORDER BY n\n" +
                ") n\n" +
                " WHERE n.n <= 1 + (LENGTH(t.tags) - LENGTH(REPLACE(t.tags, ' ', '')))\n" +
                " ORDER BY tag";
        final SQLQuery query = session.createSQLQuery(sql);
        final List list = query.list();

        return list;
    }

    public List getNotesByUserId(long userId) {
        final String sql = "SELECT n.id,\n" +
                "\t   n.title,\n" +
                "\t   n.content,\n" +
                "\t   n.tags, \n" +
                "\t   n.user_id,\n" +
                "       n.create_date,\n" +
                "       (SELECT nv.modified_date FROM note_versions AS nv WHERE nv.note_id=n.id ) AS modified_date,\n" +
                "\t   (SELECT nv.id FROM note_versions AS nv WHERE nv.note_id=n.id ) AS version_id,\n" +
                "\t   (SELECT count(*) FROM note_versions AS nv WHERE nv.note_id=n.id ) AS version_count\n" +
                "FROM notes AS n\n" +
                "WHERE n.user_id=:user_id AND n.parent_id IS NULL";
        final SQLQuery query = session.createSQLQuery(sql);

        query.setParameter("user_id", userId);

        return query.list();
    }

    public Object getNoteByUserId(long userId, long id) {
        final String sql = "SELECT n.id,\n" +
                "\t   n.title,\n" +
                "\t   n.content,\n" +
                "\t   n.tags, \n" +
                "\t   n.user_id,\n" +
                "       n.create_date,\n" +
                "       (SELECT nv.modified_date FROM note_versions AS nv WHERE nv.note_id=n.id ) AS modified_date,\n" +
                "\t   (SELECT nv.id FROM note_versions AS nv WHERE nv.note_id=n.id ) AS version_id,\n" +
                "\t   (SELECT count(*) FROM note_versions AS nv WHERE nv.note_id=n.id ) AS version_count\n" +
                "FROM notes AS n\n" +
                "WHERE n.user_id=:user_id AND n.id=:id AND n.parent_id IS NULL";
        final SQLQuery query = session.createSQLQuery(sql);

        query.setParameter("id", id);
        query.setParameter("user_id", userId);

        final List result = query.list();

        return result.size() > 0 ? result.get(0) : null;
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
        long result = 0;
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();

            final String sql = "INSERT INTO notes(title,content,tags,create_date,parent_id,user_id) " +
                    "VALUES(:title,:content,:tags,now(),NULL ,:userId);";
            final Query query = session.createSQLQuery(sql);

            query.setProperties(o);

            if (query.executeUpdate() > 0) {
                final Object lastObj = session.createSQLQuery("SELECT LAST_INSERT_ID()")
                        .uniqueResult();

                result = Long.valueOf(lastObj.toString());
            }
            transaction.commit();

        } catch (Exception ext) {

            transaction.rollback();
            ext.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean update(Object o) {
        boolean result = false;
        Transaction transaction = session.getTransaction();
        String sql = "";
        Query query = null;
        final NotesEntity entity = (NotesEntity) o;

        try {
            transaction.begin();

            sql = "UPDATE notes SET title=:title,content=:content,tags=:tags" +
                    " WHERE id=:id";
            query = session.createSQLQuery(sql);
            query.setProperties(o);
            query.executeUpdate();

            transaction.commit();

            final NoteVersionsEntity versionsEntity = new NoteVersionsEntity();
            versionsEntity.setNoteId(entity.getId());
            versionsManager.insert(versionsEntity);

            result = true;
        } catch (Exception ext) {
            transaction.rollback();
            ext.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    public boolean delete(long id, long userId) {

        if (!permissionsManager.deleteByNoteId(id))
            return false;

        if (!versionsManager.deleteByNoteId(id))
            return false;

        boolean result = false;
        String sql = "";
        Query query = null;
        Transaction transaction = session.getTransaction();

        try {

            transaction.begin();

            sql = "DELETE FROM notes WHERE id=:note_id AND user_id IN (SELECT id FROM users WHERE id=:user_id);";
            query = session.createSQLQuery(sql);
            query.setParameter("note_id", id);
            query.setParameter("user_id", userId);
            query.executeUpdate();

            transaction.commit();

            result = true;

        } catch (Exception ext) {

            transaction.rollback();
            ext.printStackTrace();

        }

        return result;
    }

}
