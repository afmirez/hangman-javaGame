package hangman;

import java.util.Scanner;

public class HangmanGame {
    public void Start() {
    	VisualRenders.RenderInit();
        Player player = Operations.CreatePlayer();
        String word = WordHandler.GetRandomWord();
        boolean continueGame = Operations.InitRound(word, player);
        Scanner scanner = new Scanner(System.in);
        while (continueGame) {
            System.out.println(" ");
            System.out.print("Press 'y' for another round. Press ANY OTHER key to exit this game: ");
            char input = Character.toLowerCase(scanner.nextLine().charAt(0));
            if (input == 'y') {
                word = WordHandler.GetRandomWord();
                continueGame = Operations.InitRound(word, player);
            } else {
                continueGame = false;
            }
        }
        scanner.close();
    }
}