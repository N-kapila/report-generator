package lk.kelaniya.uni.repository;

public interface DataRepository {

    void connect() throws DataRepositoryException;

    Result executeQuery(String query) throws DataRepositoryException;
}
