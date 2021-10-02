package lk.kelaniya.uni.repository;

public interface DataRepository {
    void connect() throws DataRepositoryException;
    void executeQuery(String query) throws DataRepositoryException;
}
