package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordHandler {
    private static List<String> wordsList = new ArrayList<String>() {
        {
            add("apple");
            add("banana");
            add("orange");
            add("grape");
            add("kiwi");
            add("strawberry");
            add("pineapple");
            add("watermelon");
            add("blueberry");
            add("peach");
            add("mango");
            add("pear");
            add("cherry");
            add("lemon");
            add("lime");
            add("coconut");
            add("apricot");
            add("fig");
            add("plum");
            add("papaya");
        }
    };

    public static String GetRandomWord() {
        int randomNumber = new Random().nextInt(wordsList.size());
        return wordsList.get(randomNumber);
    }
}