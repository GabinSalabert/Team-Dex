/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Loader;

import Metier.IPokemon;
import Metier.Type;
import Metier.Pokemon;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.*;
import org.jsoup.nodes.*;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.jsoup.select.*;

/**
 *
 * @author gryfe
 */
public class HTMLLoader implements IPkmLoader {
    private Elements tr; //contains all table row for 1st gen pokemons
    private static final String baseUrl = "http://www.pokemontrash.com/pokedex/"; //Constante Url for pokemon profile + img
    

    HTMLLoader() {

    }


    /**
     * Recupere une liste d'IPokemon depuis Internet (pokemontrash.com)
     * @return La liste d'IPokemon recupérée
     * @throws Exception
     */
    @Override
    public ObservableList<IPokemon> getLPkms() throws Exception {
        ObservableList<IPokemon> lpkm = FXCollections.observableArrayList();
        getPkmData();

        IPokemon p;
        for (Element e : tr) {
            if (e != null) {
                p = getPokemon(e);
                if (p != null) {
                    lpkm.add(p); //add pokemon to list
                }
                else
                    System.out.println("failed to add to list");
            }
        }

        return lpkm;
    }


    /**
     * Recupere l'HTML contenant les données pour chaque pokémon.
     */
    //Get all html about each pokemon
    private void getPkmData() { 
        try {
            Document doc = Jsoup.connect(baseUrl + "liste-pokemon.php").get(); //get page
                   
            tr = doc.select("h2#gen1 + table > tbody > tr"); //select each table row with 1st gen pokemon
           
        } catch (IOException ex) {
            System.out.println("Failed to connect to website");
            Logger.getLogger(HTMLLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Renvoie les stats a partir de l'HTML les contenant
     * @param stats HTML contenant les stats
     * @return
     */
    //get stats for pokemon
    private ArrayList<Integer> getStats(Elements stats) {
        ArrayList<Integer> lstats = new ArrayList<>(); //list of stats

        for (Element s : stats) {
            lstats.add(Integer.valueOf(s.select("div.graphe").text()));
        }
        
        return lstats;
    }

    /**
     * Renvoie les types a partir de l'HTML les contenant
     * @param types HTML contenant les stats
     * @return
     */
    //get types for pokemon
    private ObservableList<Type> getTypes(Elements types) {
        ObservableList<Type> ltypes = FXCollections.observableArrayList();
        for (Element el : types) {
                    ltypes.add(new Type(el.text()));   
        }
        
        return ltypes;
    }

    /**
     * Renvoie un IPokemon depuis l'HTML
     * @param e
     * @return
     */
    //return pokemon
    private IPokemon getPokemon(Element e) {
        String name;
        int id;
        String smallSprite;
        String link;
        int pv = -1, speed= -1, att= -1, def= -1, attspe= -1, defspe= -1;
        
        Elements td = e.select("td");

        id = Integer.parseInt(td.get(0).text());
        smallSprite = baseUrl + td.get(1).select("img").attr("src");
        name = td.get(2).select("a").text();
        link = baseUrl + td.get(2).select("a").attr("href");
        
        
        try {
            Document pkmhtml = Jsoup.connect(link).get(); //get Pokemon page's html

            Elements stats = pkmhtml.select("div#pokemon-strateges > table:first-child > tbody:nth-child(2) > tr"); //select stats' table body
            Elements types = pkmhtml.select("div#pokemon-types span");
            Elements img = pkmhtml.select("div#pokemon-image > img");
            System.out.println(id);
            return new Pokemon(name, id, smallSprite, img.get(0).attr("src"), getStats(stats), getTypes(types));

        } catch (IOException ex) {
            System.out.println("failed to get pokemon page");
            Logger.getLogger(HTMLLoader.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }

    
    
  
}

