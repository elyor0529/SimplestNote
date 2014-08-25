package managers;

import org.hibernate.Session;

import java.util.List;

/**
 * Created by Elyor on 8/10/2014.
 */
public interface ManagerImpl {

    Session getSession();

    List getAll();

    Object get(long id);

    long insert(Object o);

    boolean update(Object o);

    boolean delete(long id);

}
