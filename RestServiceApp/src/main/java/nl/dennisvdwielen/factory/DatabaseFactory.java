package nl.dennisvdwielen.factory;

import nl.dennisvdwielen.abstracts.ADatabaseHandler;
import nl.dennisvdwielen.database.MysqlDatabase;

/**
 * Created by Dennis on 4-5-2014 at 16:43)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.factory
 */

/**
 * This class creates the database class depending which type of database is given. Returns a Abstract Databasehandler
 */
public class DatabaseFactory {

    public ADatabaseHandler getDatabaseHandler(DatabaseType type) {

        switch (type) {
            case Mysql:
                return new MysqlDatabase();
            default:
                return null;
        }

    }

    /**
     * A Global Database type enum. Used to select a database type
     */
    public enum DatabaseType {Mysql}

}
