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
    String sheet;

    public ExcelManager(String sheet) {
        this.sheet = sheet;
    }

    public Datas extractData() throws FileNotFoundException, IOException, InvalidFormatException {
        FileInputStream fip = new FileInputStream(new File(path));
        Workbook workbook = WorkbookFactory.create(fip);
        Sheet firstSheet = workbook.getSheet(sheet);
        Iterator<Row> iterator = firstSheet.iterator();
        List<Individu> li = new ArrayList<>();
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            double[] tabIndividu = new double[nextRow.getLastCellNum()];
            String nomIndividu = "";
            int cpt = 0;
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();
                if (cpt == 0) {
                    nomIndividu = cell.getStringCellValue();
                } else {
                    tabIndividu[cpt - 1] = Double.parseDouble(cell.getStringCellValue());
                }
                cpt++;
                if(cell.getStringCellValue().equals(""))
                {
                    break;
                }
            }
            int classe = 0;
            String[] nom = nomIndividu.split("\\.");
            classe = Integer.parseInt(nom[0])/100;
            li.add(new Individu(nomIndividu, tabIndividu, classe));
        }
        return new Datas(li);
    }

}
