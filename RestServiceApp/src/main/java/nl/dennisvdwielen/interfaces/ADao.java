package nl.dennisvdwielen.interfaces;

import nl.dennisvdwielen.factory.DatabaseFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dennis on 26-4-2014.
 */
public abstract class ADao<Dto> {

    protected ADatabaseHandler handler;
    ;

    protected ADao() {
        handler = new DatabaseFactory().getDatabaseHandler(DatabaseFactory.DatabaseType.Mysql);
    }

    public ArrayList<Dto> get(int id) {
        return get(id, Option.None);
    }

    public ArrayList<Dto> get(int id, Option option) {
        return get(id, option, null);
    }

    public abstract ArrayList<Dto> get(int id, Option option, LinkedHashMap<String, List<String>> data);

    public abstract boolean add(Dto dto);

    public abstract boolean update(Dto dto);

    public abstract boolean delete();

    public static enum Option {Filter, None}

}
