package lk.kelaniya.uni.report;

import lk.kelaniya.uni.repository.DataRepository;

public class DataReportFactory {
    public DataReport getInstance(String reportType,String startData,String endDate, DataRepository dataRepository) {
        switch (reportType) {
            case "day-wise-sale-report":
                return new DayWiseSalesReport(dataRepository,startData,endDate);
            default:
                return new DayWiseSalesReport(dataRepository,startData,endDate);
        }
    }
}
