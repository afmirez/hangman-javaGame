package hangman;

import java.util.List;

public class VisualRenders {
    private static final String youLose = " __    __                  __                              __     \r\n/\\ \\  /\\ \\                /\\ \\                            /\\ \\    \r\n\\ `\\`\\\\/'/ ___   __  __   \\ \\ \\        ___     ____     __\\ \\ \\   \r\n `\\ `\\ /' / __`\\/\\ \\/\\ \\   \\ \\ \\  __  / __`\\  /',__\\  /'__`\\ \\ \\  \r\n   `\\ \\ \\/\\ \\L\\ \\ \\ \\_\\ \\   \\ \\ \\L\\ \\/\\ \\L\\ \\/\\__, `\\/\\  __/\\ \\_\\ \r\n     \\ \\_\\ \\____/\\ \\____/    \\ \\____/\\ \\____/\\/\\____/\\ \\____\\\\/\\_\\\r\n      \\/_/\\/___/  \\/___/      \\/___/  \\/___/  \\/___/  \\/____/ \\/_/\r\n                                                                  \r\n                                                                  ";
    private static final String youWon = "/\\ \\  /\\ \\                                            /\\ \\    \r\n\\ `\\`\\\\/'/ ___   __  __      __  __  __    ___     ___\\ \\ \\   \r\n `\\ `\\ /' / __`\\/\\ \\/\\ \\    /\\ \\/\\ \\/\\ \\  / __`\\ /' _ `\\ \\ \\  \r\n   `\\ \\ \\/\\ \\L\\ \\ \\ \\_\\ \\   \\ \\ \\_/ \\_/ \\/\\ \\L\\ \\/\\ \\/\\ \\ \\_\\ \r\n     \\ \\_\\ \\____/\\ \\____/    \\ \\___x___/'\\ \\____/\\ \\_\\ \\_\\/\\_\\\r\n      \\/_/\\/___/  \\/___/      \\/__//__/   \\/___/  \\/_/\\/_/\\/_/";
    private static final String hangmanLogo = "            \r\n   __ _____   _  _________  ______   _  __\r\n  / // / _ | / |/ / ___/  |/  / _ | / |/ /\r\n / _  / __ |/    / (_ / /|_/ / __ |/    / \r\n/_//_/_/ |_/_/|_/\\___/_/  /_/_/ |_/_/|_/  ";
    private static final String horizontalBanner = "-------------------------------------------------------------\n                       Developed by\n                      Andres Ramirez\n                    All rights reserved ©\n";
    private static final String[] HangmanPics = new String[]
    {
        "  +---+\n  |   |\n      |\n      |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n      |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n  |   |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|\\  |\n      |\n      |\n=========",
        "  +---+\n  |   |\n  O   |\n /|\\  |\n / \\  |\n      |\n========="
    };
    private static String spaceShip = "\"\n                     `. ___\n                    __,' __`.                _..----....____\n        __...--.'``;.   ,.   ;``--..__     .'    ,-._    _.-'\n  _..-''-------'   `'   `'   `'     O ``-''._   (,;') _,'\n,'________________                          \\ `-._`-',\n `._              ```````````------...___   '-.._'-:\n    ```--.._      ,.                     ````--...__\\-. \n            `.--. `-`                       ____    |  |\n              `. `.                       ,'`````.  ;  ;\n                `._`.        __________   `.      \\'__/'\n                   `-:._____/______/___/____`.     \\  `\n                               |       `._    `.    \\\n                               `._________`-.   `.   `.___\n                                             SSt  `------'\"";


    public static void RenderInit() {
        int counter = 0;
        int windowWidth = 80; // Assuming console width is 80 characters
        String phrase = "Loading game";
        long startTime = System.currentTimeMillis();

        while (counter < 9) {
            // Clear the screen
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            // Print the contents
            System.out.println(spaceShip);
            System.out.println(horizontalBanner);
            int elapsedTime = (int)(System.currentTimeMillis() - startTime);
            int position = (elapsedTime / 100) % (windowWidth + phrase.length());
            System.out.print(new String(new char[position]).replace('\0', '-'));
            System.out.print(phrase);
            System.out.println();
            
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }
    	System.out.print("\033[H\033[2J");
    	System.out.flush();
    }
 
    public static void RenderHangmanLogo() {
        System.out.println(hangmanLogo);
        System.out.println();
    }
    
    public static void RenderHangman(int value) {
        if (value >= 0 && value < HangmanPics.length) {
            System.out.println(HangmanPics[value]);
            System.out.println();
        }
    }

    public static void RenderYouWon() {
        System.out.println();
        System.out.println(youWon);
        System.out.println();
    }

    public static void RenderYouLose() {
        System.out.println();
        System.out.println(youLose);
        System.out.println();
    }

    public static void RenderGame(String playerName, int counter, List<Character> historicalGuesses,
                                  int hangmanToShow, String[] wordArray, String feedback, String word) {
    	System.out.print("\033[H\033[2J");
    	System.out.flush();
        RenderHangmanLogo();
        System.out.println();
        System.out.println("Player: " + playerName);
        System.out.println("Wrong guesses left: " + counter);
        System.out.println("Historical guesses: " + historicalGuesses);
        System.out.println("Feedback: " + feedback);
        System.out.println("----------------------------------------------------------\n");
        RenderHangman(hangmanToShow);
        System.out.println("Secret word (development): " + word);
        System.out.println("Secret Word: " + String.join("", wordArray));
    }
    
}