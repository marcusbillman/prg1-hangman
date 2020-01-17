import java.util.Arrays;
import java.util.Scanner;

/**
 * Hangman
 * 2020-01-16
 * Marcus Billman
 */
public class hangman {
    private static String word = "apple";
    private static String[] progress;
    private static int gameStatus = 0;

    public static void main(String[] args) {
        // Initialise progress array
        progress = new String[word.length()];
        Arrays.fill(progress, "_");

        String guess = getGuess();
        validateGuess(guess);

        // Print progress
        for (String s : progress) {
            System.out.print(s + " ");
        }
    }

    private static String getGuess() {
        Scanner scanner = new Scanner(System.in);
        String g;
        do {
            System.out.print("Guess : ");
            g = scanner.nextLine();
        } while (g.equals(""));
        return g;
    }

    private static boolean validateGuess(String g) {
        boolean match = false;
        if (word.equals(g)) {
            gameStatus = 1;
            for (int i = 0; i < word.length(); i++) {
                progress[i] = String.valueOf(word.charAt(i));
            }
            return true;
        }
        for (int i = 0; i < word.length(); i++) {
            if (String.valueOf(word.charAt(i)).equals(g)) {
                progress[i] = String.valueOf(word.charAt(i));
                match = true;
            }
        }
        return match;
    }
}
