package nl.dennisvdwielen.database;

import nl.dennisvdwielen.dto.ContainerDTO;
import nl.dennisvdwielen.interfaces.ADatabaseHandler;
import nl.dennisvdwielen.mapping.JoinBuilder;
import nl.dennisvdwielen.mapping.JoinBuilderNew;
import nl.dennisvdwielen.mapping.RecordMapper;
import nl.dennisvdwielen.mapping.SelectBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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

    public ContainerDTO multipleSelect(Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables, LinkedHashMap<String, List<String>> whereData, List<String> orderData, String groupBy, List<String> groupConcat) {
        JoinBuilderNew builder = new JoinBuilderNew(headTable, intersectionTables, extraTables);

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

        return null;
    }

    @Override
    public <T> ArrayList<T> select(Class<T> pojo, LinkedHashMap<String, List<String>> whereData, List<String> orderData, String groupBy, String groupConcat) {
        ArrayList<T> result = new ArrayList<T>();

        JoinBuilder builder = new JoinBuilder(pojo);
        String select = "";/*(groupConcat != null) ? new SelectBuilder(builder.getForeignTables(), builder.findField(groupConcat)).getStatement()
                : new SelectBuilder(builder.getForeignTables()).getStatement();*/

        String innerJoin = builder.getInnerJoin();
        String tableName = builder.getHeadTable().getTableName();
        String alias = builder.getHeadTable().getAlias();

        System.out.println(select);

        String where = "";/* (whereData == null) ? "" : createWhereString(whereData, builder.getForeignTables());*/
        String order = (orderData == null) ? "" : createOrder(orderData);
        groupBy = (groupBy == null) ? "" : String.format("GROUP BY %s", builder.FieldToString(builder.findField(groupBy)));

        System.out.println(where);

        String query = String.format("SELECT %s FROM %s %s %s %s %s %s", select, tableName, alias, innerJoin, where, groupBy, order);
        System.out.println(query);
        try {
            statement = connect.createStatement();
            resultSet = statement.executeQuery(query);

            ArrayList<Object> rawResults = new RecordMapper(pojo.newInstance(), resultSet).getMappedResults();

            for (Object obj : rawResults)
                result.add(pojo.cast(obj));

            return result;
        } catch (SQLException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        } catch (InstantiationException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        } finally {
            close();
        }

        return result;
    }

    @Override
    public ResultSet rawSelect(String query) {
        return null;
    }

    @Override
    public Integer update() {
        return -1;
    }

    @Override
    public Integer delete() {
        return -1;
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
