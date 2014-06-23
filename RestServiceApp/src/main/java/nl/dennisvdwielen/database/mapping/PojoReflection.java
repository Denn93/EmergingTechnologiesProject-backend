package nl.dennisvdwielen.database.mapping;

import nl.dennisvdwielen.annotations.ForeignKey;
import nl.dennisvdwielen.annotations.PrimaryKey;
import nl.dennisvdwielen.annotations.Table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dennis on 26-5-2014 at 01:50)
 * <p/>
 * This code is part of the RestServiceApp project.
 * This class is within package nl.dennisvdwielen.mapping
 */

/**
 * This class represent a table in the database and a entity from a class. The entity classes use custom annotations
 * which are used in this class the determine all the important data from a database table.
 */
public class PojoReflection {

    private Class pojo;
    private String tableName;
    private String alias;

    private Field[] fields;
    private String primaryKey;

    private HashMap<String, String> foreignKeys;
    private ArrayList<PojoReflection> foreignTables;

    /**
     * Contructor which start the conversion from class to a reflection
     *
     * @param pojo The class to be converted
     */
    public PojoReflection(Class pojo) {
        this.pojo = pojo;
        this.fields = pojo.getDeclaredFields();
        this.foreignKeys = new HashMap<String, String>();
        this.foreignTables = new ArrayList<PojoReflection>();

        findTableInformation();
    }

    /**
     * This method finds all annotations and sets the different field in this class.
     * E.g. Gets the primary key. Gets the table name, alias etc
     */
    private void findTableInformation() {
        Table table = (Table) pojo.getAnnotation(Table.class);
        tableName = (table != null) ? table.tableName() : "";
        alias = (table != null) ? table.alias() : "";

        for (Field field : fields) {
            PrimaryKey pk = field.getAnnotation(PrimaryKey.class);

            if (pk != null)
                primaryKey = pk.fieldName();

            ForeignKey fk = field.getAnnotation(ForeignKey.class);

            if (fk != null) {
                foreignKeys.put(fk.tableName(), fk.fieldName());
                addForeignTable(fk.tableName());
            }

        }
    }

    /**
     * This class add a new reflection of a foreign key.
     * @param tableName Get new class by String table name
     */
    private void addForeignTable(String tableName) {
        try {
            tableName = tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
            foreignTables.add(new PojoReflection(Class.forName("nl.dennisvdwielen.entity." + tableName)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter Table name
     * @return Table name
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Getter Alias
     * @return Alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Getter Primary Key
     * @return Primary Key
     */
    public String getPrimaryKey() {
        return primaryKey;
    }

    /**
     * Getter ForeignKeys
     * @return All Foreign Keys in a HashMap<Table name, key>
     */
    public HashMap<String, String> getForeignKeys() {
        return foreignKeys;
    }

    /**
     * Getter All found fields
     * @return Field Array with Field objects
     */
    public Field[] getFields() {
        return fields;
    }

    /**
     * Getter ForeignTable Reflections
     * @return ArrayList of Foreign table reflections
     */
    public ArrayList<PojoReflection> getForeignTables() {
        return foreignTables;
    }
}
