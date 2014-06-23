package nl.dennisvdwielen.database.builders;

import nl.dennisvdwielen.database.mapping.PojoReflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dennis on 30-5-2014 at 00:44)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.mapping
 */

/**
 * This class makes the complete select part of a query. All fields with aliases will be printed. The fields will be
 * collected from the PojoReflections and parameters such as the group_concat field
 */
public class SelectBuilder {

    private final String selectBase = "SELECT ";

    private LinkedList<PojoReflection> tables;
    private List<Field> groupBy;
    private String statement;

    /**
     * This constructor is part of a overloading.
     *
     * @param tables  LinkedList of PojoReflections of used tables
     * @param builder The builder from the innerjoin builder
     */
    public SelectBuilder(LinkedList<PojoReflection> tables, JoinBuilder builder) {
        this(tables, builder, null);
    }

    /**
     * This constructor is part of a overloading. This ocntructor also includes a groupby parameter
     *
     * @param tables  LinkedList of PojoReflections of used tables
     * @param builder The builder from the innerjoin builder
     * @param groupBy List of Strings with the fields that have to be concatenated in the query
     */
    public SelectBuilder(LinkedList<PojoReflection> tables, JoinBuilder builder, List<String> groupBy) {
        this.tables = tables;

        this.groupBy = new ArrayList<Field>();

        if (groupBy != null)
            for (String value : groupBy)
                this.groupBy.add(builder.findField(value));

        statement = createSelect();
    }

    /**
     * This method creates the select query. It uses al the pojoreflections and thus fields. And further checks for
     * double fields. This method only provides the looping sequence.
     *
     * @return A Select query with all the fields
     */
    private String createSelect() {
        String result = "";

        ArrayList<String> addedFields = new ArrayList<String>();

        while (tables.iterator().hasNext()) {
            PojoReflection table = tables.poll();
            if (table.getForeignTables().size() > 0) {
                for (PojoReflection foreignTable : table.getForeignTables()) {
                    for (PojoReflection innerForeign : foreignTable.getForeignTables()) {
                        result = loopFields(result, addedFields, innerForeign);
                    }

                    result = loopFields(result, addedFields, foreignTable);
                }

            }

            result = loopFields(result, addedFields, table);
        }

        return result;
    }

    /**
     * This method is also part of the previous method. Is loops multiple times though all the fields and takes the
     * group_concat into account. the building the query
     *
     * @param result      The result that has already been done by previous loops
     * @param addedFields An ArrayList of String fields that are already done.
     * @param table       The table in which the loop has to be performed
     * @return A string with te results
     */
    private String loopFields(String result, ArrayList<String> addedFields, PojoReflection table) {
        for (Field field : table.getFields()) {
            if (!addedFields.contains(field.getName())) {

                String queryField = String.format("%s.%s", table.getAlias(), field.getName());

                if (groupBy != null)
                    for (Field groupField : groupBy) {
                        if (field.getName().equalsIgnoreCase(groupField.getName())) {
                            result += (result.equals("")) ? String.format("GROUP_CONCAT(DISTINCT(%s), '') %s ", queryField, field.getName())
                                    : String.format(", GROUP_CONCAT(DISTINCT(%s), '') %s ", queryField, field.getName());

                            addedFields.add(field.getName());
                        }
                    }

                if (!addedFields.contains(field.getName())) {
                    result += (result.equals("")) ? String.format("%s ", queryField)
                            : String.format(",%s ", queryField);
                    addedFields.add(field.getName());
                }
            }
        }

        return result;
    }

    /**
     * Getter: Gets the created statement
     *
     * @return
     */
    public String getStatement() {
        return statement;
    }
}
