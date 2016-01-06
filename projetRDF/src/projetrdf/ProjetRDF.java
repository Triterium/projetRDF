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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Olivier
 */
public class ProjetRDF {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, InvalidFormatException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
            MainFrame mf = new MainFrame();
        

    }

}
