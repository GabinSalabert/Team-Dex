/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Metier.IPokemon;

import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Comparator;

import Metier.Type;
import Modele.Facade;
import Modele.PkmListCell;
import Modele.TeamListCell;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Paint;

import Comparator.*;
import Metier.Team;
import java.io.IOException;
import javafx.beans.binding.StringExpression;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Gabin
 */
public class DexController implements Initializable {


    private Facade facade;

    @FXML
    TextField filterField;
    @FXML
    ScrollPane scrollMaster;


    @FXML
    private ListView<IPokemon> entries;
    @FXML
    private ObservableList<IPokemon> masterData = FXCollections.observableArrayList();
    @FXML
    private ComboBox<Comparator<IPokemon>> sort;
    private ObservableList<Comparator<IPokemon>> pkmComparators;
    private SortedList<IPokemon> sortedList;


    @FXML
    ListView<Team> teams = new ListView<>();
    @FXML
    ListView<IPokemon> teamPkms;

    @FXML
    private StatsController test;
   
    @FXML
    Label name;
    @FXML
    ImageView sprite;
    @FXML
    Label dnd;
    


    private StringProperty selName = new SimpleStringProperty();
        public String getSelName() {
            if (facade == null ) return "bb";
            return facade.getSelPkm().nameProperty().get();
        }
        public StringProperty selNameProperty(){
            if (facade == null ) return new SimpleStringProperty("");
            return facade.getSelPkm().nameProperty();
        }

    
    @FXML
    public void newTeam(javafx.scene.input.MouseEvent arg0) {
        manageTeam(false);

    }


    /**
     * Cette fonction s'occupe de l'ajout/edition de team.
     * @param edit Ce boolean est a true lorsque la fonction est appelée pour editer le nom de l'équipe séléctionnée, et a false lors de la création d'une équipe.
     */
    public void manageTeam (boolean edit) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resources/TeamName.fxml"));
        TeamNameController controller = new TeamNameController(facade, edit);
        // Set it in the FXMLLoader
        loader.setController(controller);
        try {
            Scene scene = new Scene(loader.load(), 250, 50);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Choose the team's name !");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(name.getScene().getWindow());
            stage.showAndWait();
            teams.refresh();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    @FXML
    public void editTeam (javafx.scene.input.MouseEvent arg0) {
        manageTeam(true);
    }

   
    @FXML
    public void deleteTeam (javafx.scene.input.MouseEvent arg0) {
        facade.getLTeam().remove(teams.getSelectionModel().getSelectedItem());
    }

    /**
     * Supprime l'équipe séléctionnée
     * @param arg0
     */
    @FXML
    public void removePkmFromTeam(javafx.scene.input.MouseEvent arg0) {
        System.out.println("removing ");
        teams.getSelectionModel().getSelectedItem().getLPkm().remove(teamPkms.getSelectionModel().getSelectedItem());
    }

    /**
     * Actualise le tableau de stats
     * @param arg0
     */
    @FXML
    public void handle (javafx.scene.input.MouseEvent arg0) {
        
        name.setTextFill(Paint.valueOf(entries.getSelectionModel().getSelectedItem().getLtypes().get(0).getColor()));
        
        test.getPv().setText(Integer.toString(entries.getSelectionModel().getSelectedItem().getPv()));
        test.getSpeed().setText(Integer.toString(entries.getSelectionModel().getSelectedItem().getSpeed()));
        test.getAtt().setText(Integer.toString(entries.getSelectionModel().getSelectedItem().getAtt()));
        test.getDef().setText(Integer.toString(entries.getSelectionModel().getSelectedItem().getDef()));
        test.getAttspe().setText(Integer.toString(entries.getSelectionModel().getSelectedItem().getAttspe()));
        test.getDefspe().setText(Integer.toString(entries.getSelectionModel().getSelectedItem().getDefspe()));
        
        test.getPvbar().setProgress(entries.getSelectionModel().getSelectedItem().getPv()/200.0);
        test.getSpeedbar().setProgress(entries.getSelectionModel().getSelectedItem().getSpeed()/200.0);
        test.getAttbar().setProgress(entries.getSelectionModel().getSelectedItem().getAtt()/200.0);
        test.getDefbar().setProgress(entries.getSelectionModel().getSelectedItem().getDef()/200.0);
        test.getAttspebar().setProgress(entries.getSelectionModel().getSelectedItem().getAttspe()/200.0);
        test.getDefspebar().setProgress(entries.getSelectionModel().getSelectedItem().getDefspe()/200.0);
        
    }


    /**
     * Initialise les listeners necessaires au drag and drop
     */
    //DRAG'N'DROP
    @FXML
    private void initializeListeners(){
        entries.setOnDragDetected(new EventHandler<javafx.scene.input.MouseEvent>(){
            @Override
            public void handle(javafx.scene.input.MouseEvent event){
                System.out.println("setOnDragDetected");

                Dragboard dragBoard = entries.startDragAndDrop(TransferMode.COPY);

                ClipboardContent content = new ClipboardContent();

                content.putString(String.valueOf(entries.getSelectionModel().getSelectedIndex()));

                dragBoard.setContent(content);
            }
        });
        
        entries.setOnDragDone(new EventHandler<DragEvent>(){
            @Override
            public void handle(DragEvent dragEvent){
                System.out.println("setOnDragDone");
            }
        });
        
        entries.setOnDragEntered(new EventHandler<DragEvent>(){
            @Override
            public void handle(DragEvent dragEvent){
                System.out.println("setOnDragEntered");

                //entries.setBlendMode(BlendMode.DIFFERENCE);
            }
        });
        
        teamPkms.setOnDragEntered(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("setOnDragEntered");

                //teams.setBlendMode(BlendMode.DIFFERENCE);
            }
        });

