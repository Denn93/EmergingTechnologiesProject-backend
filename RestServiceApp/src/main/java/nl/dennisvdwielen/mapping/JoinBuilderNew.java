package nl.dennisvdwielen.mapping;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

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
        intersectionTableCreater();
        extraTableCreater();

        System.out.println(innerJoin);
    }


    private void findForeignHeadTable(PojoReflection table) {
        foreignTables = new ArrayList<PojoReflection>();

        for (PojoReflection foreignTable : table.getForeignTables()) {
            innerJoin += makeInnerJoin(foreignTable, formatField(foreignTable.getAlias(), foreignTable.getPrimaryKey()),
                    formatField(headTable.getAlias(), foreignTable.getPrimaryKey()));
            foreignTables.add(foreignTable);
        }

    }

    private void intersectionTableCreater() {
        for (PojoReflection table : intersectionTables) {
            for (PojoReflection foreignTable : table.getForeignTables()) {
                if (foreignTable.getTableName().equals(headTable.getTableName()))
                    innerJoin += makeInnerJoin(table, formatField(table.getAlias(), table.getForeignKeys().get(headTable.getTableName())),
                            formatField(headTable.getAlias(), headTable.getPrimaryKey()));

                else
                    innerJoin += makeInnerJoin(foreignTable, formatField(table.getAlias(), table.getForeignKeys().get(foreignTable.getTableName())),
                            formatField(foreignTable.getAlias(), foreignTable.getPrimaryKey()));
            }
        }
    }

    private void extraTableCreater() {
        for (PojoReflection table : extraTables) {
            if (table.getForeignKeys().get(headTable.getTableName()) == null)
                break; // TODO Give Exception Message

            innerJoin += makeInnerJoin(table, formatField(table.getAlias(), table.getForeignKeys().get(headTable.getTableName())),
                    formatField(headTable.getAlias(), headTable.getPrimaryKey()));
        }
    }

    private String makeInnerJoin(PojoReflection from, String fromField, String toField) {
        return String.format(BaseInnerJoinStructure, from.getTableName(), from.getAlias(), fromField, toField);
    }

    private String formatField(String alias, String fieldName) {
        return alias + "." + fieldName;
    }

    private LinkedList<PojoReflection> combineTableCollections() {
        LinkedList<PojoReflection> result = new LinkedList<PojoReflection>();
        ArrayList<String> addedTables = new ArrayList<String>();

        if (headTable != null && !addedTables.contains(headTable.getTableName())) {
            result.add(headTable);
            addedTables.add(headTable.getTableName());
        }

        if (intersectionTables != null)
            for (PojoReflection pojoReflection : intersectionTables) {
                if (!addedTables.contains(pojoReflection.getTableName())) {
                    result.add(pojoReflection);
                    addedTables.add(pojoReflection.getTableName());
                }
            }

        if (extraTables != null)
            for (PojoReflection pojoReflection : extraTables) {
                if (!addedTables.contains(pojoReflection.getTableName())) {
                    result.add(pojoReflection);
                    addedTables.add(pojoReflection.getTableName());
                }
            }

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

    public LinkedList<PojoReflection> getAllTables() {
        return combineTableCollections();
    }

    public Field findField(String name) {

        LinkedList<PojoReflection> tables = combineTableCollections();

        while (tables.iterator().hasNext()) {
            PojoReflection current = tables.poll();
            for (Field field : current.getFields())
                if (name.equalsIgnoreCase(field.getName()))
                    return field;

            for (PojoReflection innerForeign : current.getForeignTables())
                for (Field field : innerForeign.getFields())
                    if (name.equalsIgnoreCase(field.getName()))
                        return field;
        }

        return null;
    }

    public String FieldToString(Field value) {

        LinkedList<PojoReflection> tables = combineTableCollections();

        while (tables.iterator().hasNext()) {
            PojoReflection current = tables.poll();
            for (Field field : current.getFields())
                if (value.getName().equalsIgnoreCase(field.getName()))
                    return String.format("%s.%s", current.getAlias(), field.getName());

            for (PojoReflection innerForeign : current.getForeignTables())
                for (Field field : innerForeign.getFields())
                    if (value.getName().equalsIgnoreCase(field.getName()))
                        return String.format("%s.%s", innerForeign.getAlias(), field.getName());
        }

        return "";
    }
}
