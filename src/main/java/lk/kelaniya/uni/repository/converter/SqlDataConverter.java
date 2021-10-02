package lk.kelaniya.uni.repository.converter;

import lk.kelaniya.uni.repository.DataResult;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlDataConverter implements DataConverter {

    public DataResult convert(ResultSet sqlResultSet) throws SQLException {
        DataResult result = new DataResult();


        ResultSetMetaData md = sqlResultSet.getMetaData();

        int columnCount = md.getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            result.addFieldName(md.getColumnName(i));
        }

        while (sqlResultSet.next()) {
            ArrayList<Object> row = new ArrayList<>();

            for (int i = 1; i <= columnCount; i++) {
                row.add(sqlResultSet.getObject(i));
            }

            result.addRecord(row);
        }

        return result;


    }
}
