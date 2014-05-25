package nl.dennisvdwielen.interfaces;

import nl.dennisvdwielen.factory.DatabaseFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dennis on 26-4-2014.
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.interfaces
 */
public abstract class ADao<Dto> {

    protected ADatabaseHandler handler;

    protected ADao() {
        handler = new DatabaseFactory().getDatabaseHandler(DatabaseFactory.DatabaseType.Mysql);
    }

    public final ArrayList<Dto> get() {
        return get(-1, null, null);
    }

    ;

    public final ArrayList<Dto> get(int id) {
        return get(id, null, null);
    }

    ;

    public final ArrayList<Dto> get(int id, List<String> order) {
        return get(id, null, order);
    }

    ;

    public final ArrayList<Dto> get(int id, LinkedHashMap<String, List<String>> where) {
        return get(id, where, null);
    }

    ;

    public abstract ArrayList<Dto> get(int id, LinkedHashMap<String, List<String>> where, List<String> order);
    public abstract boolean add(Dto dto);
    public abstract boolean update(Dto dto);
    public abstract boolean delete();

    public static enum Option {Where, Order, Both, None}

}
