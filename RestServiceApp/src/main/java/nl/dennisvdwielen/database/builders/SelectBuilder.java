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
public class SelectBuilder {

    private final String selectBase = "SELECT ";

    private LinkedList<PojoReflection> tables;
    private List<Field> groupBy;
    private String statement;


    public SelectBuilder(LinkedList<PojoReflection> tables, JoinBuilder builder) {
        this(tables, builder, null);
    }

    public SelectBuilder(LinkedList<PojoReflection> tables, JoinBuilder builder, List<String> groupBy) {
        this.tables = tables;

        this.groupBy = new ArrayList<Field>();

        if (groupBy != null)
            for (String value : groupBy)
                this.groupBy.add(builder.findField(value));

        statement = createSelect();
    }

    private String createSelect() {
        String result = "";

        ArrayList<String> addedFields = new ArrayList<String>();

        while (tables.iterator().hasNext()) {
            PojoReflection table = tables.poll();
            if (table.getForeignTables().size() > 0)
                for (PojoReflection foreignTable : table.getForeignTables())
                    result = loopFields(result, addedFields, foreignTable);

            result = loopFields(result, addedFields, table);
        }

        return result;
    }

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

    public String getStatement() {
        return statement;
    }
}
