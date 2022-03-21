package demo5;

import com.example.demo5.Database;

public class JDBCController {
    private Database database;

    public JDBCController(Database database) {
        this.database = database;
    }

    public boolean query(String query) {
        return database.isAvailable();
    }


    @Override
    public String toString() {
        return "Using database with id: " + String.valueOf(database.getUniqueId());
    }

}
