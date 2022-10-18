package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(20) NOT NULL," +
                "lastName VARCHAR(40) NOT NULL," +
                "age INT(3) NOT NULL)";

        try (PreparedStatement stUpdate = Util.getInstance().getConnection().prepareStatement(SQL)) {
            stUpdate.executeUpdate();
        } catch (SQLException ignored) {
        }
        System.out.println("Таблица создана");
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE users";
        try (Statement stQry = Util.getInstance().getConnection().createStatement()) {
            stQry.executeUpdate(SQL);
        } catch (SQLException ignored) {
        }
        System.out.println("!!!ТАБЛИЦА УДАЛЕНА!!!");
    }

    public void saveUser(String name, String lastName, byte age) {
        String SQL = "INSERT INTO users (name, lastName, age)" +
                "VALUES (?, ?, ?)";
        try (PreparedStatement  stUpdate = Util.getInstance().getConnection().prepareStatement(SQL)) {
            stUpdate.setString(1, name);
            stUpdate.setString(2, lastName);
            stUpdate.setInt(3, age);
            stUpdate.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка сохранения User's в базу данных");
        }
        System.out.println("User с именем " + name + " добавлен в базу данных");
        System.out.println("=================================================");
    }

    public void removeUserById(long id) {
        String SQL = "DELETE FROM users WHERE id=?";
        try (PreparedStatement stUpdate = Util.getInstance().getConnection().prepareStatement(SQL)) {
            stUpdate.setLong(1, id);
            stUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Пользователь с id=" + id + " успешно удален из БД");
        System.out.println("=================================");
    }

    public List<User> getAllUsers() {
        List<User> people = new ArrayList<>();
        String SQL = "SELECT * FROM users";
        try (Statement stQry = Util.getInstance().getConnection().createStatement()) {
            ResultSet rs = stQry.executeQuery(SQL);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));

                people.add(user);
            }

            for (User x : people) {
                System.out.println(x.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Данные таблицы полностью выведены на экран.");
        System.out.println("===========================================");
        return people;
    }

    public void cleanUsersTable() {
        String SQL = "TRUNCATE TABLE  users";
        try (Statement stQry = Util.getInstance().getConnection().createStatement()) {
            stQry.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        } System.out.println("Таблица очищена");
    }
}

