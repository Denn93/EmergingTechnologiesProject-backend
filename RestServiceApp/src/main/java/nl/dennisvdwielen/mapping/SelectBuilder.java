package nl.dennisvdwielen.mapping;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Dennis on 30-5-2014 at 00:44)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.mapping
 */
public class SelectBuilder {

    private final String selectBase = "SELECT ";

    private ArrayList<PojoReflection> tables;
    private Field groupBy;
    private String statement;


    public SelectBuilder(ArrayList<PojoReflection> tables) {
        this(tables, null);
    }

    public SelectBuilder(ArrayList<PojoReflection> tables, Field groupBy) {
        this.tables = tables;
        this.groupBy = groupBy;

        statement = createSelect();
    }

    private String createSelect() {
        String result = "";

        ArrayList<String> addedFields = new ArrayList<String>();

        for (PojoReflection table : tables) {
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
                    if (field.getName().equalsIgnoreCase(groupBy.getName())) {
                        result += (result.equals("")) ? String.format("GROUP_CONCAT(%s, '') %s ", queryField, field.getName())
                                : String.format(", GROUP_CONCAT(%s, '') %s ", queryField, field.getName());

                        addedFields.add(field.getName());
                        continue;
                    }

                result += (result.equals("")) ? String.format("%s ", queryField)
                        : String.format(",%s ", queryField);

                addedFields.add(field.getName());
            }
        }

        return result;
    }

    public String getStatement() {
        return statement;
    }
}
