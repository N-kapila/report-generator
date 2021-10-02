package lk.kelaniya.uni.repository;

import lk.kelaniya.uni.repository.converter.SqlDataConverter;

import java.sql.*;

public class SqlDataRepository implements DataRepository {

    private Connection conn = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

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

    @Override
    public DataResult executeQuery(String query) throws DataRepositoryException {


        return null;

    }
}
