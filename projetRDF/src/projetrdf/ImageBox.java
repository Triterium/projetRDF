/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.awt.Image;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Olivier
 */
public class ImageBox {

    Individu i;

    public ImageBox(Individu i) {
        this.i = i;
    }

    public Box createBox() {
        Box b = Box.createHorizontalBox();
        b.add(Box.createGlue());
        ImageIcon icon = new ImageIcon(new ImageIcon("Wang" + System.getProperty("file.separator") + i.getNom()).getImage().getScaledInstance(Size.width, Size.height, Image.SCALE_DEFAULT));
        JLabel img = new JLabel(icon);
        b.add(img);
        b.add(Box.createGlue());
        return b;
    }
}
