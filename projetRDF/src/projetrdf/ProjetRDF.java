/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Olivier
 */
public class ProjetRDF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, InvalidFormatException {
        ExcelManager em = new ExcelManager();

        try {
            Datas d = em.extractData();

            for (Individu i : d.ensembleApprentissage) {
                System.out.println(i.toString());
            }
            MainFrame mf = new MainFrame(d);
        } catch (IOException ex) {
            Logger.getLogger(ProjetRDF.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
