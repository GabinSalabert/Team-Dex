/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loader;

import Metier.IPokemon;
import Metier.Pokemon;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import Metier.PokemonBin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author elone
 */
 public class BinaryLoader implements IPkmLoader {

    BinaryLoader() {

    }


    /**
     * Recupere une liste de IPokemon contenue dans le fichier "lpkm.bin"
     * @return La liste de IPokemon
     * @throws Exception
     */
    @Override
    public ObservableList<IPokemon> getLPkms() throws Exception {
        File f = new File("lpkm.bin");
        FileInputStream streamIn;
        if(f.exists() && !f.isDirectory()) { 
            try {
                streamIn = new FileInputStream("lpkm.bin");
                ObjectInputStream ois;
                try {
                    ois = new ObjectInputStream(streamIn);
                    try {
                        System.out.println("Getting from file...");
                        ObservableList<IPokemon> lpkm = FXCollections.observableArrayList();
                        PokemonBin p;
                        int i =0;
                        try {
                            while (true) {
                                p = (PokemonBin) ois.readObject();
                                lpkm.add(p);
                            }
                        } catch (EOFException e) {
                            ois.close();
                            streamIn.close();
                            System.out.println("Getting from file Done.");
                            return lpkm;
                        }

                    } catch (ClassNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                    ois.close();
                    streamIn.close();

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }

            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }
        }
        else
            throw new IOException("lpkm.bin doesn't exists");
        return null;
    }

    /**
     * Sauvegarde la liste de pokémons passée en argument dans un fichier "lpkm.bin"
     * @param lpkm
     * @return
     */
    public static boolean savePkmsToFile(ObservableList<IPokemon> lpkm) {
        FileOutputStream fout;
        ObjectOutputStream oos;


        File f = new File("lpkm.bin");
        FileInputStream streamIn;
        if(f.exists() && !f.isDirectory())
            return false;


        try {
        fout = new FileOutputStream("lpkm.bin");
        try {
            oos = new ObjectOutputStream(fout);

            //getLPkm().stream().map(pok -> new PokemonBin(pok));
            for (IPokemon p : lpkm) {
                oos.writeObject(new PokemonBin(p));
                System.out.println("saving " + p.getName());
            }
            oos.close();
            fout.close();
            return true;
        } catch (IOException ex) {
            Logger.getLogger(HTMLLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HTMLLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


}
