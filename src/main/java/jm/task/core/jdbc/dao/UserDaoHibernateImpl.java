package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import lombok.NoArgsConstructor;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class UserDaoHibernateImpl implements UserDao {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS base100500.superheroes (" +
            "  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
            "  name VARCHAR(45) NOT NULL," +
            "  last_name VARCHAR(45) NOT NULL," +
            "  age TINYINT)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS base100500.superheroes";
    private static final String GET_USERS = "FROM User";
    private static final String CLEAN_TABLE = "DELETE FROM base100500.superheroes";

    private static List<User> users = new ArrayList<>();
    private static Session session = Util.getFactory().openSession();


    @Override
    public void createUsersTable() {

        makeConnectionAndDoAction(() -> session.createNativeQuery(CREATE_TABLE).executeUpdate());
    }

    @Override
    public void dropUsersTable() {

        makeConnectionAndDoAction(() -> session.createNativeQuery(DROP_TABLE).executeUpdate());
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        makeConnectionAndDoAction(() -> session.save(new User(name, lastName, age)));
    }

    @Override
    public void removeUserById(long id) {

        makeConnectionAndDoAction(() -> session.remove(session.get(User.class, id)));
    }

    @Override
    public List<User> getAllUsers() {

        makeConnectionAndDoAction(() -> users = session.createQuery(GET_USERS, User.class).getResultList());

        return users;
    }

    @Override
    public void cleanUsersTable() {

        makeConnectionAndDoAction(() -> session.createNativeQuery(CLEAN_TABLE).executeUpdate());
    }

    private static void makeConnectionAndDoAction(Performable action) {

        try {
            session.getTransaction().begin();
            action.execute();
            session.getTransaction().commit();

        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

}

@FunctionalInterface
interface Performable {
    void execute();
}
