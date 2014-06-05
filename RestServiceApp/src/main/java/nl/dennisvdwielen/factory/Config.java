package nl.dennisvdwielen.factory;

/**
 * Created by Dennis on 14-5-2014 at 18:09)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.factory
 */
public class Config {

    private static Config instance;

    private static String dbUser;
    private static String dbPass;
    private static String dbDriver;
    private static String dbConnection;

    private Config() {
        dbUser = "root";
        dbPass = "";
        dbDriver = "com.mysql.jdbc.Driver";
        dbConnection = "jdbc:mysql://localhost/sinlimites";
    }

    public static Config getInstance() {

        if (instance == null)
            instance = new Config();

        return instance;
    }

    public String getDbDriver() {
        return dbDriver;
    }

    public String getConnectionString() {
        return String.format("%s?user=%s&password=%s", dbConnection, dbUser, dbPass);
    }
}
