package lk.kelaniya.uni.output;

import lk.kelaniya.uni.inputs.JsonFileInputData;
import lk.kelaniya.uni.inputs.OutputType;

public class DataOutputFactory {
    public DataOutput getInstance(
            OutputType outputType,
            DataResult dataResult,
            JsonFileInputData jsonFileInputData,
            String recipientEmail) {
        switch (outputType) {
            case File:
                return new ExcelDataOutput(dataResult);
            default:
                return new ExcelDataEmailOutput(dataResult, jsonFileInputData, recipientEmail);
        }
    }
}
