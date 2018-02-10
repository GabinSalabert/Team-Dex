/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loader;

import Loader.BinaryLoader;
import Loader.HTMLLoader;
import Loader.IPkmLoader;

import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author Gabin
 */
public class LoaderFactory {

    /**
     * Fabrique un loader en fonction de l'existence du fichier de sauvegarde
     * @return Le loader choisi
     */
    public static IPkmLoader getLoader()
    {
        File f = new File("lpkm.bin");
        FileInputStream streamIn;
        if(f.exists() && !f.isDirectory()) {
            return new BinaryLoader();
        }
        else {
            return new HTMLLoader();
        }
    }




}
