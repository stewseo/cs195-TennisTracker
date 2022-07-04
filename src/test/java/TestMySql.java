//import com.example.cs195tennis.Util.Tools;
//import org.jooq.*;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Objects;
//
//import static org.jooq.impl.DSL.*;
//
//public class TestMySql {
//
//    public static void testBuildingQueryString() {
//
//        Table<?> wtaPlayerTable = table("WtaPlayer");
//
//        Field<?> wtaPlayerId = field("player_id");
//        Field<?> wtaPlayerFirstName = field("name_first");
//        Field<?> wtaPlayerLastName = field("name_last");
//
//        Tools.print(
//                select(wtaPlayerId)
//                        .from(wtaPlayerTable)
//                        .fetch());
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName)
//                        .from(wtaPlayerTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName)
//                        .from(wtaPlayerTable)
//                        .orderBy(wtaPlayerFirstName));
//
//        Table<?> wtaRankTable = table("WtaRank");
//
//        Field<?> wtaRankDate = field("ranking_date");
//        Field<?> wtaRankPlayerId = field("player");
//        Field<?> wtaRankPoints = field("player");
//        Field<?> wtaRank = field("name_last");
//        Field<?> wtaRankId = field("id");
//
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .innerJoin(wtaRankTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .crossJoin(wtaRankTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .leftJoin(wtaRankTable));
//
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .rightJoin(wtaRankTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .fullJoin(wtaRankTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .fullOuterJoin(wtaRankTable));
//
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .naturalJoin(wtaRankTable));
//        Tools.print(
//                select(wtaPlayerId, wtaPlayerFirstName, wtaPlayerLastName, wtaRankId, wtaRankDate, wtaRankPlayerId, wtaRankPoints, wtaRank)
//                        .from(wtaPlayerTable)
//                        .leftAntiJoin(wtaRankTable));
//
//    }
//
//    static DSLContext mySqlCtx() {
//        return using(connect(), SQLDialect.MYSQL);
//    }
//
//    private static final String driver = "com.mysql.cj.jdbc.Driver";
//    private static final String url = "jdbc:mysql://localhost:3306";
//    private static final String user = "root";
//    private static final String password = "sesame";
//
//    public static Connection connect() {
//        Connection connect = null;
//        System.out.println("Driver: " + driver);
//        System.out.println("url: " + url);
//        System.out.println("user: " + user);
//        System.out.println("password: " + password);
//
//        try {
//            Class.forName(driver);
//
//            connect = DriverManager.getConnection(url, user, "beeboo12");
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//
//        return connect;
//    }
//
//
//        public static void tableInfo(){
//        List<Table<?>> tables = mySqlCtx().meta().getTables();
//        System.out.println("numbers of tables in db: " + tables.size());
//
//        tables.stream().limit(5).forEach(e->{
//            System.out.println("Schema : " + e.getSchema());
//            System.out.println("Tables : " + Objects.requireNonNull(e.getSchema()).getTables());
//            System.out.println("Primary Keys: " + e.getSchema().getPrimaryKeys());
//            System.out.println("Indexes: " + e.getSchema().getIndexes());
//            System.out.println("Unique Keys: " + e.getSchema().getUniqueKeys());
//            System.out.println("Foreign Keys : " + e.getSchema().getForeignKeys());
//            System.out.println("Catalog : " + Objects.requireNonNull(e.getCatalog()).getSchemas());
//        });
//    }
//    public static void catalogInfo(){
//        List<Catalog> catalogs = mySqlCtx().meta().getCatalogs();
//        System.out.println("Catalogs: " + catalogs.size());
//
//        if(catalogs.size() == 1) {
//            List<Schema> schemas = catalogs.get(0).getSchemas();
//            System.out.println("Schemas: " + catalogs.get(0).getSchemas());
//            schemas.forEach(schema-> {
//                System.out.println("\nSchema: " + schema.getName());
//                System.out.println("Number of Tables: " + schema.getTables().size());
//                System.out.println("Tables: " + schema.getTables());
////                System.out.println("Primary keys: " + schema.getPrimaryKeys());
//                System.out.println("Number of Foreign keys: " + schema.getForeignKeys().size());
////                System.out.println("Unique Keys: " + schema.getUniqueKeys());
////                System.out.println("Indexes: " + schema.getIndexes());
//            });
//        }
//
//    }
//    public static void schemaInfo(){
//        List<Schema> schemas = mySqlCtx().meta().getSchemas();
//        System.out.println("number of Schemas in db: " + schemas.size());
//
//        schemas.forEach(schema->{
//            System.out.println("Schema name: " + schema.getName());
//            System.out.println("Tables: " + schema.getTables());
//            System.out.println("Primary keys: " + schema.getPrimaryKeys());
//            System.out.println("Foreign keys: " + schema.getForeignKeys());
//            System.out.println("Unique Keys: " + schema.getUniqueKeys());
//            System.out.println("Indexes: " + schema.getIndexes());
//            System.out.println("\n\n");
//
//        });
//    }
//    public static void sequenceInfo(){
//        List<Sequence<?>> sequences = mySqlCtx().meta().getSequences();
//        sequences.forEach(System.out::println);
//    }
//}
