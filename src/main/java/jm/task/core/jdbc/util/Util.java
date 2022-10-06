package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;



public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/bussines";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    static Connection  conn;




    public static Connection getConnection() {
        try {
            conn = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            if (!conn.isClosed()) {
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


}