package WebServiceCourse.dao;

import WebServiceCourse.dataSet.UsersDataSet;
import WebServiceCourse.executor.Executor;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDAO {
    private Executor executor;
    public UsersDAO(Connection connection){
        executor = new Executor(connection);
    }

    public UsersDataSet get(int id) throws SQLException {
        return executor.execQuery("select * from users where personid=" + id, result -> {
            result.next();
            return new UsersDataSet(result.getInt(1), result.getString(3));
        });
    }

    public void dropTable() throws SQLException {
        String sql = "drop table users";
       executor.execUpdate(sql);
    }

    public void createTable() throws SQLException {
        String sql = "CREATE TABLE users (\n" +
                "    PersonID int,\n" +
                "    LastName varchar(255),\n" +
                "    FirstName varchar(255),\n" +
                "    Address varchar(255),\n" +
                "    City varchar(255) \n" +
                ");";
        executor.execUpdate(sql);
    }

    public void insertUser(int id, String name) throws SQLException {
        executor.execUpdate("insert into users (personid,firstname) values ('" + id + "','" + name + "')");

    }

    public int getUserId(String name) {
        return 1;
    }
}
