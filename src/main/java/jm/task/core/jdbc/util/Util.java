package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/base100500";
    private static final String LOGIN = "root1";
    private static final String PASSWORD = "rootroot";
    private static Connection connection;
    private static SessionFactory factory;


    static {
        try {
            factory = new Configuration().addAnnotatedClass(User.class).buildSessionFactory();

        } catch (Exception e) {
            System.err.println("Connection is failed");
            e.printStackTrace();
        }
    }


    public static SessionFactory getFactory() {
        return factory;
    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
