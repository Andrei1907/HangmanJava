package game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Dictionary {
    private List<String> dictionary;

    public List<String> getDictionary() {
        return dictionary;
    }

    public Dictionary() {
        dictionary = new ArrayList<>();

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\User\\IdeaProjects\\HangmanServer\\dictionary.txt"));
            String word = reader.readLine();
            while(word != null) {
                if(verify(word)) {
                    dictionary.add(word.toUpperCase());
                }
                word = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verify(String str) {
        if(str.length() < 5 || str.length() > 10)
            return false;
        if(str.contains("\'"))
            return false;
        return true;
    }

    public String generateWord() {
        Random r = new Random();
        return dictionary.get(r.nextInt(dictionary.size()));
    }

    public void removeWord(String word) {
        dictionary.remove(word);
    }
}
