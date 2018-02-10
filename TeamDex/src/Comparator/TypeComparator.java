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
public class TypeComparator implements Comparator<IPokemon>{

    @Override
    public int compare(IPokemon o1, IPokemon o2) {
        if (o1 != null && o2 != null) {
            if (o1.getLtypes() != null && o2.getLtypes()!= null) {
                return o1.getLtypes().get(0).compareTo(o2.getLtypes().get(0));
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Type";
    }    
}