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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import acm.program.GraphicsProgram;

public class HangmanCanvas extends GCanvas {

    private static final int TEXT_HEIGHT = 20;   // you can modify this to suit your ascii art
    private static final int TEXT_X_OFFSET = 12;   // you can modify this to suit your ascii art
    private int textX;
    private int textY;

    private Scanner scan = null;

    /**
     * Resets the display so that only the hangman scaffold appears
     */
    public void reset() {
        textX = TEXT_X_OFFSET;
        textY = TEXT_HEIGHT;
    }

    public void clear(){
        removeAll();
        textY = TEXT_HEIGHT;
    }

    public void printText(String text){
        GLabel line = new GLabel(text);
        textY += TEXT_HEIGHT;
        line.setFont("Consolas-Bold-14");
        add(line,  textX , textY );
    }

    public void display(int number){
        try {
            scan = new Scanner(new File("assets/display"+number+".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNextLine()) {
            String word = scan.nextLine();
            printText(word);
        }
    }

    public void displayG(int number) {
        image(number);
    }

    public void image(int number){
        GImage image = new GImage("assets/image"+number+".png");
        add(image);
    }
}
