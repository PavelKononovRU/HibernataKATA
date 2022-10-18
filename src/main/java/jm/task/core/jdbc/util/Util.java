package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    /**
     * JDBC DRIVER and database URL
     */
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL_DATABASE = "jdbc:mysql://localhost:3306/MyDB";
    /**
     * USER  and  PASSWORD
     */
    private static final String USERNAME = "root";
    private static final String PASSWORD = "rootroot";
    private volatile static Util instance;
    private static Connection connection;


    public Util() {
    }

    public static Util getInstance() {
        if (instance == null) {
            synchronized (Util.class) {
                if (instance == null)
                    instance = new Util();
            }
            System.out.println("====================================");
            System.out.println("Класс \"Util\" инстанцирован.");
            System.out.println("====================================");
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
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
    }
}

