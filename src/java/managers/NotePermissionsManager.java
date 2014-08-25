package managers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by Elyor on 8/25/2014.
 */
public class NotePermissionsManager implements ManagerImpl {
    private static Session session;

    public NotePermissionsManager(Session session) {
        this.session = session;
    }

    public NotePermissionsManager() {
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

    public boolean deleteByNoteId(long noteId) {
        boolean result = false;
        String sql = "";
        Query query = null;
        Transaction transaction = session.getTransaction();

        try {

            transaction.begin();

            sql = "DELETE FROM note_permissions WHERE note_version_id IN (SELECT id FROM note_versions WHERE note_id=:note_id);";
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
