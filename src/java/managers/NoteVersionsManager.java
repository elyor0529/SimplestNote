package managers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by Elyor on 8/25/2014.
 */
public class NoteVersionsManager implements ManagerImpl {
    private static Session session;

    public NoteVersionsManager(Session session) {
        this.session = session;
    }

    public NoteVersionsManager() {
        this(HibernateUtil.getSession());
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public List getAll() {
        return null;
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

            final String sql = "INSERT INTO note_versions(note_id,modified_date) " +
                    "VALUES(:noteId,now());";
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
        return false;
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    public boolean deleteByNoteId(long noteId) {
        boolean result = false;
        String sql = "";
        Query query = null;
        Transaction transaction = session.getTransaction();

        try {

            transaction.begin();

            sql = "DELETE FROM note_versions WHERE note_id IN (SELECT id FROM notes WHERE id=:note_id);";
            query = session.createSQLQuery(sql);
            query.setParameter("note_id", noteId);
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
