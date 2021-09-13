public interface HangmanImplement {

    void intro();
    int playOneGame(String secretWord, int ask);
    void displayHangman(int guessCount, int ask);
    String createHint(String secretWord, String guessedLetters);
    char readGuess(String guessedLetters);
    String getRandomWord(String filename);
    void stats(int gamesCount, int gamesWon, int best);

}
