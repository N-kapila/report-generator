package lk.kelaniya.uni;

import lk.kelaniya.uni.UI.CmdLineUI;
import lk.kelaniya.uni.UI.UI;
import lk.kelaniya.uni.inputs.*;
import lk.kelaniya.uni.output.DataOutputFactory;
import lk.kelaniya.uni.report.DataReportFactory;

public class Main {

    public static void main(String[] args) {
        Inputs commandLineInputs = new CommandLineInputs(args);
        Inputs jsonFileInputs = new JsonFileInputs();

        DataReportFactory dataReportFactory = new DataReportFactory();

        DataOutputFactory dataOutputFactory = new DataOutputFactory();

        UI ui = new CmdLineUI();

        ReportGeneratorApp reportGeneratorApp = new ReportGeneratorApp(commandLineInputs, jsonFileInputs, dataReportFactory, dataOutputFactory, ui);

        reportGeneratorApp.run();

    }

}
