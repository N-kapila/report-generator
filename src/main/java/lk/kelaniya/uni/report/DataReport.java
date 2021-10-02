package lk.kelaniya.uni.report;

import lk.kelaniya.uni.models.DataResult;
import lk.kelaniya.uni.repository.DataRepositoryException;

public interface DataReport {
    DataResult generate() throws DataRepositoryException;;
}
