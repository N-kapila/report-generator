package lk.kelaniya.uni.inputs;

public class CommandLineInputs implements Inputs {
    final private String args[];

    public CommandLineInputs(String[] args) {
        this.args = args;
    }

    @Override
    public CommandLineInputData getInputs() throws InputException {
        int argsLen = args.length;
        if (argsLen != 4 && argsLen != 5) {
            throw new InputException("Invalid argument format");
        }

        String reportType = args[0];
        String startDate = args[1];
        String endDate = args[2];
        String outputTypeStr = args[3].toUpperCase();
        String email = null;

        // To validate report type
        if (!reportType.equals("day-wise-sale-report")) {
            throw new InputException("Invalid report type");
        }

        // To validate dates
        if (!isDateValid(startDate)) {
            throw new InputException("Invalid start date");
        }
        if (!isDateValid(endDate)) {
            throw new InputException("Invalid end date");
        }

        // To check output format
        if (!(outputTypeStr.equals("EMAIL") || outputTypeStr.equals("FILE"))) {
            throw new InputException("Invalid output format");
        }

        if (outputTypeStr.equals("EMAIL")) {

            if (argsLen != 5) {
                throw new InputException("please provide valid email");
            }

            email = args[4];

            if (!isValidEmail(email)) {
                throw new InputException("Invalid Email Format");
            }

        }


        CommandLineInputData inputData = new CommandLineInputData();
        inputData.setReportType(reportType);
        inputData.setStartDate(startDate);
        inputData.setEndDate(endDate);
        inputData.setOutputType(outputTypeStr.equals("FILE") ? OutputType.File : OutputType.Email);
        inputData.setEmail(email);

        return inputData;

    }

    private boolean isDateValid(String date) {
        String strDateRegEx = "\\d{4}-\\d{2}-\\d{2}";
        return date.matches(strDateRegEx);
    }

    private boolean isValidEmail(String email) {
        String strEmailRegEx = "^(.+)@(.+)$";
        return email.matches(strEmailRegEx);
    }
}
