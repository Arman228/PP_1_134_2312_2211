package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
        //  connection = Util.getConnection();
    }

    public void createUsersTable () {
        String str = "CREATE TABLE IF NOT EXISTS user_table ( ID int not null auto_increment key, name varchar(45), lastname varchar(45), age int) ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(str)) {
            preparedStatement.execute(str);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable () {

        try(Connection connection=Util.getConnection();) {
            //Connection connection=Util.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("drop table  user_table");
            preparedStatement.executeUpdate();
            //stat.executeUpdate(sqlQuery);
//            System.out.println("Таблица Users удалена");
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser (String name, String lastName,byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into user_table" +
                     "(name, lastname, age)" +
                     "values (?,?,?)");
        ) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void removeUserById ( long id){
        try (Connection connection=Util.getConnection();
                PreparedStatement preparedStatement1 = connection.prepareStatement("delete from user_table where id =? ");
        ) {
            preparedStatement1.setLong(1,id);
            preparedStatement1.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




    public List<User> getAllUsers() {
        List<User> users=new ArrayList<>();
        try(Connection connection=Util.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("select * from user_table");
            ResultSet resultSet= preparedStatement.executeQuery();

        )  {
            while (resultSet.next()){
                User user=new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users ;
    }
    public void cleanUsersTable () {

        //String str = "DELETE FROM  user_table";
       // try (PreparedStatement preparedStatement = connection.prepareStatement(str)) {
          //  preparedStatement.execute(str);
           try {
               Connection connection=Util.getConnection();
               PreparedStatement preparedStatement=connection.prepareStatement("truncate table user_table");
               preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}

