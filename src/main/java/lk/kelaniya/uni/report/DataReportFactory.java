package lk.kelaniya.uni.report;

import lk.kelaniya.uni.repository.DataRepository;

public class DataReportFactory {
    public DataReport getInstance(String reportType,String startData,String endDate, DataRepository dataRepository) {
        switch (reportType) {
            case "day-wise-sale-report":
                return new DayWiseOrderReport(dataRepository,startData,endDate);
            default:
                return new DayWiseOrderReport(dataRepository,startData,endDate);
        }
    }
}
