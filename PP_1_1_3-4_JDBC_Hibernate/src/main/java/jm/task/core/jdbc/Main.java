package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        List<User> users = new ArrayList<>();
        users.add(new User("Вася", "Зайцев", (byte) 56));
        users.add(new User("Петя", "Петров", (byte) 34));
        users.add(new User("Сидор", "Сидоров", (byte) 33));
        users.add(new User("Иван", "Иванов", (byte) 24));

        for (User us : users) {
            userService.saveUser(us.getName(), us.getLastName(), (byte) us.getAge());
            System.out.println("User с именем – " + us.getName() + " добавлен в базу данных");
        }

        List<User> usersTable = userService.getAllUsers();
        for (User us : usersTable) {
            System.out.println(us);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
