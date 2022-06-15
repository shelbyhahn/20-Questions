// Programmer name: Shelby Hahn, Gracie Shull & Elaine Hsu
// Date: 6/10/22
// Class: CS 145
// Assignment: 20 Questions
// Purpose: This is a user interface for the game


import java.util.Scanner;

public class UserInterface {
    
    //User gives a yes/no answer to the question and it returns
    //as a boolean.
    
    public static boolean nextAnswer(Scanner input) {
        String answer = input.nextLine();
        return answer.trim().toLowerCase().startsWith("y");
    }
    
}