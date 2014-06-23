package nl.dennisvdwielen.dao;

import nl.dennisvdwielen.abstracts.ADao;
import nl.dennisvdwielen.dto.HandlingDTO;
import nl.dennisvdwielen.entity.Handling;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dennis on 17-6-2014 at 22:19)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dao
 */
@SuppressWarnings("unused")
public class HandlingDAO extends ADao<HandlingDTO> {

    /**
     * Abstract Get method for retrieving data. Is part of a series of overloading methods. This method has to be overridden
     *
     * @param id    et with ID
     * @param where Get data based on the where clause
     * @param order Get data based on order
     * @return ArrayList of Type generic Pojo
     */
    @Override
    public ArrayList<HandlingDTO> get(int id, LinkedHashMap<String, List<String>> where, List<String> order) {
        return dbHandler.multipleSelect(HandlingDTO.class, Handling.class);
    }

    /**
     * This method is used for updating the data in the database
     *
     * @param dto The dto with the new values
     * @return True or False. Based on if update was successful
     */
    @Override
    public boolean update(HandlingDTO dto) {
        return false;
    }

}
