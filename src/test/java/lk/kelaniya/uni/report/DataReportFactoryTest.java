package lk.kelaniya.uni.report;

import lk.kelaniya.uni.inputs.JsonFileInputData;
import lk.kelaniya.uni.repository.SqlDataRepository;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;


public class DataReportFactoryTest {

    @Test
    public void should_return_DayWiseSalesReport_object_when_reportType_is_day_wise_sale_report(){
        DataReportFactory dataReportFactory = new DataReportFactory();
        DataReport dataReport = dataReportFactory.getInstance(
                "day-wise-sale-report",
               "2005-02-11",
               "2005-03-11",
                new SqlDataRepository(new JsonFileInputData())
        );

        assertThat(dataReport,instanceOf(DayWiseSalesReport.class));
    }


}