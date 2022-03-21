import eu.hansolo.tilesfx.tools.Helper;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestResultSetStatementJDBC {

    @Test
    public void testConnSetResult() throws SQLException, IOException {

        Connection jdbcConnection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);

        Mockito.when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(true).thenReturn(false);
        Mockito.when(resultSet.getString(1)).thenReturn("table_r3").thenReturn("table_r1").thenReturn("table_r2");

        Statement statement = Mockito.mock(Statement.class);
        Mockito.when(statement.executeQuery("select name from tables")).thenReturn(resultSet);

        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);

        }
    }

