/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author elone
 */
public class Team {

    private String name;

    private ListProperty<IPokemon> lPkm = new SimpleListProperty<>();
        private void setLPkm(ObservableList<IPokemon> l) { lPkm.set(l); }
        public ObservableList<IPokemon> getLPkm() { return lPkm.get(); }
        public ListProperty<IPokemon> lPkmProperty() { return lPkm; }


    public Team(String name) {
        this.name = name;
        this.setLPkm(FXCollections.observableArrayList());
    }


    public boolean isFull() { return getLPkm().size() == 6; }

    public String getName() {
        return name;
    }
    public void setName(String name) {this.name = name;}

    /**
     * Ajoute un pokemon dans l'équipe si elle n'est pas pleine.
     * @param p
     * @throws Exception
     */
    public void addPokemon(IPokemon p) throws Exception {
        if (getLPkm().size() == 6) {
            throw new Exception("This team is full.");
        }
        getLPkm().add(p);
    }





    
    
}
