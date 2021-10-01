package lk.kelaniya.uni.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDataRepository implements DataRepository {

    private Connection conn = null;

    @Override
    public void connect() throws DataRepositoryException {
        try {
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost/classicmodels?" +
                            "user=root&password=");

        } catch (SQLException e) {
            throw new DataRepositoryException(e, e.getMessage());
        }
    }
}
