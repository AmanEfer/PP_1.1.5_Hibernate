package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/base100500";
    private static final String LOGIN = "root1";
    private static final String PASSWORD = "rootroot";
    private static Connection connection;


    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
