package nl.dennisvdwielen.interfaces;

import nl.dennisvdwielen.factory.Config;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import static nl.dennisvdwielen.factory.Config.getInstance;

/**
 * Created by Dennis on 4-5-2014 at 16:45)
 *
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.interfaces
 */
public abstract class ADatabaseHandler {

    //Default error messages that can be implemented by database implementation
    protected final String DATABASEHANDLER_NOCONNECIION_ERROR = "Can't connect to the database. A connection has occurred";
    protected final String DATABASEHANDLER_NODRIVER_ERROR = "No database connection driver has been found";

    protected Config config;

    protected ADatabaseHandler(){
        config = getInstance();

        createConnection();
    }


    public final <T> ArrayList<T> select(Class<T> pojo) {
        return select(pojo, "");
    }

    public final <T> ArrayList<T> select(Class<T> pojo, String options) {
        return select(pojo, options, null);
    }

    public abstract <T> ArrayList<T> select(Class<T> pojo, String options, HashMap<String, String> where);

    protected abstract boolean createConnection();

    public abstract Integer update();
    public abstract Integer delete();
    public abstract ResultSet rawSelect(String query);
    protected abstract void close();
}