        teamPkms.setOnDragExited(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("setOnDragExited");

                teams.setBlendMode(null);
            }
        });

        teamPkms.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("setOnDragOver");

                dragEvent.acceptTransferModes(TransferMode.COPY);
            }
        });

        teamPkms.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent dragEvent) {
                System.out.println("setOnDragDropped");

                int id = Integer.parseInt(dragEvent.getDragboard().getString());
                try {
                    if (teams.getSelectionModel().getSelectedItem().isFull()) {
                        System.out.println("team full");
                    }
                    else {
                        teamPkms.getItems().add(entries.getItems().get(id));
                        System.out.println(entries.getItems().get(id).getName());
                        //teams.getSelectionModel().getSelectedItem().addPokemon(entries.getItems().get(id));
                    }
                } catch(Exception e) {
                    System.out.println(e.getMessage());
                }
                dragEvent.setDropCompleted(true);
            }
        });
    
    }
    
    @FXML
    public void onSortByComparator() {
        sortedList.setComparator(sort.getSelectionModel().getSelectedItem());
    }


    /**
     * Initialise la listview pour la recherche et le tri
     */
    public void initListViewPkms() {
        //Sort and search.
        pkmComparators = FXCollections.observableArrayList(
                new IdComparator(),
                new AlphabeticalComparator(),
                new TypeComparator()
        );
        sort.setItems(pkmComparators);

        sort.getSelectionModel().select(0);

        FilteredList<IPokemon> filteredList = new FilteredList<>(masterData, p -> true);

        filterField.textProperty().addListener(obs->{
            String filter = filterField.getText();
            if(filter == null || filter.length() == 0) {
                filteredList.setPredicate(p -> true);
            }
            else {
                filteredList.setPredicate(p -> p.getName().toLowerCase().contains(filter));
            }
        });

        // 3. Wrap the FilteredList in a SortedList.
        sortedList = new SortedList<>(filteredList);

        // 4. Bind the SortedList comparator to the List comparator.
        //sortedList.comparatorProperty().bind(entries.comparatorProperty());

        // 5. Add sorted (and filtered) data to the List.
        entries.setItems(sortedList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        facade = new Facade();
        facade.loadPkms();
        facade.savePkms();
        
        facade.getLTeam().add(new Team("First Team"));

        for (IPokemon p : facade.getLPkm()) {
            masterData.add(p);
        }       

        entries.setCellFactory(param -> new PkmListCell());
        entries.getSelectionModel().select(0);

        facade.selPkmProperty().bind(entries.getSelectionModel().selectedItemProperty());

        teamPkms.setCellFactory(param -> new PkmListCell());

        teams.setCellFactory(param -> new TeamListCell());
        teams.itemsProperty().bind(facade.lTeamProperty());

        facade.selTeamProperty().bind(teams.getSelectionModel().selectedItemProperty());
        System.out.println("yolo");
        int a = 1;
        sprite.imageProperty().bind( Bindings.select(facade.selPkmProperty(), "sprite"));
        name.textProperty().bind( Bindings.selectString(facade.selPkmProperty(), "name"));
        teamPkms.itemsProperty().bind( Bindings.select(facade.selTeamProperty(), "lPkm"));
        //dnd.visibleProperty().bind(Bindings.isEmpty(teamPkms.getItems())); TODO: fix nullException
        


        initListViewPkms();
        initializeListeners();
    }


}