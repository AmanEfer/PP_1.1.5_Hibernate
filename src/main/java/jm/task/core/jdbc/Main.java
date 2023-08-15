package jm.task.core.jdbc;



import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Clark", "Kent", (byte) 35);
        userService.saveUser("Bruce", "Wayne", (byte) 40);
        userService.saveUser("Peter", "Parker", (byte) 23);
        userService.saveUser("Super", "Oleg", (byte) 28);

        userService.getAllUsers().forEach(System.out::println);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
