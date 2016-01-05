/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetrdf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 *
 * @author Olivier
 */
/**
 * Cette classe sert à calculer le kppv en rentrant la liste de classe de base et la liste d'individu. 
 * La fonction calcul prend en paramètre le k et est censée retourner la liste de classe agrémentée des individus. 
 * @author Olivier
 */
public class KPPV {
    
    private List<Classe> lc;
    private List<Individu> li;
    
    
    public KPPV(List<Classe> lc, List<Individu> li)
    {
        this.lc = lc;
        this.li = li;
    }
    
    public List<Classe> calcul(int k, List<Integer> lint)
    {
        Individu[] ppv = new Individu[k];
        for(int i = 0;i<k;i++)
        {
            ppv[i] = lc.get(0).getIndividu(i);
        }
        int cpt = 0;
        for(Individu i : li)
        {
            for(Classe c : lc)
            {
                for(Individu in : c.getIndividus())
                {
                    ppv = majTab(ppv, i, in);
                }
            }
            int indiceClasse = this.lc.indexOf(this.choixClasse(ppv));
            lint.add(indiceClasse+1);
            this.lc.get(indiceClasse).addIndividu(i);
        }
        
        
        return lc;
    }
    
    private double distance(Individu i1, Individu i2)
    {
        int somme = 0;
        for(int i=0; i<i1.getNbMesures(); i++)
            somme += Math.pow(i1.getMesures()[i] - i2.getMesures()[i], 2);
        
        return Math.sqrt(somme);
    }
    
    private Individu[] calculKPPV(Individu aTester, List<Individu> ensemble, int k)
    {
        Individu[] kppv = new Individu[k];
        boolean check;
        int cpt;
        
        for(int i=0; i<k; i++)
        {
            kppv[i] = ensemble.get(0);
        }
        
        for(Individu i : ensemble) 
        {
            cpt = 0;
            do
            {
                check = false;
                if(this.distance(aTester, i) < this.distance(aTester, kppv[cpt]))
                {
                    check = true;
                    for(int j=cpt; j<k-1; j++)
                    {
                        kppv[j+1] = kppv[j];
                    }
                    kppv[cpt] = i;
                }
            cpt ++;
            }while(check || cpt == 5);
        }
        
        return kppv;
    }
    
    /**
     * Etant donné un individu et un tableau des kppv, savoir si 
     * cet individu fait partie des plus proche voisins ou pas. Si oui, réorganise le tableau en poussant les autres éléments
     * Le plus proche élément est le premier
     * @param tab
     * @param ref
     * @param i
     * @return 
     */
    private Individu[] majTab(Individu[] tab,Individu ref, Individu i)
    {
        Individu[] newTab = tab;
        double dist = distance(ref, i);
        for(int j = 0;j<newTab.length;j++)
        {
            if(dist < distance(ref, newTab[j]))
            {
                for(int k = j;k<newTab.length-1;k++)
                {
                    newTab[k+1] = newTab[k];
                }
                newTab[j] = i;
                break;
            }
        }
        return newTab;
    }
    
    /**
     * permet de savoir quelle classe parmi le tableau des plus proches voisins
     * @param ppv
     * @return 
     */
    private Classe choixClasse(Individu[] ppv)
    {
        HashMap<Classe, Integer> hm = new HashMap<>();

            
        for(Individu i : ppv)
        {
            for(Classe c : this.lc)
            {
                if(c.has(i))
                {
                    if(hm.containsKey(c))
                    {
                        hm.put(c, hm.get(c)+1);
                    }
                    else
                    {
                        hm.put(c, 1);
                    }
                }
            }
        }
        int max = 0;
        Classe c = null;
        for(Map.Entry<Classe, Integer> entry : hm.entrySet())
        {
            if (entry.getValue() > max)
            {
                max = entry.getValue();
                c = entry.getKey();
            }
        }
        int nbOccurence = 0;
        for(Map.Entry<Classe, Integer> entry : hm.entrySet())
        {
            if (entry.getValue() == max)
            {
                nbOccurence++;
            }
        }
        if(nbOccurence > 1)
        {
            Random r = new Random();
            return lc.get(r.nextInt(this.lc.size()-1));
        }
        return c;
    }
}
