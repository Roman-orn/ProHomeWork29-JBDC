import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String url = "jdbc:postgresql://localhost:5432/company";
    private static final String username = "postgres";
    private static final String password = "postgres";

    public static Connection getConnection() throws SQLException {

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(username);
        dataSource.setPassword(password);

        return dataSource.getConnection();
    }
}
