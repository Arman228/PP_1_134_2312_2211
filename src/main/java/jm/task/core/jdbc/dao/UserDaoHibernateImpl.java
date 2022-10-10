package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory =  Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }

    @Override
   public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS user_table" +
                    "(id INT primary key auto_increment," + "name VARCHAR(45)," +
                    "lastname VARCHAR(45)," +
                    "age TINYINT)").addEntity(User.class);
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();


        }
    }



    @Override
    public void dropUsersTable() {
        try(Session session=sessionFactory.openSession()) {
            Query query = session.createSQLQuery("drop table IF EXISTS user_table");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();



        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User(name,lastName,age);
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e){
            e.printStackTrace();

        }


    }

    @Override
    public void removeUserById(long id) {
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();
            User users = session.get(User.class, id);
            session.delete(users);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public List<User> getAllUsers() {
return  (List<User>) sessionFactory.openSession().createSQLQuery("From User").list();
    }

    @Override
    public void cleanUsersTable() {
        try(Session session=sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("truncate table user_table");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}