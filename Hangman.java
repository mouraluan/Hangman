import java.util.Arrays;
import java.util.Scanner;

public class Hangman {

    public static String[] words = { "ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
            "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
            "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
            "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
            "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon",
            "python", "rabbit", "ram", "rat", "raven", "rhino", "salmon", "seal",
            "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
            "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
            "wombat", "zebra" };

    public static String[] gallows = { "+---+\n" +
            "|   |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "    |\n" +
            "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            "+---+\n" +
                    "|   |\n" +
                    "O   |\n" +
                    "|   |\n" +
                    "    |\n" +
                    "    |\n" +
                    "=========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|   |\n" +
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" + // if you were wondering, the only way to print '\' is with a trailing escape
                                  // character, which also happens to be '\'
                    "     |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/    |\n" +
                    "     |\n" +
                    " =========\n",

            " +---+\n" +
                    " |   |\n" +
                    " O   |\n" +
                    "/|\\  |\n" +
                    "/ \\  |\n" +
                    "     |\n" +
                    " =========\n" };

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int wrongGuessesTotal = 0;
        char[] wrongGuesses = new char[6];
        String wordString = randomWord();
        System.out.println(wordString);
        char[] word = new char[wordString.length()];
        for (int i = 0; i < word.length; i++) {
            word[i]=wordString.charAt(i);
        }
        char[] placeholders = new char[wordString.length()];
        for (int i = 0; i < placeholders.length; i++) {
            placeholders[i] = '_';
        }

        System.out.println("Welcome to the Hangman Game\n");

        while (true) {
            System.out.println(gallows[wrongGuessesTotal]);
            System.out.print("\nWord: ");
            printPlaceHolders(placeholders);
            System.out.print("\n\nMisses: ");
            if (wrongGuessesTotal>0) {
                for (int i = 0; i < wrongGuessesTotal; i++) {
                    System.out.print(" "+wrongGuesses[i]);
                }
            }
            if (wrongGuessesTotal >=6) {
                System.out.println("\nYou lose!");
                System.exit(0);
            }
            System.out.print("\n\nGuess: ");
            char guess;
            while (true) {
                String response = scan.nextLine();
                if (response.length() == 1) {
                    guess = response.charAt(0);
                    break;

                } else {
                    System.out.println("Only one character at a time. Try again: ");
                }
            }
            if (checkGuess(guess, word)) {
                placeholders = updatePlaceholders(guess, word, placeholders);
                if (Arrays.equals(placeholders, word)) {
                    System.out.println("Congratulations, you win! The word was: " + wordString);
                    scan.close();
                    System.exit(0);
                }
            }else{
                wrongGuesses[wrongGuessesTotal] = guess;
                wrongGuessesTotal++;
                
            }

        }

    }

    public static String randomWord() {
        int random = (int) (Math.random() * 64) + 1;
        return words[random];
    }

    public static void printPlaceHolders(char[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i]);
        }
    }

    public static boolean checkGuess(char guess, char[] word){
        for (int i = 0; i < word.length; i++) {
            if (word[i]==guess) {
                return true;
            }
        }
        return false;
    }
    public static char[] updatePlaceholders(char guess, char[] word, char[] placeholders){
                for (int i = 0; i < word.length; i++) {
                    if (word[i]==guess) {
                        placeholders[i]=guess;
                        return placeholders;
                    }
                }
        return  placeholders;
    }
}
