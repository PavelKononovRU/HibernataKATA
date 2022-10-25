package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        final UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        for (int i = 0; i < 25; i++) {
            userService.saveUser("Peter", "Jackson", (byte) 19);
            userService.saveUser("John", "Stanson", (byte) 20);
            userService.saveUser("Jessica", "Wallstane", (byte) 33);
            userService.saveUser("Julia", "McClein", (byte) 47);
        }

        userService.removeUserById(1);
        userService.getAllUsers();

        userService.cleanUsersTable();
        userService.dropUsersTable();

        time = (System.currentTimeMillis() - time);
        System.out.println("Время выполнения составило " + (time / 1000) + "," + (time % 1000) + " с");
    }
}

