package nl.dennisvdwielen.factory;

import nl.dennisvdwielen.database.MysqlDatabase;
import nl.dennisvdwielen.inferface.IDatabaseHandler;

/**
 * Created by Dennis on 4-5-2014 at 16:43)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.factory
 */
public class DatabaseFactory {

    public enum DatabaseType {Mysql};

    public IDatabaseHandler getDatabaseHandler(DatabaseType type) {

        switch (type) {
            case Mysql:
                return new MysqlDatabase();
            default:
                return null;
        }

    }

}
