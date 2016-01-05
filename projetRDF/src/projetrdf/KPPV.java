/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/*
 *
 * @author Olivier
 */
/**
 * Cette classe sert à calculer le kppv en rentrant la liste de classe de base
 * et la liste d'individu. La fonction calcul prend en paramètre le k et est
 * censée retourner la liste de classe agrémentée des individus.
 */

 public class KPPV {

    private List<Classe> lc;
    private List<Individu> li;

    public KPPV(List<Classe> lc, List<Individu> li) {
        this.lc = lc;
        this.li = li;
    }

    private double distance(Individu i1, Individu i2) {
        int somme = 0;
        for (int i = 0; i < i1.getNbMesures(); i++) {
            somme += Math.pow(i1.getMesures()[i] - i2.getMesures()[i], 2);
        }

        return Math.sqrt(somme);
    }

    public Individu[] calculKPPV(Individu aTester, List<Individu> ensemble, int k) {
        Individu[] kppv = new Individu[k];
        boolean check;
        int cpt;

        for (int i = 0; i < k; i++) {
            kppv[i] = ensemble.get(0);
        }

        for (Individu i : ensemble) {
            cpt = 0;
            do {
                check = false;
                if (this.distance(aTester, i) < this.distance(aTester, kppv[cpt])) {
                    check = true;
                    for(int j = cpt; j < k - 1; j++) {
                        kppv[j + 1] = kppv[j];
                    }
                    kppv[cpt] = i;
                }
                cpt++;
            } while (!check && cpt < k);
        }

        return kppv;
    }

    /**
     * permet de savoir quelle classe parmi le tableau des plus proches voisins
     *
     * @param ppv
     * @return
     */
    public List<Integer> choixClasse(Individu[] ppv) {
        HashMap<Integer, Integer> map_classes = new HashMap<>();
        for (int i = 0; i < ppv.length; i++) {
            if (!map_classes.containsKey(ppv[i].getClasse())) {
                map_classes.put(ppv[i].getClasse(), 0);
            } else {
                map_classes.put(ppv[i].getClasse(),map_classes.get(ppv[i].getClass())+1);
            }
        }
        
        int max = 0;
        for(Entry<Integer, Integer> entry : map_classes.entrySet())
        {
            if(entry.getValue() > max)
            {
                max = entry.getValue();
            }
        }
        
        ArrayList<Integer> classes_proches = new ArrayList<>();
        classes_proches.add(max);
        
        for(Entry<Integer, Integer> entry : map_classes.entrySet())
        {
            if(entry.getValue() == max)
            {
                classes_proches.add(entry.getValue());
            }
        }
        
        return classes_proches;
    }
}
