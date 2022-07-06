//package testQuery.testAtpWtaTours;//import com.example.cs195tennis.Data.Dao.DataHandeler;
//import com.example.cs195tennis.Data.Record.GrandSlamRecord;
//import com.example.cs195tennis.Util.Tools;
//import org.jooq.Loader;
//import org.jooq.LoaderError;
//import org.jooq.Record6;
//import org.jooq.Result;
//import org.jooq.meta.jaxb.Logging;
//import org.jooq.meta.jaxb.OnError;
//
//import java.io.IOException;
//
//import static com.example.cs195tennis.TestAtpPlayerAtpRank.ctx;
//import static com.example.cs195tennis.model.Game.GAME;
//import static com.example.cs195tennis.model.GrandSlam.GRANDSLAM;
//import static org.jooq.impl.DSL.field;
//import static org.jooq.impl.DSL.table;
//
//public class TestGrandSlams {
//
//    public static void insertIntoGrandSlam(String path) throws IOException {
//
//        Result<Record6<Object,Object,Object,Object,Object,Object>> rs =
//                ctx()
//                        .select(field("GrandSlamTournament.id"), field("GrandSlamTournament.slam"), field("GrandSlamTournament.player1"),
//                                field("GrandSlamTournament.player2"),field("GrandSlamTournament.match_num"),field("GrandSlamTournament.match_id"))
//                        .from(table("GrandSlamTournament"))
//                        .fetch();
//
//        Tools.title("Load into GrandSlam all records from the rs, Primary key: grandslam_id");
//
//        System.out.println(rs.size());
//        new org.jooq.meta.jaxb.Configuration().withLogging(Logging.WARN);
//        new org.jooq.meta.jaxb.Configuration().withOnError(OnError.LOG);
//
//        Loader<GrandSlamRecord> loader =
//                ctx()
//                        .loadInto(GRANDSLAM)
//                        .batchAll()
//                        .bulkAll()
//                        .commitAll()
//                        .loadRecords(rs)
//                        .fields(GRANDSLAM.ID.as("id"), GRANDSLAM.MATCH_ID, GRANDSLAM.YEAR, GRANDSLAM.NAME,
//                                GRANDSLAM.MATCH_NUM,GRANDSLAM.PLAYER_ONE,GRANDSLAM.PLAYER_TWO)
//                        .execute();
//
//
//
//        int processed = loader.processed();
//        int stored = loader.stored();
//        int ignored = loader.ignored();
//        LoaderError error = loader.errors().get(0);
//
//        System.out.println("processed: " + processed + " stored" + stored + " ignored " + ignored + " error " + error);
//
//    }
//
//    static int i = 1;
//    public static void insertSet() throws IOException {
//
//        Result<Record6<Integer, Integer, Integer, Integer, Integer, Integer>> rs =
//                ctx()
//                        .select(field("gameNo").cast(Integer.class),
//                                field("PointWinner").cast(Integer.class),
//                                field("P1Score").cast(Integer.class),
//                                field("P2Score").cast(Integer.class),
//                                field("p1Winner").cast(Integer.class),
//                                field("p2Winner").cast(Integer.class)
//                        )
//                        .from(table("GrandSlamPointByPoint"))
//                        .fetch();
//
//        System.out.println(rs.size());
//
//
//        rs.forEach(e -> {
//
//
////        Tools.title("Result");
////
////        Loader<GameRecord> loader = null;
////        try {
////            loader = ctx()
////                    .loadInto(GAME)
////                    .batchAll()
////                    .bulkAll()
////                    .commitAll()
////                    .loadRecords(rs)
////                    .fields(GAME.ID, GAME.POINT_WINNER, GAME.P1_SCORE, GAME.P2_SCORE, GAME.P1_WINNER, GAME.P2_WINNER)
////                    .execute();
////        } catch (IOException ex) {
////            ex.printStackTrace();
////        }
////
////        assert loader != null;
////        int processed = loader.processed();
////        int stored = loader.stored();
////        int ignored = loader.ignored();
////        LoaderError error = loader.errors().get(0);
////
////        DataAccessException exception = error.exception();
////        System.out.println("processed: " + processed + " stored" + stored + " ignored " + ignored + " error " + exception);
////
////        int rowIndex = error.rowIndex();
////        String[] row = error.row();
////        Query query = error.query();
//
//
//            int game_num = e.getValue(field("gameNo").cast(Integer.class));
//            int point_winner = e.getValue(field("PointWinner").cast(Integer.class));
//            int p1_score = e.getValue(field("P1Score").cast(Integer.class));
//            int p2_score = e.getValue(field("P2Score").cast(Integer.class));
//            int p1_winner = e.getValue(field("p1Winner").cast(Integer.class));
//            int p2_winner = e.getValue(field("p2Winner").cast(Integer.class));
//
//            ctx().insertInto(GAME,
//                            GAME.ID,
//                            GAME.GAME_NUM,
//                            GAME.POINT_WINNER,
//                            GAME.P1_SCORE,
//                            GAME.P2_SCORE,
//                            GAME.P1_WINNER,
//                            GAME.P2_WINNER
//
//                    )
//                    .values(i++,
//                            game_num,
//                            point_winner,
//                            p1_score,
//                            p2_score,
//                            p1_winner,
//                            p2_winner
//
//                    )
//                    .execute();
//        });
//
////        fields
////                .stream()
////                .map(e -> field(e.toString())
////                )
////                .forEach(System.out::print);
//
//
//    }
//
//
//
//
//
//
//
//
//    public static void pathToCsv(String path, String table) throws IOException {
//        DataHandeler dataHandeler = new DataHandeler();
//        dataHandeler.loadFromCsvIntoMySql(path, "GrandSlamTournament");
//
//    }
//
//}
//
