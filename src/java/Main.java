import db.UsersEntity;
import managers.UserManager;

import java.sql.Date;
import java.util.List;

/**
 * Created by Elyor on 8/10/2014.
 */
public class Main {

    public static void main(final String[] args) throws Exception {

        final UserManager manager = new UserManager();

        //all
        System.out.println("\tUser Name\t|\tPassword\t");
        for (Object o : manager.getAll()) {
            UsersEntity entity = (UsersEntity) o;
            System.out.println("\t" + entity.getUserName() + "\t" + entity.getPassword());
        }

        //by id
        UsersEntity entity = (UsersEntity) manager.get(1);
        System.out.println("User name=" + entity.getUserName() + "\t Password=" + entity.getPassword());

        //insert
        long result = manager.insert(entity);
        if (result > 0) {
            System.out.println("Insert rows affected:" + result);
        }

        //update
        entity = (UsersEntity) manager.get(2);
        entity.setBirthDay(new Date(2014, 12, 12));
        entity.setGender("Women");
        entity.seteMail("no-reply@women.org");

        if (manager.update(entity)) {
            System.out.println("'" + entity.getUserName() + "' user updated");
        }

        //delete
        entity = (UsersEntity) manager.get(3);
        if (manager.delete(entity.getId())) {
            System.out.println("'" + entity.getUserName() + "' user deleted");
        }

    }
}
