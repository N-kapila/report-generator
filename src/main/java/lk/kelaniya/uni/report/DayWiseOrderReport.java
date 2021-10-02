package lk.kelaniya.uni.report;

import lk.kelaniya.uni.models.DataResult;
import lk.kelaniya.uni.repository.DataRepository;
import lk.kelaniya.uni.repository.DataRepositoryException;

public class DayWiseOrderReport implements DataReport {
    final private DataRepository dataRepository;

    final private String startDate;
    final private String endDate;

    public DayWiseOrderReport(DataRepository dataRepository, String startDate, String endDate) {
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
                "WHERE `orderDate` BETWEEN '" + startDate + "' AND '" + endDate + "'"+
                "GROUP BY DAY(`orderDate`)";

        return dataRepository.executeQuery(query);
    }
}
