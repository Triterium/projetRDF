/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Olivier
 */
public class MainFrame extends JFrame implements ActionListener {

    private List<JButton> ljb;
    private Datas d;
    private JPanel panelCentral;

    public MainFrame(Datas d) {
        this.d = d;
        this.setLayout(new BorderLayout());
        panelCentral = new JPanel();
        ljb = new ArrayList<>();
        ljb.add(new JButton("choix fichier"));
        JPanel jp = new JPanel();

        for (JButton jb : ljb) {
            jb.addActionListener(this);
            jp.add(jb);
        }

        this.add(jp, BorderLayout.WEST);
        this.add(panelCentral, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(600, 600));
        this.pack();
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton jb = (JButton) e.getSource();
        if (jb.getText().equals("choix fichier")) {
            String repertoireCourant = new File("").getAbsolutePath();
            JFileChooser dialogue = new JFileChooser(repertoireCourant + System.getProperty("file.separator") + "Wang");

            // affichage
            dialogue.showOpenDialog(null);

            // récupération du fichier sélectionné
            String fichierChoisi = formatFileName(dialogue.getSelectedFile().getAbsolutePath());
            d.switchIndividu(fichierChoisi);
            ImageIcon icon = new ImageIcon("Wang"+System.getProperty("file.separator")+fichierChoisi);
            JLabel img = new JLabel(icon);
            JPanel jp = new JPanel();
            jp.add(img);
            this.regeneratePanel(jp);
            KPPV kppv = new KPPV(null,null);
            Individu[] tabKPPV = kppv.calculKPPV(d.test.get(0), d.ensembleApprentissage, 20);
            AfficheImageFrame aif = new AfficheImageFrame(Arrays.asList(tabKPPV));
            System.out.println("Taux erreur : " + kppv.calculErreur(tabKPPV, d.test.get(0)) + "%");
            d.reset();
        }

    }

    private String formatFileName(String s) {
        String separator = System.getProperty("file.separator");
        String[] slash = s.split(Pattern.quote(separator));
        return slash[slash.length - 1];
    }

    private void flush() {
        if (this.panelCentral != null) {
            this.remove(this.panelCentral);
            this.panelCentral = null;
        }
    }

    private void regeneratePanel(JPanel jp) {
        System.out.println("jp? : " + jp);
        flush();
        this.panelCentral = jp;
        this.add(this.panelCentral, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
        this.pack();
    }

}
