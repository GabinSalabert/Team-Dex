/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;

/**
 *
 * @author Gabin
 */
public class Pokemon implements IPokemon {

    private ListProperty<Type> ltypesProperty = new SimpleListProperty<>();
        public void setLtypes(ObservableList<Type> l) { ltypesProperty.set(l); }
        public ObservableList<Type> getLtypes() { return ltypesProperty.get(); }
        public ListProperty<Type> ltypesProperty() { return ltypesProperty; }


    private IntegerProperty id = new SimpleIntegerProperty();
        public int getId() { return id.get(); }
        public void setId(int id) { this.id.set(id); }
        public IntegerProperty idProperty() { return id; }

    private IntegerProperty pv = new SimpleIntegerProperty();
        public int getPv() { return pv.get(); }
        public void setPv(int pv) { this.pv.set(pv); }
        public IntegerProperty pvProperty() { return pv; }

    private IntegerProperty speed = new SimpleIntegerProperty();
        public int getSpeed() { return speed.get(); }
        public void setSpeed(int speed) { this.speed.set(speed); }
        public IntegerProperty speedProperty() { return speed; }

    private IntegerProperty att = new SimpleIntegerProperty();
        public int getAtt() { return att.get(); }
        public void setAtt(int att) { this.att.set(att); }
        public IntegerProperty attProperty() { return att; }

    private IntegerProperty def = new SimpleIntegerProperty();
        public int getDef() { return def.get(); }
        public void setDef(int def) { this.def.set(def); }
        public IntegerProperty defProperty() { return def; }

    private IntegerProperty attspe = new SimpleIntegerProperty();
        public int getAttspe() { return attspe.get(); }
        public void setAttspe(int attspe) { this.attspe.set(attspe); }
        public IntegerProperty attspeProperty() { return attspe; }

    private IntegerProperty defspe = new SimpleIntegerProperty();
        public int getDefspe() { return defspe.get(); }
        public void setDefspe(int defspe) { this.defspe.set(defspe); }
        public IntegerProperty defspeProperty() { return defspe; }

    private StringProperty name = new SimpleStringProperty();
        public String getName() { return name.get(); }
        public void setName(String name) { this.name.set(name); }
        public StringProperty nameProperty(){ return name; }


    private ObjectProperty<Image> sprite = new SimpleObjectProperty();
        public Image getSprite() { return sprite.get(); }
        public void setSprite(Image img) { this.sprite.set(img); }
        public ObjectProperty<Image> spriteProperty(){ return sprite; }

    private ObjectProperty<Image> smallSprite = new SimpleObjectProperty();
        public Image getSmallSprite() { return smallSprite.get(); }
        public void setSmallSprite(Image img) { this.smallSprite.set(img); }
        public ObjectProperty<Image> smallSpriteProperty(){ return smallSprite; }



    public Pokemon() {
        initProp();
    }


    public Pokemon(String name, int id, String smallSprite, String sprite, ArrayList<Integer> lstats, ObservableList<Type> types) {
        setName(name);
        setId(id);
        setSprite(new Image(sprite));
        setSmallSprite(new Image(smallSprite));
        setPv(lstats.get(0));
        setAtt(lstats.get(1));
        setDef(lstats.get(2));
        setAttspe(lstats.get(3));
        setDefspe(lstats.get(4));
        setSpeed(lstats.get(5));


        setLtypes(types);

    }

    /**
     * Initialise les propriétés du Pokemon
     */
    public void initProp() {
        ltypesProperty = new SimpleListProperty<>();
        id = new SimpleIntegerProperty();
        pv = new SimpleIntegerProperty();
        att = new SimpleIntegerProperty();
        def = new SimpleIntegerProperty();
        attspe = new SimpleIntegerProperty();
        defspe = new SimpleIntegerProperty();
        speed = new SimpleIntegerProperty();
        name = new SimpleStringProperty(this, "name", "");
        smallSprite = new SimpleObjectProperty();
        sprite = new SimpleObjectProperty();
    }


}
