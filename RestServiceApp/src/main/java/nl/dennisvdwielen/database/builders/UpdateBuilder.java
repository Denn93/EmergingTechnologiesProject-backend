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

    private String update() {
        for (Field field : obj.getClass().getFields()) {
            try {
                Method method = obj.getClass().getMethod("get" + field);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    private String createUpdate() {
        for (Method method : obj.getClass().getMethods()) {
            if (!method.getName().startsWith("get"))
                continue;

            if (defaultTypes.contains(method.getReturnType())) {
                Object returnData = invokeMethod(method);
                setValue(method, returnData);
            }/*else
            {
                for (Method foreignMethod : method.getReturnType().getMethods()) {
                    if (!foreignMethod.getName().startsWith("get"))
                        continue;

                    Object returnData = invokeMethod(foreignMethod);
                    setValue(foreignMethod, returnData);
                }
            }*/
        }

        return String.format(UpdateTemplate, headTable.getTableName(), setString, whereString);
    }

    private void setValue(Method method, Object value) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.getName().equalsIgnoreCase(method.getName().substring(3)))
                if (value != null) {
                    if (!field.getName().equalsIgnoreCase(headTable.getPrimaryKey())) {
                        setString += combineFieldValue(addedFields, field.getName().toLowerCase(), value.toString());
                        addedFields++;
                    }
//                    else
//                        whereString = combineFieldValue(addedFields, field.getName().toLowerCase(), value.toString());
                }
        }
    }

    private String combineFieldValue(int added, String field, String value) {
        if (added == 0)
            return String.format(" %s='%s' ", field, value);

        return String.format(", %s='%s' ", field, value);
    }

    private Object invokeMethod(Method method) {
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

    public String getUpdateString() {
        return updateString;
    }
}
