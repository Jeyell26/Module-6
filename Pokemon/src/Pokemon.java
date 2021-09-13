import acm.program.*;

public class Pokemon extends ConsoleProgram{
    int exit = 0;
    public void run() {
        intro();
        do{
            menu();
            print(">>> ");
            int selector = readInt();
            decide(selector);
            println();
            println();
        }while(exit!=1);

    }

    public void decide(int s){
        switch (s) {
            case 1 -> removePokemon();
            case 2 -> viewPokemon();
            case 3 -> slideshow();
            case 4 -> searchPokemon();
            case 5 -> exit = 1;
            default -> println("Invalid option!");
        }
    }


    public void removePokemon(){
        String [] poke;
        String temp;
        poke = canvas.displayAll();
        for (int i = 0; i < canvas.getTotal(); i++) {
            println(poke[i]);
        }
        print(">>> ");
        int selector = readInt();
        temp = "Do you want to remove " + canvas.getName(selector) + "?";
        if(readBoolean(temp, "Y","N")){
            canvas.deletePoke(selector);
        }
    }

    public void viewPokemon(){
        String [] poke;
        poke = canvas.displayAll();
        for (int i = 0; i < canvas.getTotal(); i++) {
            println(poke[i]);
        }
        print(">>> ");
        int selector = readInt();
        canvas.draw(selector);
    }

    public void searchPokemon(){
        String [] poke;
        poke = canvas.displayAll();
        print("Search:  ");
        String query = readLine();
        for (int i = 0; i < canvas.getTotal(); i++) {
            if(query.equalsIgnoreCase(canvas.getName(i+1))){
                println(poke[i]);
            }
        }
    }

    public void slideshow(){
        canvas.slideshow();
    }

    // TODO: Shows Introduction
    public void intro() {
        // TODO: write this method
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        println("       Welcome to Pokedex        ");
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    public void menu(){
        println("Choose an option");
        println("[1] Remove pokemon");
        println("[2] View pokemon");
        println("[3] View slideshow of pokemon");
        println("[4] Search pokemon");
        println("[5] Exit program");
    }

    public void init() {
        canvas = new PokemonCanvas();
        add(canvas);
        canvas.clear();
        canvas.initiate();
    }

    /* Solves NoClassDefFoundError */
    public static void main(String[] args) {
        new Pokemon().start(args);
    }

    private PokemonCanvas canvas;
}
