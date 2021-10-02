package lk.kelaniya.uni.report;

import lk.kelaniya.uni.repository.DataRepositoryException;
import lk.kelaniya.uni.repository.DataResult;

public interface DataReport {
    DataResult generate() throws DataRepositoryException;
}
