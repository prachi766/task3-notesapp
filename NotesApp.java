// I'm importing all the tools I think I'll need from Java's built-in libraries.
// It's like laying out all my ingredients before I start cooking.

import java.io.BufferedReader; // This will help me read the notes from the file, line by line.
import java.io.File;           // This lets me check if my notes file even exists.
import java.io.FileReader;     // This is the basic tool to read a file.
import java.io.FileWriter;     // This is the basic tool to write to a file.
import java.io.IOException;    // This is for catching errors if something goes wrong with the files.
import java.io.PrintWriter;    // A helper that makes writing to a file a bit easier (like using println!).
import java.time.LocalDateTime; // To get the current date and time.
import java.time.format.DateTimeFormatter; // To make the date and time look nice.
import java.util.Scanner;      // This is the most important one! It lets me read what the user types.

/**
 * This is my very first real Java project!
 * It's a simple app that lets you write down notes and saves them to a file.
 * You can also look at all the notes you've saved.
 * Coded by a fellow learner!
 */
public class NotesApp {

    // I'm making the filename a constant variable here.
    // This way, if I ever want to change it (like to "my_diary.txt"),
    // I only have to change it in one place. Super useful!
    private static final String FILE_NAME_FOR_NOTES = "notes.txt";

    // This is the main entry point of the whole program. Everything starts here.
    public static void main(String[] args) {

        // I need a way to read what the user types in the console.
        // The Scanner object is the perfect tool for that.
        Scanner userInputScanner = new Scanner(System.in);

        // This boolean will control my main loop. As long as it's true, the app keeps running.
        // When the user wants to exit, I'll just set it to false.
        boolean isAppRunning = true;

        // This is the main engine of the app. It's a `while` loop that will
        // run forever until we tell it to stop.
        while (isAppRunning) {
            // First, I'll print out a nice menu so the user knows what they can do.
            System.out.println("\n============================");
            System.out.println(" My Simple Notes App 📝");
            System.out.println("============================");
            System.out.println("1. Write a new note");
            System.out.println("2. Read all my notes");
            System.out.println("3. Exit the app");
            System.out.print("What would you like to do? (Enter a number): ");

            // Here, I wait for the user to type something and press Enter.
            String userChoice = userInputScanner.nextLine();

            // Now, I'll check what the user chose. A `switch` statement feels cleaner
            // than a bunch of `if-else` statements here.
            switch (userChoice) {
                case "1":
                    // If they picked "1", I'll call my method for saving a note.
                    saveNewNote(userInputScanner);
                    break;
                case "2":
                    // If they picked "2", I'll call my method for showing all the notes.
                    showAllNotes();
                    break;
                case "3":
                    // If they picked "3", I'll set my loop controller to false.
                    isAppRunning = false;
                    System.out.println("Closing the app. See you next time!");
                    break;
                default:
                    // If they type anything else, I'll just show an error message.
                    System.out.println("Oops! I don't understand that. Please pick 1, 2, or 3.");
                    break;
            }
        }

        // It's good practice to close the scanner when we're all done with it.
        // This releases the resources it was using.
        userInputScanner.close();
    }

    /**
     * This method asks the user for a note and saves it to our text file.
     */
    private static void saveNewNote(Scanner scanner) {
        System.out.print("Go ahead, type your note and press Enter: ");
        String noteText = scanner.nextLine();

        // Writing to files can sometimes cause errors (like if the disk is full),
        // so Java makes us put this code in a `try-catch` block.
        // I'm using "try-with-resources" which is cool because Java automatically closes the file for me.
        try (FileWriter fileWriter = new FileWriter(FILE_NAME_FOR_NOTES, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // The 'true' in the FileWriter part is super important!
            // It tells the program to ADD to the file (append), not erase everything and start over.

            // I want to add a timestamp so I know when I wrote the note.
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMM dd, yyyy 'at' hh:mm a"));

            // Now, I'll use my printWriter to write the final line to the file.
            printWriter.println("[" + timestamp + "] " + noteText);

            System.out.println("Success! Your note has been saved. ✅");

        } catch (IOException e) {
            // If something goes wrong, this `catch` block will run.
            System.err.println("Oh no! Something went wrong trying to save the note.");
            // This prints the detailed error message, which is helpful for debugging.
            e.printStackTrace();
        }
    }

    /**
     * This method opens the notes file and prints out every single line.
     */
    private static void showAllNotes() {
        System.out.println("\n--- Here are all your notes ---");

        // First, I need to represent the file in my code.
        File notesFile = new File(FILE_NAME_FOR_NOTES);

        // Before I try to read it, I should check if the file even exists.
        // If it doesn't, there are no notes to show!
        if (!notesFile.exists()) {
            System.out.println("(You haven't written any notes yet!)");
            return; // This exits the method early.
        }

        // Just like with writing, reading a file can also cause errors.
        // So, I'm using another `try-with-resources` block.
        try (FileReader fileReader = new FileReader(FILE_NAME_FOR_NOTES);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            // I'm wrapping my FileReader in a BufferedReader.
            // It's more efficient and gives me the awesome `readLine()` method.

            String lineOfText;
            int noteCounter = 1;

            // This is the classic way to read a file in Java.
            // The loop will grab one line, assign it to `lineOfText`, and run.
            // When it reaches the end of the file, `readLine()` will return `null`,
            // and the loop will stop. So elegant!
            while ((lineOfText = bufferedReader.readLine()) != null) {
                System.out.println(noteCounter + ". " + lineOfText);
                noteCounter++; // Increment the counter for the next note
            }

        } catch (IOException e) {
            // If an error happens while reading, I'll print a message.
            System.err.println("Oh no! Something went wrong trying to read your notes.");
            e.printStackTrace();
        }
         System.out.println("-----------------------------");
    }
}
