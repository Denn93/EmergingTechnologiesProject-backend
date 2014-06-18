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
public class HandlingDAO extends ADao<HandlingDTO> {
    @Override
    public ArrayList<HandlingDTO> get(int id, LinkedHashMap<String, List<String>> where, List<String> order) {
        return dbHandler.multipleSelect(HandlingDTO.class, Handling.class);
    }

    @Override
    public boolean add(HandlingDTO dto) {
        return false;
    }

    @Override
    public boolean update(HandlingDTO dto) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
