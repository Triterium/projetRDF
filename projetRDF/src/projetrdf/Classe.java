/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Olivier
 */
public class Classe {

    private List<Individu> individus;
    private Color color;

    public Classe() {
        Random rand = new Random();
        int r = rand.nextInt((255 - 0) + 1) + 0;
        int g = rand.nextInt((255 - 0) + 1) + 0;
        int b = rand.nextInt((255 - 0) + 1) + 0;
        this.color = new Color(r, g, b);
        this.individus = new ArrayList();
    }

    public Classe(List<Individu> i) {
        Random rand = new Random();
        int r = rand.nextInt((255 - 0) + 1) + 0;
        int g = rand.nextInt((255 - 0) + 1) + 0;
        int b = rand.nextInt((255 - 0) + 1) + 0;
        this.color = new Color(r, g, b);
        this.individus = i;
    }

    public List<Individu> getIndividus() {
        return this.individus;
    }

    public int getNbIndividus() {
        return this.individus.size();
    }

    public Individu getIndividu(int index) {
        return this.individus.get(index);
    }

    public Color getColor() {
        return this.color;
    }

    /*public double[][] getCovariance() {
        Variance v = new Variance(this.individus);
        return v.getCovariance();
    }

    public List<Double> getMoyenne() {
        Moyenne m = new Moyenne(this.individus);
        return m.getMoyenne();
    }*/

    public void addIndividu(Individu i) {
        this.individus.add(i);
    }

    public void afficheClasse() {
        System.out.println("============Classe===========");
        for (Individu i : this.individus) {
            System.out.print(i.getMesures()[0] + " | " + i.getMesures()[1]);
            System.out.println("");
        }
    }

    public double[][] convertToTab() {
        double[][] tab = new double[this.individus.size()][this.individus.get(0).getMesures().length];
        for (int i = 0; i < tab.length; i++) {
            tab[i][0] = this.individus.get(i).getMesures()[0];
            tab[i][1] = this.individus.get(i).getMesures()[1];
        }
        return tab;
    }
    
    public boolean has(Individu i)
    {
        for(Individu in : individus)
        {
            if(i.getMesures()[0] == in.getMesures()[0] && i.getMesures()[1] == in.getMesures()[1])
            {
                return true;
            }
        }
        return false;
    }
}
