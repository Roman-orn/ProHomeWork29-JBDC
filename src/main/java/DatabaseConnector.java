import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String url = "jdbc:postgresql://localhost:5432/company";
    private static final String username = "postgres";
    private static final String password = "postgres";
    private static PGSimpleDataSource dataSource;

    public static Connection getConnection() throws SQLException {

        if (dataSource == null) {
            dataSource = new PGSimpleDataSource();
            dataSource.setUrl(url);
            dataSource.setUser(username);
            dataSource.setPassword(password);
        }

        return dataSource.getConnection();
    }
}
