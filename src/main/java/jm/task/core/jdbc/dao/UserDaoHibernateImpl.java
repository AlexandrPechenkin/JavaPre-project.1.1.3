package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;

import java.util.List;


public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Transaction transaction;
        Query query;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                    "age TINYINT NOT NULL)";

            query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception ignored) {
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction;
        Query query;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS users";

            query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception ignored) {
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        User user;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();

            user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);

            session.save(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        User removeUser;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            session.getTransaction().begin();
            removeUser = session.get(User.class, id);
            session.delete(removeUser);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        Transaction transaction = null;
        String sql = "From " + User.class.getSimpleName();
        Session session = null;

        try {
            session = sessionFactory.openSession();
            users = session.createQuery(sql).list();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
            transaction.rollback();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        Query query;
        String sql = "truncate table users";
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
            transaction.rollback();
        } finally {
            session.close();
        }
    }
}

