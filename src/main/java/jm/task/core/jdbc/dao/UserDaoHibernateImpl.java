package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        String SQL = "CREATE TABLE users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(20) NOT NULL," +
                "lastName VARCHAR(40) NOT NULL," +
                "age INT(3) NOT NULL)";
        Transaction tx;

        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.createSQLQuery(SQL)
                    .addEntity(User.class).executeUpdate();

            tx.commit();
        } catch (Exception ignored) {}
    }

    @Override
    public void dropUsersTable() {
        String SQL = "DROP TABLE users";
        Transaction tx;
        try (Session sess = Util.getInstance().getSessionFactory().openSession()) {
            tx = sess.beginTransaction();

            sess.createSQLQuery(SQL)
                    .addEntity(User.class).executeUpdate();

            tx.commit();
        } catch (Exception ignored) {}

        System.out.println();
        System.out.println("Таблица удалена");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();
            User user = new User(name, lastName, age);

            session.save(user);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(); System.out.println("Пользователь " + name.toUpperCase() + " сохранен");
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            User user = session.get(User.class, id);
            session.remove(user);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();   System.out.println("Пользователь удален");
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> people = new ArrayList<>();
        final String SQL = "select * FROM users";
        try (Session sess = Util.getInstance().getSessionFactory().openSession()) {
            Transaction tx = sess.beginTransaction();
            people = (List<User>) sess.createSQLQuery(SQL).list();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //people.forEach(System.out::println);
        System.out.println();   System.out.println("***Все пользователи выведены на экран***");
        System.out.println("list size = " + people.size());
        return people;
    }

    @Override
    public void cleanUsersTable() {
        String SQL = "TRUNCATE users";
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();

            session.createSQLQuery(SQL)
                    .addEntity(User.class).executeUpdate();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();   System.out.println("***Таблица очищена***");
    }
}
