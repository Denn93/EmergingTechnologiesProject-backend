package nl.dennisvdwielen.database;

import nl.dennisvdwielen.inferface.IDatabaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dennis on 1-5-2014 at 22:11)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.database
 */

public class MysqlDatabase extends IDatabaseHandler {

    private Connection connect;

    public MysqlDatabase() {
        connect = null;

        if (!createConnection())
            System.out.println(DATABASEHANDLER_NOCONNECIION_ERROR);
    }

    @Override
    protected final boolean createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            connect = DriverManager.getConnection("jdbc:mysql://localhost/backend", "root", "");

        } catch (ClassNotFoundException e) {
            System.out.println(DATABASEHANDLER_NODRIVER_ERROR);
            return false;
        } catch (SQLException e) {
            System.out.println(DATABASEHANDLER_NOCONNECIION_ERROR);
            return false;
        } catch (InstantiationException e) {
            System.out.println(DATABASEHANDLER_DEFAULT_ERROR);
            return false;
        } catch (IllegalAccessException e) {
            System.out.println(DATABASEHANDLER_DEFAULT_ERROR);
            return false;
        }

        return true;
    }

    @Override
    protected final boolean close() {
        if (connect != null)
            try {
                connect.close();
            } catch (SQLException e) {
                //TODO Error Message
                return false;
            }

        return true;
    }
}
