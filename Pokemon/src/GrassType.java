import acm.graphics.GImage;
import acm.graphics.GLabel;
import com.sun.jdi.connect.Connector;

import java.awt.*;

public class GrassType extends Type{
    String pokemon;

    //Constructor
    // TAGAGAWA NG OBJECT
    public GrassType(String pokemon, String index, double attack, double defense, double stamina, double weight, double height){
        typeName = "Grass";
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
        return new GImage("C:\\Users\\Jeyell\\Desktop\\CPE\\LBYCPEI\\Deliverables\\Module 6\\Code\\Pokemon\\assets\\"+typeName+".jpg");
    }

    public Color getBarColor(){
        return Color.GREEN;
    }

    public Color getTextColor(){
        return Color.WHITE;
    }

    public Color getTypeColor(){
        Color temp = new Color(33, 160, 5);
        temp.brighter();
        return temp;
    }

    public String getTypeName(){
        return typeName;
    }

    public String getPokemonName(){
        return pokemon;
    }
}
