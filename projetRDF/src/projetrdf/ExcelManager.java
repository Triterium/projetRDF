/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Olivier
 */
public class ExcelManager {

    String path = "WangSignatures.xls";

    public ExcelManager() {
    }

    public Datas extractData() throws FileNotFoundException, IOException, InvalidFormatException {
        System.out.println(new File(path).getAbsolutePath());
        FileInputStream fip = new FileInputStream(new File(path));
        Workbook workbook = WorkbookFactory.create(fip);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        List<Individu> li = new ArrayList<>();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                System.out.println(cell.getCellType()+"]");
                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print("(string)"+cell.getStringCellValue());
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print("numeric"+cell.getNumericCellValue());
                        
                        break;
                }
                System.out.print(" - ");
            }
            System.out.println();
        }
        return null;
    }

}
