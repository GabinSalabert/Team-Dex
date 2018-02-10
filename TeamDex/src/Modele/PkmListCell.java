package Modele;

import Metier.IPokemon;
import Metier.Pokemon;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

public class PkmListCell extends ListCell<IPokemon> {

    private HBox root = new HBox();
    private VBox lTypes = new VBox();
    private BorderPane bPane = new BorderPane();
    private HBox hBox = new HBox();

    private Rectangle type1 = new Rectangle();
    private Rectangle type2 = new Rectangle();
    private ImageView sprite = new ImageView();
    private Label name = new Label();
    private Separator sep = new Separator();
    private Label id = new Label();



    public PkmListCell() {
        initComponents();
        setChildrens();
    }



    @Override
    public void updateItem(IPokemon p, boolean empty) {
        super.updateItem(p, empty);
        if (empty) {
            clearContent();
        } else {
            addContent(p);
        }
    }

    /**
     * Ajoute un pokemon dans la listeCell
     * @param p
     */
    private void addContent(IPokemon p) {
        setText(null);
        sprite.setImage(p.getSmallSprite());
        name.setText(p.getName());
        id.setText(String.valueOf(p.getId()));
        setGraphic(root);

        type1.setFill(Color.web(p.getLtypes().get(0).getColor()));
        if (p.getLtypes().size() > 1)
            type2.setFill(Color.web(p.getLtypes().get(1).getColor()));
        else
            type2.setFill(Color.web(p.getLtypes().get(0).getColor()));


    }

    private void clearContent() {
        setText(null);
        setGraphic(null);
    }


    /**
     * Initialise les composants (taille, propriétés, etc...)
     */
    private void initComponents() {
        root.minWidth(220.0);
        root.prefHeight(50.0);

        lTypes.prefWidth(5.0);
        lTypes.prefHeight(50.0);

        bPane.minWidth(100.0);

        hBox.setAlignment(Pos.CENTER);
        hBox.setFillHeight(false);


        type1.setWidth(5.0);
        type1.setHeight(25.0);
        type2.setWidth(5.0);
        type2.setHeight(25.0);

        sprite.setFitHeight(50.0);
        sprite.setFitWidth(50.0);
        sprite.setPickOnBounds(true);
        sprite.setPreserveRatio(true);

        name.setAlignment(Pos.CENTER_RIGHT);
        name.setContentDisplay(ContentDisplay.RIGHT);
        name.setPadding(new Insets(0, 0, 0, 10));

        sep.setHalignment(HPos.RIGHT);
        sep.setOrientation(Orientation.VERTICAL);

        id.setAlignment(Pos.CENTER_RIGHT);
        id.setTextAlignment(TextAlignment.RIGHT);
        id.setTextFill(Paint.valueOf("#000000"));
        id.setPrefWidth(30);



    }

    /**
     * Met les composants à l'interieur des autres.
     */
    private void setChildrens() {
        root.getChildren().add(lTypes);
        root.getChildren().add(sprite);
        root.getChildren().add(bPane);

        lTypes.getChildren().add(type1);
        lTypes.getChildren().add(type2);

        bPane.setLeft(name);
        bPane.setRight(hBox);

        hBox.getChildren().add(sep);
        hBox.getChildren().add(id);


        HBox.setHgrow(bPane, Priority.ALWAYS);
        HBox.setHgrow(id, Priority.ALWAYS);
        BorderPane.setAlignment(name, Pos.CENTER);
    }
}
