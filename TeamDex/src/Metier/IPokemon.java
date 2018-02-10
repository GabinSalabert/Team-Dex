/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

/**
 *
 * @author Gabin
 */
public interface IPokemon {

        public ObservableList<Type> getLtypes();
        public void setLtypes(ObservableList<Type> ltypes);
        public ListProperty<Type> ltypesProperty();


        public int getId();
        public void setId(int id);
        public IntegerProperty idProperty();

        public int getPv();
        public void setPv(int pv);
        public IntegerProperty pvProperty();

        public int getSpeed();
        public void setSpeed(int speed);
        public IntegerProperty speedProperty();

        public int getAtt();
        public void setAtt(int att);
        public IntegerProperty attProperty();

        public int getDef();
        public void setDef(int def);
        public IntegerProperty defProperty();

        public int getAttspe();
        public void setAttspe(int attspe);
        public IntegerProperty attspeProperty();

        public int getDefspe();
        public void setDefspe(int defspe);
        public IntegerProperty defspeProperty();

        public String getName();
        public void setName(String name);
        public StringProperty nameProperty();


        public Image getSprite();
        public void setSprite(Image img);
        public ObjectProperty<Image> spriteProperty();

        public Image getSmallSprite();
        public void setSmallSprite(Image img);
        public ObjectProperty<Image> smallSpriteProperty();
}
