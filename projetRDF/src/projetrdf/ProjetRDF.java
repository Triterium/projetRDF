/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Olivier
 */
public class ProjetRDF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ExcelManager em = new ExcelManager();
        try {
            em.extractData();
        } catch (IOException ex) {
            Logger.getLogger(ProjetRDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
