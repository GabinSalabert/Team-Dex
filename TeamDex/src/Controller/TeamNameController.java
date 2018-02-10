/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.Team;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import Modele.Facade;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Gabin
 */
public class TeamNameController implements Initializable {

    private ObservableList<Team> lteams;
    private Facade facade;
    private boolean edit;
    
    @FXML
    public TextField name;
    
    @FXML
    Button create;


    public TeamNameController(Facade facade, boolean edit){
        this.facade = facade;
        this.edit = edit;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }

    /**
     * Ajoute/modifie l'equipe en fonction de edit.
     * @param e
     */
    @FXML
    public void manageTeam(Event e) {
        if (!edit)
            facade.getLTeam().add(new Team(name.getText()));
        else
            facade.getSelTeam().setName(name.getText());
        Stage stage = (Stage) create.getScene().getWindow();
        stage.close();
    }




}