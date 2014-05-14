package nl.dennisvdwielen.inferface;

import nl.dennisvdwielen.factory.Config;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Dennis on 4-5-2014 at 16:45)
 *
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.inferface
 */
public abstract class IDatabaseHandler {

    //Default error messages that can be implemented by database implementation
    protected final String DATABASEHANDLER_NOCONNECIION_ERROR = "Can't connect to the database. A connection has occurred";
    protected final String DATABASEHANDLER_NODRIVER_ERROR = "No database connection driver has been found";

    protected Config config;

    protected IDatabaseHandler(){
        config = Config.getInstance();

        createConnection();
    }


    public final ResultSet select(String table){
        return select(table, "");
    }

    public final ResultSet select(String table, String where) {
        return select(table, where, "");
    }

    public abstract ResultSet select(String table, String where, String options);

    protected abstract boolean createConnection();

    public abstract Integer update();
    public abstract Integer delete();
    public abstract ResultSet rawSelect(String query);
    protected abstract void close();
}
