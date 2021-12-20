package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;

import java.util.Iterator;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "DROP TABLE IF EXISTS users";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        User removeUser = session.get(User.class, new Long(id));
        session.delete(removeUser);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String sql = "From " + User.class.getSimpleName();
        List users = session.createQuery(sql).list();

        for (Iterator<User> it = users.iterator(); it.hasNext(); ) {
            User user = it.next();
            System.out.println(user.toString());
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        String sql = "delete from users";

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}

