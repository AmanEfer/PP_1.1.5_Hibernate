package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final  String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS superheroes (" +
            "  id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT," +
            "  name VARCHAR(45) NOT NULL," +
            "  last_name VARCHAR(45) NOT NULL," +
            "  age TINYINT)";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS superheroes";
//    private static final String REMOVE_USER = "DELETE FROM base100500.superheroes WHERE id=?";
    private static final String GET_USERS = "FROM User";
    private static final String CLEAN_TABLE = "TRUNCATE TABLE superheroes";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getFactory().openSession()) {
            try {
                session.getTransaction().begin();
                session.createNativeQuery(CREATE_TABLE).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getFactory().openSession()) {
            try {
                session.getTransaction().begin();
                session.createNativeQuery(DROP_TABLE).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getFactory().openSession()) {
            try {
                session.getTransaction().begin();
                session.save(new User(name, lastName, age));
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getFactory().openSession()) {
            try {
                session.getTransaction().begin();
                session.remove(session.get(User.class, id));
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Session session = Util.getFactory().openSession()) {
            try {
                session.getTransaction().begin();
                users =  session.createQuery(GET_USERS, User.class).getResultList();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }

        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getFactory().openSession()) {
            try {
                session.getTransaction().begin();
                session.createNativeQuery(CLEAN_TABLE).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        }
    }
}
