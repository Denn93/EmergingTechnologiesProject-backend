package nl.dennisvdwielen.factory;

import nl.dennisvdwielen.database.MysqlDatabase;
import nl.dennisvdwielen.interfaces.ADatabaseHandler;

/**
 * Created by Dennis on 4-5-2014 at 16:43)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.factory
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

    public enum DatabaseType {Mysql}

}
