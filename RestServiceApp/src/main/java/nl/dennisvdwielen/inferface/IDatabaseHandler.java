package nl.dennisvdwielen.inferface;

/**
 * Created by Dennis on 4-5-2014 at 16:45)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.inferface
 */
public abstract class IDatabaseHandler {

    protected final String DATABASEHANDLER_NOCONNECIION_ERROR = "Can't connect to the database. A connection has occurred";
    protected final String DATABASEHANDLER_NODRIVER_ERROR = "No database connection driver has been found";
    protected final String DATABASEHANDLER_DEFAULT_ERROR = "An unknown error occurred";

    protected abstract boolean createConnection();

    protected abstract boolean close();
}