package nl.dennisvdwielen.database.builders;

import nl.dennisvdwielen.database.mapping.PojoReflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Dennis on 1-6-2014 at 12:58)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.mapping
 */

/**
 * This class generate the INNER JOIN clauses for the select query.  This class makes use of reflections and the
 * use of the custom annotations
 */
public class JoinBuilder {

    private final String BaseInnerJoinStructure = " INNER JOIN %s %s ON %s=%s ";

    private PojoReflection headTable;
    private ArrayList<PojoReflection> intersectionTables;
    private ArrayList<PojoReflection> extraTables;

    private ArrayList<PojoReflection> foreignTables;

    private String innerJoin;

    /**
     * Constructor sets all the fields. Gets all pojoreflections of the classes. Starts the build sequence
     *
     * @param headTable          The headtable to make the base of the innerjoin
     * @param intersectionTables ArrayList of classes which are intersection tables in the database.
     * @param extraTables        ArrayList of extra tables which can be part of the innerjoin clauses
     */
    public JoinBuilder(Class headTable, ArrayList<Class> intersectionTables, ArrayList<Class> extraTables) {
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

    /**
     * This method creates the first series of innerjoins that are part of the head table
     *
     * @param table The headtable as PojoReflection
     */
    private void findForeignHeadTable(PojoReflection table) {
        foreignTables = new ArrayList<PojoReflection>();

        for (PojoReflection foreignTable : table.getForeignTables()) {
            innerJoin += makeInnerJoin(foreignTable, formatField(foreignTable.getAlias(), foreignTable.getPrimaryKey()),
                    formatField(headTable.getAlias(), foreignTable.getPrimaryKey()));

            for (PojoReflection innnerForeignTable : foreignTable.getForeignTables()) {
                innerJoin += makeInnerJoin(innnerForeignTable, formatField(innnerForeignTable.getAlias(), innnerForeignTable.getPrimaryKey()),
                        formatField(foreignTable.getAlias(), innnerForeignTable.getPrimaryKey()));
            }

            foreignTables.add(foreignTable);
        }
    }

    /**
     * This methods makes all innerjoin clauses that are part of the intersection tables. and adds them to the innerjoin string
     */
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

    /**
     * This methods makes all innerjoin clauses that are part of the extra tables. and adds them to the innerjoin string
     */
    private void extraTableCreater() {
        for (PojoReflection table : extraTables) {
            if (table.getForeignKeys().get(headTable.getTableName()) == null)
                break; // TODO Give Exception Message

            innerJoin += makeInnerJoin(table, formatField(table.getAlias(), table.getForeignKeys().get(headTable.getTableName())),
                    formatField(headTable.getAlias(), headTable.getPrimaryKey()));
        }
    }

    /**
     * This method formats the innerjoin. According to the specified field
     *
     * @param from      The from table
     * @param fromField The from field that has to be used
     * @param toField   The to field that has to be used
     * @return A single innerjoin
     */
    private String makeInnerJoin(PojoReflection from, String fromField, String toField) {
        return String.format(BaseInnerJoinStructure, from.getTableName(), from.getAlias(), fromField, toField);
    }

    /**
     * This method formats a field into a with aliase formatted field name E.g. container.equipmentNumber
     *
     * @param alias     This field is the alias of the table
     * @param fieldName The fieldname
     * @return A formatted field name
     */
    private String formatField(String alias, String fieldName) {
        return alias + "." + fieldName;
    }

    /**
     * This method combines all tables into a single List of Pojoreflections of the tables. The make sure there are no
     * double tables in the innerjoin en and else where
     *
     * @return A LinkedList of Pojoreflections
     */
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

    /**
     * Getter HeadTable
     *
     * @return PojoReflection of headtable
     */
    public PojoReflection getHeadTable() {
        return headTable;
    }

    /**
     * Getter Innerjoin
     *
     * @return The created innerjoin
     */
    public String getInnerJoin() {
        return innerJoin;
    }

    /**
     * Getter Returns all tables
     *
     * @return LinkedList of the combined table set. No double values
     */
    public LinkedList<PojoReflection> getAllTables() {
        return combineTableCollections();
    }

    /**
     * This method finds the a field object by inputting a String name
     *
     * @param name String name input
     * @return A Field object of the given string. If not found return null
     */
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

    /**
     * This method converts a Field value to a string. Formatted with alias
     *
     * @param value The Field value
     * @return A formatted field name as String
     */
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
