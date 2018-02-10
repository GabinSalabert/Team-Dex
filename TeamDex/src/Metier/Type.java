/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.io.Serializable;

/**
 *
 * @author elone
 */

public class Type implements Comparable, Serializable{
    private String type;
    private String color;
    
    public String getType() { return this.type; }
    public String getColor() { return this.color; }

    
    public Type(String type) {
        this.type = type;
        
        switch(type) {
            case "Eau":
                this.color = "#6890F0";
                break;
            case "Feu":
                this.color = "#F08030";
                break;
            case "Plante":
                this.color = "#78C850";
                break;
            case "Électrik":
                this.color = "#00ffff";
                break;
            case "Roche":
                this.color = "#B8A038";
                break;
            case "Psy":
                this.color = "#F85888";
                break;
            case "Poison":
                this.color = "#A040A0";
                break;
            case "Combat":
                this.color = "#C03028";
                break;
            case "Vol":
                this.color = "#A890F0";
                break;
            case "Glace":
                this.color = "#98D8D8";
                break;
            case "Spectre":
                this.color = "#705898";
                break;
            case "Dragon":
                this.color = "#7038F8";
                break;   
            case "Insecte":
                this.color = "#A8B820";
                break;
            case "Sol":
                this.color = "#E0C068";
                break;
            case "Normal":
                this.color = "#A8A878";
                break;
            case "Fée":
                this.color = "#A8A878";
                break;
            case "Acier":
                this.color = "#EE99AC";
                break;
            default:
                System.out.println(type + "not found");
                break;
                
        }
    }
    
    

    @Override
    public int compareTo(Object o) {
        
        if (this == o) return 0;
        
        if (o == null) return 1;
        if (o.getClass() == this.getClass())
            return this.type.compareTo(((Type)o).type);
        return 1;
    }
    
}
