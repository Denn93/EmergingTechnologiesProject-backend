package nl.dennisvdwielen.mapping;

import nl.dennisvdwielen.annotations.ForeignKey;
import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by Dennis on 26-5-2014 at 01:50)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.mapping
 */
public class PojoReflection {

    private Class pojo;
    private String tableName;
    private String alias;

    private Field[] fields;
    private String primaryKey;

    private HashMap<String, String> foreignKeys;

    public PojoReflection(Class pojo) {
        this.pojo = pojo;
        this.fields = pojo.getDeclaredFields();
        this.foreignKeys = new HashMap<String, String>();

        findTableInformation();
    }

    private void findTableInformation() {
        Table table = (Table) pojo.getAnnotation(Table.class);
        tableName = (table != null) ? table.tableName() : "";
        alias = (table != null) ? table.alias() : "";

        for (Field field : fields) {
            PrimaryKey pk = field.getAnnotation(PrimaryKey.class);

            if (pk != null)
                primaryKey = pk.fieldName();

            ForeignKey fk = field.getAnnotation(ForeignKey.class);

            if (fk != null)
                foreignKeys.put(fk.tableName(), fk.fieldName());
        }
    }

    public String getTableName() {
        return tableName;
    }

    public String getAlias() {
        return alias;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public HashMap<String, String> getForeignKeys() {
        return foreignKeys;
    }
}
