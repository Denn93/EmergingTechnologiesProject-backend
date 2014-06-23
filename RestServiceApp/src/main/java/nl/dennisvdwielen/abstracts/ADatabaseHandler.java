package nl.dennisvdwielen.abstracts;

import nl.dennisvdwielen.database.mapping.PojoReflection;
import nl.dennisvdwielen.enums.Operators;
import nl.dennisvdwielen.enums.Orders;
import nl.dennisvdwielen.factory.Config;

import java.lang.reflect.Field;
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

    /**
     * Method for making a select query against the database. This method is further used in the overloading cycle
     *
     * @param dto       The DTO class which the result must be mapped be mapped to
     * @param headTable The headtable in which the query must be performed
     * @param <T>       Type of the DTO class.
     * @return An ArrayList of Type class dto
     */
    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable) {
        return multipleSelect(dto, headTable, null, null, null, null, null, null);
    }

    /**
     * Method for making a select query against the database. This method is further used in the overloading cycle
     *
     * @param dto       The DTO class which the result must be mapped be mapped to
     * @param headTable The headtable in which the query must be performed
     * @param whereData A LinkedHashMap in which the field for thee  where clause are defined. And optional also the operators
     * @param <T>       The type of the dto class. This type is also used as return type
     * @return A ArrayList of type Dto Class
     */
    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, LinkedHashMap<String, List<String>> whereData) {
        return multipleSelect(dto, headTable, null, null, whereData, null, null, null);
    }

    /**
     * Method for making a select query against the database. This method is further used in the overloading cycle
     *
     * @param dto       The DTO class which the result must be mapped be mapped to
     * @param headTable The headtable in which the query must be performed
     * @param whereData A LinkedHashMap in which the field for thee  where clause are defined. And optional also the operators
     * @param orderData A String List of values in de columns are specified for ordering the query. Also added the order options
     * @param <T>       The type of the dto class. This type is also used as return type
     * @return A ArrayList of type Dto Class
     */
    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, LinkedHashMap<String, List<String>> whereData, List<String> orderData) {
        return multipleSelect(dto, headTable, null, null, whereData, orderData, null, null);
    }

    /**
     * Method for making a select query against the database. This method is further used in the overloading cycle
     *
     * @param dto         The DTO class which the result must be mapped be mapped to
     * @param headTable   The headtable in which the query must be performed
     * @param whereData   A LinkedHashMap in which the field for thee  where clause are defined. And optional also the operators
     * @param orderData   A String List of values in de columns are specified for ordering the query. Also added the order options
     * @param groupBy     A Field for adding a groupby parameter to the query
     * @param groupConcat String list of field to add in a group_concat function within the query
     * @param <T>         The type of the dto class. This type is also used as return type
     * @return A ArrayList of type Dto Class
     */
    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, LinkedHashMap<String, List<String>> whereData, List<String> orderData, String groupBy, List<String> groupConcat) {
        return multipleSelect(dto, headTable, null, null, whereData, orderData, groupBy, groupConcat);
    }

    /**
     * Method for making a select query against the database. This method is further used in the overloading cycle
     *
     * @param dto                The DTO class which the result must be mapped be mapped to
     * @param headTable          The headtable in which the query must be performed
     * @param intersectionTables A ArrayList of classes in which the intersection tables a defined
     * @param whereData          A LinkedHashMap in which the field for thee  where clause are defined. And optional also the operators
     * @param <T>                The type of the dto class. This type is also used as return type
     * @return A ArrayList of type Dto Class
     */
    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersectionTables, LinkedHashMap<String, List<String>> whereData) {
        return multipleSelect(dto, headTable, intersectionTables, null, whereData, null, null, null);
    }

    /**
     * Method for making a select query against the database. This method is further used in the overloading cycle
     *
     * @param dto                The DTO class which the result must be mapped be mapped to
     * @param headTable          The headtable in which the query must be performed
     * @param intersectionTables A ArrayList of classes in which the intersection tables a defined
     * @param whereData          A LinkedHashMap in which the field for thee  where clause are defined. And optional also the operators
     * @param orderData          A String List of values in de columns are specified for ordering the query. Also added the order options
     * @param <T>                The type of the dto class. This type is also used as return type
     * @return A ArrayList of type Dto Class
     */
    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersectionTables, LinkedHashMap<String, List<String>> whereData, List<String> orderData) {
        return multipleSelect(dto, headTable, intersectionTables, null, whereData, orderData, null, null);
    }

    /**
     * Method for making a select query against the database. This method is further used in the overloading cycle
     *
     * @param dto                The DTO class which the result must be mapped be mapped to
     * @param headTable          The headtable in which the query must be performed
     * @param intersectionTables A ArrayList of classes in which the intersection tables a defined
     * @param whereData          A LinkedHashMap in which the field for thee  where clause are defined. And optional also the operators
     * @param orderData          A String List of values in de columns are specified for ordering the query. Also added the order options
     * @param groupBy            A Field for adding a groupby parameter to the query
     * @param groupConcat        String list of field to add in a group_concat function within the query
     * @param <T>                The type of the dto class. This type is also used as return type
     * @return A ArrayList of type Dto Class
     */
    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersectionTables, LinkedHashMap<String, List<String>> whereData, List<String> orderData, String groupBy, List<String> groupConcat) {
        return multipleSelect(dto, headTable, intersectionTables, null, whereData, orderData, groupBy, groupConcat);
    }

    /**
     * Method for making a select query against the database. This method is further used in the overloading cycle
     *
     * @param dto                The DTO class which the result must be mapped be mapped to
     * @param headTable          The headtable in which the query must be performed
     * @param intersectionTables A ArrayList of classes in which the intersection tables a defined
     * @param extraTables        A ArrayList of classes in which the extra tables tables a defined
     * @param whereData          A LinkedHashMap in which the field for thee  where clause are defined. And optional also the operators
     * @param <T>                The type of the dto class. This type is also used as return type
     * @return A ArrayList of type Dto Class
     */
    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables, LinkedHashMap<String, List<String>> whereData) {
        return multipleSelect(dto, headTable, intersectionTables, extraTables, whereData, null, null, null);
    }

    /**
     * Method for making a select query against the database. This method is further used in the overloading cycle
     *
     * @param dto                The DTO class which the result must be mapped be mapped to
     * @param headTable          The headtable in which the query must be performed
     * @param intersectionTables A ArrayList of classes in which the intersection tables a defined
     * @param extraTables        A ArrayList of classes in which the extra tables tables a defined
     * @param whereData          A LinkedHashMap in which the field for thee  where clause are defined. And optional also the operators
     * @param orderData          A String List of values in de columns are specified for ordering the query. Also added the order options
     * @param <T>                The type of the dto class. This type is also used as return type
     * @return A ArrayList of type Dto Class
     */
    public final <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables, LinkedHashMap<String, List<String>> whereData, List<String> orderData) {
        return multipleSelect(dto, headTable, intersectionTables, extraTables, whereData, orderData, null, null);
    }

    /**
     * Method for making a select query against the database. This method is further used in the overloading cycle
     *
     * @param dto                The DTO class which the result must be mapped be mapped to
     * @param headTable          The headtable in which the query must be performed
     * @param intersectionTables A ArrayList of classes in which the intersection tables a defined
     * @param extraTables        A ArrayList of classes in which the extra tables tables a defined
     * @param whereData          A LinkedHashMap in which the field for thee  where clause are defined. And optional also the operators
     * @param orderData          A String List of values in de columns are specified for ordering the query. Also added the order options
     * @param groupBy            A Field for adding a groupby parameter to the query
     * @param groupConcat        String list of field to add in a group_concat function within the query
     * @param <T>                The type of the dto class. This type is also used as return type
     * @return A ArrayList of type Dto Class
     */
    public abstract <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables, LinkedHashMap<String, List<String>> whereData, List<String> orderData, String groupBy, List<String> groupConcat);

    /**
     * This method creates the connection. If it already e
     *
     * @return
     */
    protected abstract boolean createConnection();

    /**
     * This method creates a Where string clause. With parameters its creates a where clause with greater and less than signs.
     * It also keeps into account if it is the first value and adds the appropriate aliases.
     *
     * @param where  LinkedHashmap with all the fields and values that have to be included in the where clause. If List<String> is > 1 it also contains a operator
     * @param tables The Reflection of the tables in which the field will correspond. This is to check the correct aliases.
     * @return A where clause string
     */
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

    /**
     * This method is used for creating a order clause. It makes this clause based on the given data
     *
     * @param data List String of fields and order options
     * @return Combined order string clause
     */
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

    /**
     * This method is used for performing a update query to the database. This method has to be implemented
     * This query will be build depending on the given parameters
     *
     * @param obj       A object with the values that the update query needs
     * @param className The className in which in update query has to perform. E.g. Container.class
     * @param <T>       The type of object and dto has to be the same.
     * @return Return True of False. Depending on the result of query.
     */
    public final <T> Boolean update(T obj, Class<T> className) {
        return update(obj, className, null);
    }

    /**
     * This method is used for performing a update query to the database. This method has to be implemented
     * This query will be build depending on the given parameters
     *
     * @param obj       A object with the values that the update query needs
     * @param className The className in which in update query has to perform. E.g. Container.class
     * @param where     These is where a Hashmap of key value pairs are given to create a where clause
     * @param <T>       The type of object and dto has to be the same.
     * @return Return True of False. Depending on the result of query.
     */
    public abstract <T> Boolean update(T obj, Class<T> className, HashMap<String, String> where);

    /**
     * This method is used for closing all statements, connections and resultsets
     */
    protected abstract void close();
}
