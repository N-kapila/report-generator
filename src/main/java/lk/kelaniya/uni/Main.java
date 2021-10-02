package lk.kelaniya.uni;

import lk.kelaniya.uni.output.DataOutput;
import lk.kelaniya.uni.output.DataOutputException;
import lk.kelaniya.uni.output.ExcelDataEmailOutput;
import lk.kelaniya.uni.report.DataReport;
import lk.kelaniya.uni.report.DayWiseOrderReport;
import lk.kelaniya.uni.repository.DataRepository;
import lk.kelaniya.uni.repository.DataRepositoryException;
import lk.kelaniya.uni.repository.DataResult;
import lk.kelaniya.uni.repository.SqlDataRepository;

public class Main {

    public static void main(String[] args) {
        DataRepository dataRepository = new SqlDataRepository();
        DataReport dataReport = new DayWiseOrderReport(dataRepository, "2003-01-02", "2013-01,31");
        try {
            dataRepository.connect();
            DataResult result = dataReport.generate();
            DataOutput dataOutput = new ExcelDataEmailOutput(result, "test", "nirmalkapilarathne1998@gmail.com");
            dataOutput.execute();
        } catch (DataRepositoryException | DataOutputException e) {
            e.printStackTrace();
        }
    }

}
