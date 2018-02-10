/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import Loader.BinaryLoader;
import Loader.IPkmLoader;
import Loader.LoaderFactory;
import Metier.IPokemon;
import Metier.Pokemon;

import Metier.Team;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author elone
 */
public class Facade {


    private IPkmLoader loader;





    private ListProperty<IPokemon> lPkm= new SimpleListProperty<>();
        private void setLPkm(ObservableList<IPokemon> l) { lPkm.set(l); }
        public ObservableList<IPokemon> getLPkm() { return lPkm.get(); }
        public ListProperty<IPokemon> lPkmProperty() { return lPkm; }

    private ObjectProperty<IPokemon> selPkm = new SimpleObjectProperty<>(this, "selpkm", new Pokemon());
        public void setSelPkm(IPokemon p) { selPkm.set(p); }
        public IPokemon getSelPkm() { return selPkm.get(); }
        public ObjectProperty<IPokemon> selPkmProperty() { return selPkm; }



    private ListProperty<Team> lTeam = new SimpleListProperty<>();
        private void setLTeam(ObservableList<Team> l) { lTeam.set(l); }
        public ObservableList<Team> getLTeam() { return lTeam.get(); }
        public ListProperty<Team> lTeamProperty() { return lTeam; }


    private ObjectProperty<Team> selTeam = new SimpleObjectProperty<>();
        public void setSelTeam(Team t) { selTeam.set(t); }
        public Team getSelTeam() { return selTeam.get(); }
        public ObjectProperty<Team> selTeamProperty() { return selTeam; }



    public Facade() {
        setLTeam(FXCollections.observableArrayList());
        setLPkm(FXCollections.observableArrayList());
    }

    /**
     * Load la liste de IPokemons depuis un loader choisi
     */
    public void loadPkms() {

        loader = LoaderFactory.getLoader();

        try {
            setLPkm(loader.getLPkms());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Sauvegarde la liste de pokémons en binaire
     */
    public void savePkms() {
        BinaryLoader.savePkmsToFile(getLPkm());
    }


}
