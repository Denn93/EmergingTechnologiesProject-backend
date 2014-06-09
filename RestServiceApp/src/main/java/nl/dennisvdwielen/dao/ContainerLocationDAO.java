package nl.dennisvdwielen.dao;

import nl.dennisvdwielen.abstracts.ADao;
import nl.dennisvdwielen.dto.LocationDTO;
import nl.dennisvdwielen.entity.ContainerLocation;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dennis on 9-6-2014 at 12:12)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dao
 */
public class ContainerLocationDAO extends ADao<LocationDTO> {

    @Override
    public ArrayList<LocationDTO> get(int id, LinkedHashMap<String, List<String>> where, List<String> order) {
        ArrayList<LocationDTO> result = dbHandler.multipleSelect(LocationDTO.class, ContainerLocation.class, where, order);
        return result;
    }

    @Override
    public boolean add(LocationDTO dto) {
        return false;
    }

    @Override
    public boolean update(LocationDTO dto) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}