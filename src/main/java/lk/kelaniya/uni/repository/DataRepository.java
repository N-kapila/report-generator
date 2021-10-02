package lk.kelaniya.uni.repository;

public interface DataRepository {

    void connect() throws DataRepositoryException;

    DataResult executeQuery(String query) throws DataRepositoryException;
}
