package lk.kelaniya.uni;

import lk.kelaniya.uni.models.DataResult;
import lk.kelaniya.uni.output.DataOutput;
import lk.kelaniya.uni.output.DataOutputException;
import lk.kelaniya.uni.output.ExcelDataEmailOutput;
import lk.kelaniya.uni.report.DataReport;
import lk.kelaniya.uni.report.DayWiseOrderReport;
import lk.kelaniya.uni.repository.DataRepository;
import lk.kelaniya.uni.repository.DataRepositoryException;
import lk.kelaniya.uni.repository.SqlDataRepository;

public class Main {

    public static void main(String[] args) {
        DataRepository dataRepository = new SqlDataRepository();
        try {
            dataRepository.connect();

            DataReport dataReport = new DayWiseOrderReport(dataRepository,"2005-01-01","2013-01-01");
            DataResult result = dataReport.generate();
            DataOutput dataOutput = new ExcelDataEmailOutput(result,"Report","nethminid1999@gmail.com");
            dataOutput.execute();
        } catch (DataRepositoryException | DataOutputException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Welcome to JAVA Report Generator");
    }

}
