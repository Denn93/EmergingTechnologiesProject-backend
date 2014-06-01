package nl.dennisvdwielen.mapping;

import java.lang.reflect.Field;
import java.util.ArrayList;
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

    //----------------------------
    private Class[] pojos;
    private ArrayList<PojoReflection> mainTables;

    public JoinBuilder(Class pojo) {
        this.pojo = pojo;
        headTable = new PojoReflection(pojo);
        innerJoinCollection = new Stack<String>();

        findForeignTables();

        innerJoinCollection.push(createInnerJoin());
        innerJoin = combineInnerJoin();
    }

    public JoinBuilder(Class mainTable, ArrayList<Class> intercectionTables, ArrayList<Class> extraTables, String primaryKey) {
        this.pojos = pojos;
        this.mainTables = new ArrayList<PojoReflection>();
        innerJoinCollection = new Stack<String>();

        for (Class pojo : pojos)
            this.mainTables.add(new PojoReflection(pojo));

        findForeignTablesNew();
        innerJoin = combineInnerJoin();

    }

    private JoinBuilder(PojoReflection pojo) {
        this.pojo = pojo.getPojo();
        headTable = pojo;

        findForeignTables();
        innerJoin = createInnerJoin();
    }

    private void findForeignTablesNew() {
        foreignTables = new ArrayList<PojoReflection>();

        for (PojoReflection mainTable : mainTables)
            for (PojoReflection foreignTable : mainTable.getForeignTables()) {
                if (foreignTable.getForeignKeys().size() > 0)
                    innerJoinCollection.push(new JoinBuilder(foreignTable).getInnerJoin());

                foreignTables.add(foreignTable);
            }
    }

    private void findForeignTables() {
        foreignTables = new ArrayList<PojoReflection>();

        for (PojoReflection foreignTable : headTable.getForeignTables()) {
            if (foreignTable.getForeignKeys().size() > 0)
                innerJoinCollection.push(new JoinBuilder(foreignTable).getInnerJoin());

            foreignTables.add(foreignTable);
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

        System.out.println(result);

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

    public Field findField(String name) {

        for (PojoReflection foreignTable : foreignTables) {
            for (Field field : foreignTable.getFields())
                if (name.equalsIgnoreCase(field.getName()))
                    return field;

            for (PojoReflection innerForeign : foreignTable.getForeignTables())
                for (Field field : innerForeign.getFields())
                    if (name.equalsIgnoreCase(field.getName()))
                        return field;
        }

        return null;
    }

    public String FieldToString(Field value) {
        for (PojoReflection foreignTable : foreignTables) {
            for (Field field : foreignTable.getFields())
                if (value.getName().equalsIgnoreCase(field.getName()))
                    return String.format("%s.%s", foreignTable.getAlias(), field.getName());

            for (PojoReflection innerForeign : foreignTable.getForeignTables())
                for (Field field : innerForeign.getFields())
                    if (value.getName().equalsIgnoreCase(field.getName()))
                        return String.format("%s.%s", innerForeign.getAlias(), field.getName());
        }

        return "";
    }
}
