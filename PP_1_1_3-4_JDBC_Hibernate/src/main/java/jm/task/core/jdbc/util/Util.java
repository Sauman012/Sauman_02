package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static Connection connection = null;
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/db_test?";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "pomela";
    private static final String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static final String DB_SHOW_SQL = "false";
    private static final String DB_CURRENT_SESSION_CONTEXT_CLASS = "thread";
    private static final String DB_HBM2DDL_AUTO = "";

    public static Connection getConnection() {
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DB_DRIVER);
                settings.put(Environment.URL, DB_URL);
                settings.put(Environment.USER, DB_USER);
                settings.put(Environment.PASS, DB_PASSWORD);
                settings.put(Environment.DIALECT, DB_DIALECT);
                settings.put(Environment.SHOW_SQL, DB_SHOW_SQL);
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, DB_CURRENT_SESSION_CONTEXT_CLASS);
                settings.put(Environment.HBM2DDL_AUTO, DB_HBM2DDL_AUTO);

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
