package nl.dennisvdwielen.dto;

import nl.dennisvdwielen.pojo.*;

/**
 * Created by Dennis on 31-5-2014 at 02:17)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dto
 */
public class DtoMapper {

    private ContainerDTO dto;
    private Object[] pojos;

    public DtoMapper(ContainerDTO dto, Object... pojos) {
        this.dto = dto;
        this.pojos = pojos;

        mapToDTo();
    }

    private void mapToDTo() {
        for (Object pojo : pojos) {
            if (pojo.getClass().equals(Container.class))
                dto.setContainer((Container) pojo);

            if (pojo.getClass().equals(Packagekind.class))
                dto.setKinds((Packagekind) pojo);

            if (pojo.getClass().equals(ContainerLocation.class))
                dto.setLocation((ContainerLocation) pojo);

            if (pojo.getClass().equals(Shippingname.class))
                dto.setShippingnames((ContainerShippingnames) pojo);
        }
    }

    public ContainerDTO getDto() {
        return dto;
    }
}
