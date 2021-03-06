package lk.kelaniya.uni.output;

import lk.kelaniya.uni.repository.DataResult;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;


public class ExcelDataOutput implements DataOutput {

    final private DataResult dataResult;
    protected String outputPath = null;

    public ExcelDataOutput(DataResult dataResult) {
        this.dataResult = dataResult;
    }

    @Override
    public void execute() throws DataOutputException {
        final ArrayList<String> fieldsNames = dataResult.getFieldNames();
        final ArrayList<ArrayList<Object>> records = dataResult.getRecords();

        final XSSFWorkbook workbook = new XSSFWorkbook();
        final XSSFSheet sheet = workbook.createSheet("Sheet1");

        Row row = null;
        Cell cell = null;

        row = sheet.createRow(0);

        for (int i = 0; i < fieldsNames.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(fieldsNames.get(i));
        }

        for (int i = 0; i < records.size(); i++) {
            row = sheet.createRow(i + 1);
            for (int j = 0; j < fieldsNames.size(); j++) {
                cell = row.createCell(j);
                Object obj = records.get(i).get(j);
                cell.setCellValue(obj.toString());
            }
        }

        try {
            //Write the workbook in file system
            outputPath = "" + dataResult.getName() + ".xlsx";
            FileOutputStream out = new FileOutputStream(new File(outputPath));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            throw new DataOutputException(e, e.getMessage());
        }


    }
}


