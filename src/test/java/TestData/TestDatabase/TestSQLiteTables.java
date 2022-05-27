package TestData.TestDatabase;

import org.jooq.Table;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static com.example.cs195tennis.Dao.PlayerDao.ctx;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestSQLiteTables {

    @Test
    public void testSqlTables() throws SQLException {
        List<Table<?>> r = ctx().meta().getTables();

        assertNotEquals(r.size(), 0);

        for(int i=0; i < r.size(); i++){

            int valuesCount = ctx().fetchCount(DSL.selectFrom(r.get(i)));

            assertNotEquals(valuesCount,0);

            if(valuesCount > 0) {

                assertNotNull(r.get(i).fieldsRow());

                System.out.println("Table Name: " + r.get(i).getName());
                System.out.println("Columns: " + r.get(i).fieldsRow());
                System.out.println("Primary Key: " + r.get(i).getPrimaryKey());
            }
        }
    }
}
