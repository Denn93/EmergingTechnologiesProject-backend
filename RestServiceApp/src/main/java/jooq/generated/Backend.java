/**
 * This class is generated by jOOQ
 */
package jooq.generated;

/**
 * This class is generated by jOOQ.
 */
@javax.annotation.Generated(value = {"http://www.jooq.org", "3.3.2"},
        comments = "This class is generated by jOOQ")
@java.lang.SuppressWarnings({"all", "unchecked", "rawtypes"})
public class Backend extends org.jooq.impl.SchemaImpl {

    /**
     * The singleton instance of <code>backend</code>
     */
    public static final Backend BACKEND = new Backend();
    private static final long serialVersionUID = -1735845224;

    /**
     * No further instances allowed
     */
    private Backend() {
        super("backend");
    }

    @Override
    public final java.util.List<org.jooq.Table<?>> getTables() {
        java.util.List result = new java.util.ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final java.util.List<org.jooq.Table<?>> getTables0() {
        return java.util.Arrays.<org.jooq.Table<?>>asList(
                jooq.generated.tables.User.USER);
    }
}
