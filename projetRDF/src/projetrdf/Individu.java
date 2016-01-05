/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

/**
 *
 * @author Pierre
 */
public class Individu {
    
    private final int nb_mesures;
    private final double[] mesures;
    private String nom;
    private int classe;

    public Individu(String nom, double[] m, int classe)
    {
        this.nb_mesures = m.length;
        this.mesures = m;
        this.nom = nom;
        this.classe = classe;
   }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public int getNbMesures()
    {
        return this.nb_mesures;
    }
    
    public double[] getMesures()
    {
        return this.mesures;
    }
 
    public double[][] convertToMatrix()
    {
        double[][] ret = new double[this.getMesures().length][1];
        for(int i = 0;i<this.getMesures().length;i++)
        {
            ret[i][0] = this.getMesures()[i];
        }
        return ret;
    }
    
    @Override
    public String toString()
    {
        String s = "[" + this.nom + " - " + this.classe+ " - ";
        for(double d : this.mesures)
        {
            s += " " + d;
        }
        
        s += "]";
        return s;
    }
}
