package nl.dennisvdwielen.dao;

import nl.dennisvdwielen.abstracts.ADao;
import nl.dennisvdwielen.dto.ContainerDTO;
import nl.dennisvdwielen.entity.Container;
import nl.dennisvdwielen.entity.ContainerKinds;
import nl.dennisvdwielen.entity.ContainerLocation;
import nl.dennisvdwielen.entity.ContainerShippingnames;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dennis on 25-4-2014 at 16:37)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dao
 */
@SuppressWarnings("unused")
public class ContainerDAO extends ADao<ContainerDTO> {

    /**
     * Abstract Get method for retrieving data. Is part of a series of overloading methods. This method has to be overridden
     *
     * @param id    et with ID
     * @param where Get data based on the where clause
     * @param order Get data based on order
     * @return ArrayList of Type generic Pojo
     */
    @Override
    public ArrayList<ContainerDTO> get(int id, LinkedHashMap<String, List<String>> where, List<String> order) {
        ArrayList<Class> intersection = new ArrayList<Class>();
        ArrayList<Class> extra = new ArrayList<Class>();

        intersection.add(ContainerKinds.class);
        intersection.add(ContainerShippingnames.class);
        extra.add(ContainerLocation.class);

        ArrayList<String> groupConcat = new ArrayList<String>();
        groupConcat.add("kindName");
        groupConcat.add("shippingName");

        ArrayList<ContainerDTO> result = dbHandler.multipleSelect(ContainerDTO.class, Container.class, intersection, extra, where, order, "equipmentNumber", groupConcat);

        return result;
    }

    /**
     * This method is used for updating the data in the database
     *
     * @param dto The dto with the new values
     * @return True or False. Based on if update was successful
     */
    @Override
    public boolean update(ContainerDTO dto) {
        HashMap<String, String> where = new HashMap<String, String>();
        where.put("equipmentNumber", dto.getEquipmentNumber().getEquipmentNumber());
        return dbHandler.update(dto.getEquipmentNumber(), Container.class, where);
    }
}
