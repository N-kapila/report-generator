package lk.kelaniya.uni.repository;

public class DataRepositoryException extends Throwable {
    public DataRepositoryException(Exception e, String message) {
        super(message, e);
    }
}