package nl.dennisvdwielen.dao;

import nl.dennisvdwielen.dto.ContainerDTO;
import nl.dennisvdwielen.interfaces.ADao;
import nl.dennisvdwielen.pojo.Container;
import nl.dennisvdwielen.pojo.ContainerKinds;
import nl.dennisvdwielen.pojo.ContainerLocation;
import nl.dennisvdwielen.pojo.ContainerShippingnames;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dennis on 25-4-2014 at 16:37)
 * <p/>
 * This code is part of the ${PROJECT_NAME} project.
 * This class is within package ${PACKAGE_NAME}
 */
@SuppressWarnings("unused")
public class ContainerDAO extends ADao<Container> {

    @Override
    public ArrayList<Container> get(int id, LinkedHashMap<String, List<String>> where, List<String> order) {
        ArrayList<Class> intersection = new ArrayList<Class>();
        ArrayList<Class> extra = new ArrayList<Class>();

        intersection.add(ContainerKinds.class);
        intersection.add(ContainerShippingnames.class);
        extra.add(ContainerLocation.class);

        ArrayList<String> groupConcat = new ArrayList<String>();
        groupConcat.add("kindName");
        groupConcat.add("shippingName");

        ContainerDTO dto = dbHandler.multipleSelect(Container.class, intersection, extra, where, order, "equipmentNumber", groupConcat);

        return null;
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
