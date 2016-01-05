/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.util.List;

/**
 *
 * @author Olivier
 */
public class Datas {
    public List<Individu> ensembleApprentissage;
    public List<Individu> test;
    
    public Datas(List<Individu> ea, List<Individu> t)
    {
        this.ensembleApprentissage = ea;
        this.test = t;
    }

    public List<Individu> getEnsembleApprentissage() {
        return ensembleApprentissage;
    }

    public void setEnsembleApprentissage(List<Individu> ensembleApprentissage) {
        this.ensembleApprentissage = ensembleApprentissage;
    }

    public List<Individu> getTest() {
        return test;
    }

    public void setTest(List<Individu> test) {
        this.test = test;
    }
    
    
}
