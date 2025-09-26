Simple Notes App 
This is a simple Java program that lets you write and view text notes. I built it step by step to make it easy to use and maintain.
First, I planned the main features: writing a new note, reading saved notes, and keeping the notes stored between runs.
I used a text file (notes.txt) to save the notes, and each note is automatically stamped with the date and time. 
To make it user-friendly, I added a menu system with clear options so you can choose what to do.
The program runs in a loop so you can write or read multiple notes until you decide to exit.

Main Features:
-Write a new note (with automatic timestamp)
-Read all previously saved notes
-Notes are stored in notes.txt for future use
-Menu-driven interface for easy navigation
-Loop-based system so you can perform multiple actions in one session

How to Run:
The entire application is contained within a single file, NotesApp.java.
1.Save the file: Ensure the file NotesApp.java is saved in a local directory.
2.Open a terminal/command prompt in that folder.
3.Compile the code:
     Bash
     javac NotesApp.java
4.Run the program:
     Bash
     java NotesApp
