/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Olivier
 */
public class MultiImagePanel extends JPanel {

    List<Individu> li;

    public MultiImagePanel(List<Individu> li) {
        this.li = li;
        this.setLayout(new GridLayout(li.size()/6+1, 6));
        for(Individu i : li)
        {
            ImageIcon icon = new ImageIcon("Wang"+System.getProperty("file.separator")+i.getNom())/*.getImage().getScaledInstance(Size.width, Size.height, Image.SCALE_DEFAULT))*/;
            JLabel img = new JLabel(icon);
            this.add(img);
        }
        this.setBorder(BorderFactory.createLineBorder(Color.red));
        this.setVisible(true);
        this.setPreferredSize(new Dimension(Size.width*6, Size.height * (li.size()/6+1)));
        
        
    }
    
    
}
