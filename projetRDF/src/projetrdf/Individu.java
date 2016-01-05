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
    
    private int nb_mesures;
    private double[] mesures;

    public Individu(double[] m)
    {
        this.nb_mesures = m.length;
        this.mesures = m;
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
}
