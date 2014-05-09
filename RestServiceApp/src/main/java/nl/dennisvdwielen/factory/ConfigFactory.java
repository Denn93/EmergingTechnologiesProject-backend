package nl.dennisvdwielen.factory;

import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dennis on 10-5-2014 at 00:12)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.factory
 */
public class ConfigFactory {

    private static ConfigFactory instance;
    private final String MysqlConnectionString = "jdbc:mysql://localhost/";
    private String connectionString = MysqlConnectionString;
    private Configuration config = null;
    private SQLDialect defaultDatabase = SQLDialect.MYSQL;
    private String databaseName = "emerging";
    private String databaseUser = "root";
    private String databasePass = "";

    private ConfigFactory() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Connection connect = DriverManager.getConnection(getConnectionString(), databaseUser, databasePass);
            config = DSL.using(connect, defaultDatabase).configuration();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static ConfigFactory getInstance() {
        if (instance == null)
            instance = new ConfigFactory();

        return instance;
    }

    private String getConnectionString() {
        return String.format(connectionString + databaseName);
    }

    public void setDefaultDatabase(SQLDialect dialect) {
        defaultDatabase = dialect;

        switch (dialect) {
            case MYSQL:
                connectionString = MysqlConnectionString;
                break;
        }
    }

    public Configuration getConfig() {
        return config;
    }

}
