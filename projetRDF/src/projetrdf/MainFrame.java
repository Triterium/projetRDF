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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Olivier
 */
public class MainFrame extends JFrame implements ActionListener {

    private List<JButton> ljb;
    private JPanel panelCentral;
    private JComboBox WangSignature;
    private JTextField nbVoisins;

    public MainFrame() {
        panelCentral = new JPanel();
        this.WangSignature = new JComboBox();
        JLabel nbVoisinsLabel = new JLabel("Nombre de voisins : ");
        this.nbVoisins = new JTextField();
        ljb = new ArrayList<>();
        ljb.add(new JButton("Trouver les k pp images"));
        ljb.add(new JButton("Classer une image"));
        ljb.add(new JButton("Calcul erreur ( peux prendre du temps )"));
        JPanel jp = new JPanel();
        WangSignature.addItem("WangSignaturesPHOG");
        WangSignature.addItem("WangSignaturesJCD");
        WangSignature.addItem("WangSignaturesCEDD");
        WangSignature.addItem("WangSignaturesFCTH");
        WangSignature.addItem("WangSignaturesFuzzyColorHistogr");

        for (JButton jb : ljb) {
            jb.addActionListener(this);
            jp.add(jb);
        }

        Box b = Box.createVerticalBox();
        b.add(Box.createGlue());
        b.add(WangSignature);
        JPanel jp2 = new JPanel();
        jp2.add(nbVoisinsLabel);
        jp2.add(nbVoisins);
        nbVoisins.setPreferredSize(new Dimension(100, 20));
        b.add(jp2);
        b.add(jp);
        b.add(Box.createGlue());
        this.add(b);

        this.setPreferredSize(new Dimension(400, 300));
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
        if (jb.getText().equals("Trouver les k pp images")) {
            try {
                String repertoireCourant = new File("").getAbsolutePath();
                JFileChooser dialogue = new JFileChooser(repertoireCourant + System.getProperty("file.separator") + "Wang");
                int nbVoisin = 5;
                try {
                    nbVoisin = Integer.parseInt(nbVoisins.getText());
                } catch (Exception ex) {
                }

                // affichage
                dialogue.showOpenDialog(null);

                // récupération du fichier sélectionné
                ExcelManager em = new ExcelManager((String) this.WangSignature.getSelectedItem());
                Datas d = em.extractData();
                ArrayList<Individu> ai = (ArrayList<Individu>) d.ensembleApprentissage;
                ArrayList<Individu> ai2 = (ArrayList<Individu>) ai.clone();

                KPPV kppv = new KPPV(null, null);
                /*
                double erreur = 0.;
                int cpt = 0;
                for (Individu i : ai2) {
                    d.switchIndividu(i.getNom());
                    Individu[] li2 = kppv.calculKPPV(d.test.get(0), d.ensembleApprentissage, 5);
                    erreur += kppv.calculErreur(li2, d.test.get(0));
                    d.reset();
                }
                System.out.println("erreur : " + erreur / (double) (d.ensembleApprentissage.size()-1));
                */
                String fichierChoisi = formatFileName(dialogue.getSelectedFile().getAbsolutePath());
                d.switchIndividu(fichierChoisi);
                ImageFrame imf = new ImageFrame(d, nbVoisin);

                imf.pack();
                d.reset();

                /*
                
                ImageIcon icon = new ImageIcon("Wang"+System.getProperty("file.separator")+fichierChoisi);
                JLabel img = new JLabel(icon);
                JPanel jp = new JPanel();
                jp.add(img);
                this.regeneratePanel(jp);
                KPPV kppv = new KPPV(null,null);
                <<<<<<< HEAD
                AfficheImageFrame aif = new AfficheImageFrame(Arrays.asList(kppv.calculKPPV(d.test.get(0), d.ensembleApprentissage, 4)));
                 */
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(jb.getText().equals("Calcul erreur ( peux prendre du temps )")){
            try {
                ExcelManager em = new ExcelManager((String) this.WangSignature.getSelectedItem());
                Datas d = em.extractData();
                ArrayList<Individu> ai = (ArrayList<Individu>) d.ensembleApprentissage;
                ArrayList<Individu> ai2 = (ArrayList<Individu>) ai.clone();

                KPPV kppv = new KPPV(null, null);
                
                double erreur = 0.;
                int cpt = 0;
                int[][] tab_classes = new int[10][10];
                for (Individu i : ai2) {
                d.switchIndividu(i.getNom());
                Individu[] li2 = KPPV.calculKPPV(d.test.get(0), d.ensembleApprentissage, 5);
                double erreurIndividu = KPPV.calculErreur(li2, d.test.get(0));

                tab_classes[KPPV.choixClasse(li2).get(0)][d.test.get(0).getClasse()]++;

                erreur += erreurIndividu; 
                d.reset();
                }
                for(int j=0;j<tab_classes.length; j++)
                {
                    for(int k=0;k<tab_classes.length; k++)
                    {
                        System.out.println("Classe[" + j + "][" + k + "] : " + tab_classes[k][j]);   
                    }
                }
                JOptionPane.showMessageDialog(this, "erreur : " + erreur / (double) (d.ensembleApprentissage.size()-1) + "%");
                //System.out.println("erreur : " + erreur / (double) (d.ensembleApprentissage.size()-1));
                
            } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (jb.getText().equals("Classer une image")){
                try {
                    String repertoireCourant = new File("").getAbsolutePath();
                    JFileChooser dialogue = new JFileChooser(repertoireCourant + System.getProperty("file.separator") + "Wang");
                    int nbVoisin = 5;
                    try {
                        nbVoisin = Integer.parseInt(nbVoisins.getText());
                    } catch (Exception ex) {
                    }
                    
                    // affichage
                    dialogue.showOpenDialog(null);
                    
                    // récupération du fichier sélectionné
                    ExcelManager em = new ExcelManager((String) this.WangSignature.getSelectedItem());
                    Datas d = em.extractData();
                    ArrayList<Individu> ai = (ArrayList<Individu>) d.ensembleApprentissage;
                    ArrayList<Individu> ai2 = (ArrayList<Individu>) ai.clone();
                    
                    KPPV kppv = new KPPV(null, null);
                    String fichierChoisi = formatFileName(dialogue.getSelectedFile().getAbsolutePath());
                    d.switchIndividu(fichierChoisi);
                    Individu[] ppv = KPPV.calculKPPV(d.test.get(0), d.ensembleApprentissage, nbVoisin);
                    String classesMajoritaires = "";
                    for(Integer i : kppv.choixClasse(ppv)){
                        classesMajoritaires += i;
                        classesMajoritaires += ", ";
                    }
                    JOptionPane.showMessageDialog(this, "Classe(s) principale(s) de l'image : " + classesMajoritaires);
                    d.reset();
                    
                    
                } catch (IOException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidFormatException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
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
