package nl.dennisvdwielen.mapping;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Dennis on 26-5-2014 at 00:39)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.mapping
 */
public class JoinBuilder {

    private final String BaseInnerJoinStructure = " INNER JOIN %s %s ON %s=%s ";

    private Class pojo;
    private PojoReflection headTable;
    private ArrayList<PojoReflection> foreignTables;
    private String innerJoins;

    public JoinBuilder(Class pojo) {
        this.pojo = pojo;
        headTable = new PojoReflection(pojo);

        getForeignTables();
        innerJoins = createInnerJoin();
    }

    private void getForeignTables() {
        foreignTables = new ArrayList<PojoReflection>();

        for (Map.Entry<String, String> fk : headTable.getForeignKeys().entrySet()) {
            try {
                foreignTables.add(new PojoReflection(Class.forName("nl.dennisvdwielen.pojo." + fk.getKey())));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private String createInnerJoin() {
        String result = "";

        for (PojoReflection foreignTable : foreignTables)
            result += String.format(BaseInnerJoinStructure, foreignTable.getTableName(), foreignTable.getAlias(), foreignTable.getAlias() + "." + foreignTable.getPrimaryKey(), headTable.getAlias() + "." + foreignTable.getPrimaryKey());

        return result;
    }

    public PojoReflection getHeadTable() {
        return headTable;
    }

    public String getInnerJoin() {
        return innerJoins;
    }
}
