package nl.dennisvdwielen.interfaces;

import nl.dennisvdwielen.enums.Operators;
import nl.dennisvdwielen.factory.Config;

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

    protected Config config;

    protected ADatabaseHandler(){
        config = getInstance();

        createConnection();
    }


    public final <T> ArrayList<T> select(Class<T> pojo) {
        return select(pojo, "");
    }

    public final <T> ArrayList<T> select(Class<T> pojo, String options) {
        return select(pojo, options, null);
    }

    public abstract <T> ArrayList<T> select(Class<T> pojo, String options, LinkedHashMap<String, List<String>> where);

    protected abstract boolean createConnection();

    protected String createWhereString(LinkedHashMap<String, List<String>> where) {
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

            if (where.size() == i)
                result += String.format("%s %s '%s'", entry.getKey(), operator, entry.getValue().get(0));
            else
                result += String.format("%s %s '%s' AND ", entry.getKey(), operator, entry.getValue().get(0));

            i++;
        }

        return result;
    }

    public abstract Integer update();
    public abstract Integer delete();
    public abstract ResultSet rawSelect(String query);
    protected abstract void close();
}
