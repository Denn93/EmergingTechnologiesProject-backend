package nl.dennisvdwielen.database.mapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Dennis on 14-5-2014 at 21:49)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.mapping
 */

/**
 * This class maps the database results to the according object with the use of advanced annotations and reflections
 */
public class RecordMapper {

    ArrayList<Object> tables;
    ArrayList<Object> dtoData;
    private Class headTable;
    private ArrayList<Class> intersectionTables;
    private ArrayList<Class> extraTables;
    private ArrayList<ArrayList<Object>> mappedResults;
    private ArrayList<String> queryFields;
    private ArrayList<HashMap<String, String>> queryResult;
    private ArrayList<String> insertedForeignFields;

    /**
     * This constructor sets all the fields. and makes of all tables a new instance to use with each new record
     *
     * @param headTable          The head table to which has to mapped
     * @param intersectionTables The used intersection tables for the query. Also used here to map the results
     * @param extraTables        The used extra tables for the query. Also used here to map the results
     * @param result             The resultset from the database
     */
    public RecordMapper(Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables, ResultSet result) {
        mappedResults = new ArrayList<ArrayList<Object>>();
        insertedForeignFields = new ArrayList<String>();
        this.headTable = headTable;
        this.intersectionTables = intersectionTables;
        this.extraTables = extraTables;

        makeNewTables();

        this.queryResult = ResultSetToArrayList(result);
        this.queryFields = RetrieveQueryFields(result);

        MapToPojo();
    }

    /**
     * Private Constructor only used within this class to create a infinite loop op this class the create a mapping that
     * can  different dimensions of objects. Some object have Foreign key. These objects also heve to be mapped
     * @param pojo The pojo of the the object that has to uses from here on out.
     * @param record The record with te values
     */
    private RecordMapper(Object pojo, HashMap<String, String> record) {
        mappedResults = new ArrayList<ArrayList<Object>>();
        this.tables = new ArrayList<Object>();
        this.tables.add(pojo);
        this.queryResult = new ArrayList<HashMap<String, String>>();
        insertedForeignFields = new ArrayList<String>();

        this.queryResult.add(record);

        this.queryFields = new ArrayList<String>();
        for (String key : record.keySet())
            this.queryFields.add(key);

        MapToPojo();
    }

    /**
     * This method makes new instances of the tables that are used within the mapping of the resultset. Every new record
     * needs one or multiple new instances of objects to map to.
     */
    private void makeNewTables() {

        if (this.headTable != null) {
            this.tables = new ArrayList<Object>();
            try {
                this.tables.add(headTable.newInstance());

                if (intersectionTables != null)
                    for (Class cl : intersectionTables)
                        this.tables.add(cl.newInstance());

                if (extraTables != null)
                    for (Class cl : extraTables)
                        this.tables.add(cl.newInstance());

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method returns all field that are in the executed query
     *
     * @param resultSet The result of performed query
     * @return ArrayList of fields
     */
    private ArrayList<String> RetrieveQueryFields(ResultSet resultSet) {
        ArrayList<String> result = new ArrayList<String>();

        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++)
                result.add(metaData.getColumnName(i));

        } catch (SQLException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        }

        return result;
    }

    /**
     * This method loops through every record, every field and every method in Pojo class where the result will be mapped to.
     * If field and method are equal the method will be invoked in de InvokeMethod Method.
     */
    private void MapToPojo() {
        for (HashMap<String, String> record : queryResult) {
            ArrayList<Object> recordResults = new ArrayList<Object>();
            insertedForeignFields = new ArrayList<String>();
            makeNewTables();

            for (Object pojo : tables) {
                for (String field : queryFields) {
                    if (insertedForeignFields.contains(field))
                        continue;
                    Method[] methods = pojo.getClass().getMethods();

                    for (Method method : methods) {
                        if (!method.getName().startsWith("set"))
                            continue;

                        if (method.getName().substring(3, method.getName().length()).equalsIgnoreCase(field))
                            InvokeMethod(pojo, method, record, field);
                    }
                }
                recordResults.add(pojo);
                // TODO FIx Cant cast ArrayList problems

            }
            mappedResults.add(recordResults);

        }
        System.out.println(mappedResults.toString());
    }

    /**
     * This method will perform the method.invoke. But before that happens the value will be checked and parsed accordingly
     *
     * @param method Method to be invoked
     * @param record Current record with values
     * @param field  Specific field to invoked with the method.
     */
    private void InvokeMethod(Object pojo, Method method, HashMap<String, String> record, String field) {
        Class fieldType = method.getParameterTypes()[0];

        String value = record.get(field);

        try {
            if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
                method.invoke(pojo, Integer.parseInt(value));
                insertedForeignFields.add(field);
            } else if (fieldType.equals(String.class)) {
                method.invoke(pojo, value);
                insertedForeignFields.add(field);
            } else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
                method.invoke(pojo, Double.parseDouble(value));
                insertedForeignFields.add(field);
            } else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
                method.invoke(pojo, Long.parseLong(value));
                insertedForeignFields.add(field);
            } else if (fieldType.equals(Timestamp.class)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
                Date parsedDate = dateFormat.parse(value);
                Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
                method.invoke(pojo, timestamp);
                insertedForeignFields.add(field);
            } else {
                Class cls = Class.forName(fieldType.getName());
                Object temp = new RecordMapper(cls.newInstance(), record).getRecord();

                method.invoke(pojo, cls.cast(temp));
            }


        } catch (IllegalAccessException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        } catch (InstantiationException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * This Method converts the resultset from the database to an ArrayList. This way it will be easier to handle and resultSet cursor can be closed.
     *
     * @param resultSet
     * @return
     */
    private ArrayList<HashMap<String, String>> ResultSetToArrayList(ResultSet resultSet) {
        ArrayList<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        ArrayList<String> fields = RetrieveQueryFields(resultSet);

        try {
            while (resultSet.next()) {
                HashMap<String, String> map = new HashMap<String, String>();

                for (String field : fields)
                    map.put(field, resultSet.getString(field));

                result.add(map);
            }
        } catch (SQLException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        }

        return result;
    }

    /**
     * This method returns the first result of the mapped results. Only used when certain only one value exists
     * @return
     */
    public Object getRecord() {
        return mappedResults.get(0).get(0);
    }

    /**
     * This method returns the created dto. The DTO mapper is also called from this class. Thus, this getter
     * @param dtoClass The dto class in to where the cast should point
     * @param <T> The type of dto class. This will also be the return type
     * @return ArrayList of type dto class
     */
    public <T> ArrayList<T> getDto(Class<T> dtoClass) {
        ArrayList<T> result = new ArrayList<T>();

        dtoData = new DtoMapper(dtoClass, mappedResults, this.tables).getDto();
        for (Object data : dtoData)
            result.add(dtoClass.cast(data));

        return result;
    }
}
