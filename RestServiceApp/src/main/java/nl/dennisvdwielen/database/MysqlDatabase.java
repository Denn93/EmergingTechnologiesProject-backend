package nl.dennisvdwielen.database;

import nl.dennisvdwielen.inferface.IDatabaseHandler;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.*;

import static jooq.generated.Tables.USER;

/**
 * Created by Dennis on 1-5-2014 at 22:11)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.database
 */

public class MysqlDatabase extends IDatabaseHandler {

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public MysqlDatabase() {

        if (!createConnection())
            System.out.println(DATABASEHANDLER_NOCONNECIION_ERROR);
    }

    @Override
    public boolean createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connect = DriverManager.getConnection("jdbc:mysql://localhost/backend", "root", "");

            DSLContext create = DSL.using(connect, SQLDialect.MYSQL);
            Result<Record> result = create.select().from(USER).fetch();

            for (Record r : result) {
                Integer id = r.getValue(USER.ID);
                String firstName = r.getValue(USER.NAME);
                String lastName = r.getValue(USER.COUNTRY);

                System.out.println("ID: " + id + " first name: " + firstName + " last name: " + lastName);
            }

        } catch (ClassNotFoundException e) {
            System.out.println(DATABASEHANDLER_NODRIVER_ERROR);
            return false;
        } catch (SQLException e) {
            System.out.println(DATABASEHANDLER_NOCONNECIION_ERROR);
            return false;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public ResultSet select(String table, String where, String options) {
        ResultSet result = null;

        //statement = connect.createStatement();
        //resultSet = statement.executeQuery("SELECT * FROM testing");



        return result;
    }

    @Override
    public ResultSet rawSelect(String query) {
        return null;
    }

    @Override
    public Integer update() {
        return -1;
    }

    @Override
    public Integer delete() {
        return -1;
    }

    public ResultSet readDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost/test?"
                    + "user=root&password=");

            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM testing");

            while(resultSet.next()){
                String city = resultSet.getString("city");
                String name = resultSet.getString("name");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (SQLException e) {
            System.out.println("Can't connect to database");
        } finally {
            close();
        }

        return resultSet;
    }

    private void close() {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        if (connect != null)
            try {
                connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
}
