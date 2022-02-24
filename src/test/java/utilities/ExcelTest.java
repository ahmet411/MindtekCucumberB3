package utilities;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelTest {

    public static void main(String[] args) {

        String path="C:\\Users\\akabu\\IdeaProjects\\MindtekCucumber\\src\\test\\resources\\data\\TestData.xlsx";

        try {
            FileInputStream input=new FileInputStream(path);
            Workbook book= new XSSFWorkbook(input);
            Sheet page=book.getSheet("Sheet1");
            String name1=page.getRow(1).getCell(0).toString();
            System.out.println(name1);
            String position1=page.getRow(2).getCell(2).toString();
            System.out.println(position1);

//            page.createRow(3).createCell(0).setCellValue("Kim");
            page.getRow(3).createCell(1).setCellValue("Yan");

            FileOutputStream output=new FileOutputStream(path);
            book.write(output);

        } catch (IOException e) {
            e.printStackTrace();
        }

        ExcelUtils.readExcel("TestData","Sheet1");

        System.out.println(ExcelUtils.getValue(2,3));

        ExcelUtils.setValue(3,2,"Scrum Master");
        ExcelUtils.setValue(3,3,100000);

        List<Map<String, Object>> data=ExcelUtils.getExcelData();

        System.out.println(data.get(2).get("FirstName"));


    }

}
