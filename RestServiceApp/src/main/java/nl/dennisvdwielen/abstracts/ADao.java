package nl.dennisvdwielen.abstracts;

import nl.dennisvdwielen.factory.DatabaseFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dennis on 26-4-2014.
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.abstracts
 */
public abstract class ADao<Pojo> {

    protected final ADatabaseHandler dbHandler;

    protected ADao() {
        dbHandler = new DatabaseFactory().getDatabaseHandler(DatabaseFactory.DatabaseType.Mysql);
    }

    public final ArrayList<Pojo> get() {
        return get(-1, null, null);
    }


    public final ArrayList<Pojo> get(int id) {
        return get(id, null, null);
    }

    public final ArrayList<Pojo> get(int id, List<String> order) {
        return get(id, null, order);
    }


    public final ArrayList<Pojo> get(int id, LinkedHashMap<String, List<String>> where) {
        return get(id, where, null);
    }

    public abstract ArrayList<Pojo> get(int id, LinkedHashMap<String, List<String>> where, List<String> order);

    public abstract boolean add(Pojo dto);

    public abstract boolean update(Pojo dto);

    public abstract boolean delete();
}
