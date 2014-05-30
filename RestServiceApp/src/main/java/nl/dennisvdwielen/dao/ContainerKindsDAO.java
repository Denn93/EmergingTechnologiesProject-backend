package nl.dennisvdwielen.dao;

import nl.dennisvdwielen.interfaces.ADao;
import nl.dennisvdwielen.pojo.ContainerKinds;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dennis on 27-5-2014 at 16:53)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dao
 */
public class ContainerKindsDAO extends ADao<ContainerKinds> {

    @Override
    public ArrayList<ContainerKinds> get(int id, LinkedHashMap<String, List<String>> where, List<String> order) {
        return dbHandler.select(ContainerKinds.class, where, order, "equipmentNumber", "kindName");
    }

    @Override
    public boolean add(ContainerKinds dto) {
        return false;
    }

    @Override
    public boolean update(ContainerKinds dto) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
