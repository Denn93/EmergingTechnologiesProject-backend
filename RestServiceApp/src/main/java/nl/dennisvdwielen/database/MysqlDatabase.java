package nl.dennisvdwielen.database;

import nl.dennisvdwielen.abstracts.ADatabaseHandler;
import nl.dennisvdwielen.database.builders.JoinBuilder;
import nl.dennisvdwielen.database.builders.SelectBuilder;
import nl.dennisvdwielen.database.builders.UpdateBuilder;
import nl.dennisvdwielen.database.mapping.RecordMapper;
import nl.dennisvdwielen.dto.ContainerDTO;
import nl.dennisvdwielen.entity.Container;

import java.sql.*;
import java.util.*;

/**
 * Created by Dennis on 1-5-2014 at 22:11)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.database
 */
public class MysqlDatabase extends ADatabaseHandler {

    private static Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    protected boolean createConnection() {
        try {
            Class.forName(config.getDbDriver());

            if (connect == null || connect.isClosed())
                connect = DriverManager.getConnection(config.getConnectionString());

        } catch (ClassNotFoundException e) {
            System.out.println(DATABASEHANDLER_NODRIVER_ERROR);
            return false;
        } catch (SQLException e) {
            System.out.println(DATABASEHANDLER_NOCONNECIION_ERROR);
            return false;
        }

        return true;
    }

    public <T> ArrayList<T> multipleSelect(Class<T> dto, Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables, LinkedHashMap<String, List<String>> whereData, List<String> orderData, String groupBy, List<String> groupConcat) {
        createConnection();

        JoinBuilder builder = new JoinBuilder(headTable, intersectionTables, extraTables);

        String headTableName = builder.getHeadTable().getTableName();
        String headTableAlias = builder.getHeadTable().getAlias();
        String innerJoin = builder.getInnerJoin();
        groupBy = (groupBy == null) ? "" : String.format("GROUP BY %s", builder.FieldToString(builder.findField(groupBy)));
        String select = (groupConcat != null) ? new SelectBuilder(builder.getAllTables(), builder, groupConcat).getStatement()
                : new SelectBuilder(builder.getAllTables(), builder).getStatement();

        String where = (whereData == null) ? "" : createWhereString(whereData, builder.getAllTables());
        String order = (orderData == null) ? "" : createOrder(orderData);

        String query = String.format("SELECT %s FROM %s %s %s %s %s %s", select, headTableName, headTableAlias, innerJoin, where, groupBy, order);
        System.out.println(query);

        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            return new RecordMapper(headTable, intersectionTables, extraTables, resultSet).getDto(dto);

        } catch (SQLException e) {

            ArrayList<T> result = new ArrayList<T>();
            Container con = new Container();
            con.setEquipmentNumber((e.getMessage()));
            ContainerDTO dt = new ContainerDTO();
            dt.setEquipmentNumber(con);
            result.add((T) dt);
            return result;
        } finally {
            close();
        }
    }

    @Override
    public <T> Boolean update(T obj, Class<T> className, HashMap<String, String> where) {
        createConnection();

        UpdateBuilder builder = new UpdateBuilder(obj, className);

        String query = builder.getUpdateString();
        Map.Entry<String, String> wherepair = where.entrySet().iterator().next();

        query = String.format(query + " %s='%s'", wherepair.getKey(), wherepair.getValue());

        try {
            statement = connect.createStatement();

            if (statement.executeUpdate(query) == 1)
                return true;
        } catch (SQLException e) {
            e.printStackTrace(); // TODO Create a Beautiful Message
        } finally {
            close();
        }
        return false;
    }

    @Override
    protected void close() {
        if (resultSet != null)
            try {
                resultSet.close();
            } catch (SQLException e) {
                //TODO Add Useful Error Message
                e.printStackTrace();
            }

        if (statement != null)
            try {
                statement.close();
            } catch (SQLException e) {
                //TODO Add Useful Error Message
                e.printStackTrace();
            }

        if (connect != null)
            try {
                connect.close();
            } catch (SQLException e) {
                //TODO Add Useful Error Message
                e.printStackTrace();
            }
    }
}
