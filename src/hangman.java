import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Hangman
 * 2020-01-16
 * Marcus Billman
 */
public class hangman {
    private static String word;
    private static String[] progress;
    private static int gameStatus = 0;

    public static void main(String[] args) {
        word = generateWord();

        // Initialise progress array
        progress = new String[word.length()];
        Arrays.fill(progress, "_");

        // Main game loop
        while (gameStatus == 0) {
            // Print progress
            System.out.println();
            for (String s : progress) {
                System.out.print(s + " ");
            }
            System.out.println();

            String guess = getGuess();
            validateGuess(guess);
            gameStatus = checkWin();
        }

    }

    private static String generateWord() {
        // Read nounlist file into memory
        Scanner file = null;
        try {
            file = new Scanner(new File("./resources/nounlist.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> wordlist = new ArrayList<>();
        while(file.hasNext())
            wordlist.add(file.nextLine());

        // Return random word
        Random random = new Random();
        return wordlist.get(random.nextInt(wordlist.size()));
    }

    private static int checkWin() {
        for (String s : progress) {
            if (s.equals("_")) {
                return 0;
            }
        }
        return 1;
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
