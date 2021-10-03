package lk.kelaniya.uni;

import lk.kelaniya.uni.UI.UI;
import lk.kelaniya.uni.inputs.CommandLineInputData;
import lk.kelaniya.uni.inputs.InputException;
import lk.kelaniya.uni.inputs.Inputs;
import lk.kelaniya.uni.inputs.JsonFileInputData;
import lk.kelaniya.uni.output.DataOutput;
import lk.kelaniya.uni.output.DataOutputException;
import lk.kelaniya.uni.output.DataOutputFactory;
import lk.kelaniya.uni.output.DataResult;
import lk.kelaniya.uni.report.DataReport;
import lk.kelaniya.uni.report.DataReportFactory;
import lk.kelaniya.uni.repository.DataRepository;
import lk.kelaniya.uni.repository.DataRepositoryException;
import lk.kelaniya.uni.repository.SqlDataRepository;

public class ReportGeneratorApp {
    final private Inputs commandLineInputs;
    final private Inputs jsonFileInputs;
    final private DataReportFactory dataReportFactory;
    final private DataOutputFactory dataOutputFactory;
    final private UI ui;

    public ReportGeneratorApp(Inputs commandLineInputs, Inputs jsonFileInputs, DataReportFactory dataReportFactory, DataOutputFactory dataOutputFactory, UI ui) {
        this.commandLineInputs = commandLineInputs;
        this.jsonFileInputs = jsonFileInputs;
        this.dataReportFactory = dataReportFactory;
        this.dataOutputFactory = dataOutputFactory;
        this.ui = ui;
    }

    public void run() {
        try {
            CommandLineInputData commandLineInputData = (CommandLineInputData) commandLineInputs.getInputs();
            JsonFileInputData jsonFileInputData = (JsonFileInputData) jsonFileInputs.getInputs();

            DataRepository dataRepository = new SqlDataRepository(jsonFileInputData);
            dataRepository.connect();

            DataReportFactory dataReportFactory = new DataReportFactory();
            DataReport dataReport = dataReportFactory.getInstance(
                    commandLineInputData.getReportType(),
                    commandLineInputData.getStartDate(),
                    commandLineInputData.getEndDate(),
                    dataRepository
            );
            DataResult dataResult = dataReport.generate();

            DataOutputFactory dataOutputFactory = new DataOutputFactory();
            DataOutput dataOutput = dataOutputFactory.getInstance(
                    commandLineInputData.getOutputType(),
                    dataResult,
                    jsonFileInputData,
                    commandLineInputData.getEmail()
            );
            dataOutput.execute();

        } catch (InputException | DataRepositoryException | DataOutputException e) {
            ui.showErrorMessage("Error occur:" + e.getMessage());
        }

        ui.showSuccessMessage("Your report successfully generated!");

    }
}
