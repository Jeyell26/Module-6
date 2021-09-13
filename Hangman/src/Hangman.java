import acm.program.*;
import acm.util.RandomGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import acm.util.*;
import java.applet.*;

public class Hangman extends ConsoleProgram implements HangmanImplement{

    public void run() {
        // TODO: write this method
        int playAgain = 1;
        int result,gamesCount,gamesWon,best;
        int ask;

        gamesCount = 0;
        best = 0;
        gamesWon = 0;
        intro();
        println();
        ask = graphicType();
        do {
            result = playOneGame(getRandomWord("large"),ask);
            gamesCount += 1;
            if (result>0){
                if (best<result){
                    best = result;
                }
                gamesWon += 1;
            }
            if (readBoolean("Do you want to play again?", "Y", "N")){
                playAgain = 1;
            }
            else{
                playAgain = 0;
            }
        }while(playAgain==1);
        println();
        println();
        stats(gamesCount,gamesWon,best);
    }

    private int graphicType(){
        if (readBoolean("ASCII/Graphics? (A/G)", "A", "G")){
           return 0;
        }
        else{
            return 1;
        }
    }

    // TODO: Shows Introduction
    public void intro() {
        // TODO: write this method
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        println("       Welcome to Hangman!       ");
        println("  I will think of a random word. ");
        println(" You'll try to guess its letters.");
        println("  Every time you guess a letter  ");
        println("that isn't in my word, a new body");
        println("part of the hanging man appears. ");
        println("            Goodluck!            ");
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    // TODO: comment this method
    // plays one whole hangman game
    public int playOneGame(String secretWord, int ask) {
        // TODO: write this method
        String guessedLetters = new String("");
        char c;
        int guessLeft = 8;
        int index;
        displayHangman(guessLeft,ask);
        println();
        while(guessLeft>0) {
            //Check if won already
            if (createHint(secretWord, guessedLetters).toUpperCase().indexOf('-') == -1){
                println("You win! My word was " + "\"" + secretWord + "\"");
                return guessLeft;
            }
            //Printing
            println("Secret word : " + createHint(secretWord, guessedLetters));
            println("Your guesses: " + guessedLetters);
            println("Guesses left: " + guessLeft);
            c = readGuess(guessedLetters);
            guessedLetters += c;
            index = secretWord.toUpperCase().indexOf(c);
            if (index != -1) {println("Correct!");}
            else { println("Incorrect."); guessLeft-=1; }
            displayHangman(guessLeft,ask);
        }
        println();
        println();
        println("You lose. My word was " + "\"" + secretWord + "\"");
        return 0;
    }

    // TODO: comment this method
    // Returns a string with dashes and the current guessed letters
    // For example:
    // Secret word: Programmer
    // Guessed letters: PR
    // Output: PR--R----R
    public String createHint(String secretWord, String guessedLetters) {
        // TODO: write this method
        int length = secretWord.length();
        int guessLength = guessedLetters.length();
        StringBuilder hint = new StringBuilder("");

        int index;

        //Filling the hint with '-'
        for (int i = 0; i < length; i++) {
            hint.append("-");
        }
        //hint: ----------

        //Replacing the - from hint to actual letters based on guessedLetters
        for (int i = 0; i < guessLength; i++) {
            //variable = wordToLookAt.toUpper/LowerCase.indexOf(letter)
            index = secretWord.toUpperCase().indexOf(guessedLetters.charAt(i));
            while (index != -1) {
                hint.setCharAt(index,guessedLetters.charAt(i));
                index = secretWord.toUpperCase().indexOf(guessedLetters.charAt(i),index + 1);
            }
        }

        return new String(hint);
    }

    // TODO: comment this method
    // Takes the input of the player
    public char readGuess(String guessedLetters) {
        // TODO: write this method
        int loop = 1;
        String lineInput;
        char c;
        int i;
        do {
            c = '0';
            i = 0;
            print("Your Guess? ");
            lineInput = readLine();
            if(lineInput.length()==1){
                c = lineInput.charAt(0);
                c = Character.toUpperCase(c);
                i = guessedLetters.toUpperCase().indexOf(c);
            }

            //Case 1: input is a word
            if (lineInput.length() != 1 || !Character.isLetter(c)) {
                println("Type a single letter from A-Z.");
            }
            //Case 2: input was already guessed
            else if (i != -1) {
                println("You already guessed that letter.");
            }
            else {
                return Character.toUpperCase(c);
            }
        }while(loop == 1);
        return Character.toUpperCase(c);
    }

    // TODO: comment this method
    // Updates the hangman picture
    public void displayHangman(int guessCount, int ask) {
        // TODO: write this method
        canvas.clear();
        if (ask == 0){
            canvas.display(guessCount);
        }
        else if (ask == 1){
            canvas.displayG(guessCount);
        }

    }

    // TODO: comment this method
    // Shows the stats of the player
    public void stats(int gamesCount, int gamesWon, int best) {
        // TODO: write this method
        double winPercent =  ((double)gamesWon/(double)gamesCount)*100;
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        println("         Overall statistics:          ");
        println("           Games played: "+ gamesCount);
        println("             Games won: "+ gamesWon);
        println("          Win percent: "+ winPercent + "%");
        println("   Best game: "+ best +" guess(es) remaining");
        println("          Thanks for playing!         ");
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    // TODO: comment this method
    // Gets a random word from a textgile
    public String getRandomWord(String filename) {
        // TODO: write this method
        //Scans the file
        Scanner scan = null;
        try {
            scan = new Scanner(new File("assets/"+filename+".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //Records the amount of words
        int wordCount = scan.nextInt(); //Parse the first "word" as integer

        //Creates an array to store words
        String[] array = new String[wordCount+1];

        //Store words into array
        for (int i = 0; i <= wordCount; i++) {
            String word = scan.nextLine();
            array[i] = word;
        }

        //Randomize which word to return
        RandomGenerator rgen = RandomGenerator.getInstance();

        int choose = rgen.nextInt(1,wordCount);

        return array[choose];
    }

    public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
        canvas.reset();  // sample canvas method call
        canvas.clear();
    }

    /* Solves NoClassDefFoundError */
    public static void main(String[] args) {
        new Hangman().start(args);
    }

    private HangmanCanvas canvas;
}
