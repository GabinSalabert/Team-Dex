/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparator;


import Metier.IPokemon;
import Metier.Pokemon;

import java.util.Comparator;
/**
 *
 * @author elraffray
 */
public class AlphabeticalComparator implements Comparator<IPokemon>{

    @Override
    public int compare(IPokemon o1, IPokemon o2) {
        if (o1 != null && o2 != null) {
            if (o1.getName() != null && o2.getName()!= null) {
                return o1.getName().compareTo(o2.getName());
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Nom";
    }    
}