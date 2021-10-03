package lk.kelaniya.uni.output;

import lk.kelaniya.uni.inputs.OutputType;

public class OutputFactory {
    public DataOutput getInstance(OutputType outputType,DataResult dataResult,String recipientEmail) {
        switch (outputType) {
            case File:
                return new ExcelDataOutput(dataResult);
            default:
                return new ExcelDataEmailOutput(dataResult,recipientEmail);
        }
    }
}
