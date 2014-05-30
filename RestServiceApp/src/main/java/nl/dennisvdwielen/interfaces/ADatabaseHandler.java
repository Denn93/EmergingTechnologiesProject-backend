package nl.dennisvdwielen.interfaces;

import nl.dennisvdwielen.enums.Operators;
import nl.dennisvdwielen.enums.Orders;
import nl.dennisvdwielen.factory.Config;
import nl.dennisvdwielen.mapping.PojoReflection;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static nl.dennisvdwielen.factory.Config.getInstance;

/**
 * Created by Dennis on 4-5-2014 at 16:45)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.interfaces
 */
public abstract class ADatabaseHandler {

    //Default error messages that can be implemented by database implementation
    protected final String DATABASEHANDLER_NOCONNECIION_ERROR = "Can't connect to the database. A connection has occurred";
    protected final String DATABASEHANDLER_NODRIVER_ERROR = "No database connection driver has been found";

    protected final Config config;

    protected ADatabaseHandler(){
        config = getInstance();
        createConnection();
    }

    public final <T> ArrayList<T> select(Class<T> pojo) {
        return select(pojo, null);
    }

    public final <T> ArrayList<T> select(Class<T> pojo, LinkedHashMap<String, List<String>> where) {
        return select(pojo, null, null);
    }
    public abstract <T> ArrayList<T> select(Class<T> pojo, LinkedHashMap<String, List<String>> where, List<String> order);

    protected abstract boolean createConnection();

    protected final String createWhereString(LinkedHashMap<String, List<String>> where) {
        return createWhereString(where, null);
    }

    protected final String createWhereString(LinkedHashMap<String, List<String>> where, ArrayList<PojoReflection> tables) {
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

            for (PojoReflection reflection : tables)
                for (Field field : reflection.getFields())
                    if (entry.getKey().equalsIgnoreCase(field.getName()))
                        key = String.format("%s.%s", reflection.getAlias(), entry.getKey());

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
