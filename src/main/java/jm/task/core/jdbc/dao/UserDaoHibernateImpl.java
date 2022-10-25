package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(20) NOT NULL," +
                "lastName VARCHAR(40) NOT NULL," +
                "age INT(3) NOT NULL)";

        Transaction tx;
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.createSQLQuery(SQL)
                    .addEntity(User.class).executeUpdate();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Таблица создана");
            System.out.println();
    }

    @Override
    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS users";
        Transaction tx;
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.createSQLQuery(query)
                    .addEntity(User.class).executeUpdate();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Таблица удалена");
            System.out.println();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction tx;
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {

            User user = new User(name, lastName, age);
            tx = session.beginTransaction();

            session.save(user);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Пользователь " + name.toUpperCase() + " сохранен");
            System.out.println();
    }

    @Override
    public void removeUserById(long id) {
        Transaction tx;
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.createQuery("delete User where id = :id")
                    .setParameter("id", id).executeUpdate();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Пользователь удален");
            System.out.println();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> people = null;
        Transaction tx;
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {

            tx = session.beginTransaction();
            people = session.createQuery("from jm.task.core.jdbc.model.User").getResultList();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert people != null;
            people.forEach(System.out::println);

        System.out.println("Все пользователи выведены на экран");
            System.out.println();

        return people;
    }

    @Override
    public void cleanUsersTable() {
        String SQL = "TRUNCATE users";
        Transaction tx;
        try (Session session = Util.getInstance().getSessionFactory().openSession()) {
            tx = session.beginTransaction();

            session.createSQLQuery(SQL)
                    .addEntity(User.class).executeUpdate();

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("***Таблица очищена***");
            System.out.println();
    }
}
