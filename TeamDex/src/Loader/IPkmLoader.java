/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loader;

import Metier.IPokemon;
import Metier.Pokemon;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;

import java.io.Serializable;

/**
 *
 * @author elone
 */
public interface IPkmLoader {
    
    public ObservableList<IPokemon> getLPkms() throws Exception;


}
