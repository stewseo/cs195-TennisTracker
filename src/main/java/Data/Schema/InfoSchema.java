//package Data.Schema;
//
//import Data.Catalog.MyCatalog;
//import Data.Listeners.StatisticsListener;
//import com.example.cs195tennis.Data.Table.Tables;
//import org.jooq.Catalog;
//import org.jooq.Schema;
//import org.jooq.impl.SchemaImpl;
//import org.jooq.util.xml.jaxb.*;
//
//import java.util.Arrays;
//import java.util.List;
//
//
//
//
//public class InfoSchema extends SchemaImpl implements Schema {
//    public static final InfoSchema AP = new InfoSchema("ap");
//    public static final InfoSchema MY_GUITAR_SHOP = new InfoSchema("my_guitar_shop");
//
//    public static final InfoSchema MY_SQL = new InfoSchema("MY_SQL");
//    public static final InfoSchema PERFORMANCE_SCHEMA = new InfoSchema("performance_schema");
//    public static final InfoSchema SYS = new InfoSchema("sys");
//
//
//    private InfoSchema(String name) {
//        super(name, null);
//    }
//
//
//
//    private static final long serialVersionUID = 1825826342;
//
//    public static final InformationSchema INFORMATION_SCHEMA = new InformationSchema();
//
//    public final CheckConstraint CHECK_CONSTRAINTS = new CheckConstraint();
//
//    public final Column COLUMNS = new Column();
//
//    public final KeyColumnUsage KEY_COLUMN_USAGE = new KeyColumnUsage();
//
//    public final Parameter PARAMETERS = new Parameter();
//
//    public final ReferentialConstraint REFERENTIAL_CONSTRAINTS = new ReferentialConstraint();
//
//    public StatisticsListener STATISTICS = new StatisticsListener();
//
//    public final TableConstraint TABLE_CONSTRAINTS = new TableConstraint();
//
//    public final Tables TABLES = new Tables();
//
//    public final View VIEWS = new View();
//
//    private InfoSchema() {
//        super("information_schema", null);
//    }
//
//    @Override
//    public Catalog getCatalog() {
//        return MyCatalog.CATALOG;
//    }
//
////    @Override
////    public final List<Table<?>> getTables() {
////        return new ArrayList<Table<?>>(
////                CHECK_CONSTRAINTS,
////                COLUMNS,
////                KEY_COLUMN_USAGE,
////                PARAMETERS,
////                REFERENTIAL_CONSTRAINTS,
////                STATISTICS,
////                TABLE_CONSTRAINTS,
////                TABLES,
////                VIEWS);
////    }
//}
