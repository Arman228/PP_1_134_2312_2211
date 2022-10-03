package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Connection connection=null;
    PreparedStatement preparedStatement=null;
    public UserDaoJDBCImpl() {
        connection=Util.getConnection();

    }

    public void createUsersTable() {
String str="CREATE TABLE new_table( ID int not null auto_increment key, name varchar(45), lastname varchar(45), age int) ";
try(PreparedStatement preparedStatement=connection.prepareStatement(str)) {
   preparedStatement.execute(str);

} catch (SQLException e) {
    throw new RuntimeException(e);
}

    }

    public void dropUsersTable() {
        String sqlQuery = "DROP TABLE IF EXISTS new_table";
        try (Statement stat =connection.createStatement()) {
            stat.executeUpdate(sqlQuery);
//            System.out.println("Таблица Users удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        String str="INSERT INTO myDbTest.Users (name, lastname, age) value (?, ?, ?)";
        try(PreparedStatement prepareStatement = connection.prepareStatement(str)) {
            prepareStatement.setString(1,"Vanya");
            prepareStatement.setString(2,"Serovna");
            prepareStatement.setString(3,String.valueOf(age));
            prepareStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        String sre="DELETE FROM  new_table ID?";
        try(PreparedStatement preparedStatement1=connection.prepareStatement(sre)) {
            preparedStatement1.setString(1,String.valueOf(id));
            preparedStatement1.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        ArrayList<User> list=new ArrayList<>();
        String str="SELECT *FROM new_table";
        try(Statement statement=connection.createStatement()) {
            ResultSet resultSet=statement.executeQuery(str);
            while (resultSet.next()){
                User user=new User();
                user.setId(Long.valueOf(resultSet.getString("id")));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
               user.setAge(Byte.valueOf(resultSet.getString("age")));
               list.add(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void cleanUsersTable() {
        String str="DELETE FROM  new_table";
        try(PreparedStatement preparedStatement=connection.prepareStatement(str)) {
            preparedStatement.execute(str);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    }




