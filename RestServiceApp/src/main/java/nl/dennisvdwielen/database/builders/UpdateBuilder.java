package nl.dennisvdwielen.database.builders;

import nl.dennisvdwielen.database.mapping.PojoReflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Dennis on 11-6-2014 at 15:33)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.database.builders
 */

/**
 * This class created the update query that can be performed on the database. It makes its query with a couple of
 * important fields.
 */
public class UpdateBuilder {

    private final String UpdateTemplate = "UPDATE %s SET %s WHERE %s";
    private PojoReflection headTable;
    private Object obj;
    private String where;
    private String setString;
    private String whereString;
    private String updateString;
    private int addedFields;
    private ArrayList<Class> defaultTypes = new ArrayList<Class>();

    /**
     * Constructor that sets all fields and defaultTypes of non Entity objects. And starts the build sequence
     *
     * @param obj       The object that has to be updated.
     * @param className The className that has be the same type as the object
     * @param <T>       Checks that obj en className are from the same class
     */
    public <T> UpdateBuilder(T obj, Class<T> className) {
        this.headTable = new PojoReflection(className);
        this.obj = obj;
        this.where = "";

        this.setString = "";
        this.whereString = "";
        this.addedFields = 0;


        defaultTypes.add(Integer.class);
        defaultTypes.add(Double.class);
        defaultTypes.add(String.class);
        defaultTypes.add(Long.class);
        defaultTypes.add(Timestamp.class);
        defaultTypes.add(int.class);
        defaultTypes.add(double.class);
        defaultTypes.add(long.class);
        defaultTypes.add(float.class);
        defaultTypes.add(Float.class);

        updateString = createUpdate();
    }

    /**
     * This method creates the update query. It loops multiple times if type of the value is a object. If it is a
     * default type it directly sets the value
     * @return The final update query.
     */
    private String createUpdate() {
        for (Method method : obj.getClass().getMethods()) {
            if (!method.getName().startsWith("get"))
                continue;

            if (defaultTypes.contains(method.getReturnType())) {
                Object returnData = invokeMethod(method, obj);
                setValue(method, returnData);
            } else
            {
                try {
                    Object returnData = invokeMethod(method, obj);
                    if (returnData != null) {
                        for (Field headTableField : obj.getClass().getDeclaredFields()) {
                            for (Field innerTableField : returnData.getClass().getDeclaredFields()) {
                                if (headTableField.getName().equalsIgnoreCase(innerTableField.getName())) {
                                    String tempFieldName = innerTableField.getName().substring(0, 1).toUpperCase() + innerTableField.getName().substring(1, innerTableField.getName().length());

                                    Method innerMethod = returnData.getClass().getMethod("get" + tempFieldName);
                                    Object extraReturnData = invokeMethod(innerMethod, returnData);

                                    setValue(innerMethod, extraReturnData);
                                }
                            }
                        }
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }
        }

        return String.format(UpdateTemplate, headTable.getTableName(), setString, whereString);
    }

    /**
     * This method sets the value to the update string
     * @param method The method where the value can be found
     * @param value The value that has to be set
     */
    private void setValue(Method method, Object value) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(method.getName().substring(3)))
                if (value != null) {
                    if (!field.getName().equalsIgnoreCase(headTable.getPrimaryKey())) {
                        setString += combineFieldValue(addedFields, field.getName().toLowerCase(), value.toString());
                        addedFields++;
                    }
                }
        }
    }

    /**
     * This method formats the value and field to the appropriate format
     * @param added Check if the value is the first or not
     * @param field The field that has to be used
     * @param value The value for the given field
     * @return A formatted String of field and value
     */
    private String combineFieldValue(int added, String field, String value) {
        if (added == 0)
            return String.format(" %s='%s' ", field, value);

        return String.format(", %s='%s' ", field, value);
    }

    /**
     * This method invokes the method and returns the given object
     * @param method The method that has to be invoked
     * @param obj The object where on which the method has to be invoked
     * @return The result of the invocation
     */
    private Object invokeMethod(Method method, Object obj) {
        Object result = null;

        try {
            result = method.invoke(obj.getClass().cast(obj));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Getter: Update string
     * @return The created update string
     */
    public String getUpdateString() {
        return updateString;
    }
}
