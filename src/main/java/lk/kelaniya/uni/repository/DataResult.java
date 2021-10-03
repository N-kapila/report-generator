package lk.kelaniya.uni.repository;

import java.util.ArrayList;

public class DataResult {

    private ArrayList<String> fieldNames = new ArrayList<>();
    private ArrayList<ArrayList<Object>> records = new ArrayList<>();

    public void addRecord(ArrayList<Object> record) {
        records.add(record);
    }

    public void addFieldName(String name) {
        fieldNames.add(name);
    }

    public ArrayList<String> getFieldNames() {
        return fieldNames;
    }

    public ArrayList<ArrayList<Object>> getRecords() {
        return records;
    }
}
