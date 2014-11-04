/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autohearthstone;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Bash
 */
public class AutoHearthstone implements Serializable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BiffException, IOException, WriteException {
        // TODO code application logic here
        Hearthstone hs = new Hearthstone();
    }
    
}
