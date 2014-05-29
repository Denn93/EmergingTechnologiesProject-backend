package nl.dennisvdwielen.dao;

import nl.dennisvdwielen.interfaces.ADao;
import nl.dennisvdwielen.pojo.Container;
import nl.dennisvdwielen.pojo.ContainerKinds;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dennis on 25-4-2014 at 16:37)
 * <p/>
 * This code is part of the ${PROJECT_NAME} project.
 * This class is within package ${PACKAGE_NAME}
 */
public class ContainerDAO extends ADao<Container> {

    @Override
    public ArrayList<Container> get(int id, LinkedHashMap<String, List<String>> where, List<String> order) {
        ArrayList<ContainerKinds> cc = dbHandler.select(ContainerKinds.class, where, order);

        return dbHandler.select(Container.class, where, order);
    }

    @Override
    public boolean add(Container student) {
        return false;
    }

    @Override
    public boolean update(Container student) {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
