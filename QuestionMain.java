// Programmer name: Shelby Hahn, Gracie Shull & Elaine Hsu
// Date: 6/10/22
// Class: CS 145
// Assignment: 20 Questions
// Purpose: Main method for the 20 questions game


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

//A basic user interface for the game
public class QuestionMain {
    private static final String PLAY_AGAIN_MESSAGE = "Challenge me again? ";
    private static final String SAVE_MESSAGE = "Shall I remember these games? ";
    private static final String LOAD_MESSAGE = "Shall I recall our previous games? ";
    private static final String SAVE_LOAD_FILENAME_MESSAGE = "What is the file name? ";
    private static final String STATUS_MESSAGE = "Games played: %d\nI won: %d";
    private static final String BANNER_MESSAGE = "Think of an item, and I will guess it.";

    public static void main(String[] args) {
        QuestionMain tq = new QuestionMain();
        tq.run();
    }

    // fields
    private Scanner input = new Scanner(System.in);
    private PrintStream output = System.out;
    private QuestionTree tree;

    //Constructs a text user interface and its question tree
    public QuestionMain() {
        tree = new QuestionTree(input, output);
    }

    // private helper for overall game(s)
    private void run() {
        output.println("Welcome to the game of 20 Questions!");
        load();

        output.println("\n" + BANNER_MESSAGE);

        do {
            // play one complete game
            output.println(); 
            tree.play();
            output.print(PLAY_AGAIN_MESSAGE);
        } while (UserInterface.nextAnswer(input)); //play again?

        // Print overall stats
        // games played and how many won
        output.println("\n" + String.format(STATUS_MESSAGE, tree.totalGames(), tree.gamesWon()));

        save();
    }

    // Asks the user for input file
    private void load() {
        output.print(SAVE_LOAD_FILENAME_MESSAGE);
        if (UserInterface.nextAnswer(input)) {
            output.print(SAVE_LOAD_FILENAME_MESSAGE);
            String filename = input.nextLine();
            try {
                Scanner in = new Scanner(new File(filename));
                tree.load(in);
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Asks the user if they want to save or load
    private void save() {
        output.print(SAVE_MESSAGE);
        if (UserInterface.nextAnswer(input)) {
            output.print(SAVE_LOAD_FILENAME_MESSAGE);
            String filename = input.nextLine();
            try {
                PrintStream out = new PrintStream(new File(filename));
                tree.save(out);
                out.close();
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}