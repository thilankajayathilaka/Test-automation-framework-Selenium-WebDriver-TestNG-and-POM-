package puma.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataProvider {
    private static final String FILE_PATH = "src/test/resources/loginData.xlsx";

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws IOException {
        return readExcelData(FILE_PATH);
    }

    public static Object[][] readExcelData(String filePath) throws IOException {
        List<Object[]> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip header row

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            String username = row.getCell(0).getStringCellValue();
            String password = row.getCell(1).getStringCellValue();
            String status = row.getCell(2).getStringCellValue(); // Read "valid" or "invalid"

            boolean expectedSuccess = "valid".equalsIgnoreCase(status); // Convert to boolean
            data.add(new Object[]{username, password, expectedSuccess});
        }

        workbook.close();
        fis.close();
        return data.toArray(new Object[0][0]);
    }
}
