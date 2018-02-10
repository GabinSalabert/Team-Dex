/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.Pokemon;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
/**
 * FXML Controller class
 *
 * @author Gabin
 */
public class StatsController extends GridPane {

    @FXML
    private Label pv; 
    @FXML
    private Label speed;
    @FXML
    private Label att;
    @FXML 
    private Label def;
    @FXML 
    private Label attspe;
    @FXML 
    private Label defspe;
    @FXML
    private ProgressBar pvbar;
    @FXML
    private ProgressBar speedbar;
    @FXML
    private ProgressBar attbar;
    @FXML
    private ProgressBar defbar;
    @FXML
    private ProgressBar attspebar;
    @FXML
    private ProgressBar defspebar;
    

    public Label getPv() {
        return pv;
    }
    
    public Label getSpeed() {
        return speed;
    }
    
    public Label getAtt() {
        return att;
    }
    
    public Label getDef() {
        return def;
    }
    
    public Label getAttspe() {
        return attspe;
    }
    
    public Label getDefspe() {
        return defspe;
    }
    
    public ProgressBar getPvbar() {
        return pvbar;
    }
    
    public ProgressBar getSpeedbar() {
        return speedbar;
    }
    
    public ProgressBar getAttbar() {
        return attbar;
    }
    
    public ProgressBar getDefbar() {
        return defbar;
    }
    
    public ProgressBar getAttspebar() {
        return attspebar;
    }
    
    public ProgressBar getDefspebar() {
        return defspebar;
    }
    
    
    
    
    
    
    public StatsController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
        "/Resources/Stats.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
            
    }
    
   
}
