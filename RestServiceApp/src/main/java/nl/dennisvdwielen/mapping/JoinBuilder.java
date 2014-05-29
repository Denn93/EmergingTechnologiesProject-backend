package nl.dennisvdwielen.mapping;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

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
    private Stack<String> innerJoinCollection;

    private String innerJoin;

    public JoinBuilder(Class pojo) {
        this.pojo = pojo;
        headTable = new PojoReflection(pojo);
        innerJoinCollection = new Stack<String>();

        findForeignTables();

        innerJoinCollection.push(createInnerJoin());
        innerJoin = combineInnerJoin();
    }

    private JoinBuilder(PojoReflection pojo) {
        this.pojo = pojo.getPojo();
        headTable = pojo;

        findForeignTables();
        innerJoin = createInnerJoin();
    }

    private void findForeignTables() {
        foreignTables = new ArrayList<PojoReflection>();

        for (Map.Entry<String, String> fk : headTable.getForeignKeys().entrySet()) {
            try {
                PojoReflection foreignTable = new PojoReflection(Class.forName("nl.dennisvdwielen.pojo." + fk.getKey()));
                if (foreignTable.getForeignKeys().size() > 0)
                    innerJoinCollection.push(new JoinBuilder(foreignTable).getInnerJoin());

                foreignTables.add(foreignTable);
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

    private String combineInnerJoin() {
        String result = "";

        while (innerJoinCollection.iterator().hasNext())
            result += innerJoinCollection.pop();

        return result;
    }

    public PojoReflection getHeadTable() {
        return headTable;
    }

    public String getInnerJoin() {
        return innerJoin;
    }

    public ArrayList<PojoReflection> getForeignTables() {
        return foreignTables;
    }
}
