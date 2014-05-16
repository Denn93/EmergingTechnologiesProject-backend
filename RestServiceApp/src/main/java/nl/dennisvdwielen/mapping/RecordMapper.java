package nl.dennisvdwielen.mapping;

import nl.dennisvdwielen.interfaces.IRecordMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dennis on 14-5-2014 at 21:49)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.mapping
 */
public class RecordMapper implements IRecordMapper {

    private ArrayList<Object> mappedResults;

    private Object pojo;
    private ArrayList<String> queryFields;
    private ArrayList<HashMap<String, String>> queryResult;

    public RecordMapper(Object pojo, ResultSet result) {
        mappedResults = new ArrayList<Object>();

        this.pojo = pojo;
        this.queryResult = ResultSetToArrayList(result);
        this.queryFields = RetrieveQueryFields(result);

        MapToPojo();
    }

    public RecordMapper(Object pojo, HashMap<String, String> record) {
        mappedResults = new ArrayList<Object>();

        this.pojo = pojo;
        this.queryResult = new ArrayList<HashMap<String, String>>();
        this.queryResult.add(record);

        this.queryFields = new ArrayList<String>();
        for(String key : record.keySet())
            this.queryFields.add(key);

        MapToPojo();
    }

    //public RecordMapper(Object pojo, String )

    /**
     * This method returns  all field that are in the executed query
     * @return ArrayList of fields
     */
    private ArrayList<String> RetrieveQueryFields(ResultSet resultSet) {
        ArrayList<String> result = new ArrayList<String>();

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for(int i = 1; i <= columnCount; i++)
                result.add(metaData.getColumnName(i));

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private void MapToPojo() {
        for(HashMap<String, String> record : queryResult)
        {
            for(String field : queryFields)
            {
                Method[] methods = pojo.getClass().getMethods();

                for(Method method : methods)
                {
                    if (!method.getName().startsWith("set"))
                        continue;

                    if (method.getName().substring(3, method.getName().length()).equalsIgnoreCase(field))
                        InvokeMethod(method, record, field);
                }
            }

            mappedResults.add(pojo);

            try {
                pojo = (Object) pojo.getClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void InvokeMethod(Method method, HashMap<String, String> record, String field) {
        Class fieldType = method.getParameterTypes()[0];

        String value = record.get(field);

        try{
            if (fieldType.equals(Integer.class) || fieldType.equals(int.class))
                method.invoke(pojo, Integer.parseInt(value));
            else if (fieldType.equals(String.class))
                method.invoke(pojo, value);
            else if (fieldType.equals(Double.class) || fieldType.equals(double.class))
                method.invoke(pojo, Double.parseDouble(value));
            else
            {
                Class cls = Class.forName(fieldType.getName());
                Object temp = new RecordMapper(cls.newInstance(), record).getRecord();

                method.invoke(pojo, cls.cast(temp));
            }
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<HashMap<String, String>> ResultSetToArrayList(ResultSet resultSet) {
        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        ArrayList<String> fields = RetrieveQueryFields(resultSet);

        try {
            while(resultSet.next())
            {
                HashMap<String, String> map = new HashMap<String, String>();

                for(String field : fields)
                    map.put(field, resultSet.getString(field));

                result.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


    public Object getRecord() {
        return mappedResults.get(0);
    }

    public ArrayList<Object> getMappedResults() {
        return mappedResults;
    }
}
