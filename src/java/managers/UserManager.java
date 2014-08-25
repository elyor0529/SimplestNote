package managers;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

/**
 * Created by Elyor on 8/10/2014.
 */
public class UserManager implements ManagerImpl {

    private static Session session;

    public UserManager(Session session) {
        this.session = session;
    }

    public UserManager() {
        this(HibernateUtil.getSession());
    }

    public Object get(String userName, String password) {
        final String sql = "from UsersEntity  where userName=:user_name and password=:password";
        final Query query = session.createQuery(sql);

        query.setParameter("user_name", userName);
        query.setParameter("password", password);

        final List result = query.list();

        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public Session getSession() {
        return session;
    }

    @Override
    public List getAll() {
        final String sql = "from UsersEntity";
        final Query query = session.createQuery(sql);

        return query.list();
    }

    @Override
    public Object get(long id) {
        final String sql = "from UsersEntity  where id=:id";
        final Query query = session.createQuery(sql);

        query.setParameter("id", id);

        final List result = query.list();

        return result.size() > 0 ? result.get(0) : null;
    }

    @Override
    public long insert(Object o) {
        long result = 0;
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();

            final String sql = "INSERT INTO users(first_name,last_name,surname,e_mail,birth_day,user_name,password,gender,is_verified,verification_code) " +
                    "VALUES(:firstName,:lastName,:surname,:eMail,:birthDay,:userName,:password,:gender,:isVerified,:verificationCode);";
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

        try {
            transaction.begin();

            final String sql = "UPDATE users SET first_name=:firstName,last_name=:lastName,surname=:surname,e_mail=:eMail,birth_day=:birthDay,user_name=:userName,password=:password,gender=:gender " +
                    "WHERE id=:id";
            final Query query = session.createSQLQuery(sql);

            query.setProperties(o);

            result = query.executeUpdate() > 0;

            transaction.commit();

        } catch (Exception ext) {
            transaction.rollback();
            ext.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean delete(long id) {
        boolean result = false;
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();

            final String sql = "DELETE FROM users WHERE id=:id";
            final Query query = session.createSQLQuery(sql);

            query.setParameter("id", id);

            result = query.executeUpdate() > 0;

            transaction.commit();

        } catch (Exception ext) {
            transaction.rollback();
            ext.printStackTrace();
        }

        return result;
    }
}
