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
    /**
     * JDBC DRIVER and database URL
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL_DATABASE = "jdbc:mysql://localhost:3306/MyDB";

    private static final String SQL_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    /**
     * USER  and  PASSWORD
     */
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootroot";
    private volatile static Util instance;
    //private static Connection connection;
    private static SessionFactory sessionFactory;

    public Util() {
    }

    public static Util getInstance() {
        if (instance == null) {
            synchronized (Util.class) {
                if (instance == null)
                    instance = new Util();
            }
        }

        System.out.println("====================================");
        System.out.println("    Класс \"Util\" инстанцирован.   ");
        System.out.println("====================================");
        return instance;
    }

/*    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Ошибка подключения к БД");
        }

        //Получаем соединение
        try {
            connection = DriverManager.getConnection(URL_DATABASE, USERNAME, PASSWORD);
            System.out.println("Соединение установлено.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }*/

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties config = new Properties();
                config.put(Environment.DRIVER, DRIVER);
                config.put(Environment.URL, URL_DATABASE);
                config.put(Environment.USER, USERNAME);
                config.put(Environment.PASS, PASSWORD);
                config.put(Environment.DIALECT, SQL_DIALECT);

                config.put(Environment.SHOW_SQL, "true");
                config.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                config.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(config);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

                System.out.println("#############################################");
                System.out.println("             Фабрика  создана                ");
                System.out.println("#############################################");
            } catch (Exception e) {
                System.out.println("Ошибка");
            }
        }
        return sessionFactory;
    }
}

