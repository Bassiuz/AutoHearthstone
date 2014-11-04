package ExcelReader;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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
public class DataReader implements Serializable{

    public ArrayList<Integer> p0 = new ArrayList<Integer>();
    public ArrayList<Integer> p1 = new ArrayList<Integer>();
    public ArrayList<Integer> p2 = new ArrayList<Integer>();
    public ArrayList<Integer> p3 = new ArrayList<Integer>();
    public ArrayList<Integer> p4 = new ArrayList<Integer>();
    public ArrayList<Integer> p5 = new ArrayList<Integer>();
    public ArrayList<Integer> p6 = new ArrayList<Integer>();
    public ArrayList<Integer> p7 = new ArrayList<Integer>();
    public ArrayList<Integer> p8 = new ArrayList<Integer>();
    public ArrayList<Integer> p9 = new ArrayList<Integer>();
    public ArrayList<Integer> p10 = new ArrayList<Integer>();
    public Boolean done = false;

    public ArrayList<ArrayList<Integer>> creatureTable = new ArrayList<ArrayList<Integer>>();
    public HashMap<Integer, Integer> lifeValues = new HashMap<Integer, Integer>();

    public DataReader() throws BiffException, IOException, WriteException
    {
        creatureTable.add(p0);
        creatureTable.add(p1);
        creatureTable.add(p2);
        creatureTable.add(p3);
        creatureTable.add(p4);
        creatureTable.add(p5);
        creatureTable.add(p6);
        creatureTable.add(p7);
        creatureTable.add(p8);
        creatureTable.add(p9);
        creatureTable.add(p10);

        Workbook workbook = Workbook.getWorkbook(new File("E://Documents//HearthstoneExcel//HearthstoneBoardEvaluation.xls"));
        Sheet sheet = workbook.getSheet(0);
        for (int p=0; p<11; p++) {
            ArrayList<Integer> thisPower = creatureTable.get(p);
            for (int t=0; t<11; t++) {
                Cell cell = sheet.getCell(t + 2, p + 4);
                int value = Integer.parseInt(cell.getContents());
                thisPower.add(t, value);
            }
        }

        Workbook workbook2 = Workbook.getWorkbook(new File("E://Documents//HearthstoneExcel//HearthstoneBoardEvaluation.xls"));
        Sheet sheet2 = workbook2.getSheet(2);

        for (int l=0; l<30; l++) {
            Cell cell2 = sheet2.getCell(1, 3+l);
            int key = Integer.parseInt(cell2.getContents());
            Cell cell3 = sheet2.getCell(2, 3+l);
            int value = Integer.parseInt(cell3.getContents());
            lifeValues.put(key, value);
        }


        done = true;

    }

    public int getVanillaValue(int Power, int Toughness)
           {

               return creatureTable.get(Toughness).get(Power);

    }

    public int getLifeValue(int Life){

        return lifeValues.get(Life);

    }
}
