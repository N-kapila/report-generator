package lk.kelaniya.uni.output;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ExcelOutputFile implements OutputFile {

    @Override
    public void write() throws OutputException {
        //Create blank workbook instance
        XSSFWorkbook workbook = new XSSFWorkbook();

        //Create a blank sheet inside the workbook
        XSSFSheet sheet = workbook.createSheet("Sheet1");

        // Setting Foreground Color
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.PLUM.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[]{"ID", "NAME", "LASTNAME"});
        data.put("2", new Object[]{1, "Amit", "Shukla"});
        data.put("3", new Object[]{2, "Lokesh", "Gupta"});
        data.put("4", new Object[]{3, "John", "Adwards"});
        data.put("5", new Object[]{4, "Brian", "Schultz"});

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            // create a new row
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                // create a new cell
                Cell cell = row.createCell(cellnum++);

                if (rownum==1){
                    cell.setCellStyle(style);
                }

                // set the value in the cell
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }

        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\NETHMINIDEVYANJALEE\\Desktop\\demo1.xlsx"));
            workbook.write(out);
            out.close();
            System.out.println("demo1.xlsx written successfully.");
        } catch (Exception e) {
             throw new OutputException(e,"");
        }

    }
}

