import acm.graphics.GImage;
import acm.graphics.GLabel;
import com.sun.jdi.connect.Connector;

import java.awt.*;

public class PsychicType extends Type{
    String pokemon;

    public PsychicType(String pokemon, String index, double attack, double defense, double stamina, double weight, double height){
        typeName = "Psychic";
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
        Color temp = new Color(156, 1, 155);
        temp.brighter();
        return temp;
    }

    public Color getTextColor(){
        return Color.WHITE;
    }

    public Color getTypeColor(){
        Color temp = new Color(224, 43, 241);
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
