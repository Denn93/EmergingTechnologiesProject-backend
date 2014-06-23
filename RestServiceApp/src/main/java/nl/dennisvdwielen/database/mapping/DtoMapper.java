package nl.dennisvdwielen.database.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by Dennis on 31-5-2014 at 02:17)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.dto
 */

/**
 * This class maps the results from the recordMapper to a DTO. A Dto is a transferable object to the client. Thus is
 * has ben encapsulated to this format
 */
public class DtoMapper {

    private Object dto;
    private ArrayList<ArrayList<Object>> data;
    private ArrayList<Object> tables;
    private ArrayList<String> mappedFields;
    private ArrayList<Object> result;

    /**
     * Constructor loads a new instance of the dto object ands sets all fields. Starts the building process
     *
     * @param dto    The result to where mapping should point
     * @param data   The Record mapping results
     * @param tables The tables that are used within the mapping sequence
     */
    public DtoMapper(Class dto, ArrayList<ArrayList<Object>> data, ArrayList<Object> tables) {
        try {
            this.dto = dto.newInstance();

            this.data = data;
            this.tables = tables;
            this.mappedFields = new ArrayList<String>();
            this.result = new ArrayList<Object>();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        mapToDTo();
    }

    /**
     * An enormous method to map the results from the recordMapper to a dto. It takes into account if the field correspond.
     * If the field already has been mapped. To save data consumption. If value is a other entity it loops again to also
     * map these results.
     */
    private void mapToDTo() {
        for (ArrayList record : data) {
            mappedFields = new ArrayList<String>();
            try {
                dto = dto.getClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            for (Object recordPart : record) {
                Field[] fields = recordPart.getClass().getDeclaredFields();
                for (Field recordPartField : fields) {
                    if (mappedFields.contains(recordPartField.getName()))
                        continue;

                    for (Method method : dto.getClass().getMethods()) {

                        if (!method.getName().startsWith("set"))
                            continue;

                        if (recordPartField.getName().equalsIgnoreCase(method.getName().substring(3, method.getName().length()))) {
                            try {
                                Class fieldType = method.getParameterTypes()[0];
                                Class cls = Class.forName(fieldType.getName());

                                if (cls.equals(recordPart.getClass())) {
                                    method.invoke(dto, cls.cast(recordPart));
                                    mappedFields.add(recordPartField.getName());
                                } else {
                                    String tempFieldName = recordPartField.getName().substring(0, 1).toUpperCase() + recordPartField.getName().substring(1, recordPartField.getName().length());
                                    Object obj = recordPart.getClass().getMethod("get" + tempFieldName).invoke(recordPart);
                                    method.invoke(dto, cls.cast(obj));
                                    mappedFields.add(recordPartField.getName());
                                }
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchMethodException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }

            result.add(dto);
        }
    }

    /**
     * Getter Dto
     * @return Mapped Dto results
     */
    public ArrayList<Object> getDto() {
        return result;
    }
}