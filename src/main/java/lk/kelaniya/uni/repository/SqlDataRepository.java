package lk.kelaniya.uni.repository;

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
    public void executeQuery(String query) throws DataRepositoryException {
        try {

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            // Now do something with the ResultSet ....
        } catch (SQLException e) {

            throw new DataRepositoryException(e, e.getMessage());

        } finally {

            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new DataRepositoryException(e, e.getMessage());
                }

                resultSet = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new DataRepositoryException(e, e.getMessage());
                }

                statement = null;
            }
        }


    }
}
