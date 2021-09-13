/*
 * File: HangmanCanvas.java
 * ---------------------
 * This class holds the graphics elements to the Hangman game.
 * Author: Cobalt - M.Cabatuan
 * Date modified: 06/11/2019
 */


import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;

import java.awt.*;
import acm.graphics.GRect;

public class PokemonCanvas extends GCanvas {
    int total = 20;
    Type[] pokemons = new Type[20];

    public String[] displayAll(){
        int x;
        String [] poke = new String[total];
        for (int i = 0; i < total; i++) {
            x = i + 1;
            poke[i] = ("[" + x + "]   " + pokemons[i].getPokemonName());
        }
        return poke;
    }

    public void clear(){
        removeAll();
    }

    public void initiate(){
        pokemons[0] = new ElectricType("pikachu", "001",100,20,60,18,5);
        pokemons[1] = new GrassType("treecko","002",100,20,60,18,5);
        pokemons[2] = new GhostPoison("gastly","003",100,30,50,15,3);
        pokemons[3] = new FireType("charmander","004",49,49,45,6.9,0.7);
        pokemons[4] = new GrassPoison("bulbasaur","005",49,49,45,6.9,0.7);
        pokemons[5] = new GrassPoison("ivysaur","006",82,82,80,8.3,1.5);
        pokemons[6] = new GrassPoison("venusaur","007",82,83,80,100, 2.0);
        pokemons[7] = new WaterType("squirtle","008",48,65,43,9.0 , 0.5);
        pokemons[8] = new WaterType("wartortle", "009",63,80,58,22.5 ,1.0);
        pokemons[9] = new WaterType("blastoise", "010",83,100,78,85.5 ,1.6);
        pokemons[10] = new RockGround("geodude", "011",80,100,78,20 ,0.4);
        pokemons[11] = new RockGround("graveler", "012",95,115,35,105.0 ,1.0);
        pokemons[12] = new RockGround("golem", "013",120,130,45,300.0  ,1.4 );
        pokemons[13] = new PsychicType("abra","014",20,15,90,19.5,0.9);
        pokemons[14] = new PsychicType("kadabra","015",35,30,105,56.5 ,1.3 );
        pokemons[15] = new PsychicType("alakazam","016",50,45,120,48.0,1.5 );
        pokemons[16] = new SteelRock("aron","017",70,100,30,60.0 ,0.4  );
        pokemons[17] = new SteelRock("lairon","018",90,140,40,120.0 ,0.9);
        pokemons[18] = new SteelRock("aggron","019",110,180,50,360.0,2.1);
        pokemons[19] = new PsychicType("mewtwo","020",110,90,130,122.0 ,2.0);
    }

    public void draw(int s){
        draw(pokemons[s-1]);
    }

    public void draw(Type p){
        removeAll();
        if(p instanceof MultiType){
            MultiType obj;
            obj = (MultiType) p;
            process(obj,p.getIndex());
        }
        else{
            process(p,p.getIndex());
        }
    }

