package nl.dennisvdwielen.abstracts;

import nl.dennisvdwielen.database.mapping.PojoReflection;
import nl.dennisvdwielen.enums.Operators;
import nl.dennisvdwielen.enums.Orders;
import nl.dennisvdwielen.factory.Config;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.*;

import static nl.dennisvdwielen.factory.Config.getInstance;

/**
 * Created by Dennis on 4-5-2014 at 16:45)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.abstracts
 */
public abstract class ADatabaseHandler {

    //Default error messages that can be implemented by database implementation
    protected final String DATABASEHANDLER_NOCONNECIION_ERROR = "Can't connect to the database. A connection has occurred";
    protected final String DATABASEHANDLER_NODRIVER_ERROR = "No database connection driver has been found";

    protected final Config config;

    protected ADatabaseHandler() {
        config = getInstance();
        createConnection();
    }

    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable) {
        return multipleSelect(dto, headTable, null, null, null, null, null, null);
    }

    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, LinkedHashMap<String, List<String>> whereData) {
        return multipleSelect(dto, headTable, null, null, whereData, null, null, null);
    }

    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, LinkedHashMap<String, List<String>> whereData, List<String> orderData) {
        return multipleSelect(dto, headTable, null, null, whereData, orderData, null, null);
    }

    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, LinkedHashMap<String, List<String>> whereData, List<String> orderData, String groupBy, List<String> groupConcat) {
        return multipleSelect(dto, headTable, null, null, whereData, orderData, groupBy, groupConcat);
    }

    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersection, LinkedHashMap<String, List<String>> whereData) {
        return multipleSelect(dto, headTable, intersection, null, whereData, null, null, null);
    }

    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersection, LinkedHashMap<String, List<String>> whereData, List<String> orderData) {
        return multipleSelect(dto, headTable, intersection, null, whereData, orderData, null, null);
    }

    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersection, LinkedHashMap<String, List<String>> whereData, List<String> orderData, String groupBy, List<String> groupConcat) {
        return multipleSelect(dto, headTable, intersection, null, whereData, orderData, groupBy, groupConcat);
    }

    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables, LinkedHashMap<String, List<String>> whereData) {
        return multipleSelect(dto, headTable, intersectionTables, extraTables, whereData, null, null, null);
    }

    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables, LinkedHashMap<String, List<String>> whereData, List<String> orderData) {
        return multipleSelect(dto, headTable, intersectionTables, extraTables, whereData, orderData, null, null);
    }

    public abstract <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables, LinkedHashMap<String, List<String>> whereData, List<String> orderData, String groupBy, List<String> groupConcat);

    protected abstract boolean createConnection();

    protected final String createWhereString(LinkedHashMap<String, List<String>> where, LinkedList<PojoReflection> tables) {
        String result = "Where ";
        int i = 1;

        for (Map.Entry<String, List<String>> entry : where.entrySet()) {

            List<String> values = entry.getValue();
            String operator = Operators.Equals.getOperator();
            if (values.size() > 1) {
                operator = values.get(1);
                if (operator.equalsIgnoreCase(Operators.GreaterThan.getShort()))
                    operator = Operators.GreaterThan.getOperator();
                else if (operator.equalsIgnoreCase(Operators.LessThan.getShort()))
                    operator = Operators.LessThan.getOperator();
                else
                    operator = Operators.Equals.getOperator();
            }

            String key = entry.getKey();

            while (tables.iterator().hasNext() && key.equalsIgnoreCase(entry.getKey())) {
                PojoReflection reflection = tables.poll();
                for (Field field : reflection.getFields())
                    if (entry.getKey().equalsIgnoreCase(field.getName())) {
                        key = String.format("%s.%s", reflection.getAlias(), entry.getKey());
                        break;
                    }

            }

            if (where.size() == i)
                result += String.format("%s %s '%s'", key, operator, entry.getValue().get(0));
            else
                result += String.format("%s %s '%s' AND ", key, operator, entry.getValue().get(0));

            i++;
        }

        return result;
    }

    protected final String createOrder(List<String> data) {

        String order = "";
        String columns = "";

        for (String option : data) {
            if (option.equalsIgnoreCase(Orders.ASCENDING.getValue()))
                order = Orders.ASCENDING.getValue();
            else if (option.equalsIgnoreCase(Orders.DESCENDING.getValue()))
                order = Orders.DESCENDING.getValue();
            else if (!option.equalsIgnoreCase(""))
                columns += (!columns.equals("") ? ", " + option : option);
        }

        String result = "Order by " + columns + " " + order;
        System.out.println(result);

        return result;
    }

    public abstract Integer update();

    public abstract Integer delete();

    public abstract ResultSet rawSelect(String query);

    protected abstract void close();
}