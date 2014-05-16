package nl.dennisvdwielen.dao;

import nl.dennisvdwielen.interfaces.ADao;
import nl.dennisvdwielen.pojo.Container;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dennis on 25-4-2014 at 16:37)
 *
 * This code is part of the ${PROJECT_NAME} project.
 * This class is within package ${PACKAGE_NAME}
 */
public class ContainerDAO extends ADao<Container> {

    @Override
    public ArrayList<Container> get(int id, Option option, HashMap<String, String> data) {

        ArrayList<Container> result = new ArrayList<Container>();

        switch (option) {
            case Filter:
                result = handler.select(Container.class, "", data);
                break;
            case None:
                result = handler.select(Container.class);
        }

        return result;
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
