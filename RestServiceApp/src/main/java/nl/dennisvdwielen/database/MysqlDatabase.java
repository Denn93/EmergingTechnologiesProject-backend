package nl.dennisvdwielen.database;

import nl.dennisvdwielen.interfaces.ADatabaseHandler;
import nl.dennisvdwielen.mapping.RecordMapper;

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

    @Override
    public <T> ArrayList<T> select(Class<T> pojo, String options, LinkedHashMap<String, List<String>> filter) {
        ArrayList<T> result = new ArrayList<T>();

        String tableName = pojo.getSimpleName().toLowerCase();
        String where = (filter == null) ? "" : createWhereString(filter);
        String order = (filter == null || filter.get("order") == null) ? "" : createOrder(filter.get("order"));

        System.out.println(where);

        try{
            statement = connect.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM container c   INNER JOIN Ship s ON c.shipID = s.shipID INNER JOIN Handling h ON h.handlingID = c.handlingID " +
                    "                                                       INNER JOIN Packaginggroup pg ON pg.packagingID = c.packagingID " + where);

            ArrayList<Object> rawResults = new RecordMapper(pojo.newInstance(), resultSet).getMappedResults();

            for (Object obj : rawResults)
                result.add(pojo.cast(obj));

            return result;
        }catch(SQLException e)
        {
            //TODO Add Useful Error Message
            e.printStackTrace();
        } catch (InstantiationException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            //TODO Add Useful Error Message
            e.printStackTrace();
        } finally{
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
