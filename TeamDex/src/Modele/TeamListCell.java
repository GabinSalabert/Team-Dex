package Modele;

import Metier.Pokemon;
import Metier.Team;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class TeamListCell extends ListCell<Team> {

    private BorderPane root = new BorderPane();

    private Label name = new Label();



    public TeamListCell() {
        initComponents();
        setChildrens();
    }



    @Override
    public void updateItem(Team t, boolean empty) {
        super.updateItem(t, empty);
        if (empty) {
            clearContent();
        } else {
            addContent(t);
        }
    }

    private void addContent(Team t) {
        setText(null);
        name.setText(t.getName());
        setGraphic(root);
    }

    private void clearContent() {
        setText(null);
        setGraphic(null);
    }

    /**
     * Initialise les composants (taille, propriétés, etc...)
     */
    private void initComponents() {
        root.prefHeight(50.0);
        root.prefWidth(220.0);
        root.minHeight(50.0);
        root.minWidth(220.0);
        root.setStyle("-fx-background-color: #c0c3c8;");


        name.setAlignment(Pos.CENTER_RIGHT);
        name.setContentDisplay(ContentDisplay.RIGHT);
        name.setPadding(new Insets(0, 0, 0, 10));
        name.setStyle("-fx-text-fill: black;");
    }

    /**
     * Met les composants à l'interieur des autres.
     */
    private void setChildrens() {
        root.setLeft(name);
    }
}
