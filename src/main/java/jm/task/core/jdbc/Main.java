package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;


public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();

        userDaoJDBC.saveUser("Vova", "Alexeev", (byte) 28);
        System.out.println("User с именем – Vova добавлен в базу данных");
        userDaoJDBC.saveUser("Alex", "Stepanov", (byte) 28);
        System.out.println("User с именем – Alex добавлен в базу данных");
        userDaoJDBC.saveUser("Ivan", "Inanov", (byte) 28);
        System.out.println("User с именем – Ivan добавлен в базу данных");
        userDaoJDBC.saveUser("Petr", "Petrov", (byte) 28);
        System.out.println("User с именем – Petr добавлен в базу данных");

        System.out.println(userDaoJDBC.getAllUsers());

        userDaoJDBC.cleanUsersTable();

        userDaoJDBC.dropUsersTable();
    }
}
