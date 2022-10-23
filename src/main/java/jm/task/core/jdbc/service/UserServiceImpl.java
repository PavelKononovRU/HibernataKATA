package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    final private static UserDao userHiberNateDao = new UserDaoHibernateImpl();

    @Override
    public void createUsersTable() {
        userHiberNateDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userHiberNateDao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        userHiberNateDao.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        userHiberNateDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userHiberNateDao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        userHiberNateDao.cleanUsersTable();
    }
}
