import org.jooq.Field;
import org.jooq.Table;

public class TestGrandSlamModel<T> {
    Object test_player1, test_player2, test_match;
    Field<T>[] test_fields;
    Table<?> test_table;
    String test_tourney_name, test_tourney_date;
    int test_id;

    public TestGrandSlamModel(Object test_player1, Object test_player2, Field<T>[] test_collection, Table<?> test_table, String test_tourney_name, int test_id) {
        this.test_player1 = test_player1;
        this.test_player2 = test_player2;
        this.test_fields = test_collection;
        this.test_table = test_table;
        this.test_tourney_name = test_tourney_name;
        this.test_id = test_id;
    }

    public TestGrandSlamModel(Object test_player1, Object test_player2, Object test_match, Field<T>[] test_fields, Table<?> test_table, String test_tourney_name, String test_tourney_date, int test_id) {
        this.test_player1 = test_player1;
        this.test_player2 = test_player2;
        this.test_match = test_match;
        this.test_fields = test_fields;
        this.test_table = test_table;
        this.test_tourney_name = test_tourney_name;
        this.test_tourney_date = test_tourney_date;
        this.test_id = test_id;
    }
}
