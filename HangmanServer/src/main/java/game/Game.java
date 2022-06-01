package game;

public class Game {
    private Player player;
    private String word;
    private StringBuilder guessedWord;
    private int errorCount;

    public Game(Player player, String word) {
        this.player = player;
        this.word = word;
        // de verificat
        String aux = new String(new char[word.length()]).replace('\0', '_');
        this.guessedWord = new StringBuilder(aux);
        this.errorCount = 0;
        //System.out.println(word);
        //System.out.println(aux);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getGuessedWord() {
        return guessedWord.toString();
    }

    public void setGuessedWord(StringBuilder guessedWord) {
        this.guessedWord = guessedWord;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public String verifyLetter(String letter) {
        int pos = word.indexOf(letter);
        if(pos == -1) {
            errorCount += 1;
            if(errorCount == 6) {
                System.out.println("Lost");
                return "Lost";
            }
            else return "Wrong";
        }
        else {
            while(pos >= 0) {
                guessedWord.setCharAt(pos, word.charAt(pos));
                pos = word.indexOf(letter, pos+1);
            }
            if(guessedWord.indexOf("_") == -1) {
                return "Won";
            }
            else return "Right";
        }
    }
}
