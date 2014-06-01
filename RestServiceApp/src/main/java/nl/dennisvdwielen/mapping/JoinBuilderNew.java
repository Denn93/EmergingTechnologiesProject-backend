package nl.dennisvdwielen.mapping;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Dennis on 1-6-2014 at 12:58)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.mapping
 */
public class JoinBuilderNew {

    private final String BaseInnerJoinStructure = " INNER JOIN %s %s ON %s=%s ";

    private PojoReflection headTable;
    private ArrayList<PojoReflection> intersectionTables;
    private ArrayList<PojoReflection> extraTables;

    private ArrayList<PojoReflection> foreignTables;

    private String innerJoin;

    public JoinBuilderNew(Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables) {
        this.headTable = new PojoReflection(headTable);
        this.intersectionTables = new ArrayList<PojoReflection>();
        this.extraTables = new ArrayList<PojoReflection>();
        this.innerJoin = "";

        if (intersectionTables != null)
            for (Class table : intersectionTables)
                this.intersectionTables.add(new PojoReflection(table));

        if (extraTables != null)
            for (Class table : extraTables)
                this.extraTables.add(new PojoReflection(table));

        findForeignHeadTable(this.headTable);
        intersectionTableCombiner();
        extraTableCombiner();

        System.out.println(innerJoin);
    }


    private void findForeignHeadTable(PojoReflection table) {
        foreignTables = new ArrayList<PojoReflection>();

        for (PojoReflection foreignTable : table.getForeignTables()) {
            innerJoin += String.format(BaseInnerJoinStructure, foreignTable.getTableName(), foreignTable.getAlias(),
                    foreignTable.getAlias() + "." + foreignTable.getPrimaryKey(),
                    headTable.getAlias() + "." + foreignTable.getPrimaryKey());
            foreignTables.add(foreignTable);
        }

    }

    private void intersectionTableCombiner() {
        for (PojoReflection table : intersectionTables) {
            for (PojoReflection foreignTable : table.getForeignTables()) {
                if (foreignTable.getTableName().equals(headTable.getTableName()))
                    innerJoin += String.format(BaseInnerJoinStructure, table.getTableName(), table.getAlias(), table.getAlias()
                                    + "." + table.getForeignKeys().get(headTable.getTableName()),
                            headTable.getAlias() + "." + headTable.getPrimaryKey()
                    );
                else
                    innerJoin += String.format(BaseInnerJoinStructure, foreignTable.getTableName(), foreignTable.getAlias(),
                            table.getAlias() + "." + table.getForeignKeys().get(foreignTable.getTableName()),
                            foreignTable.getAlias() + "." + foreignTable.getPrimaryKey());
            }
        }
    }

    private void extraTableCombiner() {
        for (PojoReflection table : extraTables) {
            if (table.getForeignKeys().get(headTable.getTableName()) == null)
                break; // TODO Give Exception Message

            innerJoin += String.format(BaseInnerJoinStructure, table.getTableName(), table.getAlias(), table.getAlias() + "." +
                            table.getForeignKeys().get(headTable.getTableName()),
                    headTable.getAlias() + "." + headTable.getPrimaryKey()
            );
        }
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
