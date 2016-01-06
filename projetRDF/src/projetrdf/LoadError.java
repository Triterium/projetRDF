/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

/**
 *
 * @author Olivier
 */
public class LoadError extends Thread {

    int cpt = 0;
    int tailleMax;

    public LoadError(int tailleMax) {
        this.tailleMax = tailleMax;
    }

    @Override
    public void run() {
        JFrame jf = new JFrame();
        JProgressBar jpb = new JProgressBar(0, tailleMax);
        jf.add(jpb);
        jf.setPreferredSize(new Dimension(400, 300));
        jf.pack();
        jf.setVisible(true);
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                jf.dispose();
            }
        });
        while(cpt < tailleMax)
        {
            jpb.setValue(cpt);
            System.out.println("cpt : " + cpt);
            jpb.repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(LoadError.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
