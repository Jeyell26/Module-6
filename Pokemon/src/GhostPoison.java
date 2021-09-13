import acm.graphics.GImage;
import acm.graphics.GLabel;
import com.sun.jdi.connect.Connector;

import java.awt.*;

public class GhostPoison extends MultiType{
    String pokemon;

    public GhostPoison(String pokemon, String index, double attack, double defense, double stamina, double weight, double height){
        typeName = "Ghost";
        typeName2 = "Poison";
        this.index = index;
        this.pokemon = pokemon;
        this.attack = attack;
        this.defense = defense;
        this.stamina = stamina;
        this.weight = weight;
        this.height = height;
    }

    public GImage getPokemonImage(){
        return new GImage("C:\\Users\\Jeyell\\Desktop\\CPE\\LBYCPEI\\Deliverables\\Module 6\\Code\\Pokemon\\assets\\"+pokemon+".png");
    }

    public GImage getBackgroundImage(){
        GImage temp = new GImage("C:\\Users\\Jeyell\\Desktop\\CPE\\LBYCPEI\\Deliverables\\Module 6\\Code\\Pokemon\\assets\\"+typeName+".jpg");
        temp.setSize(500,500);
        return temp;
    }

    public Color getBarColor(){
        Color temp = new Color(201, 24, 236);
        temp.brighter();
        return temp;
    }

    public Color getTextColor(){
        return Color.BLACK;
    }

    public Color getTypeColor(){
        Color temp = new Color(101, 18, 118);
        temp.brighter();
        return temp;
    }

    public String getTypeName(){
        return typeName;
    }

    public String getPokemonName(){
        return pokemon;
    }

    public Color getType2Color() {
        Color temp = new Color(199, 48, 231);
        temp.brighter();
        return temp;

    }

    public String getType2Name() {
        return typeName2;
    }

}
