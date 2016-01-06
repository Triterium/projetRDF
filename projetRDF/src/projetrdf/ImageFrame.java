/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Olivier
 */
public class ImageFrame extends JFrame {

    Datas d;

    public ImageFrame(Datas d, int prochesVoisins) {
        this.setLayout(new GridLayout(2, 1));

        KPPV kppv = new KPPV(null, null);
        List<Individu> li = Arrays.asList(kppv.calculKPPV(d.test.get(0), d.ensembleApprentissage, prochesVoisins));
        ImageBox ib = new ImageBox(d.test.get(0));
        MultiImagePanel mip = new MultiImagePanel(li);
        this.add(ib.createBox());
        this.add(mip);

        this.setPreferredSize(new Dimension(mip.getWidth(), 2*mip.getHeight()));
        this.pack();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

}