    public void process(Type p, String index){

        double canvasWidth = getWidth();
        double attack = p.getAttack();
        double defense = p.getDefense();
        double stamina = p.getStamina();
        double height = p.getHeight();
        double weight = p.getWeight();
        Color barColor = p.getBarColor();
        Color textColor = p.getTextColor();
        Color typeColor = p.getTypeColor();
        GImage pokeImg = p.getPokemonImage();
        GImage bgImg = p.getBackgroundImage();
        GLabel number = new GLabel("#" + index);
        String name = p.getPokemonName();
        String type = p.getTypeName();

        //Background image
        add(bgImg,0,0);

        //Counter label
        number.setColor(Color.WHITE);
        number.setFont("Arial-Italic-32");
        add(number,10,30);

        //Pokemon Image
        pokeImg.setSize(200,200);
        double pokeWidth = pokeImg.getWidth();
        add(pokeImg,(canvasWidth/2) - (pokeWidth/2),-10);

        //Label rectangle
        GRect rect = new GRect(canvasWidth,200);
        rect.setFilled(true);
        rect.setColor(typeColor);
        add(rect,0,170);

        //Stat block rectangle
        GRect rect2 = new GRect(canvasWidth,200);
        rect2.setFilled(true);
        rect2.setColor(Color.BLACK);
        add(rect2,0,370);

        //Type Rectangle
        GRect rect3 = new GRect(canvasWidth,60);
        rect3.setFilled(true);
        rect3.setColor(typeColor.darker());
        add(rect3,0,310);

        //Name label
        GLabel pokeName = new GLabel(name.toUpperCase());
        pokeName.setFont("Rockwell-Bold-32");
        double nameHeight = pokeName.getHeight();
        double nameWidth = pokeName.getWidth();
        pokeName.setColor(textColor);
        add(pokeName,(getWidth()/2) - (nameWidth/2), 200);

        //Height and weight labels
        GLabel heightPoke = new GLabel("Height:   " + height + "m");
        GLabel weightPoke = new GLabel("Weight:   " + weight + "kg");
        heightPoke.setFont("Eras Medium ITC-Bold-24");
        weightPoke.setFont("Eras Medium ITC-Bold-24");
        add(heightPoke,(getWidth()/2) - (heightPoke.getWidth()/2),200+nameHeight);
        add(weightPoke,(getWidth()/2) - (heightPoke.getWidth()/2),200+nameHeight+heightPoke.getHeight());

        //Type label
        GLabel pokeType = new GLabel(type.toUpperCase());
        pokeType.setFont("Rockwell-Bold-32");
        pokeType.setColor(textColor);
        add(pokeType,(getWidth()/2) - (pokeType.getWidth()/2), 310+pokeType.getHeight());

        //Stats
        GLabel attackLabel = new GLabel("ATTACK:  ");
        GLabel defenseLabel = new GLabel("DEFENSE: ");
        GLabel staminaLabel = new GLabel("STAMINA: ");
        GRect outRect = new GRect(200,20);
        GRect outRect1 = new GRect(200,20);
        GRect outRect2 = new GRect(200,20);
        GRect attackRect = new GRect(attack,20);
        GRect defenseRect = new GRect(defense,20);
        GRect staminaRect = new GRect(stamina,20);
        attackRect.setFilled(true);
        attackRect.setColor(barColor);
        defenseRect.setFilled(true);
        defenseRect.setColor(barColor);
        staminaRect.setFilled(true);
        staminaRect.setColor(barColor);
        outRect.setColor(Color.WHITE);
        outRect1.setColor(Color.WHITE);
        outRect2.setColor(Color.WHITE);
        attackLabel.setFont("Arial-Bold-24");
        defenseLabel.setFont("Arial-Bold-24");
        staminaLabel.setFont("Arial-Bold-24");
        attackLabel.setColor(Color.WHITE);
        defenseLabel.setColor(Color.WHITE);
        staminaLabel.setColor(Color.WHITE);

        double tempHeight = 370+attackLabel.getHeight();
        add(attackLabel,20, tempHeight);
        add(attackRect,150,380);
        add(outRect,150,380);

        tempHeight = tempHeight+defenseLabel.getHeight();
        add(defenseLabel,20, tempHeight);
        add(defenseRect,150,410);
        add(outRect1,150,410);

        tempHeight = tempHeight+staminaLabel.getHeight();
        add(staminaLabel,20, tempHeight);
        add(staminaRect,150,440);
        add(outRect2,150,440);
    }

