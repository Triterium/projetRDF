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
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Olivier
 */
public class AfficheImageFrame extends JFrame{
    List<Individu> li;
    public AfficheImageFrame(List<Individu> li)
    {
        this.li = li;
        this.setLayout(new GridLayout(1, li.size()));
        
        for(int i = 0;i<li.size();i++)
        {
            ImageIcon icon = new ImageIcon("Wang"+System.getProperty("file.separator")+li.get(i).getNom());
            JLabel img = new JLabel(icon);
            JPanel jp = new JPanel();
            jp.add(img);
            this.add(jp, i);
        }
        
        this.setPreferredSize(new Dimension(600, 600));
        this.pack();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
    
}
