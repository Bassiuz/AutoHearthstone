package ExcelReader;

import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import jxl.read.biff.BiffException;

import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * Created by Bash on 3-11-2014.
 */
public class DataReader {

    public DataReader()
    {

    }

    public static int getVanillaValue(int Power, int Toughness)
            throws BiffException, IOException, WriteException {

        Workbook workbook = Workbook.getWorkbook(new File("E://Documents//HearthstoneExcel//HearthstoneBoardEvaluation.xls"));
        Sheet sheet = workbook.getSheet(0);
        Cell cell2 = sheet.getCell(Power + 3, Toughness + 5);
        try{
            int num = Integer.parseInt( cell2.getContents());
            // is an integer!
            return num;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static int getLifeValue(int Life)
            throws BiffException, IOException, WriteException {

        Workbook workbook = Workbook.getWorkbook(new File("E://Documents//HearthstoneExcel//HearthstoneBoardEvaluation.xls"));
        Sheet sheet = workbook.getSheet(2);
        Cell cell2 = sheet.getCell(3, Life + 3);
        try{
            int num = Integer.parseInt( cell2.getContents());
            // is an integer!
            return num;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
