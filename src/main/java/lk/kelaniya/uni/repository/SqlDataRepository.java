package lk.kelaniya.uni.repository;

import lk.kelaniya.uni.converter.SqlDataConverter;
import lk.kelaniya.uni.inputs.JsonFileInputData;
import lk.kelaniya.uni.output.DataResult;

import java.sql.*;

public class SqlDataRepository implements DataRepository {

    final private JsonFileInputData jsonFileInputData;
    private Connection conn = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public SqlDataRepository(JsonFileInputData jsonFileInputData) {
        this.jsonFileInputData = jsonFileInputData;
    }


    @Override
    public void connect() throws DataRepositoryException {
        try {
            String url = "jdbc:mysql://" + jsonFileInputData.getDatabaseHost() +
                    "/" + jsonFileInputData.getDatabaseName() + "?user="
                    + jsonFileInputData.getDatabaseUserName() + "&password=" + jsonFileInputData.getGetDatabasePassword();
            conn =
                    DriverManager.getConnection(url);

        } catch (SQLException e) {
            throw new DataRepositoryException(e, "Database connection failed, Check database name,password and host");
        }
    }

    @Override
    public DataResult executeQuery(String query) throws DataRepositoryException {

        try {

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            SqlDataConverter dataConverter = new SqlDataConverter();
            return dataConverter.convert(resultSet);

        } catch (SQLException e) {

            throw new DataRepositoryException(e, "Database error.");

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
