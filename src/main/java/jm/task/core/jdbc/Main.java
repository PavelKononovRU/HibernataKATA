package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Павел", "Павлов", (byte) 20);
        userService.saveUser("Сергей", "Сергеев", (byte) 25);
        userService.saveUser("Кирилл", "Киреев", (byte) 31);
        userService.saveUser("Стас", "Стасов", (byte) 38);
        userService.removeUserById(1);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

        time = (System.currentTimeMillis() - time);
        System.out.println("Время выполнения составило " + (time / 1000) + "," + (time % 1000) + " с");
    }
}

