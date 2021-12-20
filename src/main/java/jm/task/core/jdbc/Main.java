package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;



public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Vova", "Alexeev", (byte) 28);
        System.out.println("User с именем – Vova добавлен в базу данных");
        userService.saveUser("Alex", "Stepanov", (byte) 28);
        System.out.println("User с именем – Alex добавлен в базу данных");
        userService.saveUser("Ivan", "Inanov", (byte) 28);
        System.out.println("User с именем – Ivan добавлен в базу данных");
        userService.saveUser("Petr", "Petrov", (byte) 28);
        System.out.println("User с именем – Petr добавлен в базу данных");

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}











