package hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays; 

public class Operations {
    private static final Scanner scanner = new Scanner(System.in);

    public static String PlayerValidator() {
        VisualRenders.RenderHangmanLogo();
        System.out.print("Give me your name/nickname: ");
        String input = scanner.nextLine().trim();
        while (input.isEmpty()) {
            System.out.println("Name cannot be empty. Press ENTER to try again");
            scanner.nextLine(); 
            VisualRenders.RenderHangmanLogo();
            System.out.print("Give me your name/nickname: ");
            input = scanner.nextLine().trim();
        }
        return input;
    }


    public static Player CreatePlayer() {
        String playerName = PlayerValidator();
        return new Player(playerName);
    }

    public static char CharacterValidator() {
        String inputString = scanner.nextLine();
        char input;
        if (inputString.isEmpty()) {
            input = '0'; 
        } else {
            input = Character.toLowerCase(inputString.charAt(0));
            if (!Character.isLetter(input)) {
                input = '0';
            }
        }
        return input;
    }
    
    public static boolean InitRound(String word, Player player) {
        int maxWrongGuesses = 5; 
        int hangmanToShow = 0;
        List<Character> historicalGuesses = new ArrayList<>();
        String[] desiredArray = word.chars().mapToObj(c -> (char) c + " ").toArray(String[]::new);
        String[] wordArray = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            wordArray[i] = "_ ";
        }
        String feedback = "New game just started. Guess your first LETTER!";
        boolean roundDone = false;

        // First Render
        VisualRenders.RenderGame(player.getName(), maxWrongGuesses, historicalGuesses, hangmanToShow, wordArray, feedback, word);

        while (maxWrongGuesses > 0 && !Arrays.equals(wordArray, desiredArray)) {
            System.out.print("Guess a letter: ");
            char character = CharacterValidator();
            if (character == '0') {
                feedback = "This is not a valid LETTER. Please try again.";
            } else {
                boolean isCorrectGuess = false;
                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == character) {
                        wordArray[i] = Character.toString(Character.toLowerCase(character)) + " ";
                        if (!historicalGuesses.contains(character)) {
                            historicalGuesses.add(character);
                            feedback = "That letter WAS in the secret word! Guess another one";
                        } else if (historicalGuesses.contains(character) && !isCorrectGuess) {
                            feedback = "You've ALREADY GUESSED the letter '" + character + "'. Guess another one";
                        }
                        isCorrectGuess = true;
                    }
                }

                if (!isCorrectGuess) {
                    if (!historicalGuesses.contains(character)) {
                        if (maxWrongGuesses < 5) {
                            hangmanToShow++;
                        }
                        maxWrongGuesses--;
                        historicalGuesses.add(character);
                        feedback = "That letter WAS NOT in the secret word! Guess another one";
                    } else {
                        feedback = "You ALREADY USED this letter! (btw it's not in the secret word). Guess another one";
                    }
                }
            }

            VisualRenders.RenderGame(player.getName(), maxWrongGuesses, historicalGuesses, hangmanToShow, wordArray, feedback, word);

            if (Arrays.equals(wordArray, desiredArray)) {
                roundDone = true;
                feedback = "Congrats! YOU WON this game";
                VisualRenders.RenderGame(player.getName(), maxWrongGuesses, historicalGuesses, hangmanToShow, wordArray, feedback, word);
                VisualRenders.RenderYouWon();
                break;
            }
        }

        // Last Render ||  Player lose
        roundDone = true;
        feedback = "Sorry! YOU LOST this game";
        if (!Arrays.equals(wordArray, desiredArray)) { 
            VisualRenders.RenderGame(player.getName(), maxWrongGuesses, historicalGuesses, hangmanToShow, wordArray, feedback, word);
            VisualRenders.RenderYouLose();
        }
        return roundDone;
    }
}
