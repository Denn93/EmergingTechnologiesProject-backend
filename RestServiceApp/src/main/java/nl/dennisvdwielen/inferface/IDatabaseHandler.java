package nl.dennisvdwielen.inferface;

import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Dennis on 4-5-2014 at 16:45)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.inferface
 */
public abstract class IDatabaseHandler {

    protected final String DATABASEHANDLER_NOCONNECIION_ERROR = "Can't connect to the database. A connection has occurred";
    protected final String DATABASEHANDLER_NODRIVER_ERROR = "No database connection driver has been found";

    protected String connectionString;
    protected Statement statement;

    public final ResultSet select(String table) {
        return select(table, "");
    }

    public final ResultSet select(String table, String where) {
        return select(table, where, "");
    }

    public abstract ResultSet select(String table, String where, String options);

    public abstract boolean createConnection();

    public abstract Integer update();

    public abstract Integer delete();

    public abstract ResultSet rawSelect(String query);
}
