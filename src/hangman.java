import java.util.Scanner;

/**
 * Hangman
 * 2020-01-16
 * Marcus Billman
 */
public class hangman {
    private static String word = "apple";
    private static String[] progress;

    public static void main(String[] args) {
        String guess;

        progress = new String[word.length()];

        guess = getGuess();
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

    private static
}