    public void process(MultiType p, String index){
        double canvasWidth = getWidth();
        double attack = p.getAttack();
        double defense = p.getDefense();
        double stamina = p.getStamina();
        double height = p.getHeight();
        double weight = p.getWeight();
        Color barColor = p.getBarColor();
        Color textColor = p.getTextColor();
        Color typeColor = p.getTypeColor();
        Color type2Color = p.getType2Color();
        GImage pokeImg = p.getPokemonImage();
        GImage bgImg = p.getBackgroundImage();
        GLabel number = new GLabel("#" + index);
        String name = p.getPokemonName();
        String type = p.getTypeName();
        String type2 = p.getType2Name();

        //Background image
        add(bgImg,0,0);

        //Counter label
        number.setColor(Color.WHITE);
        number.setFont("Arial-Italic-32");
        add(number,10,30);

        //Pokemon Image
        pokeImg.setSize(200,200);
        double pokeWidth = pokeImg.getWidth();
        add(pokeImg,(canvasWidth/2) - (pokeWidth/2),-10);

        //Label rectangle
        GRect rect = new GRect(canvasWidth,200);
        rect.setFilled(true);
        rect.setColor(typeColor);
        add(rect,0,170);

        //Stat block rectangle
        GRect rect2 = new GRect(canvasWidth,200);
        rect2.setFilled(true);
        rect2.setColor(Color.BLACK);
        add(rect2,0,370);

        //Type Rectangle
        GRect rect3 = new GRect(canvasWidth,60);
        rect3.setFilled(true);
        rect3.setColor(typeColor.darker());
        add(rect3,0,310);

        //Type2 Rectangle
        GRect rect4 = new GRect(canvasWidth,60);
        rect4.setFilled(true);
        rect4.setColor(type2Color.darker());
        add(rect3,canvasWidth/2,310);

        //Name label
        GLabel pokeName = new GLabel(name.toUpperCase());
        pokeName.setFont("Rockwell-Bold-32");
        double nameHeight = pokeName.getHeight();
        double nameWidth = pokeName.getWidth();
        pokeName.setColor(textColor);
        add(pokeName,(getWidth()/2) - (nameWidth/2), 200);

        //Height and weight labels
        GLabel heightPoke = new GLabel("Height:   " + height + "m");
        GLabel weightPoke = new GLabel("Weight:   " + weight + "kg");
        heightPoke.setFont("Eras Medium ITC-Bold-24");
        weightPoke.setFont("Eras Medium ITC-Bold-24");
        add(heightPoke,(getWidth()/2) - (heightPoke.getWidth()/2),200+nameHeight);
        add(weightPoke,(getWidth()/2) - (heightPoke.getWidth()/2),200+nameHeight+heightPoke.getHeight());

        //Type label
        GLabel pokeType = new GLabel(type.toUpperCase());
        pokeType.setFont("Rockwell-Bold-32");
        pokeType.setColor(textColor);
        add(pokeType,(canvasWidth/4) - (pokeType.getWidth()/2), 310+pokeType.getHeight());

        GLabel pokeType2 = new GLabel(type2.toUpperCase());
        pokeType2.setFont("Rockwell-Bold-32");
        pokeType2.setColor(textColor);
        add(pokeType2,(canvasWidth*3/4) - (pokeType2.getWidth()/2), 310+pokeType.getHeight());

        //Stats
        GLabel attackLabel = new GLabel("ATTACK:  ");
        GLabel defenseLabel = new GLabel("DEFENSE: ");
        GLabel staminaLabel = new GLabel("STAMINA: ");
        GRect outRect = new GRect(200,20);
        GRect outRect1 = new GRect(200,20);
        GRect outRect2 = new GRect(200,20);
        GRect attackRect = new GRect(attack,20);
        GRect defenseRect = new GRect(defense,20);
        GRect staminaRect = new GRect(stamina,20);
        attackRect.setFilled(true);
        attackRect.setColor(barColor);
        defenseRect.setFilled(true);
        defenseRect.setColor(barColor);
        staminaRect.setFilled(true);
        staminaRect.setColor(barColor);
        outRect.setColor(Color.WHITE);
        outRect1.setColor(Color.WHITE);
        outRect2.setColor(Color.WHITE);
        attackLabel.setFont("Arial-Bold-24");
        defenseLabel.setFont("Arial-Bold-24");
        staminaLabel.setFont("Arial-Bold-24");
        attackLabel.setColor(Color.WHITE);
        defenseLabel.setColor(Color.WHITE);
        staminaLabel.setColor(Color.WHITE);

        double tempHeight = 370+attackLabel.getHeight();
        add(attackLabel,20, tempHeight);
        add(attackRect,150,380);
        add(outRect,150,380);

        tempHeight = tempHeight+defenseLabel.getHeight();
        add(defenseLabel,20, tempHeight);
        add(defenseRect,150,410);
        add(outRect1,150,410);

        tempHeight = tempHeight+staminaLabel.getHeight();
        add(staminaLabel,20, tempHeight);
        add(staminaRect,150,440);
        add(outRect2,150,440);
    }

    public String getName(int selector){
        selector = selector - 1;
        return pokemons[selector].getPokemonName();
    }

    public void deletePoke(int selector){
        selector = selector - 1;
        for (int i = selector; i < total-1; i++) {
            pokemons[i] = pokemons[i+1];
        }
        pokemons[total-1]=null;
        total = total - 1;
    }

    public int getTotal(){
        return total;
    }

    public void slideshow(){
        for (int i = 0; i < total; i++) {
            draw(pokemons[i]);
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
