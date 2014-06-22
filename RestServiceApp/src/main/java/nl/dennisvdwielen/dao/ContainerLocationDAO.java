package nl.dennisvdwielen.dao;

import nl.dennisvdwielen.abstracts.ADao;
import nl.dennisvdwielen.dto.ContainerDTO;
import nl.dennisvdwielen.dto.LocationDTO;
import nl.dennisvdwielen.entity.ContainerLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Dennis on 9-6-2014 at 12:12)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dao
 */
@SuppressWarnings("unused")
public class ContainerLocationDAO extends ADao<LocationDTO> {

    @Override
    public ArrayList<LocationDTO> get(int id, LinkedHashMap<String, List<String>> where, List<String> order) {
        ArrayList<LocationDTO> result = dbHandler.multipleSelect(LocationDTO.class, ContainerLocation.class, where, order);
        return result;
    }

    @Override
    public boolean update(LocationDTO dto) {
        LinkedHashMap<String, List<String>> selectWhere = new LinkedHashMap<String, List<String>>();

        List<String> whereValues = new ArrayList<String>();
        whereValues.add(dto.getLocationID().getEquipmentNumber().getEquipmentNumber());
        selectWhere.put("equipmentNumber", whereValues);


        if (!dbHandler.multipleSelect(LocationDTO.class, ContainerLocation.class, selectWhere).isEmpty()) {
            HashMap<String, String> where = new HashMap<String, String>();
            where.put("equipmentNumber", dto.getLocationID().getEquipmentNumber().getEquipmentNumber());

            Boolean locationResult = dbHandler.update(dto.getLocationID(), ContainerLocation.class, where);

            ContainerDTO containerDTO = new ContainerDTO();
            containerDTO.setEquipmentNumber(dto.getLocationID().getEquipmentNumber());

            Boolean containerResult = new ContainerDAO().update(containerDTO);

            if (containerResult && locationResult)
                return true;
        }

        return false;
    }
}
