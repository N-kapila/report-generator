package lk.kelaniya.uni.repository;

import lk.kelaniya.uni.output.DataResult;

public interface DataRepository {

    void connect() throws DataRepositoryException;

    DataResult executeQuery(String query) throws DataRepositoryException;
}
