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
    private static int incorrectGuesses = 0;
    private static ArrayList<String> previousGuesses = new ArrayList<>();

    public static void main(String[] args) {
        printArt(102);
        System.out.println();

        int gameMode = getGameMode();
        word = getWord(gameMode);

        // Initialise progress array
        progress = new String[word.length()];
        Arrays.fill(progress, "_");

        // Main game loop
        while (gameStatus == 0) {
            // Print progress
            clearTerminal();
            printArt(incorrectGuesses);
            System.out.println();
            for (String s : progress) {
                System.out.print(s + " ");
            }
            System.out.println();
            System.out.println();
            for (String s : previousGuesses) {
                System.out.print(s + " ");
            }
            System.out.println();
            System.out.println();

            String guess = getGuess();
            if (!validateGuess(guess)) {
                incorrectGuesses++;
                previousGuesses.add(guess);
            }
            gameStatus = checkWin();
            if (incorrectGuesses >= 10) {
                gameStatus = 2;
            }
        }

        // Show end screen
        if (gameStatus == 1) {
            printArt(100);
        } else {
            clearTerminal();
            printArt(incorrectGuesses);
            printArt(101);
        }
    }

    private static void printArt(int n) {
        Scanner file = null;
        try {
            if (n <= 10) {
                file = new Scanner(new File("./resources/frame" + n + ".txt"));
            } else if (n == 100){
                file = new Scanner(new File("./resources/winscreen.txt"));
            } else if (n == 101){
                file = new Scanner(new File("./resources/losescreen.txt"));
            } else if (n == 102){
                file = new Scanner(new File("./resources/startscreen.txt"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(file.hasNext())
            System.out.println(file.nextLine());
    }

    private static String getWord(int mode) {
        String word;
        if (mode == 1 || mode == 3) {
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
            word = wordlist.get(random.nextInt(wordlist.size()));
        }
        else {
            Scanner scanner = new Scanner(System.in);
            do {
                System.out.print("Choose word : ");
                word = scanner.nextLine();
            } while (word.equals(""));
        }
        return word;
    }

    private static int checkWin() {
        for (String s : progress) {
            if (s.equals("_")) {
                return 0;
            }
        }
        return 1;
    }

    private static int getGameMode() {
        Scanner scanner = new Scanner(System.in);
        String input;
        int mode = 0;
        System.out.println("SELECT GAME MODE\n" +
                "1 : Singleplayer\n" +
                "2 : Multiplayer\n" +
                "3 : True multiplayer");
        do {
            System.out.print("Mode : ");
            input = scanner.nextLine();
            switch (input) {
                case "1":
                    mode = 1;
                    break;
                case "2":
                    mode = 2;
                    break;
                case "3":
                    mode = 3;
                    break;
            }
        } while (mode != 1 && mode != 2 && mode != 3);
        return mode;
    }

    private static String getGuess() {
        Scanner scanner = new Scanner(System.in);
        String g;
        do {
            System.out.print("Guess : ");
            g = scanner.nextLine();
        } while (g.equals("") || previousGuesses.contains(g) || !(g.length() == 1 || g.length() == word.length()));
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

    private static void clearTerminal() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
}
