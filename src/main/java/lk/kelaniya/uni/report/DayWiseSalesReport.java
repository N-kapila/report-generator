package lk.kelaniya.uni.report;

import lk.kelaniya.uni.output.DataResult;
import lk.kelaniya.uni.repository.DataRepository;
import lk.kelaniya.uni.repository.DataRepositoryException;

public class DayWiseSalesReport implements DataReport {
    final private DataRepository dataRepository;

    final private String startDate;
    final private String endDate;

    public DayWiseSalesReport(DataRepository dataRepository, String startDate, String endDate) {
        this.dataRepository = dataRepository;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public DataResult generate() throws DataRepositoryException {
        String query = "SELECT" +
                "`orderDate`," +
                "COUNT(DISTINCT `orders`.orderNumber) AS 'Number of Orders'," +
                "SUM(`quantityOrdered`*`priceEach`) AS 'Income'" +
                "FROM `orderdetails`" +
                "INNER JOIN `orders` ON `orders`.`orderNumber`= `orderdetails`.`orderNumber`" +
                "WHERE `orderDate` BETWEEN '" + startDate + "' AND '" + endDate + "'" +
                "GROUP BY DAY(`orderDate`)";

        DataResult result = dataRepository.executeQuery(query);

        result.setName("day-wise-report-from-" + startDate + "-to-" + endDate);

        return result;
    }
}
