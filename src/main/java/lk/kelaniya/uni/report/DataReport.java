package lk.kelaniya.uni.report;

import lk.kelaniya.uni.output.DataResult;
import lk.kelaniya.uni.repository.DataRepositoryException;

public interface DataReport {
    DataResult generate() throws DataRepositoryException;;
}
