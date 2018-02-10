package Metier;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

public class PokemonBin implements IPokemon, Serializable {

    private transient IPokemon p;

    @Override
    public ObservableList<Type> getLtypes() {
        return p.getLtypes();
    }

    @Override
    public ListProperty<Type> ltypesProperty() {
        return p.ltypesProperty();
    }

    @Override
    public int getId() {
        return p.getId();
    }

    @Override
    public IntegerProperty idProperty() {
        return p.idProperty();
    }

    @Override
    public int getPv() {
        return p.getPv();
    }

    @Override
    public IntegerProperty pvProperty() {
        return p.pvProperty();
    }

    @Override
    public int getSpeed() {
        return p.getSpeed();
    }

    @Override
    public IntegerProperty speedProperty() {
        return p.speedProperty();
    }

    @Override
    public int getAtt() {
        return p.getAtt();
    }

    @Override
    public IntegerProperty attProperty() {
        return p.attProperty();
    }

    @Override
    public int getDef() {
        return p.getDef();
    }

    @Override
    public IntegerProperty defProperty() {
        return p.defProperty();
    }

    @Override
    public int getAttspe() {
        return p.getAttspe();
    }

    @Override
    public IntegerProperty attspeProperty() {
        return p.attspeProperty();
    }

    @Override
    public int getDefspe() {
        return p.getDefspe();
    }

    @Override
    public IntegerProperty defspeProperty() {
        return p.defspeProperty();
    }

    @Override
    public String getName() {
        return p.getName();
    }

    @Override
    public StringProperty nameProperty() {
        return p.nameProperty();
    }

    @Override
    public Image getSprite() {
        return p.getSprite();
    }

    @Override
    public ObjectProperty<Image> spriteProperty() {
        return p.spriteProperty();
    }

    @Override
    public Image getSmallSprite() {
        return p.getSmallSprite();
    }

    @Override
    public ObjectProperty<Image> smallSpriteProperty() {
        return p.smallSpriteProperty();
    }


    @Override
    public void setId(int id) { p.setId(id); }
    @Override
    public void setPv(int pv) { p.setPv(pv); }
    @Override
    public void setSpeed(int speed) { p.setSpeed(speed); }
    @Override
    public void setAtt(int att) { p.setAtt(att); }
    @Override
    public void setDef(int def) { p.setDef(def); }
    @Override
    public void setAttspe(int attspe) { p.setAttspe(attspe); }
    @Override
    public void setDefspe(int defspe) { p.setDefspe(defspe); }
    @Override
    public void setName(String name) { p.setName(name); }
    @Override
    public void setSprite(Image img) { p.setSprite(img); }
    @Override
    public void setSmallSprite(Image img) { p.setSmallSprite(img); }
    @Override
    public void setLtypes(ObservableList<Type> l) { p.setLtypes(l); }
    
    
    public PokemonBin(IPokemon p) {
        this.p = p;
    }


    /**
     * Lit un Pokemon dans un flux binaire
     * @param s flux binaire
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        p = new Pokemon();
        ((Pokemon)p).initProp();
        s.defaultReadObject();

        setId(s.readInt());
        setName(s.readUTF());

        setPv(s.readInt());
        setAtt(s.readInt());
        setDef(s.readInt());
        setAttspe(s.readInt());
        setDefspe(s.readInt());
        setSpeed(s.readInt());

        setLtypes(readListProp(s));


        try {
            //setSprite(SwingFXUtils.toFXImage(ImageIO.read(s), null));
            Image i = new Image(s);
            setSprite(i);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        try {
//            BufferedImage bi = ImageIO.read(s);
//            WritableImage wi = SwingFXUtils.toFXImage(bi, null);
//            setSmallSprite(wi);
            Image i = new Image(s);
            setSmallSprite(i);
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Ecrit un pokémon dans un flux binaire
     * @param s
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();

        s.writeInt(idProperty().intValue());
        s.writeUTF(nameProperty().getValueSafe());

        s.writeInt(pvProperty().intValue());
        s.writeInt(attProperty().intValue());
        s.writeInt(defProperty().intValue());
        s.writeInt(attspeProperty().intValue());
        s.writeInt(defspeProperty().intValue());
        s.writeInt(speedProperty().intValue());

        writeListProp(s,ltypesProperty());

        System.out.println(ImageIO.write(SwingFXUtils.fromFXImage(getSprite(), null), "png", s));

        System.out.println(ImageIO.write(SwingFXUtils.fromFXImage(getSmallSprite(), null), "png", s));



    }


    /**
     * Ecrit une ListProperty dans un flux binaire
     * @param s
     * @param lstProp
     * @throws IOException
     */
    // write a ListProperty to ObjectOutputStream
    private static void writeListProp(ObjectOutputStream s, ListProperty lstProp) throws IOException {
        if(lstProp==null || lstProp.getValue()==null) {
            s.writeInt(0);
            return;
        }
        s.writeInt(lstProp.size());
        for(Object elt:lstProp.getValue()) s.writeObject(elt);
    }

    /**
     * Lit une ListProperty dans un flux binaire
     * @param s
     * @return La ListProprty lue
     * @throws IOException
     * @throws ClassNotFoundException
     */
    // Read a ListProperty from ObjectInputStream (and return it)
    private static ListProperty readListProp(ObjectInputStream s) throws IOException, ClassNotFoundException {

        ListProperty lst = new SimpleListProperty(FXCollections.observableArrayList());
        int loop=s.readInt();
        for(int i = 0;i<loop;i++) {
            lst.add(s.readObject());
        }

        return lst;
    }
    
}
