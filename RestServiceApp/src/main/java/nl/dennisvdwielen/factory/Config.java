package nl.dennisvdwielen.factory;

/**
 * Created by Dennis on 14-5-2014 at 18:09)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.factory
 */

/**
 * This is a Singleton Config class. This class contains the database info. This class is called once by the database handlere
 */
public class Config {

    private static Config instance;

    private static String dbUser;
    private static String dbPass;
    private static String dbDriver;
    private static String dbConnection;

    /**
     * Private constructor. Standard in a singleton class
     */
    private Config() {
        dbUser = "root";
        dbPass = "";
        dbDriver = "com.mysql.jdbc.Driver";
        dbConnection = "jdbc:mysql://localhost/sinlimites";
    }

    /**
     * Static getInstance method. Creates or returns the current object
     *
     * @return
     */
    public static Config getInstance() {

        if (instance == null)
            instance = new Config();

        return instance;
    }

    /**
     * Getter for DbDriver
     * @return dbDriver
     */
    public String getDbDriver() {
        return dbDriver;
    }

    /**
     * Getter for a combined ConnectionString
     * @return Formatted connection string
     */
    public String getConnectionString() {
        return String.format("%s?user=%s&password=%s", dbConnection, dbUser, dbPass);
    }
}
