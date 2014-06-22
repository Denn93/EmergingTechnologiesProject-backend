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
public abstract class ADao<DTO> {

    protected final ADatabaseHandler dbHandler;

    /**
     * Global constructor to set the config values and set the database handler
     */
    protected ADao() {
        dbHandler = new DatabaseFactory().getDatabaseHandler(DatabaseFactory.DatabaseType.Mysql);
    }

    /**
     * Final Get method for retrieving data. Is part of a series of overloading methods
     *
     * @return ArrayList of Type generic Pojo
     */
    public final ArrayList<DTO> get() {
        return get(-1);
    }

    /**
     * Final Get method for retrieving data. Is part of a series of overloading methods
     *
     * @param id Get with ID
     * @return ArrayList of Type generic Pojo
     */
    public final ArrayList<DTO> get(int id) {
        return get(id, null, null);
    }

    /**
     * Final Get method for retrieving data. Is part of a series of overloading methods
     *
     * @param id    et with ID
     * @param order Get data based on order
     * @return ArrayList of Type generic Pojo
     */
    public final ArrayList<DTO> get(int id, List<String> order) {
        return get(id, null, order);
    }

    /**
     * Final Get method for retrieving data. Is part of a series of overloading methods
     *
     * @param id    et with ID
     * @param where Get data based on the where clause
     * @return ArrayList of Type generic Pojo
     */
    public final ArrayList<DTO> get(int id, LinkedHashMap<String, List<String>> where) {
        return get(id, where, null);
    }

    /**
     * Abstract Get method for retrieving data. Is part of a series of overloading methods. This method has to be overridden
     *
     * @param id    et with ID
     * @param where Get data based on the where clause
     * @param order Get data based on order
     * @return ArrayList of Type generic Pojo
     */
    public abstract ArrayList<DTO> get(int id, LinkedHashMap<String, List<String>> where, List<String> order);

    /**
     * This method is used for updating the data in the database
     *
     * @param dto The dto with the new values
     * @return True or False. Based on if update was successful
     */
    public abstract boolean update(DTO dto);
}
